package com.dubbo.demo.core.service;

import com.alibaba.fastjson.JSON;
import com.dubbo.demo.common.exception.RpcException;
import com.dubbo.demo.common.service.BaseService;
import com.dubbo.demo.common.vo.ResponseVO;
import com.dubbo.demo.core.utils.SpringContextHolder;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.alibaba.fastjson.JSON.parseObject;

/**
 * 概述: 默认基础接口实现<br/>
 * 背景:<br>
 * 调用示例:<br>
 * logService.call("logService.findOperateLogsTwo");<br>
 * 其中logService是类名,findOperateLogsTwo是方法名称<br>
 *
 * @author dinghui
 */
@Service("defaultService")
public class DefaultService implements BaseService {
    private static final Logger log = LoggerFactory.getLogger(DefaultBaseService.class);

    @Autowired
    private SpringContextHolder springContextHolder;

    private ParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();

    private Map<String, Method> methodMap = null;

    private DefaultConversionService convertSerivce = new DefaultConversionService();

    /**
     * 将JSON 参数转换成对象<br>
     * 单独抽取出来的原因是未来以后如果有更好更高效的JSON to Object 的工具类可以集中替换<br>
     *
     * @param jsonParam
     * @param clazz
     * @return
     */
    public <T> T parseModel(String jsonParam, Class<T> clazz) {
        return parseObject(jsonParam, clazz);
    }


    @Override
    public Object call(Object... args) throws RpcException {
        try {
            //校验参数是否存在
            if (args.length == 0) {
                throw new RpcException("invoke error:parameter is null!");
            }
            // 1. 把类名和方法名转成数组,第一个是类名,第二个是方法名
            String classMethodString = args[0].toString();
            Map<String, Object> params = new HashedMap();
            if (args.length > 0) {
                params = JSON.parseObject(args[1].toString(), Map.class);
            }
            return callMethod(classMethodString, params);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RpcException(e.getMessage());
        }
    }

    @Override
    public Object call(String methodName, Map<String, Object> parameterMap) throws RpcException {
        try {
            if (StringUtils.isBlank(methodName)) {
                log.error("rpc调用参数错误,没有传递类名和方法名。或者没有byte参数");
                throw new RpcException("invoke error:parameter is null!");
            }
            // 1. 把类名和方法名转成数组,第一个是类名,第二个是方法名
            return callMethod(methodName, parameterMap);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RpcException(e.getMessage());
        }
    }


    private Map<String, Method> getMethodMap(Class<?> clazz) {
        Map<String, Method> methodMap = new HashMap<>();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            //方法名忽略大小写
            methodMap.put(method.getName().toLowerCase(), method);
        }
        return methodMap;
    }

    /**
     * 判断参数类型是否需要转换
     *
     * @param clz
     * @return
     */
    public static boolean isPrimitiveParameter(Class clz) {
        try {
            //判断字符串类型
            if (clz.equals(String.class)) {
                return true;
            }
            //判断是否基本类型
            if (clz.isPrimitive()) {
                return true;
            }
            //判断是否是基本类型的封装类型
            return ((Class) clz.getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 从Map封装的参数对象中解析出对应的参数,并匹配需要call的方法的序列
     * 填充方法参数Object[]
     *
     * @param method
     * @param mapParams
     * @return
     */
    private Object[] createParameters(Method method, Map mapParams) {
        try {
            String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);
            Class[] parameterClasses = method.getParameterTypes();
            if (parameterNames.length < 1) {
                return null;
            }
            Object[] params = new Object[parameterNames.length];
            log.debug("方法名称:" + method.getName());

            int i = 0;
            for (String parameterName : parameterNames) {
                String parameterLowerName = parameterName.toLowerCase();//转小写
                Class parameterClass = parameterClasses[i];
                String paramClazz = parameterClass.getName();
                log.debug(String.format("参数名称:%s(%s) 类型:%s", parameterName, parameterLowerName, paramClazz));
                Object objParam = mapParams.get(parameterLowerName);

                try {
                    if (null == objParam) {
                        log.warn(String.format("%s(%s) 参数值为NULL", parameterName, parameterLowerName));
                    }
                    //如果是基础类型,则将Json字符串强转成对应的类型
                    else if (isPrimitiveParameter(parameterClass)) {
                        objParam = convertSerivce.convert(objParam, parameterClass);
                    }
                    //如果参数不是Java基础类型,则需要转换
                    else {
                        objParam = JSON.parseObject(objParam.toString(), parameterClass);
                    }
                } catch (Exception e) {
                    log.error(String.format("参数 %s(%s):%s 类型转换错误", parameterName, paramClazz, mapParams.get(parameterLowerName)), e);
                }
                params[i] = objParam;
                i++;
            }
            log.debug("*****************************");
            return params;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将转发层传输过来的JsonString参数转换为Map参数数组
     *
     * @param jsonString
     * @return
     */
    private Map getParamsFromJsonString(String jsonString) {
        Map<String, Object> params = JSON.parseObject(jsonString, Map.class);
        return parseParamsMapKey(params);
    }

    /**
     * 将Map参数的key转化为小写
     *
     * @param paramsMap
     * @return
     */
    private Map parseParamsMapKey(Map<String, Object> paramsMap) {
        Map<String, Object> params = new HashedMap();
        if (paramsMap == null || paramsMap.size() < 1) {
            return params;
        }
        for (Map.Entry<String, Object> entry : paramsMap.entrySet()) {
            //将参数名转换为小写
            params.put(entry.getKey().toLowerCase(), entry.getValue());
        }
        return params;
    }


    /**
     * 调用类的方法
     *
     * @param classMethodString
     * @param parameterMap
     * @return
     * @throws Exception
     */
    private Object callMethod(String classMethodString, Map<String, Object> parameterMap) throws Exception {

        //获取类名和参数名
        String[] classMethods = StringUtils.split(classMethodString, ".");
        if (classMethods.length < 2) {
            log.error("rpc调用,参数错误,类名和方法名错误");
            throw new RpcException(String.format("invoke error:parameter count error, params:%s", classMethodString));
        }
        String className = classMethods[0];
        String methedName = classMethods[1];
        // 2. 如果带有. 需要获取对应service 再反射方法调用
        // 3. 获得spring托管的bean,比如获得logService的实现logServiceImpl类对象
        Class<?> classType = springContextHolder.getBean(className).getClass();

        //方法参数名转小写(为了方便大小写进行不敏感处理)
        Map<String, Object> parseParams = parseParamsMapKey(parameterMap);

        methodMap = getMethodMap(classType);
        // 获得类的方法
        Method method = methodMap.get(methedName.toLowerCase());
        // 创建方法参数
        Object[] objects = createParameters(method, parseParams);

        Class<?> returnType = method.getReturnType();
        Object result = method.invoke(this, objects);
        if (result instanceof ResponseVO) {
            String jsonStirng = ((ResponseVO) result).toJsonString();
            log.warn(jsonStirng);
            return jsonStirng;
        }
        return result;

    }
}