package com.vector.controller;

import com.vector.Utils.*;
import com.vector.pojo.Sub;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class CaculateController {

  @RequestMapping("/calculate")
  public String toCaculate(int B1, int B2, int B3, int B4, int B5) {

    Classify cl = new Classify();
    String cs = cl.cf(B1, B2, B3, B4);

    SubMask subMask = new SubMask(B5);
    Sub sub = subMask.subnet();
    
    String sum="";
    String ss = "子网掩码："+sub.getS1()+"."+sub.getS2()+"."+sub.getS3()+"."+sub.getS4();

    System.out.println("得到的s1"+sub.getS1());
    if (cs=="101"){
      sum = "地址输入不合法，请重新输入";
    }else {
      if (B1 <= 223 && B1 >= 192) {
        Cip C = new Cip(B1, B2, B3, B4, sub.getS1(), sub.getS2(), sub.getS3(), sub.getS4());
        sum = cs + "<br>" + ss + "<br>" + C.ShowC();
      }
      if (B1 <= 191 && B1 >= 128) {
        Bip B = new Bip(B1, B2, B3, B4, sub.getS1(), sub.getS2(), sub.getS3(), sub.getS4());
        sum = cs + "<br>" + ss + "<br>" + B.ShowB();
      }
      if (B1 <= 127 && B1 >= 1) {
        Aip A = new Aip(B1, B2, B3, B4, sub.getS1(), sub.getS2(), sub.getS3(), sub.getS4());
        sum = cs + "<br>" + ss + "<br>" + A.ShowA();
      }
    }
    return sum;
  }
}
