package com.dubbo.demo.logs.dao.start;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 概述: 日志模块dao层启动类<br>
 * 背景: <br>
 * Created by Infi on 17/3/16.
 */
public class LogDaoStart {

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("classpath:application.xml");
        synchronized (LogDaoStart.class) {
            while (true) {
                try {
                    LogDaoStart.class.wait();
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
