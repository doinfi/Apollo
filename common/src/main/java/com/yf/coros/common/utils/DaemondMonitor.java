/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yf.coros.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 监控中心<br />
 * 持有各接口的监控指标(触发发加载)<br />
 * 负责协调定时发送数据给中央监控模块<br />
 * 支持需要更新参数, 如url或tps_unit, 但还没上报的数据会丢失, 2分钟内左右, 当前分钟结算上一分钟, 而且结算了不一定等到上报<br />
 * <br />
 * 涉及配置文件: dubbomonitor.properties<br />
 * 使用方法<br />
 * DaemondMonitor.fireData(json);	懒加载, 第一次会加载配置文件, 接口第一次会初始化datacell<br />
 * DaemondMonitor.reload();	//会丢失最近2分钟左右的数据, 见上面备注<br />
 * <br />
 * 释放方法<br />
 * DaemondMonitor.cancelMonitor();<br />
 *
 * @author lizhiwei
 */
@Slf4j
public class DaemondMonitor {

    /**
     * 定时上报器, 每分钟将监控指标数据上报中央监控中心
     */
    static Timer timer1;
    static String clientId;
    static String module;
    static int tps_unit = 15;
    static boolean initial = false;

    private static String url;
    static Map<String, DataCell> mapCell = new HashMap<>();

    static {
        // TODO 停止监控数据上报，监控调整后在上报tps数据
//        startTimer();        //开启定时上报器
    }

    public DaemondMonitor() {
    }

    /**
     * 初始化
     */
    public static void init(int initTpsUnit, String initUrl) {
        if (initial) {
            return;
        }
        url = initUrl;
        tps_unit = initTpsUnit;
        mapCell.clear();
        initial = true;
        log.info("初始化配置完成");
    }

    /**
     * 取消监控
     */
    public static void cancelMonitor() {
        log.info("取消监控...");
        if (null != timer1)
            timer1.cancel();
        if (null != mapCell) {
            Set<String> set = mapCell.keySet();
            for (String key : set) {
                DataCell cell = mapCell.get(key);
                cell.release();
            }
            mapCell.clear();
        }
        log.info("取消监控完成");
    }

    public static Map<String, String> getDataCell(String[] app) {
        Map<String, String> m = new HashMap<>();
        if (null == app || 0 == app.length)
            app = mapCell.keySet().toArray(new String[0]);
        for (String key : app) {
            DataCell cell = null;
            if (mapCell.containsKey(key)) {
                cell = mapCell.get(key);
                if (!cell.map_done.isEmpty()) {
                    String txt = JSON.toJSONString(cell.map_done);
                    m.put(key, txt);
                }
            }
        }
        return m;
    }

    /**
     * 上报监控信息<br />
     * 如: {app:"FirmwareService.getWatchfaceList", startTime:1498136560780, endTime:1498136572154, idle1:null, idle2:null}<br />
     * 或简写: {clientId:"5092.1", app:"FirmwareService.getWatchfaceList", startTime:1498136560780, endTime:1498136572154}<br />
     *
     * @param json clientId, app, startTime, endTime, idle1, idle2<br />
     *             clientId: 客户端Id, 如5092.1, 5092为统计模块id, 1为集群机号, app: 接口名, startTime: 开始时间, endTime: 结束时间, idle1: 空闲总时长(待扩展), idle2: 空闲明细(json, 待扩展)<br />
     * @return
     */
    public static boolean fireData(String json) {
        boolean flag = false;
        try {
            JSONObject jo = JSON.parseObject(json);
            String app = jo.getString("app");
            String _clientId = jo.getString("clientId");
            app = String.format("%s:%s", _clientId, app);
            long defaultValue = -1;
            long startTime = getLongValue(jo.getLong("startTime"), defaultValue);
            long endTime = getLongValue(jo.getLong("endTime"), defaultValue);
//			long idle1=getLongValue(jo.getLong("idle1"), defaultValue);
//			long idle2=getLongValue(jo.getLong("idle2"), defaultValue);
            DataCell cell = mapCell.get(app);
            if (null == cell) {
                cell = new DataCell(app, tps_unit);
                mapCell.put(app, cell);
            }
            cell.addData(startTime, endTime);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return flag;
    }

    private static long getLongValue(Long param, long defaultValue) {
        Long value = defaultValue;
        if (null != param)
            value = param;
        return value;
    }

    public static void startTimer() {
        log.info("守护线程定时上报器开户...");
        timer1 = new Timer();
        long delay = 30 * 1000;
        long period = 60 * 1000;
        timer1.schedule(new TimerTask() {

            @Override
            public void run() {
                if (!initial) {
                    log.warn("DaemondMonitor Not init yet!");
                    return;
                }
                try {
                    Map<String, String> map = getDataCell(null);
                    //在线程上报时获取第一个接口key, 赋值clientId和modue, 这么做造成了一个缺陷, 在模块启动后, 一起没有接口访问时, 不会上报心跳
                    Set<String> set = map.keySet();
                    String[] array = set.toArray(new String[0]);
                    if (array.length > 0) {
                        //5089.1:FirmwareService.getWatchfaceList
                        String[] tmp = array[0].split(":");
                        clientId = tmp[0];    //5089.1
                        module = tmp[1];        //FirmwareService.getWatchfaceList
                        module = tmp[1].substring(0, module.indexOf('.'));    //FirmwareService
                    }
                    //{"min":201706252252,"app":"5089.1:FirmwareService.getWatchfaceList","cnt_max":350,"cnt":16,"cnt_min":250,"cnt_avg":300,"pointcut":"[4,4,3,5]"}
                    String txt = JSON.toJSONString(map);
                    log.info("监控数据上报准备: " + txt);
                    if (null == url || url.isEmpty()) {
                        url = "http://localhost:8090/DubboIndexService/indexService_multi.jsp";
                    }
                    String[] param = {"json", txt};
                    if (null != set && set.size() > 0) {
                        String result = HttpUtil.post(url, param);
                        if (null == result) {
                            result = "";
                        }
                        log.info("监控数据上报完成: " + result.trim());
                        set = mapCell.keySet();
                        for (String key : set) {
                            DataCell cell = mapCell.get(key);
                            cell.clearDoneData();
                            log.info("清空已上报数据");
                        }
                    } else {
                        log.info("没有数据, 发心跳包");
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
                        String heartbeat = String.format("{\"min\":%s,\"app\":\"%s:%s.heartbeat\"}", sdf.format(new Date()), clientId, module);
                        String[] _param = {"json", heartbeat};
                        HttpUtil.post(url, _param);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }, delay, period);
        log.info("守护线程定时上报器开户完成");
    }
}
