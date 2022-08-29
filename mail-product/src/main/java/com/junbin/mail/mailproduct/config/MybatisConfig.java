package com.junbin.mail.mailproduct.config;

import com.baomidou.mybatisplus.annotation.DbType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ Author     ：苏俊滨
 * @ Date       ：Created in 17:31 2022/8/18
 * @ Description：${description}
 **/
@Configuration
@EnableTransactionManagement
@MapperScan("com.junbin.mail.mailproduct.dao")
public class MybatisConfig {

    //分页插件
    @Bean
    public PaginationInterceptor paginationInnerInterceptor() {
        PaginationInterceptor pii = new PaginationInterceptor();
        pii.setOverflow(true);
        pii.setLimit(1000);

        return pii;
    }


}
