package com.vector.Utils;

public class Classify {
  public String cf(int B1,int B2,int B3,int B4){
    if(B1<=223&&B2<=255&&B3<=255&&B4<=255)	{
      //(1)A类地址范围：1.0.0.1—126.155.255.254
      if (1<=B1&&B1<=127) {
        return "地址合法 A类地址";
      }
      //(2) B类地址范围：128.0.0.1—191.255.255.254
      else if(128<=B1&&B1<=191){

        return "地址合法 B类地址";
      }
      //(3) C类地址范围：192.0.0.1—223.255.255.254
      else if (192<=B1&&B1<=223) {
        return "地址合法 C类地址";
      }
    }
      return "101";
  }
}
