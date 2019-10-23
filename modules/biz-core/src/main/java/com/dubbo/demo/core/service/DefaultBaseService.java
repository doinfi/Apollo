package com.dubbo.demo.core.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dubbo.demo.common.exception.RpcException;
import com.dubbo.demo.common.service.BaseService;
import com.dubbo.demo.core.utils.SpringContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 概述: 默认基础接口实现<br/>
 * 背景:<br>
 * 调用示例:<br>
 * logService.call("logService.findOperateLogsTwo");<br>
 * 其中logService是类名,findOperateLogsTwo是方法名称<br>
 *
 * @author hudahua
 */
@Service("baseService")
public class DefaultBaseService implements BaseService {
    private static final Logger log = LoggerFactory.getLogger(DefaultBaseService.class);

    @Autowired
    private SpringContextHolder springContextHolder;

    private JSONObject jsonObject;

    private void setJsonParameter(String json) {
        if (StringUtils.isBlank(json)) {
            return;
        }
        System.out.println(json);
        if (jsonObject == null) {
            jsonObject = new JSONObject();
        }
        jsonObject = JSON.parseObject(json);
    }

    protected JSONObject getJsonObject() {
        return jsonObject;
    }

    /**
     * 将JSON 参数转换成对象<br>
     * 单独抽取出来的原因是未来以后如果有更好更高效的JSON to Object 的工具类可以集中替换<br>
     *
     * @param jsonParam
     * @param clazz
     * @return
     */
    public <T> T parseModel(String jsonParam, Class<T> clazz) {
        return JSON.parseObject(jsonParam, clazz);
    }

    @Override
    public Object call(Object... args) throws RpcException {

        String exptionMsg = null;
        StackTraceElement[] stackTrace = null;
        try {
            if (args.length == 0) {
                throw new RpcException("invoke error:parameter is null!");
            }
            Class<?> classType = getClass();
            // 1. 把类名和方法名转成数组,第一个是类名,第二个是方法名
            String[] classMethods = StringUtils.split(args[0].toString(), ".");
            // 2. 如果带有. 需要获取对应service 再反射方法调用
            if (classMethods.length > 1) {
                // 3. 获得spring托管的bean,比如获得logService的实现logServiceImpl类对象
                classType = springContextHolder.getBean(classMethods[0]).getClass();
            }

            // 5. 如果方法有参数,就传入参数,如果没有参数,就不传入
            if (args.length > 1) {
                // json参数赋值给jsonObject
                setJsonParameter(args[1].toString());
                // TODO 其他参数
//                Object[] objects = new Object[args.length - 1];
//                for (int i = 1; i < args.length; i++) {
//                    objects = ArrayUtils.add(objects, args[i]);
//                }
                // 获得类的方法
                Method method = classType.getDeclaredMethod(classMethods[1], String.class);
                Class claz = method.getReturnType();
                //TODO 传递数组参数,接口也必需接受数组参数,否则不能传递。这里目前只传一个参数给接口
                Object object = method.invoke(this, args[1]);
                return JSON.toJSONString(claz.cast(object));
            } else {
                // 获得类的方法
                Method method = classType.getDeclaredMethod(classMethods[1]);
                Object object = method.invoke(this, null);
                return JSON.toJSONString(object);
            }
        } catch (Exception e) {
            exptionMsg = e.getMessage();
            stackTrace = e.getStackTrace();
            e.printStackTrace();
        }

        RpcException rpcException = new RpcException(exptionMsg);
        rpcException.setStackTrace(stackTrace);
        throw rpcException;
    }

    @Override
    public Object call(String methodName, Map<String, Object> parameterMap) throws RpcException {
        String exptionMsg = null;
        StackTraceElement[] stackTrace = null;
        try {
            if (StringUtils.isBlank(methodName) || parameterMap == null || parameterMap.size() < 1) {
                log.error("rpc调用参数错误,没有传递类名和方法名。或者没有byte参数");
                throw new RpcException("invoke error:parameter is null!");
            }
            Class<?> classType = getClass();
            // 1. 把类名和方法名转成数组,第一个是类名,第二个是名,比如"logService.saveLog"
            String[] classMethods = StringUtils.split(methodName, ".");
            if (classMethods.length < 2) {
                log.error("rpc调用,参数错误,类名和方法名错误");
                throw new RpcException("invoke error:parameter is null!");
            }
            // 3. 获得spring托管的bean,比如获得logService的实现logServiceImpl类对象
            classType = springContextHolder.getBean(classMethods[0]).getClass();
            // 4. 获得类的方法
            Method method = classType.getDeclaredMethod(classMethods[1], String.class);
            Object object = method.invoke(this, parameterMap);
            return JSON.toJSONString(object);

        } catch (Exception e) {
            exptionMsg = e.getMessage();
            stackTrace = e.getStackTrace();
            e.printStackTrace();
        }
        RpcException rpcException = new RpcException(exptionMsg);
        rpcException.setStackTrace(stackTrace);
        throw rpcException;
    }
}
