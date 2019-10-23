package com.dubbo.demo.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@SuppressWarnings({"rawtypes", "unchecked"})
@Component
//@Service("springContextUtils")
public class SpringContextUtils {

    @Autowired
    private static ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext arg0)
            throws BeansException {
        applicationContext = arg0;
    }

    /**
     * 获取applicationContext对象
     *
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 根据bean的id来查找对象
     *
     * @param id
     * @return
     */
    public Object getBeanById(String id) {
        return getApplicationContext().getBean(id);
    }

    /**
     * 根据bean的class来查找对象
     *
     * @param c
     * @return
     */
    public static Object getBeanByClass(Class c) {
        return applicationContext.getBean(c);
    }

    /**
     * 根据bean的class来查找所有的对象(包括子类)
     *
     * @param c
     * @return
     */
    public static Map getBeansByClass(Class c) {
        return applicationContext.getBeansOfType(c);
    }
}
