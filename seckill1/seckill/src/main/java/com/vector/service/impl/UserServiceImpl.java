package com.vector.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vector.exception.GlobalException;
import com.vector.mapper.UserMapper;
import com.vector.pojo.User;
import com.vector.service.IUserService;
import com.vector.utils.CookieUtil;
import com.vector.utils.MD5Util;
import com.vector.utils.UUIDUtile;
import com.vector.utils.ValidatorUtil;
import com.vector.vo.LoginVo;
import com.vector.vo.RespBean;
import com.vector.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;
import sun.security.krb5.internal.Ticket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cgc
 * @since 2021-10-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
  @Autowired
  private UserMapper userMapper;
  @Autowired
  private RedisTemplate redisTemplate;

  @Override
  public RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response){
    String mobile = loginVo.getMobile();
    String password = loginVo.getPassword();
//    参数校验
//    if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)){
//      return RespBean.error(RespBeanEnum.LOGIN_ERROR);
//    }
//    if (!ValidatorUtil.isMobile(mobile)){
//      return RespBean.error(RespBeanEnum.MOBILE_ERROR);
//    }
    // 根据手机号获取用户
    User user = userMapper.selectById(mobile);
    if (null==user){
      throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
//      return RespBean.error(RespBeanEnum.LOGIN_ERROR);
    }
    // 判断密码是否正确
    if (!MD5Util.formPassToDBPass(password,user.getSlat()).equals(user.getPassword())){
      throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
//      return RespBean.error(RespBeanEnum.LOGIN_ERROR);
    }

    // 生成cookie
    String ticket = UUIDUtile.uuid();
    // 将用户存在redis中
    redisTemplate.opsForValue().set("user:"+ticket,user);
    request.getSession().setAttribute(ticket,user);
    CookieUtil.setCookie(request,response,"userTicket",ticket);
    return RespBean.success(ticket);
  }

  @Override
  public User getUserByCookie(String userTicket,HttpServletRequest request,HttpServletResponse response) {
    if (StringUtils.isEmpty(userTicket)){
      return null;
    }
    User user =(User) redisTemplate.opsForValue().get("user:" + userTicket);
    if (user != null){
      CookieUtil.setCookie(request,response,"userTicket",userTicket);
    }
    return user;
  }

  /**
   * 缓存中更新密码
   * @param userTicket
   * @param password
   * @param request
   * @param response
   * @return
   */
  @Override
  public RespBean updatePassword(String userTicket, String password, HttpServletRequest request, HttpServletResponse response) {
    User user = getUserByCookie(userTicket, request, response);
    if (user==null){
      throw  new GlobalException(RespBeanEnum.MOBILE_NOT_EXIST);
    }
    user.setPassword(MD5Util.inputPassToDBPass(password,user.getSlat()));
    int result = userMapper.updateById(user);
    if (1==result){
      // 删除ticket
      redisTemplate.delete("user:"+userTicket);
      return RespBean.success();
    }
    return RespBean.error(RespBeanEnum.PASSWORD_UPDATE_FAIL);
  }

}
