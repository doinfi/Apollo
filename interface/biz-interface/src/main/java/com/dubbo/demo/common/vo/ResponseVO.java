package com.dubbo.demo.common.vo;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by dinghui on 17/2/22.
 * 说明:消息通用类
 */
public class ResponseVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2587841145983842867L;

    private String result;

    private String message;

    Map<String, Object> map = new LinkedHashMap<String, Object>();

    public ResponseVO() {
        this.result = "0000";
        this.message = "ok";
        map.put("result", "0000");
        map.put("message", "ok");
    }

    public ResponseVO(StatusResultEnum result) {
        this.result = result.getResult();
        this.message = result.getMessage();
        map.put("result", result);
        map.put("message", message);
    }

    public ResponseVO(String result, String message) {
        map.put("result", result);
        map.put("message", message);
    }

    public void put(String key, Object data) {
        map.put(key, data);
    }

    public void put(Map<String, Object> map) {
        if (map == null || map.size() < 1) {
            return;
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            this.map.put(entry.getKey(), entry.getValue());
        }
    }

    public String toJsonString() {
        String jsonStr = JSON.toJSONString(map).replace("\\", "");
        return jsonStr;
    }


    /**
     * 获取请求返回编号
     *
     * @return result 请求返回编号
     */
    public String getResult() {
        return result;
    }

    /**
     * 设置请求返回编号
     *
     * @param result 请求返回编号
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * 获取请求返回信息
     *
     * @return message 请求返回信息
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置请求返回信息
     *
     * @param message 请求返回信息
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
