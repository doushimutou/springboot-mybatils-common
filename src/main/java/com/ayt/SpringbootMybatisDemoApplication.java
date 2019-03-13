package com.ayt;



import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ayt"})
public class SpringbootMybatisDemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringbootMybatisDemoApplication.class, args);
    }
}
