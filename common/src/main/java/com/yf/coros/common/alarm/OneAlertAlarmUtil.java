package com.yf.coros.common.alarm;

import com.yf.coros.common.alarm.entity.Alarm;
import com.yf.coros.common.alarm.entity.AlarmServerConfig;
import com.yf.coros.common.alarm.entity.OneAlertAlarm;
import com.yf.coros.common.exception.YfException;
import com.yf.coros.common.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * oneAlert告警工具类
 * @author lihuaijin
 */
@Slf4j
public class OneAlertAlarmUtil {

    /**
     * 向oneAlert发送告警
     * @param alarm 告警标准实体
     * @param config 告警服务配置实体
     * @throws YfException 失败时抛出异常
     */
    public static void sendAlarm(Alarm alarm, AlarmServerConfig config) throws YfException {
        OneAlertAlarm oneAlertAlarm = formatAlarm(alarm, config);
        ArrayList<Header> headers = new ArrayList<>();
        int retries = 1;
        boolean success = false;
        while (retries <= AlarmConstants.ALARM_RETRY) {
            try {
                HttpUtil.postJson(config.getUrl(), headers, oneAlertAlarm);
                success = true;
                break;
            } catch (IOException e) {
                log.warn("send alarm failed, retry count: " + retries, e);
                retries++;
            }
        }
        if (!success) {
            throw new YfException("Send oneAlert alarm failed.");
        }
    }

    private static OneAlertAlarm formatAlarm(Alarm alarm, AlarmServerConfig config) {
        OneAlertAlarm oneAlertAlarm = new OneAlertAlarm();
        oneAlertAlarm.setApp(String.valueOf(config.getAuthParam().get(AlarmConstants.ONE_ALERT_APP)));
        oneAlertAlarm.setEventType(transOneAlertEventType(alarm.getStatus()));
        oneAlertAlarm.setEventId(String.valueOf(alarm.getId()));
        oneAlertAlarm.setAlarmName(alarm.getName());
        oneAlertAlarm.setAlarmContent(alarm.getName());
        oneAlertAlarm.setEntityName(alarm.getTarget());
        oneAlertAlarm.setEntityId(alarm.getTarget());
        oneAlertAlarm.setPriority(alarm.getPriority());
        oneAlertAlarm.setHost(alarm.getTarget());
        oneAlertAlarm.setService(alarm.getSubTarget());
        Map<String, Object> details = new HashMap<>();
        details.put(AlarmConstants.DESCRIPTION, alarm.getDescription());
        details.put(AlarmConstants.METRIC_DESC, alarm.getMetricDesc());
        details.put(AlarmConstants.METRIC_VALUE, alarm.getValue());
        oneAlertAlarm.setDetails(details);
        oneAlertAlarm.setMetric(alarm.getMetric());
        oneAlertAlarm.setValue(alarm.getValue());
        return oneAlertAlarm;
    }

    private static String transOneAlertEventType(Integer alarmStatus) {
        switch (alarmStatus) {
            case AlarmConstants.ALARM_TRIGGER:
                return AlarmConstants.ONE_ALERT_TRIGGER;
            case AlarmConstants.ALARM_RECOVER:
            case AlarmConstants.ALARM_MANUAL_RECOVER:
                return AlarmConstants.ONE_ALERT_RESOLVE;
            default:
                return AlarmConstants.ONE_ALERT_TRIGGER;
        }
    }
}
