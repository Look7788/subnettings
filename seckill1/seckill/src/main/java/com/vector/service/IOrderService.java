package com.vector.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vector.pojo.Order;
import com.vector.pojo.User;
import com.vector.vo.GoodsVo;
import com.vector.vo.OrderDetailVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cgc
 * @since 2021-10-31
 */
public interface IOrderService extends IService<Order> {
  //秒杀
  Order seckill(User user, GoodsVo goods);

  //订单详情
  OrderDetailVo detail(Long orderId);

  //获取秒杀地址
  String createPath(User user, Long goodsId);

  //校验秒杀地址
  boolean checkPath(User user, Long goodsId, String path);

  //验证码校验
  boolean checkCapcha(User user, Long goodsId, String captcha);

}
