package com.junbin.mail.coupon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ Author     ：苏俊滨
 * @ Date       ：Created in 17:33 2022/8/8
 * @ Description：${description}
 **/
@SpringBootApplication
@MapperScan("com.junbin.mail.coupon.dao")
@EnableDiscoveryClient
public class Coupon {
    public static void main(String[] args){
        SpringApplication.run(Coupon.class, args);
    }
}
