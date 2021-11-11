package com.vector.controller;


import com.vector.pojo.User;
import com.vector.vo.RespBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *  用户信息（测试）
 * @author cgc
 * @since 2021-10-24
 */
@RestController
@RequestMapping("/user")
public class UserController {

  public RespBean info(User user){
    return  RespBean.success(user);
  }
}
