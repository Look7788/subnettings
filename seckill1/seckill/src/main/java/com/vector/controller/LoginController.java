package com.vector.controller;

import com.vector.service.IUserService;
import com.vector.vo.LoginVo;
import com.vector.vo.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 登陆页面
 */
@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {
  @Autowired
  private IUserService userService;

  //跳转登录
  @RequestMapping("/toLogin")
  public String toLogin(){
    return "login";
  }

  //登录
  @RequestMapping("/doLogin")
  @ResponseBody
  public RespBean doLogin(@Valid LoginVo loginVo, HttpServletRequest request, HttpServletResponse response){
    return userService.doLogin(loginVo,request,response);
  }
}
