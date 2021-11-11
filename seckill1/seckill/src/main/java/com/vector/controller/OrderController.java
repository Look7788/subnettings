package com.vector.controller;


import com.vector.pojo.User;
import com.vector.service.IOrderService;
import com.vector.vo.OrderDetailVo;
import com.vector.vo.RespBean;
import com.vector.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cgc
 * @since 2021-10-31
 */
@RestController
@RequestMapping("/order")
public class OrderController {

  @Autowired
  private IOrderService orderService;
  // 订单详情
  @RequestMapping("/detail")
  @ResponseBody
  public RespBean detail(User user,Long orderId){
    if (user == null){
      return RespBean.error(RespBeanEnum.SESSION_ERROR);
    }
    OrderDetailVo detail = orderService.detail(orderId);
    return RespBean.success(detail);
  }
}
