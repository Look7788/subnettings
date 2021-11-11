package com.vector.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vector.mapper.GoodsMapper;
import com.vector.pojo.Goods;
import com.vector.service.IGoodsService;
import com.vector.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cgc
 * @since 2021-10-31
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

  @Autowired
  private GoodsMapper goodsMapper;
  @Override
  public List<GoodsVo> findGoodsVo() {
    return goodsMapper.findGoodsVo();
  }

  /**
   * 获取商品详情
   * @param goodsId
   * @return
   */
  @Override
  public GoodsVo findGoodsVoByGoodsId(long goodsId) {
    return goodsMapper.findGoodsVoByGoodsId(goodsId);
  }
}
