package com.hupun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages= {"com.hupun.demo.Interface"})
@ComponentScan(basePackages = "com.hupun")
public class Web_App
{
    public static void main(String[] args)
    {
        SpringApplication.run(Web_App.class, args);
    }
}
