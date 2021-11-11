package com.vector;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.vector.mapper")
public class SeckillApplication {

  public static void main(String[] args) {
    SpringApplication.run(SeckillApplication.class, args);
  }

}
