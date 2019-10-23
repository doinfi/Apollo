package com.dubbo.demo.core.common;

import com.dubbo.demo.core.utils.ResourceUtil;
import com.dubbo.demo.core.utils.Tools;
import org.springframework.stereotype.Component;

/**
 * Created by dinghui on 17/2/24.
 */
@Component("appConfig")
public class AppConfig {
    public final static String CINFIG_PATH = "app.properties";

    // buket名称前缀
    public static final String BUCKET_PREFIX = getProperty("oss.bucket.prefix");

    //oss endpoint
    public static final String ENDPOINT = getProperty("oss.endpoint");

    //oss img endpoint
    public static final String OSS_IMG_ENDPOINT = getProperty("oss.img.endpoint");

    //调试模式
    public static final boolean DEBUG = getProperty("debug").equals("true");

    //锁客户端ID
    public final static String LOCK_CLIENT_ID = getProperty("lock.clientId");

    /**
     * 获取配置项
     *
     * @param key
     * @return
     */
    public static String getProperty(String key) {
        return ResourceUtil.getValueByKey(CINFIG_PATH, key);
    }

    /**
     * 获取配置项
     *
     * @param key
     * @param defaultValue 默认值
     * @return
     */
    public static String getProperty(String key, String defaultValue) {
        String value = ResourceUtil.getValueByKey(CINFIG_PATH, key);
        return Tools.isEmpty(value) ? defaultValue : value;
    }


}
