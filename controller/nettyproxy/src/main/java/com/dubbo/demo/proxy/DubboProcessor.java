/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubbo.demo.proxy;

import com.dubbo.demo.common.service.BaseService;
import com.dubbo.demo.proxy.config.CallConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

/**
 * dubbo引用处理<br />
 * 目前只用于检查dubbo引用是否有效<br />
 * 未来扩展成dubbo引用的动态加载<br />
 * <br />
 * 使用方法<br />
 * DubboProcessor dp=new DubboProcessor(ctx);	//加载spring dubbo.xml<br />
 * dp.doProcess(...);		<br />
 *
 * @author lizhiwei
 */
@Slf4j
public class DubboProcessor {

    /**
     * dubbo服务引用配置信息
     */
    ClassPathXmlApplicationContext ctx;

    public DubboProcessor() {
    }

    public DubboProcessor(ClassPathXmlApplicationContext ctx) {
        this.ctx = ctx;
    }

    /**
     * 处理dubbo监控请求, 目前是清单显示, 热加载未来扩展
     *
     * @param uri /config/dubbo/list, 或 /config/dubbo/reload
     * @param map 参数, 后面扩展用, 也许是 /config/dubbo/list?module=firmware这样子
     * @return
     */
    public String doProcess(String uri, Map<String, Object> map) {
        String result = "";
        if (0 == uri.indexOf("/config/dubbo/list")) {
            try {
                String beanId = (String) map.get("id");
                BaseService service = (BaseService) ctx.getBean(beanId);
                result = String.format("{id: %s, bean: %s}", beanId, service);
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    ByteArrayOutputStream bs = new ByteArrayOutputStream();
                    e.printStackTrace(new PrintStream(bs));
                    result = String.format("error message: %s, \n printStackTrace: %s", e.getMessage(), new String(bs.toByteArray(), "utf-8"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } else if (0 == uri.indexOf("/config/dubbo/reload")) {
            result = "热加载未实现 -- 20170317, lizhiwei";
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
