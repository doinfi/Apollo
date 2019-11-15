package com.yf.coros.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author yangyueming
 */
@SpringBootApplication
@ImportResource({"classpath:config/dubbo.xml"})
@MapperScan("com.yf.coros.user.dao")
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
        //启动DB监控
//        DruidDataSource datasource = (DruidDataSource) SpringUtils.getBean("dataSource");
//        DbConMonitor.startTimer(datasource);
    }
}

