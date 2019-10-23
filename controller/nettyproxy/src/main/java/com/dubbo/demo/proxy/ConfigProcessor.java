/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubbo.demo.proxy;

import com.alibaba.fastjson.JSON;
import com.dubbo.demo.proxy.config.CallConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 处理路由配置相关的请求<br />
 * 目前是热加载和显示清单<br />
 * 配合DubboNettyServer里的doProcess使用<br />
 * <br />
 * 使用方法<br />
 * CallConfig con=new CallConfig();<br />
 * con.reload();<br />
 * ConfigProcessor p=new ConfigProcessor(con);<br />
 * p.doProcess(uri, map); //实现热加载或清单显示<br />
 *
 * @author lizhiwei
 */
public class ConfigProcessor {
    private static final Logger log = LoggerFactory.getLogger(ConfigProcessor.class);

    CallConfig config;

    /**
     * 初始化, 加载配置信息类
     *
     * @param config
     */
    public ConfigProcessor(CallConfig config) {
        this.config = config;
    }

    /**
     * 处理配置信息类请求, 目前是热加载和清单显示
     *
     * @param uri /confing/json/list, 或 /config/json/reload
     * @param map 参数, 后面扩展用, 也许是 /config/list?module=firmware这样子
     * @return
     */
    public String doProcess(String uri, Map<String, Object> map) {
        String result = "";
        if (uri.equalsIgnoreCase("/config/json/list")) {
            result = JSON.toJSONString(config.getAppPropList(null));
        } else if ("/config/json/reload".equalsIgnoreCase(uri)) {
            config.reload();
            result = "config 重载成功, 新的清单如下:\n" + JSON.toJSONString(config.getAppPropList(null));
            log.debug(result);
        }
        return result;
    }

    public static void main(String[] args) {
        CallConfig con = new CallConfig();
        con.reload();
        ConfigProcessor p = new ConfigProcessor(con);
        String result = p.doProcess("/config/List", null);
        System.out.println("-----");
        System.out.println(result);
    }
}
