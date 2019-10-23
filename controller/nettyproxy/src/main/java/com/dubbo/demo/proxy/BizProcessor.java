/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubbo.demo.proxy;

import com.alibaba.fastjson.JSON;
import com.dubbo.demo.common.service.BaseService;
import com.dubbo.demo.proxy.config.AppProperty;
import com.dubbo.demo.proxy.config.CallConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 处理远程rpc请求<br />
 * netty从HttpRequest获取url和参数后<br />
 * 通过路由配置读取, 获取对应的模块和接口名, 以及对应参数规则<br />
 * 再从dubbo获取对应的baseService, 并调用<br />
 * token验证(userInfo(UserInfoVO)的情况或是valideate为真的情况), 以及token->userInfoVO, 这个比较复杂, 详见doToken<br />
 * <br />
 * 使用方法<br />
 * task.doProcess(...)<br />
 * <br />
 * <br />
 *
 * @author lizhiwei
 */
public class BizProcessor {
    private static final Logger log = LoggerFactory.getLogger(BizProcessor.class);

    /**
     * dubbo服务引用配置信息
     */
    ClassPathXmlApplicationContext ctx;
    /**
     * url <-> rpc 路由配置信息
     */
    CallConfig config;

    public BizProcessor(CallConfig config, ClassPathXmlApplicationContext ctx) {
        this.config = config;
        this.ctx = ctx;
    }

    /**
     * 处理http请求转发dubbo rpc请求, 采用baseService.call(Object... os)方法
     *
     * @param uri    http请求url, 如: http://localhost:8007/FirmwareService/getWatchList.do
     * @param mapReq http的param, 如userId=1342, happenDate=20170309
     * @return baseService.call()返回的值
     * @throws Exception
     */
    public String doProcess(String uri, Map<String, Object> mapReq) throws Exception {
        String result = "";
        String urlName = "";
        String rpcName = "";
        String module = "";
        String shortName = "";                            //FirmwareService.getGJ		//json parse里, 最后2个英文单词, 即 接口名.方法名
        String app = "";                                    //firmwareService.getGJ		//json 里, module(dubbo ref id), 加上parse里的方法名, 即 模块引用名.方法名
        BaseService service = null;
        boolean validate = false;                            //接口是否需要校验, 如果这里不需要, 但参数里存在userInfo(UserInfoVO), 效果是一样的, 都是去账号模块获取UserInfoVO, 那上接口包括了校验, 过期了不会返回UserInfoVO
        Map<String, Object> mapRpc = new HashMap<>();        //储存rpc调用的参数, 经过.json配置文件转换后的
        try {
            //处理uri, 把最后/后面的内容截取出来, http://localhost:8007/FirmwareService/getWatchList.do -> getWatchList.do
            int n = uri.lastIndexOf('/');
            int n2 = uri.lastIndexOf('?');
            if (-1 != n2)
                urlName = uri.substring(n + 1, n2);
            else
                urlName = uri.substring(n + 1);
            //获取对应的模块和rpc接口
            AppProperty.Entity en = config.getAppProp(urlName, null);
            if (null == en) {
                result = String.format("{result:'unknow', message:'找不到对应的rpc接口方法, 请检查供应者注册情况, 内外网络或.json配置文件, uri: %s'}", urlName);
                return result;
            }
            //太长, 截成 Firmware.getWatchfaceList
            rpcName = en.getRpcName();
            shortName = CallConfig.getRpcShortName(rpcName);
            app = String.format("%s.%s", module, shortName.substring(shortName.lastIndexOf('.') + 1));
            validate = en.isValidate();
            module = en.getApp().getModule();
            try {
                service = (BaseService) ctx.getBean(module);
                if (null == service)
                    throw new Exception(String.format("BaseService null, module: %s, 请检查module的接口包命名空间, 或zookeeper注册中心是否注册了对应的供应者", module));
            } catch (Exception ex) {
                ex.printStackTrace();
                Map<String, String> m = new HashMap<>();
                m.put("result", "unknow");
                m.put("message", String.format("从zookeeper获取module(%s) bean失败, error message:\n%s", module, ex.getMessage()));
                result = JSON.toJSONString(m);
                log.error(result);
                result = result.replaceAll("\n", "<br />");
                return result;
            }
            if (mapReq.isEmpty()) {
                Map<String, Object> map = new HashMap<>();
                result = (String) service.call(shortName, map);
            }
            //uri带的参数 -> rpc要的参数, 如 token -> accessToken
            mapRpc = convertParam(mapReq, en);
            //处理token转换, 检查rpc key里有没带(UserInfoVO)或(TUserVO)之类的, 如:userInfoVO(UserInfoVO)
            try {
                mapRpc = doToken(mapRpc, validate);
            } catch (Exception ex) {
                result = String.format("{result:'unknow', message: '%s'}", ex.getMessage());
                return result;
            }
            long t1 = new Date().getTime();
            log.info(String.format("执行准备 - module %s, call(%s, %s), ts: %d", module, app, JSON.toJSONString(mapRpc), t1));
            try {
                Object obj = service.call(app, JSON.toJSONString(mapRpc));
                result = (String) obj;
            } catch (Exception ex) {
                ex.printStackTrace();
                String msg = String.format("执行baseService.call失败: 参数:uri:%s\nmap(request):%s\nmap(rpc call)%s\nerror message:\n%s", uri, JSON.toJSONString(mapReq), JSON.toJSONString(mapRpc), ex.getMessage());
                throw new Exception(msg);
            }
            long ts = new Date().getTime() - t1;
            log.info(String.format("执行完成 - module %s, call(%s, %s), ts: %d", module, app, JSON.toJSONString(mapRpc), ts));
        } catch (Exception e) {
            e.printStackTrace();
            String msg = String.format("执行doProcess失败: %s\n参数:uri:%s\nmap(request):%s\nmap(rpc call)%s\n", e.getMessage(), uri, JSON.toJSONString(mapReq), JSON.toJSONString(mapRpc));
            log.error(msg);
            throw new Exception(msg);
        }
        return result;
    }

    /**
     * 处理http请求转发dubbo rpc请求, 采用baseService.call(rpcName, map)方法<br />
     * 主要用于带文件流的情况
     *
     * @param uri    http请求url, 如: http://localhost:8007/FirmwareService/getWatchList.do
     * @param mapReq http的param, 如userId=1342, happenDate=20170309
     * @return baseService.call()返回的值
     * @throws Exception
     */
    public String doProcessMap(String uri, Map<String, Object> mapReq) throws Exception {
        String result = "";
        String urlName = "";
        String rpcName = "";
        String module = "";
        String shortName = "";                            //FirmwareService.getGJ		//json parse里, 最后2个英文单词, 即 接口名.方法名
        String app = "";                                    //firmwareService.getGJ		//json 里, module(dubbo ref id), 加上parse里的方法名, 即 模块引用名.方法名
        BaseService service = null;
        boolean validate = false;                            //接口是否需要校验, 如果这里不需要, 但参数里存在userInfo(UserInfoVO), 效果是一样的, 都是去账号模块获取UserInfoVO, 那上接口包括了校验, 过期了不会返回UserInfoVO
        Map<String, Object> mapRpc = new HashMap<>();        //储存rpc调用的参数, 经过.json配置文件转换后的
        try {
            //处理uri, 把最后/后面的内容截取出来, http://localhost:8007/FirmwareService/getWatchList.do -> getWatchList.do
            int n = uri.lastIndexOf('/');
            int n2 = uri.lastIndexOf('?');
            if (-1 != n2)
                urlName = uri.substring(n + 1, n2);
            else
                urlName = uri.substring(n + 1);
            //获取对应的模块和rpc接口
            AppProperty.Entity en = config.getAppProp(urlName, null);
            if (null == en) {
                result = String.format("{result:'unknow', message:'找不到对应的rpc接口方法, 请检查供应者注册情况, 内外网络或.json配置文件, uri: %s'}", urlName);
                return result;
            }
            //太长, 截成 Firmware.getWatchfaceList
            rpcName = en.getRpcName();
            shortName = CallConfig.getRpcShortName(rpcName);
            app = String.format("%s.%s", module, shortName.substring(shortName.lastIndexOf('.') + 1));
            validate = en.isValidate();
            module = en.getApp().getModule();
            try {
                service = (BaseService) ctx.getBean(module);
                if (null == service)
                    throw new Exception(String.format("BaseService null, module: %s, 请检查module的接口包命名空间, 或zookeeper注册中心是否注册了对应的供应者", module));
            } catch (Exception ex) {
                ex.printStackTrace();
                Map<String, String> m = new HashMap<>();
                m.put("result", "unknow");
                m.put("message", String.format("从zookeeper获取module(%s) bean失败, error message:\n%s", module, ex.getMessage()));
                result = JSON.toJSONString(m);
                log.error(result);
                result = result.replaceAll("\n", "<br />");
                return result;
            }
            if (mapReq.isEmpty()) {
                Map<String, Object> map = new HashMap<>();
                result = (String) service.call(shortName, map);
            }
            //uri带的参数 -> rpc要的参数, 如 token -> accessToken
            mapRpc = convertParam(mapReq, en);
            //处理token转换, 检查rpc key里有没带(UserInfoVO)或(TUserVO)之类的, 如:userInfoVO(UserInfoVO)
            try {
                mapRpc = doToken(mapRpc, validate);
            } catch (Exception ex) {
                result = String.format("{result:'unknow', message: '%s'}", ex.getMessage());
                return result;
            }
            long t1 = new Date().getTime();
            log.info(String.format("执行准备 - module %s, call(%s, %s), ts: %d", module, app, JSON.toJSONString(mapRpc), t1));
            try {
                Object obj = service.call(app, mapRpc);
                result = (String) obj;
            } catch (Exception ex) {
                ex.printStackTrace();
                String msg = String.format("执行baseService.call失败: 参数:uri:%s\nmap(request):%s\nmap(rpc call)%s\nerror message:\n%s", uri, JSON.toJSONString(mapReq), JSON.toJSONString(mapRpc), ex.getMessage());
                throw new Exception(msg);
            }
            long ts = new Date().getTime() - t1;
            log.info(String.format("执行完成 - module %s, call(%s, %s), ts: %d", module, app, JSON.toJSONString(mapRpc), ts));
        } catch (Exception e) {
            e.printStackTrace();
            String msg = String.format("执行doProcess失败: %s\n参数:uri:%s\nmap(request):%s\nmap(rpc call)%s\n", e.getMessage(), uri, JSON.toJSONString(mapReq), JSON.toJSONString(mapRpc));
            log.error(msg);
            throw new Exception(msg);
        }
        return result;
    }

    /**
     * 处理token与UserInfo之类的信息转换(重要!)<br />
     * 存在2种情况:1.对接口访问的token校验这种情况<br />
     * 2.接口参数token转化为UserInfoVO往对应的rpc接口转<br />
     * 为了减少对账号模块的访问, 这里2合1处理<br />
     * 实现逻辑:<br />
     * 先对所有参数遍历, 发现需要token->userInfoVO, 就去账号模块转化了, 转化同时也是校验, 用户模块的token过期的话, 不会发userInfoVO过来的<br />
     * 如果参数遍历后没有发现需要token->userInfoVO, 则以配置项里的validate为补充, 这里为true, 则之前又没校验过, 就跑去校验咯<br />
     * <br />
     * 配置文件里出现{"name":"token","parse":"userInfoVO(UserInfoVO)"},代表前端带来的是token, 但后端模块需要的是UserInfoVO<br />
     * 这时候, 需要通过token的值(0X7QQ2OY3IPUK9QK5GIWRYHDO5M91ISC), 去账号模块获取UserInfoVO, 然后以userInfoVO的key形式, 转给将要调用的rpc接口<br />
     * 如:<br />
     * userInfoVO(UserInfoVO) - 0X7QQ2OY3IPUK9QK5GIWRYHDO5M91ISC<br />
     * 经过这个函数处理后<br />
     * userInfoVO - {'userId':745591, ...}<br />
     *
     * @param mapRpc   与json配置匹配转换后的新key-value数据结构, 可能部分key带有需要转换的类型, 目前只有UserInfo, 如userInfo(UserInfoVO) - 0X7QQ2OY3IPUK9QK5GIWRYHDO5M91ISC
     * @param validate 是否需要对token校验, 如果这里为真, 则不管map里有没有userInfoVO(UserInfoVO), 都会去账号模块校验
     * @return 新的key-value结构, 把带userInfo(UserInfoVO) - 0X7QQ2OY3IPUK9QK5GIWRYHDO5M91ISC之类的键值
     */
    private Map<String, Object> doToken(Map<String, Object> map, boolean validate) throws Exception {
        try {
            boolean isChecked = false;            //标识是否已经去用户模块校验过, 防止参数遍历时去账号模块校验后, 返回null, 然后validate为true时, 看见null又跑过校验
            String userInfoVo = null;
            String[] set = map.keySet().toArray(new String[0]);
            BaseService service = null;
            for (String key : set) {
                int n = key.indexOf('(');
                if (n > 0) {
                    String json = String.format("{accessToken:'%s'}", map.get(key));
                    try {
                        if (null == service)
                            service = (BaseService) ctx.getBean("accountService");
                        if (null == service)
                            throw new Exception();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        String msg = String.format("token转换失败, 获取远程dubbo模块AccountService为null, error msg:\n%s", ex.getMessage());
                        log.error(msg);
                        throw new Exception(msg);
                    }
                    try {
                        log.debug("toekn校验准备, service.call(\"accountService.getUserInfoByAccessToken\",%s)", json);
                        Object obj = service.call("accountService.getUserInfoByAccessToken", json);
                        userInfoVo = (String) obj;
                        log.debug("toekn校验完成, result UserInfoVO: %s", userInfoVo);
                        isChecked = true;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        String msg = String.format("token 获取失败, 获取远程dubbo模块(%s),\nservice.call(\"accountService.getUserInfoByAccessToken\", %s)为调用错误, error msg:\n%s", service, json, ex.getMessage());
                        log.error(msg);
                    }
                    log.debug("userInfo: " + userInfoVo);
                    map.remove(key);
                    String newKey = key.substring(0, n);
                    map.put(newKey, userInfoVo);
                }
            }
            //针对以上参数遍历后的补充, 如果接口需要校验, 但没给userInfo(UserInfoVO)的情况
            if (validate && !isChecked) {
                //2次重复代码, 虽然代码冗余了, 但思路直观, 后面慢慢重构
                try {
                    if (null == service)
                        service = (BaseService) ctx.getBean("accountService");
                    if (null == service)
                        throw new Exception();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    String msg = String.format("token转换失败, 获取远程dubbo模块AccountService为null, error msg:\n%s", ex.getMessage());
                    log.error(msg);
                    throw new Exception(msg);
                }
                Object obj = map.get("accessToken");        //目前固定为token, 配置文件没有专项配置这里
                if (null == obj) {
                    String msg = String.format("token校验错过, 找不到token参数");
                    throw new Exception(msg);
                }
                String json = (String) obj;
                if (json.isEmpty()) {
                    String msg = String.format("token校验错过, token为空");
                    throw new Exception(msg);
                }
                log.debug("validate校验准备, service.call(\"accountService.getUserInfoByAccessToken\",%s)", json);
                Object _obj = service.call("accountService.getUserInfoByAccessToken", json);
                userInfoVo = (String) _obj;
                log.debug("validate校验完成, result UserInfoVO: %s", userInfoVo);

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return map;
    }

    /**
     * 把url的参数转化成后端rpc接口的参数
     *
     * @param mapReq url参数
     * @param entity 参数转化规则
     * @return
     */
    public static Map<String, Object> convertParam(Map<String, Object> mapReq, AppProperty.Entity entity) {
        Map<String, Object> mapApp = new HashMap<>();
        try {
            String[] param = entity.getParam();
            int len = param.length / 2;
            for (int i = 0; i < len; i++) {
                String url_param = param[2 * i + 0];
                String app_param = param[2 * i + 1];
                Object value = mapReq.get(url_param);
                mapApp.put(app_param, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapApp;
    }

//	/**
//	 * 测试用的, 请忽略他
//	 * @return
//	 */
//	public static UserInfoVO getUserInfo() {
//        //初始化 用户信息
//        UserInfoVO userInfoVO = new UserInfoVO();
//        userInfoVO.setUserId(140541);
//        userInfoVO.setAccount("15900000001");
//        userInfoVO.setBirthday(1980);
//        userInfoVO.setSex(1);
//        userInfoVO.setHeight(175f);
//        userInfoVO.setWeight(75000f);
//
//        DailyTargetVO targetVO = new DailyTargetVO();
//        targetVO.setUserId(140541);
//        targetVO.setStartDate(20101010);
//        targetVO.setEndDate(20201010);
//        targetVO.setTargetType(1);
//        targetVO.setTargetValue(10000);
//        userInfoVO.getDailyTargets().add(targetVO);
//        return userInfoVO;
//    }

    public static void main1(String[] args) {
        CallConfig config = new CallConfig();
        config.reload();
        Map<String, Object> mapReq = new HashMap<>();
        AppProperty.Entity en = config.getAppProp("motionData", "");
        mapReq.put("yfheader", "12345");
        mapReq.put("token", "0X7QQ2OY3IPUK9QK5GIWRYHDO5M91ISC");
        Map<String, Object> map = BizProcessor.convertParam(mapReq, en);
        String result = JSON.toJSONString(map);
        System.out.println(result);
    }

    public static void main(String[] args) {
        System.out.println("start");
        try {
            String[] xml = {"dubbo_consumer.xml"};
            ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(xml);
            CallConfig config = new CallConfig();
            config.reload();
            BizProcessor task = new BizProcessor(config, ctx);
            Map<String, Object> map = new HashMap<>();
            //
//			UserInfoVO user=getUserInfo();
            //
            map.put("accessToken", "KEZ01I3VE7ZX6KL4UKW20MNU92921J27");
            map.put("version", 1);
            map.put("versionType", "WEATHER");
            String url;
            url = "http://localhost:8007/biz/logsService/saveLogs.do";
            String result = task.doProcess(url, map);
            System.out.println("result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }
}

