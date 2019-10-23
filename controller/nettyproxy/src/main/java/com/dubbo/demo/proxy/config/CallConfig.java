/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubbo.demo.proxy.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * url和rpc之间路由处理<br />
 * 把从前端(app)对应的url路由到对应后端模块接口上, 并对参数进行转换<br />
 * 默认配置文件在/config目录里<br />
 * <br />
 * 使用方法:<br />
 * CallConfig c=new CallConfig();	//初始化<br />
 * c.reload();							//加载配置文件(重新加载也是这个方法)<br />
 * <br />
 * <br />
 *
 * @author lizhiwei
 */
public class CallConfig {
    private static final Logger log = LoggerFactory.getLogger(CallConfig.class);
    /**
     * key - module, 如firmware
     */
    Map<String, List<AppProperty>> mapModule;
    /**
     * key - url最后一个/后面的内容, 如http://localhost:8007/FirmwareService/getWatchList.do?userId=123, 会取getWatchfaceList.do, 不带url后面的参数
     */
    Map<String, AppProperty.Entity> mapUrlName;
    /**
     * key - url最后一个/后面的内容的正式表达则, 如http://localhost:8007/FirmwareService/getWatchList.do?userId=123, 会取getWatchfaceList.do, 不带url后面的参数<br />
     * 主要处理以下情况:<br />
     * userAction!*getUserInfoforUserId 或 userAction!*getUserInfoforUserId
     */
    Map<String, AppProperty.Entity> mapUrlNameRegular;
    /**
     * key - module.app,
     */
    Map<String, AppProperty.Entity> mapRpcName;

    /**
     * json文件的加载目录
     */
    String configPath = "/config";

    public CallConfig() {
    }

    /**
     * 加载配置文件, 默认值见 configPath<br />
     * 重载也是用这个方法<br />
     *
     * @return
     */
    public boolean reload() {
        boolean flag = false;
        log.info("重新加载配置文件开始");
        try {
            mapUrlName = new HashMap<>();
            mapUrlNameRegular = new HashMap<>();
            mapModule = new HashMap<>();
            mapRpcName = new HashMap<>();
            File[] files = null;
            URL url = this.getClass().getResource(configPath);
            File dir = new File(url.getFile());
            if (dir.isDirectory()) {
                log.info(String.format("加载配置文件目录:%s", dir.getName()));
                files = dir.listFiles(new FilenameFilter() {

                    @Override
                    public boolean accept(File dir, String name) {
                        int n = name.lastIndexOf('.');
                        String fileType = name.substring(n + 1);
                        return ("json".equalsIgnoreCase(fileType));
                    }
                });
                for (File f : files) {
                    log.info(String.format("加载配置文件:%s", f.getName()));
                    try {
                        AppProperty app = parseProperty(f);
                        List<AppProperty> l = mapModule.get(app.module);
                        if (null == l)
                            l = new ArrayList<>();
                        l.add(app);
                        mapModule.put(app.module, l);
                        for (AppProperty.Entity e : app.entitis) {
                            if (e.getUrlName().indexOf('/') == 0) {
                                //正式表达则存在, mapUrlNameRegular, 匹配起来很蛋疼, 丁慧很有追求
                                mapUrlNameRegular.put(e.urlName, e);
                            } else {
                                //非正式表达则存在, mapUrlName, 匹配的时候直接equal
                                mapUrlName.put(e.urlName, e);
                            }
                            mapRpcName.put(e.rpcName, e);
                        }
                    } catch (Exception e) {
                        log.error(String.format("解析json配置文件失败, json文件: %s", f.getName()));
                        e.printStackTrace();
                    }
                }
            }
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            log.info("重新加载配置文件结束");
        }
        return flag;
    }

    /**
     * 从json文件中读取配置
     *
     * @param f
     * @return
     */
    public AppProperty parseProperty(File f) {
        AppProperty app = null;
        try {
            FileInputStream fin = new FileInputStream(f);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fin, "utf-8"));
            String str = null;
            StringBuilder sb = new StringBuilder();
            while (null != (str = reader.readLine())) {
                str = str.trim();
                str = str.replaceAll("^//.*$", "");        //去掉js格式的备注
                sb.append(str);
            }
            fin.close();
            String text = sb.toString();
            app = parseProperty(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return app;
    }

    /**
     * 从json文件中读取配置
     *
     * @param text json文件里的字符串, 不能包含备注
     * @return
     */
    public AppProperty parseProperty(String text) {
        AppProperty app = null;
        try {
            JSONObject jo = (JSONObject) JSON.parse(text);
            app = new AppProperty(jo.getString("module"), jo.getString("desc"));
            app.parseEntity(jo.getJSONArray("funcs"));
        } catch (Exception e) {
            log.error(String.format("解析json错误, error message:\n%s\ntext:\n%s", e.getMessage(), text));
            e.printStackTrace();
        }
        return app;
    }

    /**
     * 获取各种属性数量, 见flag
     *
     * @param flag 1-module数量, 2-rpc数量
     * @return
     */
    public Integer getCount(int flag) {
        Integer result = -1;
        if (1 == flag) {
            result = mapModule.size();
        } else if (2 == flag) {
            result = mapRpcName.size();
        }
        return result;
    }

    /**
     * 获取配置信息
     * 当urlName和rpcName并存时, 以urlName为准
     *
     * @param urlName httpurl 接口名, 如: getWatchfaceList.do, 第1优先
     * @param rpcName rpc 接口名, FirmwareService.getWatchfaceList 或者
     * @return
     */
    public AppProperty.Entity getAppProp(String urlName, String rpcName) {
        AppProperty.Entity app = null;
        if (null != urlName && !urlName.isEmpty()) {
            //先直接判断, 找不到再找正式匹配
            if (mapUrlName.containsKey(urlName)) {
                app = mapUrlName.get(urlName);
            } else {
                String[] regex = mapUrlNameRegular.keySet().toArray(new String[0]);
                for (String reg : regex) {
                    if (checkUrlReg(reg, urlName)) {
                        app = mapUrlNameRegular.get(reg);
                        break;
                    }

                }
            }
        } else if (null != rpcName && !rpcName.isEmpty()) {
            app = mapRpcName.get(rpcName);
        }
        return app;
    }

    /**
     * 获取指定模块的配置信息
     *
     * @param module 模块名, 如果为空, 则返回全部
     * @return
     */
    public List<AppProperty> getAppPropList(String module) {
        List<AppProperty> list = new ArrayList<>();
        String[] key = null;
        if (null != module && !module.isEmpty())
            return mapModule.get(module);
        else
            key = mapModule.keySet().toArray(new String[0]);
        if (null == key || key.length < 1)
            return list;
        Arrays.sort(key);
        for (String k : key) {
            List<AppProperty> l = mapModule.get(k);
            list.addAll(l);
        }
        return list;
    }

    /**
     * 获取接口的短名称<br />
     * 用于call(rpcName, param);<br />
     *
     * @param rpcName 如: com.dubbo.demo.logs.service.LogsService.saveLogs
     * @return 返回 Firmware.getWatchfaceList
     */
    public static String getRpcShortName(String rpcName) {
        String result = null;
        try {
            String[] tmp = rpcName.split("\\.");
            int len = tmp.length;
            result = String.format("%s.%s", tmp[len - 2], tmp[len - 1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 判断http的url与配置文件里的url_regular是否匹配
     *
     * @param reg     /firmware!?\\*?getfirmwConfigList\\.do/
     * @param urlName http://localhost:8007/biz/firmware!getfirmwConfigList.do 或 firmware!getfirmwConfigList.do
     */
    public static boolean checkUrlReg(String reg, String urlName) {
        boolean flag = false;
        try {
            reg = reg.substring(1, reg.length() - 1);
            Pattern p = Pattern.compile(reg);
            Matcher m = p.matcher(urlName);
            flag = m.find();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static void testCheckUrlReg(String[] args) {
        String reg = "/firmware!?\\*?getfirmwConfigList\\.do/";
        String urlName = "http://biz/firmware!*getfirmwConfigList.do";
        boolean flag = checkUrlReg(reg, urlName);
        System.out.println(flag);
    }

    public static void main(String[] args) {
        System.out.println("start");
        CallConfig con = new CallConfig();
        con.reload();
        String json = null;
        json = JSON.toJSONString(con.getAppPropList("weatherService"));
        System.out.println(json);
        json = JSON.toJSONString(con.getAppPropList("statisticsService"));
        System.out.println(json);
        json = JSON.toJSONString(con.getAppProp(null, "com.dubbo.demo.logs.service.Logs.saveLogs"));
        System.out.println("getAppProp: " + json);
        json = JSON.toJSONString(con.getAppProp("downLoadWatchfaceSuccess", ""));
        System.out.println("getAppProp: " + json);
        json = JSON.toJSONString(con.getAppProp("firmware!*getfirmwConfigList.do", ""));
        System.out.println("getAppProp: " + json);
        System.out.println("end");
    }
}
