package com.vector.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 公共返回对象
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespBean {
  private long code;
  private String message;
  private Object obj;

  // 成功返回
  public static RespBean success(){
    return new RespBean(RespBeanEnum.SUCCESS.getCode(), RespBeanEnum.SUCCESS.getMessage(), null);
  }
  // 继承
  public static RespBean success(Object obj){
    return new RespBean(RespBeanEnum.SUCCESS.getCode(), RespBean.success().getMessage(), obj);
  }
  // 失败返回结果
  public static RespBean error(RespBeanEnum respBeanEnum){
    return new RespBean(respBeanEnum.getCode(),respBeanEnum.getMessage(),null);
  }
  // 失败返回结果 继承
  public static RespBean error(RespBeanEnum respBeanEnum,Object obj){
    return new RespBean(respBeanEnum.getCode(),respBeanEnum.getMessage(),obj);
  }
}
