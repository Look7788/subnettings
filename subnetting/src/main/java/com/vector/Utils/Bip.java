package com.vector.Utils;

public class Bip {
  int i1,i2,i3,i4;
  int s1,s2,s3,s4;
  public Bip(int i1,int i2,int i3,int i4,int s1,int s2,int s3,int s4){
    this.i1=i1;
    this.i2=i2;
    this.i3=i3;
    this.i4=i4;
    this.s1=s1;
    this.s2=s2;
    this.s3=s3;
    this.s4=s4;

  }
  //将十进制数换成八位的二进制数(字符串)
  public  String Eight(int num) {
    String a = Integer.toBinaryString(num);
    while (a.length() < 8)
    {
      a = '0' + a;
    }
    return a;
  }
  //将二进制数转换成十进制的数
  public  int Ten(String s){

    int x = 0;
    for(char c: s.toCharArray())
      x = x * 2 + (c == '1' ? 1 : 0);
    return x;
  }
  //B类地址的相关计算

  //子网数的计算
  public  int SubnetB(String s1,String s2,String s3,String s4){
    int B=16;
    String aString =s1+s2+s3+s4;
    int num=0;
    for (int i = 0; i < aString.length(); i++) {
      if (aString.charAt(i)=='1') {
        num++;
      }
    }
    return (int)(Math.pow(2, num-B));
  }
  //主机数的计算
  public  int HostnumB(String s1,String s2,String s3,String s4){
    String aString =s1+s2+s3+s4;
    int num=0;
    for (int i = 0; i < aString.length(); i++) {
      if (aString.charAt(i)=='1') {
        num++;
      }
    }
    return (int)(Math.pow(2, 32-num)-2);
  }
  //各主机的ip范围与广播地址(各位数字，主机数，子网数)
  public String  ShowB(){
    int hostnum=HostnumB(Eight(s1), Eight(s2), Eight(s3), Eight(s4));
    int subnum=SubnetB(Eight(s1), Eight(s2), Eight(s3), Eight(s4));
    int n=0;
    int zu=256/subnum;
    //定义字符串save保存所有数据
    String save;
    save="可用子网数是："+subnum+"<br>";
    save=save+"有效主机数是："+hostnum+"<br>";
    for (int i = 0; i < subnum; i++) {
      save=save+ "可用IP范围："+i1+"."+i2+"."+n+"."+1+"~"+i1+"."+i2+"."+(n+zu-1)+"."+254+"<br>";
      save=save+"广播地址是："+i1+"."+i2+"."+(n+zu-1)+"."+255+"<br>";
      n = n+zu;
    }
    return save;
  }
}
