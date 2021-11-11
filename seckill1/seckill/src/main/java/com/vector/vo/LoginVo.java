package com.vector.vo;

import com.vector.validator.IsMobile;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 登陆参数
 */
@Data
public class LoginVo {
  @NotNull
  @IsMobile
  private String mobile;

  @NotNull
  @Length(min = 32)
  private String password;
}
