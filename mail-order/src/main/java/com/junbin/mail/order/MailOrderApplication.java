package com.junbin.mail.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.junbin.mail.order.dao")
public class MailOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailOrderApplication.class, args);
    }

}
