/*
 * 文件名：ResourceUtil.java
 * 时 间：下午4:40:35
 * 作 者：SYSTEM
 * 版 权： 2014-2022 智慧园区, 公司保留所有权利.
 * 联 系：www.szyungu.com
 */
package com.dubbo.demo.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.Map.Entry;

/**
 * @author lyf
 * @ClassName: ResourceUtil
 * @Description: (资源文件操作类.)
 * @date 2014年4月23日 下午4:40:35
 */
public class ResourceUtil {

    private static final Logger log = LoggerFactory.getLogger(ResourceUtil.class);

    private ResourceUtil() {

    }

    /**
     * @param file
     * @param key
     * @return
     * @标题: getValueByKey
     * @描述: (这里用一句话描述这个方法的作用)
     * @参数信息
     * @返回类型 String
     * @开发者 liyf
     * @可能抛出异常
     */
    public static String getValueByKey(File file, String key) {
        try {
            Properties properties = new Properties();
            FileInputStream in = new FileInputStream(file);
            properties.load(new InputStreamReader(in, "utf-8"));
            return properties.getProperty(key);
        } catch (Throwable e) {
            log.error("", e);
        }
        return null;
    }

    /**
     * @param @param  filePath
     * @param @param  key
     * @param @return
     * @return String
     * @throws
     * @Title: getValueByKey
     * @Description: (通过key获取value)
     * @user lyf
     */
    public static String getValueByKey(String filePath, String key) {
        // log.info("开始加载[" + filePath + "]文件....");

        Enumeration<URL> resouces;
        try {
            resouces = ResourceUtil.class.getClassLoader().getResources(filePath);
            if (null != resouces) {
                for (Enumeration<URL> tmp = resouces; tmp.hasMoreElements(); ) {
                    URL url = tmp.nextElement();
                    Properties properties = new Properties();
                    properties.load(new InputStreamReader(url.openStream(), "utf-8"));

                    Object reulst = properties.get(key);
                    if (null != reulst) {
                        return reulst.toString();
                    }
                }
            }
        } catch (IOException e) {
            log.error("读取资源文件[" + filePath + "]失败!!!", e);
        }

        return null;
    }

    /**
     * @param @param  filePath
     *                文件路径,如：errror_code/zh_CN/error_code_zh_CN.properties
     * @param @return
     * @return Map<String,String>
     * @throws
     * @Title: getPropertie
     * @Description: (资源文件所有key和value值.)
     * @user SYSTEM
     */
    public static Map<String, String> getPropertie(String filePath) {
        // log.info("开始加载[" + filePath + "]文件....");
        Map<String, String> map = new HashMap<String, String>();

        Enumeration<URL> resouces;
        try {
            resouces = ResourceUtil.class.getClassLoader().getResources(filePath);
            if (null != resouces) {
                for (Enumeration<URL> tmp = resouces; tmp.hasMoreElements(); ) {
                    URL url = tmp.nextElement();
                    readControllerCfg(url, map);
                }
            }
        } catch (IOException e) {
            log.error("读取资源文件[" + filePath + "]失败!!!", e);
        }
        return map;
    }

    /**
     * @param @param  url
     * @param @param  map
     * @param @throws IOException
     * @return void
     * @throws
     * @Title: readControllerCfg
     * @Description: (穷举所有配置文件信息)
     * @user lyf
     */
    private static void readControllerCfg(URL url, Map<String, String> map) throws IOException {
        Properties properties = new Properties();
        properties.load(new InputStreamReader(url.openStream(), "utf-8"));
        if (null != properties) {
            Set<Entry<Object, Object>> entrySet = properties.entrySet();

            for (Entry<Object, Object> entry : entrySet) {
                String value = String.valueOf(entry.getValue());
                if (null != value) {
                    map.put(entry.getKey().toString(), value);
                }
            }
        } // end if
    }

    /**
     * @param @param path
     * @param @param key
     * @param @param value
     * @return void
     * @throws
     * @Title: writerValue
     * @Description: (写数据)
     * @user liyf
     */
    public static void writerValue(String path, String key, String value) {
        Properties prop = null;
        try {
            prop = new Properties();

            InputStream in = ResourceUtil.class.getClassLoader().getResourceAsStream(path);

            prop.load(in);

            prop.put(key, value);

            URL url = ResourceUtil.class.getClassLoader().getResource(path);

            File file = new File(url.getFile());
            FileOutputStream out = new FileOutputStream(file);

            prop.store(out, "");
        } catch (IOException e) {
            log.error("", e);
        }
    }

    /**
     * @param file
     * @param key
     * @param value
     * @标题: writerValue
     * @描述: (这里用一句话描述这个方法的作用)
     * @参数信息
     * @返回类型 void
     * @开发者 liyf
     * @可能抛出异常
     */
    public static void writerValue(File file, String key, String value) {
        Properties prop = null;
        try {
            prop = new Properties();

            InputStream in = new FileInputStream(file);

            prop.load(new InputStreamReader(in, "utf-8"));

            prop.put(key, value);

            FileOutputStream out = new FileOutputStream(file);

            prop.store(out, "");
        } catch (IOException e) {
            log.error("", e);
        }
    }

    /**
     * @param file
     * @return
     * @标题: getProperties
     * @描述: 获取Properties文件
     * @参数信息
     * @返回类型 Properties
     * @开发者 weidt
     * @可能抛出异常
     */
    public static Properties getProperties(File file) {
        Properties prop = null;
        try {
            prop = new Properties();

            InputStream in = new FileInputStream(file);

            prop.load(new InputStreamReader(in, "utf-8"));
            return prop;
        } catch (IOException e) {
            log.error("", e);
        }
        return null;
    }

    /**
     * @param directoryPath
     * @return
     * @方法名称: getPropertieByDirectory
     * @描述: 获取某个目录下的所有配置文件
     * @参数:
     * @返回类型: Map<String,String>
     * @作者:liyf
     * @可能抛出异常:
     */
    public static Map<String, String> getPropertieByDirectory(String directoryPath) {
        Map<String, String> resultMap = new HashMap<String, String>();

        URL url = ResourceUtil.class.getClassLoader().getResource(directoryPath);

        File file = new File(url.getFile());
        if (file.exists() && file.isDirectory() && file.canRead()) {
            File[] files = file.listFiles();
            for (File f : files) {
                if (f.getName().trim().contains(".properties")) {
                    Properties properties = getProperties(f);
                    for (Object key : properties.keySet()) {
                        resultMap.put(key.toString(), properties.get(key).toString());
                    }

                }
            }
        }
        return resultMap;
    }
}
