package com.vector.vo;

import com.vector.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 详情返回页面
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailVo {

  private User user;

  private GoodsVo goodsVo;

  private int seckillStatus;

  private int remainSeconds;
}
