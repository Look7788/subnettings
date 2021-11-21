package com.vector.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vector.exception.GlobalException;
import com.vector.mapper.OrderMapper;
import com.vector.pojo.Order;
import com.vector.pojo.SeckillGoods;
import com.vector.pojo.SeckillOrder;
import com.vector.pojo.User;
import com.vector.service.IGoodsService;
import com.vector.service.IOrderService;
import com.vector.service.ISeckillGoodsService;
import com.vector.service.ISeckillOrderService;
import com.vector.utils.MD5Util;
import com.vector.utils.UUIDUtile;
import com.vector.vo.GoodsVo;
import com.vector.vo.OrderDetailVo;
import com.vector.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cgc
 * @since 2021-10-31
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

  @Autowired
  private ISeckillGoodsService seckillGoodsService;
  @Autowired
  private OrderMapper orderMapper;
  @Autowired
  private ISeckillOrderService seckillOrderService;
  @Autowired
  private IGoodsService goodsService;
  @Autowired
  private RedisTemplate redisTemplate;

  // 秒杀
  @Transactional
  @Override
  public Order seckill(User user, GoodsVo goods) {
    ValueOperations valueOperations = redisTemplate.opsForValue();
    //秒杀商品表前库存
    SeckillGoods seckillGoods = seckillGoodsService.getOne(new QueryWrapper<SeckillGoods>().eq
            ("goods_id", goods.getId()));
    seckillGoods.setStockCount(seckillGoods.getStockCount()-1);
    boolean seckillGoodsResult = seckillGoodsService.update(new UpdateWrapper<SeckillGoods>().setSql("stock_count = stock_count-1").eq(
            "goods_id",goods.getId()).gt("stock_count",0));
    if (seckillGoods.getStockCount()<1){
      //判断是否还有库存
       valueOperations.set("isStockEmpty:"+goods.getId(),"0");
       return null;
    }
    //生成订单
    Order order = new Order();
    order.setUserId(user.getId());
    order.setGoodsId(goods.getId());
    order.setGoodsName(goods.getGoodsName());
    order.setDeliveryAddrId(0L);
    order.setGoodsCount(1);
    order.setGoodsPrice(seckillGoods.getSeckillPrice());
    order.setOrderChannel(1);
    order.setStatus(0);
    order.setCreateDate(new Date());
    orderMapper.insert(order);
    //生成秒杀订单
    SeckillOrder seckillOrder = new SeckillOrder();
    seckillOrder.setUserId(user.getId());
    seckillOrder.setOrderId(order.getId());
    seckillOrder.setGoodsId(goods.getId());
    seckillOrderService.save(seckillOrder);
    redisTemplate.opsForValue().set("order:"+user.getId()+":"+goods.getId(),seckillOrder);
    return order;
  }

  @Override
  public OrderDetailVo detail(Long orderId) {
    if(orderId == null){
      throw new GlobalException(RespBeanEnum.ORDER_NOT_EXIST);
    }
    Order order = orderMapper.selectById(orderId);
    GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(order.getGoodsId());
    OrderDetailVo detail = new OrderDetailVo();
    detail.setOrder(order);
    detail.setGoodsVo(goodsVo);
    return detail;
  }

  /**
   * 获取秒杀地址
   * @param user
   * @param goodsId
   * @return
   */
  @Override
  public String createPath(User user, Long goodsId) {
    String str = MD5Util.md5(UUIDUtile.uuid()+"123456");
    redisTemplate.opsForValue().set("seckillPath:"+user.getId()+":"+goodsId,str,60, TimeUnit.SECONDS);
    return str;
  }

  /**
   * 校验秒杀地址
   * @param user
   * @param goodsId
   * @param path
   * @return
   */
  @Override
  public boolean checkPath(User user, Long goodsId, String path) {
    if (user == null || goodsId <0 || StringUtils.isEmpty(path)){
      return false;
    }
    String redisPath = (String) redisTemplate.opsForValue().get("seckillPath:" + user.getId() + ":" + goodsId);
    return path.equals(redisPath);
  }

  /**
   *验证码校验
   * @param user
   * @param goodsId
   * @param captcha
   * @return
   */
  @Override
  public boolean checkCapcha(User user, Long goodsId, String captcha) {
    if (StringUtils.isEmpty(captcha)||user==null||goodsId<0){
      return false;
    }
    String redisCaptcha = (String) redisTemplate.opsForValue().get("captcha" + user.getId() + ":" + goodsId);
    return captcha.equals(redisCaptcha);
  }
}
