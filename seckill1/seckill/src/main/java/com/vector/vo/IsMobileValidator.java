package com.vector.vo;

import com.vector.utils.ValidatorUtil;
import com.vector.validator.IsMobile;
import org.thymeleaf.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 手机号校验规则
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile,String> {
  private boolean required = false;
  @Override
  public void initialize(IsMobile constraintAnnottation){
    required = constraintAnnottation.required();
  }

  @Override
  public boolean isValid(String s, ConstraintValidatorContext context) {
    if (required){
      return ValidatorUtil.isMobile(s);
    }else {
      if (StringUtils.isEmpty(s)){
        return true;
      }else {
        return ValidatorUtil.isMobile(s);
      }
    }
  }


}
