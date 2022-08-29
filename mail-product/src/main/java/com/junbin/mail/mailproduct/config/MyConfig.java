package com.junbin.mail.mailproduct.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ Author     ：苏俊滨
 * @ Date       ：Created in 8:35 2022/8/29
 * @ Description：${description}
 **/
@Configuration
public class MyConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //这里是指在url上面打的内容
        registry.addResourceHandler("/**")
                //下面的是指可以对应resources文件下那些内容
                .addResourceLocations("classpath:/")
                .addResourceLocations("classpath:/templates/")
                .addResourceLocations("classpath:/static");
    }
}