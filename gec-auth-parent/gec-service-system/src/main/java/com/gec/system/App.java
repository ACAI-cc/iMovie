package com.gec.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.gec.system.mapper")
//@ComponentScan("com.gec.system.config")
public class App
{
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }

}
