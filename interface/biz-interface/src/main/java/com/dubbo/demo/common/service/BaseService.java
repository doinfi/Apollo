package com.dubbo.demo.common.service;

import com.dubbo.demo.common.exception.RpcException;

import java.util.Map;


/**
 * 概述: 默认基础接口实现,公共Service RPC 调用方法<br/>
 * 背景:<br>
 * 调用示例:<br>
 * logService.call("logService.findOperateLogsTwo");<br>
 * 其中logService是类名,findOperateLogsTwo是方法名称<br>
 * <br>
 * <br>
 * 接口方法说明:
 * putBytesParameter:传入byte[]参数,比如说可以把文件转成byte[]参数,key表示文件名<br>
 * setJsonParameter:传入json参数<br>
 * getJsonObject:获取jsonObject参数<br>
 * getBytesParameter:获取byte[]参数,通过key获取<br>
 * getByteParameterMap:获取整个byte[] map对象,比如接口中有多个文件传参时,可以通过该方法一次或的所有文件参数<br>
 * call:RPC调用<br>
 *
 * @author hudahua<br>
 */
public interface BaseService {


    /**
     * RPC松耦调用<br>
     * 调用方法:<br>
     * 第一个参数: 接口名+参数名。如:"logService.saveLog"<br>
     * 其他参数: json。比如:{"userId":123,"name":"infi"}<br>
     *
     * @param args 第一个参数为方法名，第二个参数为对应方法参数JSON字符串<br>
     * @return 返回对应方法返回值<br>
     */
    Object call(Object... args) throws RpcException;

    /**
     * RPC松耦合调用<br>
     * 调用方法:<br>
     * 第一个参数: 接口名+参数名。如:"logService.saveLog"<br>
     * 第二个参数: map对象<br>
     *
     * @param methodName   类名.方法名。比如:"logService.saveLog"表示logService的saveLog方法<br>
     * @param parameterMap map 参数,可以传文件<br>
     * @return 返回对应方法的返回值<br>
     */
    Object call(String methodName, Map<String, Object> parameterMap) throws RpcException;
}
