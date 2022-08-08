package com.junbin.mail.ware;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.junbin.mail.ware.dao")
public class MailWareApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailWareApplication.class, args);
    }

}
