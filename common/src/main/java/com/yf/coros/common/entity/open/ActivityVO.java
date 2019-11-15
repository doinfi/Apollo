package com.yf.coros.common.entity.open;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by dinghui on 17/2/22.
 * <p>
 * { "result":"0000",
 * "message":"ok",
 * "data": [
 * {
 * "deviceName": "Hey3s",//设备名称
 * "calorie": 145944,//卡路里，单位：小卡
 * "distance": 2349,//距离,单位：米
 * "endTime": 1494338160,//跑步结束时间时间戳
 * "avgFrequency": 146,//平均步频 单位： 步／分钟
 * "labelUuid": "5c57c5eb63bc44b2938f0a69fd571e3f",//跑步记录唯一id
 * "avgSpeed": 459,//平均配速，单位：秒／千米
 * "startTime": 1494337080,//跑步开始时间戳
 * "step": 2638 //总步数
 * }
 * ]
 * }
 */
@Getter
@Setter
public class ActivityVO implements Serializable {
    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 跑步标签id
     */
    private String labelUuid;

    /**
     * 运动类型。2：跑步、4：骑行、8：游泳
     */
    private Integer type;
    /**
     * 跑步类型  1:室外(默认)  2:室内
     */
    private Integer mode;

    /**
     * 跑步时长（排除暂停），单位秒
     */
    private Long duration;

    /**
     * 开始时间，单位：秒
     */
    private Long startTime;

    /**
     * 结束时间，单位：秒
     */
    private Long endTime;

    /**
     * 步数
     */
    private Integer step;

    /**
     * 距离，单位：米
     */
    private Float distance;

    /**
     * 卡路里，单位：小卡
     */
    private Float calorie;

    /**
     * 配速，xx秒/公里
     */
    private Integer avgSpeed;

    /**
     * 步频，xx步/分钟
     */
    private Integer avgFrequency;
    private Integer startTimezone;
    private Integer endTimezone;
    private String fitUrl;
    private String openId;
    private List<ActivityVO> triathlonItemList;

    @JSONField(serialize = false)
    private Long userId;
}