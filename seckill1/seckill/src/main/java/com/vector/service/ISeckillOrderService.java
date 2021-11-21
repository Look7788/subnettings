package com.vector.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vector.pojo.SeckillOrder;
import com.vector.pojo.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cgc
 * @since 2021-10-31
 */
public interface ISeckillOrderService extends IService<SeckillOrder> {
// 获取秒杀结果
  Long getResult(User user, Long goodsId);
}
