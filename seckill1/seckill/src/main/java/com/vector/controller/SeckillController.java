package com.vector.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vector.exception.GlobalException;
import com.vector.pojo.Order;
import com.vector.pojo.SeckillMessage;
import com.vector.pojo.SeckillOrder;
import com.vector.pojo.User;
import com.vector.rabbitmq.MQSender;
import com.vector.service.IGoodsService;
import com.vector.service.IOrderService;
import com.vector.service.ISeckillOrderService;
import com.vector.utils.JsonUtill;
import com.vector.vo.GoodsVo;
import com.vector.vo.RespBean;
import com.vector.vo.RespBeanEnum;
import com.wf.captcha.ArithmeticCaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 秒杀
 */
@Slf4j
@Controller
@RequestMapping("/seckill")
public class SeckillController implements InitializingBean {
  @Autowired
  private IGoodsService goodsService;
  @Autowired
  private ISeckillOrderService seckillOrderService;
  @Autowired
  private IOrderService orderService;
  @Autowired
  private RedisTemplate redisTemplate;
  @Autowired
  private MQSender mqSender;
  @Autowired
  private RedisScript<Long> script;

  private Map<Long,Boolean> emptyStockMap = new HashMap<>();

  // 秒杀
  /*@RequestMapping("/doSeckill2")
  public String doSeckill2(Model model, User user,Long goodsId){
    if (user == null){
      return "login";
    }
    model.addAttribute("user",user);
    GoodsVo goods = goodsService.findGoodsVoByGoodsId(goodsId);
    if (goods.getStockCount()<1){
      model.addAttribute("errmsg", RespBeanEnum.EMPTY_STOCK.getMessage());
      return "seckillFail";
    }
    //判断是否重复抢购
//    SeckillOrder seckillOrder = seckillOrderService.getOne(new QueryWrapper<SeckillOrder>().eq("user_id", user.getId()).eq("goods_id", goodsId));
    SeckillOrder seckillOrder = (SeckillOrder)
            redisTemplate.opsForValue().get("order:"+user.getId()+":"+goodsId);
    if (seckillOrder != null){
      model.addAttribute("errmsg",RespBeanEnum.REPEATE_ERROR.getMessage());
      return "seckillFail";
    }
    //
    Order order = orderService.seckill(user,goods);
    model.addAttribute("order",order);
    model.addAttribute("goods",goods);
    return "orderDetail";
  }*/

  /**
   * 优化的秒杀页面
   * @param model
   * @param user
   * @param goodsId
   * @return
   */
  @RequestMapping( "/{path}/doSeckill")
  @ResponseBody
  public RespBean doSeckill(@PathVariable String path, User user, Long goodsId){
    if (user == null){
      return RespBean.error(RespBeanEnum.SESSION_ERROR);
    }
    /*GoodsVo goods = goodsService.findGoodsVoByGoodsId(goodsId);
    //判断库存
    if (goods.getStockCount()<1){
      return RespBean.error(RespBeanEnum.EMPTY_STOCK);
    }
    //判断是否重复抢购
//    SeckillOrder seckillOrder = seckillOrderService.getOne(new QueryWrapper<SeckillOrder>().eq("user_id", user.getId()).eq("goods_id", goodsId));
    SeckillOrder seckillOrder = (SeckillOrder)
            redisTemplate.opsForValue().get("order:"+user.getId()+":"+goodsId);
    if (seckillOrder != null){
      return RespBean.error(RespBeanEnum.REPEATE_ERROR);
    }
    Order order = orderService.seckill(user,goods);
    return RespBean.success(order);*/

    // 优化 预减库存
    ValueOperations valueOperations = redisTemplate.opsForValue();
    boolean check = orderService.checkPath(user,goodsId,path);
    if (!check){
      return RespBean.error(RespBeanEnum.REPEATE_ILLEGAL);
    }
    //判断是否重复抢购
    SeckillOrder seckillOrder = (SeckillOrder)
            redisTemplate.opsForValue().get("order:"+user.getId()+":"+goodsId);
    if (seckillOrder != null){
      return RespBean.error(RespBeanEnum.REPEATE_ERROR);
    }
    // 内存表記：減少redis的訪問
    if (emptyStockMap.get(goodsId)){
      return RespBean.error(RespBeanEnum.EMPTY_STOCK);
    }
    //预减库存
    //Long stock = valueOperations.decrement("seckillGoods:" + goodsId);
    Long stock = (Long) redisTemplate.execute(script, Collections.singletonList("seckillGoods:" + goodsId),
            Collections.EMPTY_LIST);
    if (stock < 0){
      emptyStockMap.put(goodsId,true);
      valueOperations.increment("seckillGoods:" + goodsId);
      return RespBean.error(RespBeanEnum.EMPTY_STOCK);
    }
    SeckillMessage seckillMessage = new SeckillMessage(user, goodsId);
    mqSender.sendSeckillMessage(JsonUtill.object2JsonStr(seckillMessage));
    return RespBean.success(0);
  }


  /**
   * 获取秒杀结果
   */
  @RequestMapping(value = "/result",method = RequestMethod.GET)
  @ResponseBody
  public RespBean result(User user,Long goodsId){
    if (user==null){
      return RespBean.error(RespBeanEnum.SESSION_ERROR);
    }
    Long orderId = seckillOrderService.getResult(user,goodsId);
    return RespBean.success(orderId);
  }

  /**
   * 获取秒杀地址
   */

  @RequestMapping(value = "/path",method = RequestMethod.GET)
  @ResponseBody
  public RespBean getPath(User user,Long goodsId,String captcha){
    if (user == null){
      return RespBean.error(RespBeanEnum.SESSION_ERROR);
    }
    boolean check = orderService.checkCapcha(user,goodsId,captcha);
    System.out.println("panduan:"+ check);
    if (!check){
      return RespBean.error(RespBeanEnum.ERROR_CAPTCHA);
    }
    String str = orderService.createPath(user,goodsId);
    return RespBean.success(str);
  }

  /**
   * 验证码检验
   */
  @RequestMapping(value = "/captcha",method = RequestMethod.GET)
  public void verifyCode(User user, Long goodsId, HttpServletResponse response){
    if (user == null || goodsId < 0){
      throw new GlobalException(RespBeanEnum.REPEATE_ILLEGAL);
    }
    // 设置请求为输出图片格式
    response.setContentType("image/jpg");
    response.setHeader("Pargam","No-cache");
    response.setHeader("Cache-Control","no-cache");
    response.setDateHeader("Expires",0);
    // 生成验证码，将结果放入到redis中
    ArithmeticCaptcha captcha = new ArithmeticCaptcha(130,32,3);
    redisTemplate.opsForValue().set("captcha"+user.getId()+":"+goodsId,captcha.text(),300,
            TimeUnit.SECONDS);
    try {
      captcha.out(response.getOutputStream());
    } catch (IOException e) {
      log.error("验证码生成失败",e.getMessage());
    }
  }
  /**
   * 系统初始化，把商品库存数量加载到Rtadis中
   * @throws Exception
   */
  @Override
  public void afterPropertiesSet() throws Exception {
    List<GoodsVo> list = goodsService.findGoodsVo();
    if (CollectionUtils.isEmpty(list)){
      return;
    }
    list.forEach(goodsVo ->{
      redisTemplate.opsForValue().set("seckillGoods:"+goodsVo.getId(),goodsVo.getStockCount());
      emptyStockMap.put(goodsVo.getId(),false);
    });
  }
}
