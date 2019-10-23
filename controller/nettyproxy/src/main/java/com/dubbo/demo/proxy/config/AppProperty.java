/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubbo.demo.proxy.config;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 配置文件类<br />
 * 对应json配置文件, 如firmware.json, 主要是url<->rpc之间转换
 *
 * @author lizhiwei
 */
public class AppProperty {

    String module;
    String desc;
    Entity[] entitis;

    public AppProperty(String module, String desc) {
        this.module = module;
        this.desc = desc;
    }

    public Entity[] parseEntity(JSONArray array) {
        Entity[] en = new Entity[array.size()];
        int n = 0;
        for (Object o : array) {
            JSONObject jo = (JSONObject) o;
            String name = jo.getString("name");
            boolean validate = jo.getBoolean("validate");
            String rpcName = jo.getString("parse");

            JSONArray ary = jo.getJSONArray("params");
            int len = ary.size();
            String[] param = new String[len * 2];
            int i = 0;
            for (i = 0; i < len; i++) {
                param[i * 2 + 0] = ((JSONObject) ary.get(i)).getString("name");            //url param
                if (((JSONObject) ary.get(i)).containsValue("parse"))
                    param[i * 2 + 1] = ((JSONObject) ary.get(i)).getString("parse");        //rpc param
                else
                    param[i * 2 + 1] = param[i * 2 + 0];                                        //rpc param, 默认名
                //System.out.println(name+": "+param[i*2+0]+", "+param[i*2+1]);
            }
            Entity e = new Entity(this, name, validate, rpcName, param);
            en[n++] = e;
        }
        this.entitis = en;
        return en;
    }

    public String getModule() {
        return module;
    }

    public String getDesc() {
        return desc;
    }

    public Entity[] getEntitis() {
        return entitis;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setEntitis(Entity[] entitis) {
        this.entitis = entitis;
    }

    /**
     * 针对每个模块的URL, RPC接口, 参数转换等内容
     */
    public class Entity {

        /**
         * 为了引用父类的modue和desc, 不必序列化, 同时避免了序列化后的父类的entitys循环引用问题
         */
        @JSONField(serialize = false)
        AppProperty app;

        /**
         * url 最后一个/跟着的名称, 如 http://localhost:8080/biz/weatherCityCollection?city=深圳, 取weatherCityCollection
         */
        String urlName;
        /**
         * 是否需要验证登陆
         */
        boolean validate;
        /**
         * rpc service name
         */
        String rpcName;
        /**
         * [url_param1,rpc_param1,url_param2,rpc_param2,...]
         */
        String[] param;

        public Entity(String name, boolean validate, String app, String[] param) {
            this.urlName = name;
            this.validate = validate;
            this.rpcName = app;
            this.param = param;
        }

        public Entity(AppProperty app, String urlName, boolean validate, String rpcName, String[] param) {
            this.app = app;
            this.urlName = urlName;
            this.validate = validate;
            this.rpcName = rpcName;
            this.param = param;
        }

        public AppProperty getApp() {
            return app;
        }

        public String getUrlName() {
            return urlName;
        }

        public boolean isValidate() {
            return validate;
        }

        public String getRpcName() {
            return rpcName;
        }

        public String[] getParam() {
            return param;
        }

        public void setApp(AppProperty app) {
            this.app = app;
        }

        public void setUrlName(String urlName) {
            this.urlName = urlName;
        }

        public void setValidate(boolean validate) {
            this.validate = validate;
        }

        public void setRpcName(String rpcName) {
            this.rpcName = rpcName;
        }

        public void setParam(String[] param) {
            this.param = param;
        }
    }
}
