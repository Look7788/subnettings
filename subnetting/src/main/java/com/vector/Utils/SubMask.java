package com.vector.Utils;
import com.vector.pojo.Sub;

public class SubMask {
  int num;
  public SubMask(int m){
    num = m;
  }
  Sub sub = new Sub();
  //对子网掩码的计算
  public Sub subnet(){
    int a,b,c;
    String s1="";
    a = (int)(num/8);
    b = num%8;
    for (int i = 1;i <= b;i++){
      s1 += "1";
    }
    if (s1.length()<8){
      for (int w = s1.length();w < 8;w++){
        s1 += "0";
      }
    }
    c = Ten(s1);
   switch (a){
     case 0:{
       sub.setS1(c);
       sub.setS2(0);
       sub.setS3(0);
       sub.setS4(0);
       break;
     }
     case 1:{
       sub.setS1(255);
       sub.setS2(c);
       sub.setS3(0);
       sub.setS4(0);
       break;
     }
     case 2:{
       sub.setS1(255);
       sub.setS2(255);
       sub.setS3(c);
       sub.setS4(0);
       break;
     }
     case 3:{
       sub.setS1(255);
       sub.setS2(255);
       sub.setS3(255);
       sub.setS4(c);
       break;
     }
     case 4:{
       sub.setS1(255);
       sub.setS2(255);
       sub.setS3(255);
       sub.setS4(255);
       break;
     }
   }
   System.out.println(sub);
   return sub;
  }
  //将二进制数转换成十进制的数
  public  int Ten(String s){
    int x = 0;
    for(char c: s.toCharArray())
      x = x * 2 + (c == '1' ? 1 : 0);
    return x;
  }

//  public static void main(String[] args) {
//    SubMask mask = new SubMask(2);
//    mask.subnet();
//  }
}
