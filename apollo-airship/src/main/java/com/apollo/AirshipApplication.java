package com.apollo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author yangyueming
 */
@SpringBootApplication
@ImportResource({"classpath:config/dubbo.xml"})
@MapperScan("com.apollo.dao")
public class AirshipApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirshipApplication.class, args);
    }
}

