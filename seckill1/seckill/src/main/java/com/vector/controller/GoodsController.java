package com.vector.controller;

import com.vector.pojo.User;
import com.vector.service.IGoodsService;
import com.vector.service.IUserService;
import com.vector.vo.DetailVo;
import com.vector.vo.GoodsVo;
import com.vector.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/goods")
public class GoodsController {
  @Autowired
  private IUserService userService;
  @Autowired
  private IGoodsService goodsService;
  @Autowired
  private RedisTemplate redisTemplate;
  @Autowired
  private ThymeleafViewResolver thymeleafViewResolver;
//跳转到商品页面
  @RequestMapping(value = "/toList",produces = "text/html;charset=utf-8")
  @ResponseBody
  // user入参前就已经校验了
  public String toList(Model model, User user,
                       HttpServletRequest request, HttpServletResponse response){
    // redis中获取html
    ValueOperations valueOperations = redisTemplate.opsForValue();
    String html = (String) valueOperations.get("goodsList");
    if (!StringUtils.isEmpty(html)){
      return html;
    }
    model.addAttribute("user",user);
    model.addAttribute("goodsList",goodsService.findGoodsVo());
    //如果为空，手动渲染，存入redis中
    WebContext context = new WebContext(request,response,request.getServletContext(),request.getLocale()
    ,model.asMap());
    html = thymeleafViewResolver.getTemplateEngine().process("goodsList", context);
    if (!StringUtils.isEmpty(html)){
      valueOperations.set("goodsList",html,60, TimeUnit.SECONDS);
    }
    return html;
  }
  // 跳转商品详情
  /*@RequestMapping(value = "/toDetail2/{goodsId}",produces = "text/html;charset=utf-8")
  @ResponseBody
  public String toDetail2(Model model,User user,@PathVariable long goodsId
  ,HttpServletRequest request,HttpServletResponse response){
    ValueOperations valueOperations = redisTemplate.opsForValue();
    String html = (String) valueOperations.get("goodsDetail:" + goodsId);
    if (!StringUtils.isEmpty(html)){
      return html;
    }
    model.addAttribute("user",user);
    GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(goodsId);
    Date startDate = goodsVo.getStartDate();
    Date endDate = goodsVo.getEndDate();
    Date nowDate = new Date();
    //秒杀状态
    int seckillStatus = 0;
    //秒杀倒计时
    int remainSeconds = 0;
    if (nowDate.before(startDate)){
      remainSeconds = ((int) ((startDate.getTime() - nowDate.getTime())/1000));
    }else if (nowDate.after(endDate)){
      //秒杀已结束
      seckillStatus = 2;
      remainSeconds = -1;
    }else {
      //秒杀进行中
      seckillStatus = 1;
      remainSeconds = 0;
    }
    model.addAttribute("remainSeconds",remainSeconds);
    model.addAttribute("seckillStatus",seckillStatus);
    model.addAttribute("goods",goodsVo);
    //return "goodsDetail";
    //如果为空，手动渲染，存入redis中
    WebContext context = new WebContext(request,response,request.getServletContext(),request.getLocale()
            ,model.asMap());
    html = thymeleafViewResolver.getTemplateEngine().process("goodsDetail", context);
    if (!StringUtils.isEmpty(html)){
      valueOperations.set("goodsDetail:"+goodsId,html,60, TimeUnit.SECONDS);
    }
    return html;
  }*/
  /**
   * 优化跳转商品页面
   * @param model
   * @param user
   * @param goodsId
   * @return
   */
  @RequestMapping("/detail/{goodsId}")
  @ResponseBody
  public RespBean detail(User user, @PathVariable Long goodsId){
    GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(goodsId);
    Date startDate = goodsVo.getStartDate();
    Date endDate = goodsVo.getEndDate();
    Date nowDate = new Date();
    //秒杀状态
    int seckillStatus = 0;
    //秒杀倒计时
    int remainSeconds = 0;
    if (nowDate.before(startDate)){
      remainSeconds = ((int) ((startDate.getTime() - nowDate.getTime())/1000));
    }else if (nowDate.after(endDate)){
      //秒杀已结束
      seckillStatus = 2;
      remainSeconds = -1;
    }else {
      //秒杀进行中
      seckillStatus = 1;
      remainSeconds = 0;
    }
    //return "goodsDetail";
    DetailVo detailVo = new DetailVo();
    detailVo.setUser(user);
    detailVo.setGoodsVo(goodsVo);
    detailVo.setSeckillStatus(seckillStatus);
    detailVo.setRemainSeconds(remainSeconds);
    return RespBean.success(detailVo);
  }
}
