package com.yf.coros.common.entity.open;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by dinghui on 2017/9/13.
 * {
 * "result": "0000",
 * "message": "ok",
 * "data":
 * {
 * "labelUuid": "45a050b1c7d14a17893dt64e41dfa4c8", //跑步记录唯一id
 * "avgSpeed": 459,//平均配速，单位：秒／千米
 * "distance": 70, //距离 单位：米
 * "duration": 93, //跑步时长（不含暂停） 单位：秒
 * "calorie": 1232, //卡路里
 * "startTime": 1445851745, //跑步开始时间戳
 * "endTime": 1445853748, //跑步结束时间戳
 * "latlng": [
 * [31264284,121652809,1445851745],
 * [31264284,121652809,1445851750],
 * [31264284,121652809,1445851755],
 * …略…
 * [31264284,121652809,1445852860],
 * [31264284,121652809,1445852865]
 * ], //坐标点数据，每项采集内容为[纬度,经度, 时间戳]，采用wgs84坐标系，注意室外模式才有此项数据
 * "nodes": [
 * [1000,724],
 * [2000,1345],
 * [3000,2253]
 * ], //节点数据，每项采集内容为[里程（米），时间（秒），坐标点索引(从0开始,-1表示没有)] , 注意点数据中可能存在没有经纬度的情况（GPS信号不佳时），则 坐标点索引(从0开始)=-1
 * "heartRate":[[1,78],[5,80],[10,88]],//心率数据，每项采集内容为[时间（秒）,心率]
 * <p>
 * "steps": [[60,180],[120,400],[180,600]] //步数数据，每项采集数据内容为[时间（秒）,步数] ,暂时没有里程（米）信息
 * }
 * }
 */
@Getter
@Setter
public class ActivityDetailVO implements Serializable {

    private String labelUuid;//跑步记录唯一id
    private String deviceName;

    private Integer avgSpeed;//平均配速，单位：秒／千米

    private Long startTime; // 开始时间（UTC 秒）

    private Long endTime;// 结束时间（UTC 秒）

    private Integer startTimezone;

    private Integer endTimezone;

    private Integer step;//步数

    private Float distance;//距离 单位：米

    private Float calorie;//卡路里

    private Long duration;//跑步时长（不含暂停） 单位：秒

    /**
     * 上升高度
     */
    private Integer ascent;

    /**
     * 下降高度
     */
    private Integer descent;

    /**
     * 平均心率
     */
    private Integer avgHeartRate;

    /**
     * 平均步频
     */
    private Integer avgFrequency;

    /**
     * 运动类型。2：跑步，4：骑行，8：游泳
     */
    private Integer type;
    /**
     * 运动子类型。1:室外，2：室内
     */
    private Integer mode;

    /**
     * 平均划频率，只游泳有此数据
     */
    private Integer avgStrkRateLength;
    /**
     * 总划水次数，只游泳有此数据
     */
    private Integer strokeCount;
    /**
     * 泳池长度，只游泳有此数据
     */
    private Integer lapDistance;

    /**
     * 暂停数据,
     * 格式[[暂停时间戳，暂停时长], [暂停时间戳，暂停时长]],
     * [100,50]表示在跑步开始后第100秒的时候暂停50秒，100秒是指相对跑步开始时间戳的值。
     */
    private List<Long[]> pause;

    /**
     * 坐标点数据，每项采集内容为[纬度,经度, 时间戳]，采用wgs84坐标系，注意室外模式才有此项数据
     */
    private List<Object[]> latlng;

    /**
     * 节点数据，每项采集内容为[里程（米），时间（秒），坐标点索引(从0开始,-1表示没有)] , 注意点数据中可能存在没有经纬度的情况（GPS信号不佳时），则 坐标点索引(从0开始)=-1
     */
    private List<Object[]> nodes;

    /**
     * 心率数据，每项采集内容为[时间（秒）,心率]
     */
    private List<Object[]> heartRate;

    /**
     * 步数数据，每项采集数据内容为[时间（秒）,步数] ,暂时没有里程（米）信息
     */
    private List<Long[]> steps;

    /**
     * 步数数据，每项采集数据内容为[时间（秒）,步数] ,暂时没有里程（米）信息
     */
    private Object[][] altitudeArray;
    /**
     * 距离数据，5秒一个值
     */
    private List<Object[]> distanceArray;

    /**
     * 铁人三项每个单项的明细
     */
    private List<ActivityDetailVO> triathlonDetailList;
}
