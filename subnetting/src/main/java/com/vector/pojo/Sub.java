package com.vector.pojo;

public class Sub {
  private int S1;
  private int S2;
  private int S3;
  private int S4;

  public Sub() {
    super();
  }

  public int getS1() {
    return S1;
  }

  public void setS1(int s1) {
    S1 = s1;
  }

  public int getS2() {
    return S2;
  }

  public void setS2(int s2) {
    S2 = s2;
  }

  public int getS3() {
    return S3;
  }

  public void setS3(int s3) {
    S3 = s3;
  }

  public int getS4() {
    return S4;
  }

  public void setS4(int s4) {
    S4 = s4;
  }

  @Override
  public String toString() {
    return "Sub{" +
            "S1=" + S1 +
            ", S2=" + S2 +
            ", S3=" + S3 +
            ", S4=" + S4 +
            '}';
  }
}
