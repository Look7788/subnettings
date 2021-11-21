package com.vector.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vector.mapper.SeckillOrderMapper;
import com.vector.pojo.SeckillOrder;
import com.vector.pojo.User;
import com.vector.service.ISeckillOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cgc
 * @since 2021-10-31
 */
@Service
public class SeckillOrderServiceImpl extends ServiceImpl<SeckillOrderMapper, SeckillOrder> implements ISeckillOrderService {

  @Autowired
  private SeckillOrderMapper seckillOrderMapper;
  @Autowired
  private RedisTemplate redisTemplate;
  // 获取秒杀结果
  @Override
  public Long getResult(User user, Long goodsId) {
    SeckillOrder seckillOrder = seckillOrderMapper.selectOne(new QueryWrapper<SeckillOrder>().eq("user_id",
            user.getId()).eq("goods_id", goodsId));
    if (seckillOrder != null){
      return seckillOrder.getOrderId();
    }else if (redisTemplate.hasKey("isStockEmpty:"+goodsId)){
      return -1L;
    }else {
      return 0L;
    }
  }
}
