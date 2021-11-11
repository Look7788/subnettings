package com.vector.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vector.pojo.Goods;
import com.vector.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cgc
 * @since 2021-10-31
 */
public interface IGoodsService extends IService<Goods> {
//获取商品列表
  List<GoodsVo> findGoodsVo();
// 获取商品详情
  GoodsVo findGoodsVoByGoodsId(long goodsId);
}
