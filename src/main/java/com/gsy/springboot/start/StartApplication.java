package com.gsy.springboot.start;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class})
@EnableAsync
@EnableScheduling
@MapperScan("com.gsy.springboot.start.mapper")
//下面这种配置方式可以使用，但是如果要在工程启动之初就加载，还不如直接让application.properties文件自己管理
//@PropertySource(value = "classpath:application-emailConfig.properties")
public class StartApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }

}
