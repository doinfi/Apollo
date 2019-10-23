/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubbo.demo;

import java.util.Map;

/**
 * 接口基类<br />
 * 概述: 所有接口都继承这个类, 用于消费者通吃所有模块的接口, 而不用一个一个引用各模块接口包, <br />
 * 背景: dubbo架构使用json, protobuf协议封装参数, 而不是使用具体的业务类, 为的是解耦<br />
 * 达到对模块接口定义和数据结构变更解耦的效果
 * <br />
 * 调用示例:<br>
 * baseRpc.call("logService.findOperateLogsTwo", "{\"userId\": 123456}")
 *
 * @author lizhiwei
 */
public interface BaseRPC {

//	 public String call(String app, String json);

    /**
     * 通用服务调用方法, 目前针对json使用
     *
     * @param obj 第一个参数是接口名, 如: StatisticsServier.reachStandaredPoint, <br />
     *            第二个参数是json字符串, 封装了调用接口的请求参数, 如{"userId":133454, "happenDate": 20170203}
     * @return 一般情况下是字符串, 但后面是object, 所有前面也就同样object了
     */
    public Object call(Object... obj);        //app, json

    /**
     * 通用服务调用方法, 目前针对上传文件等涉及流的接口使用, 如uploadWactchFace
     *
     * @param app 接口名, 如: StatisticsServier.reachStandaredPoint
     * @param map key-value形式, 封装了调用接口的请求参数, 如: {"userId"-33454, file-byte[]}
     * @return 正常情况是返回json字符串
     */
    public byte[] call(String app, Map<String, Object[]> map);    //用于
    /*
    这各做法对使用者有隐性要求, 不推荐
	//1.
	pbulic void putParameter(String name, byte[] b);
	//2.
	public void call(String app, String json);
	*/
//	public String call(String app, String json);
}
