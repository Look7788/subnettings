package com.vector.vo;

import com.vector.pojo.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品返回值
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsVo extends Goods {
  private BigDecimal seckillPrice;
  private Integer stockCount;
  private Date startDate;
  private Date endDate;

}
