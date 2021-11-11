package com.vector.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vector.pojo.User;
import com.vector.vo.LoginVo;
import com.vector.vo.RespBean;
import com.vector.vo.RespBeanEnum;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cgc
 * @since 2021-10-24
 */
public interface IUserService extends IService<User> {
  RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);
  //根据cookie获取用户
  User getUserByCookie(String userTicket,HttpServletRequest request,HttpServletResponse response);
  // 在缓存中更新密码
  RespBean updatePassword(String userTicket,String password,HttpServletRequest request,HttpServletResponse response);
}
