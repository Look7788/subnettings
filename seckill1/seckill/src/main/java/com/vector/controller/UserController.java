package com.vector.controller;


import com.vector.pojo.User;
import com.vector.rabbitmq.MQSender;
import com.vector.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

  @Autowired
  private MQSender mqSender;

  public RespBean info(User user){
    return  RespBean.success(user);
  }

  // 测试发送RabbitMQ消息
  /*@RequestMapping("/mq")
  @ResponseBody
  public void mq(){
    mqSender.send("Hello");
  }*/
}
