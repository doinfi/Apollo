package com.yf.coros.common.config;

import com.yf.coros.common.enums.TPSClientEnum;

import java.lang.annotation.*;

/**
 * Created by dinghui on 17/7/21.
 */
@Documented //说明该注解将被包含在javadoc中
@Inherited //允许子类继承父类中的注解
@Target(ElementType.METHOD) //定义注解的作用目标**作用范围字段、枚举的常量/方法
@Retention(RetentionPolicy.RUNTIME) // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
public @interface MonitorMethod {
    /**
     * 默认该方法需要监控
     *
     * @return
     */
    boolean monitor() default true;

    /**
     * 方法的client_id，需要填，如果不填，则认为统计到USER
     */
    TPSClientEnum client() default TPSClientEnum.DEFAULT;
}
