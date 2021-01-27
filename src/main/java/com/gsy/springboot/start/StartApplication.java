package com.gsy.springboot.start;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableAsync
@EnableScheduling

//下面这种配置方式可以使用，但是如果要在工程启动之初就加载，还不如直接让application.properties文件自己管理
//@PropertySource(value = "classpath:application-emailConfig.properties")
public class StartApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(StartApplication.class);
    }

}
