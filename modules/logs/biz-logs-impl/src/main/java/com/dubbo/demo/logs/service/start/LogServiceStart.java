package com.dubbo.demo.logs.service.start;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 概述: 日志模块service层启动类<br>
 * 背景: <br>
 * Created by Infi on 17/3/16.
 */
public class LogServiceStart {
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("classpath:application.xml");
        synchronized (LogServiceStart.class) {
            while (true) {
                try {
                    LogServiceStart.class.wait();
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
