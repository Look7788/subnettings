package com.vector.utils;

import java.util.UUID;

public class UUIDUtile {
  public static String uuid(){
    return UUID.randomUUID().toString().replace("-","");
  }
}
