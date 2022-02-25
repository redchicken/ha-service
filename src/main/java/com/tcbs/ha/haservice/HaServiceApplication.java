package com.tcbs.ha.haservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@ComponentScan({"com.tcbs"})
@EnableAspectJAutoProxy
public class HaServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(HaServiceApplication.class, args);
  }

}
