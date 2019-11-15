package com.yf.coros.common.entity.data.analyse;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.yf.coros.common.entity.data.analyse.json.DoubleFormatScaleSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lihuaijin
 */
@Data
public class AnalyseRunVo implements Serializable {

    // 字段来自于视图 user_run_sport_view

    /* 用户个人信息 */

    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long userId;
    private String gmUserId;
    private Integer heartrateMax;
    private Integer manualRhr;
    private String sex;
    private Integer birthday;
    @JSONField(serializeUsing = DoubleFormatScaleSerializer.class)
    private Double stature;
    @JSONField(serializeUsing = DoubleFormatScaleSerializer.class)
    private Double weight;
    private Integer userTimezone;

    /* 运动概要 */

    private String startTimeCst;
    @JSONField(serializeUsing = DoubleFormatScaleSerializer.class)
    private String staminaLevel;
    private Integer corosVo2max;
    private Integer corosCurrentVo2max;
    private String gomoreVo2max;
    @JSONField(serializeUsing = DoubleFormatScaleSerializer.class)
    private Double distance;
    private Long duration;
    private Integer avgPace;
    private String sportHeartrateMax;
    private String sportHeartrateAvg;
    private String trainingEffectStamina;
    private String trainingEffectAerobic;
    private String trainingEffectAnaerobic;
    private String heartRateDataFilePath;

    /**
     * 非数据库字段
     */
    private String hrFileUrl;


    /* 运动明细 */

    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long labelId;
    private Integer mode;
    private Integer subMode;
    private String userWorkoutId;
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long startTime;
    private String typeId;
    @JSONField(serializeUsing = DoubleFormatScaleSerializer.class)
    private Double altitude;
    private Double temperature;
    private String staminaMaxUse;
    private String staminaEnd;
    private String timeSecondsRecovery;
    private String lactateThresholdHeartrate2;
    private String lactateThresholdSpeed2;

    /* 备用，目前没展示 */

    private Integer happenDay;
    private Integer startTimezone;
    private Integer endTimezone;
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long endTime;
    private Double altitudeMin;
    private Double altitudeMax;
    private Double temperatureMin;
    private Double temperatureMax;

    /**
     * 生成导出标题头
     * @param seperator 字段分隔符
     * @return 标题头的行字符串
     */
    public static String generateExportTitleLine(String seperator) {
        StringBuilder sb = new StringBuilder();

        sb.append("运动开始 - 北京时间").append(seperator);
        sb.append("体力等级").append(seperator);
        sb.append("coros最大摄氧量（本次）").append(seperator);
        sb.append("coros最大摄氧量（总体）").append(seperator);
        sb.append("博晶最大摄氧量").append(seperator);
        sb.append("运动距离").append(seperator);
        sb.append("运动时长").append(seperator);
        sb.append("平均配速").append(seperator);
        sb.append("运动最大心率").append(seperator);
        sb.append("运动平均心率").append(seperator);
        sb.append("体能训练效果").append(seperator);
        sb.append("有氧训练效果").append(seperator);
        sb.append("无氧训练效果").append(seperator);
        sb.append("心率文件名").append(seperator);

        sb.append("运动ID").append(seperator);
        sb.append("博晶运动ID").append(seperator);
        sb.append("开始时间戳").append(seperator);
        sb.append("mode").append(seperator);
        sb.append("sub_mode").append(seperator);
        sb.append("运动类型").append(seperator);
        sb.append("平均海拔").append(seperator);
        sb.append("平均温度").append(seperator);
        sb.append("体能消耗%").append(seperator);
        sb.append("剩余体能%").append(seperator);
        sb.append("预计恢复时间").append(seperator);
        sb.append("乳酸阈心率").append(seperator);
        sb.append("乳酸阈配速").append(seperator);

        sb.append("用户ID").append(seperator);
        sb.append("用户博晶ID").append(seperator);
        sb.append("设置最大心率").append(seperator);
        sb.append("设置静息心率").append(seperator);
        sb.append("性别").append(seperator);
        sb.append("生日").append(seperator);
        sb.append("身高").append(seperator);
        sb.append("体重").append(seperator);
        sb.append("用户时区").append(seperator);

        sb.append("发生日期").append(seperator);
        sb.append("运动开始时区").append(seperator);
        sb.append("运动结束时区").append(seperator);
        sb.append("结束时间戳").append(seperator);
        sb.append("最低海拔").append(seperator);
        sb.append("最高海拔").append(seperator);

        // sb.append("最低温度").append(seperator);
        // sb.append("最高温度").append(seperator);

        return sb.toString();
    }

    /**
     * 生成导出数据
     * @param seperator 字段分隔符
     * @return 数据行字符串
     */
    public String generateExportDataLine(String seperator) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.valueOf(this.startTimeCst)).append(seperator);
        sb.append(String.valueOf(this.staminaLevel)).append(seperator);
        sb.append(String.valueOf(this.corosCurrentVo2max)).append(seperator);
        sb.append(String.valueOf(this.corosVo2max)).append(seperator);
        sb.append(String.valueOf(this.gomoreVo2max)).append(seperator);
        sb.append(String.valueOf(this.distance)).append(seperator);
        sb.append(String.valueOf(this.duration)).append(seperator);
        sb.append(String.valueOf(this.avgPace)).append(seperator);
        sb.append(String.valueOf(this.sportHeartrateMax)).append(seperator);
        sb.append(String.valueOf(this.sportHeartrateAvg)).append(seperator);
        sb.append(String.valueOf(this.trainingEffectStamina)).append(seperator);
        sb.append(String.valueOf(this.trainingEffectAerobic)).append(seperator);
        sb.append(String.valueOf(this.trainingEffectAnaerobic)).append(seperator);
        sb.append(String.valueOf(this.heartRateDataFilePath)).append(seperator);

        sb.append(String.valueOf(this.labelId)).append(seperator);
        sb.append(String.valueOf(this.userWorkoutId)).append(seperator);
        sb.append(String.valueOf(this.startTime)).append(seperator);
        sb.append(String.valueOf(this.mode)).append(seperator);
        sb.append(String.valueOf(this.subMode)).append(seperator);
        sb.append(String.valueOf(this.typeId)).append(seperator);
        sb.append(String.valueOf(this.altitude)).append(seperator);
        sb.append(String.valueOf(this.temperature)).append(seperator);
        sb.append(String.valueOf(this.staminaMaxUse)).append(seperator);
        sb.append(String.valueOf(this.staminaEnd)).append(seperator);
        sb.append(String.valueOf(this.timeSecondsRecovery)).append(seperator);
        sb.append(String.valueOf(this.lactateThresholdHeartrate2)).append(seperator);
        sb.append(String.valueOf(this.lactateThresholdSpeed2)).append(seperator);

        sb.append(String.valueOf(this.userId)).append(seperator);
        sb.append(String.valueOf(this.gmUserId)).append(seperator);
        sb.append(String.valueOf(this.heartrateMax)).append(seperator);
        sb.append(String.valueOf(this.manualRhr)).append(seperator);
        sb.append(String.valueOf(this.sex)).append(seperator);
        sb.append(String.valueOf(this.birthday)).append(seperator);
        sb.append(String.valueOf(this.stature)).append(seperator);
        sb.append(String.valueOf(this.weight)).append(seperator);
        sb.append(String.valueOf(this.userTimezone)).append(seperator);

        sb.append(String.valueOf(this.happenDay)).append(seperator);
        sb.append(String.valueOf(this.startTimezone)).append(seperator);
        sb.append(String.valueOf(this.endTimezone)).append(seperator);
        sb.append(String.valueOf(this.endTime)).append(seperator);
        sb.append(String.valueOf(this.altitudeMin)).append(seperator);
        sb.append(String.valueOf(this.altitudeMax)).append(seperator);

        // sb.append(String.valueOf(this.temperatureMin)).append(seperator);
        // sb.append(String.valueOf(this.temperatureMax)).append(seperator);

        return sb.toString();
    }
}
