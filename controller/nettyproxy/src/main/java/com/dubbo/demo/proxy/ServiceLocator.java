package com.dubbo.demo.proxy;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * @author lizhiwei
 */
public class ServiceLocator implements BeanFactoryAware {

    private static BeanFactory beanFactory = null;

    private static ServiceLocator servlocator = null;

    @Override
    public void setBeanFactory(BeanFactory factory) throws BeansException {
        ServiceLocator.beanFactory = factory;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public static ServiceLocator getInstance() {
        if (servlocator == null) {
            servlocator = (ServiceLocator) beanFactory.getBean("serviceLocator");
        }
        return servlocator;
    }

    /**
     * 根据提供的bean名称得到相应的服务类
     *
     * @param servName bean名称
     * @return
     */
    public static Object getService(String servName) {
        return beanFactory.getBean(servName);
    }

    /**
     * 根据提供的bean名称得到对应于指定类型的服务类
     *
     * @param servName bean名称
     * @param clazz    返回的bean类型,若类型不匹配,将抛出异常
     * @return
     */
    public static Object getService(String servName, Class clazz) {
        return beanFactory.getBean(servName, clazz);
    }
}
