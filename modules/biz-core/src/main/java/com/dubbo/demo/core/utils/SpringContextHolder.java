package com.dubbo.demo.core.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 概述:获得spring的bean<br>
 * 背景:<br>
 * <p>
 * <p>
 * Created by Infi on 17/2/23.
 */
@Component
public class SpringContextHolder implements ApplicationContextAware {


    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextHolder.applicationContext = applicationContext;
    }

    /**
     * 得到Spring 上下文环境<br>
     *
     * @return applicationContext
     */
    public static ApplicationContext getApplicationContext() {
        checkApplicationContext();
        return applicationContext;
    }

    /**
     * 通过Spring Bean name 得到Bean<br>
     *
     * @param name bean 上下文定义名称<br>
     */
    public static <T> T getBean(String name) {
        checkApplicationContext();
        return (T) applicationContext.getBean(name);
    }

    /**
     * 通过类型得到Bean<br>
     *
     * @param clazz 类对象<br>
     * @return 返回 spring bean 对象
     */
    public static <T> T getBean(Class<T> clazz) {
        checkApplicationContext();
        return applicationContext.getBean(clazz);
    }

    private static void checkApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("applicaitonContext未注入,请在application-context.xml中定义SpringContextHolder");
        }
    }

}
