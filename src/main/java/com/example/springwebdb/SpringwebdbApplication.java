package com.example.springwebdb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.springwebdb.mapper")
public class SpringwebdbApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringwebdbApplication.class, args);
    }
}