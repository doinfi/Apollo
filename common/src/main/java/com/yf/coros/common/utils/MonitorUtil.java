package com.yf.coros.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dinghui on 17/6/30.
 * 上报数据给志伟的监控服务
 */
@Slf4j
public class MonitorUtil {

    //线程池执行上报监控数据方法,最大并发数为10
    private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

    /**
     * 调用志伟的监控服务,上报监控数据
     *
     * @param clientId   模块实例 如5012.1
     * @param methodName 服务名.方法名
     * @param startTime  方法调用开始时间
     */
    public static void send(final String clientId, final String methodName, final Long startTime) {
        final long t2 = System.currentTimeMillis();
        try {
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    String jsonData = "";
                    try {
                        jsonData = String.format("{clientId:'%s',app:'%s',startTime:%d,endTime:%d}", clientId, methodName, startTime, t2);
                        DaemondMonitor.fireData(jsonData);
                    } catch (Exception ex) {
                        log.error(String.format("监控守护线程错误, message: %s, json: %s", ex.getMessage(), jsonData), ex);
                    }
                }
            });
        } catch (Exception ex) {
            log.error(String.format("send数据报错, clientId: %s, methodName: %s, error: %s", clientId, methodName, ex.getMessage()), ex);
        }

    }

}
