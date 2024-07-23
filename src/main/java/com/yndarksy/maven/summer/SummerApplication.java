package com.yndarksy.maven.summer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.yndarksy.maven.summer.userserver.mapper")
@SpringBootApplication
public class SummerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SummerApplication.class, args);
    }

}
