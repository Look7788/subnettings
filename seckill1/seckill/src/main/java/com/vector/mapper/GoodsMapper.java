package com.vector.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vector.pojo.Goods;
import com.vector.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cgc
 * @since 2021-10-31
 */
public interface GoodsMapper extends BaseMapper<Goods> {

  //获取商品列表
  List<GoodsVo> findGoodsVo();

  //获取商品详情
  GoodsVo findGoodsVoByGoodsId(long goodsId);
}
