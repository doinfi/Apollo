package com.yf.coros.common.entity.thirdparty.gomore;

import com.alibaba.fastjson.annotation.JSONField;
import com.yf.coros.common.entity.thirdparty.gomore.json.DateTimeSerializer;
import com.yf.coros.common.entity.thirdparty.gomore.json.KiloDoubleSerializer;
import com.yf.coros.common.entity.thirdparty.gomore.json.KiloIntegerSerializer;
import lombok.Data;

/**
 * 上传gomore数据参数实体
 * @author lihuaijin
 */
@Data
public class GoMoreSportParam {

    @JSONField(name = "type_id")
    private String workoutType;

    @JSONField(name = "time_start", serializeUsing = DateTimeSerializer.class)
    private Long timeStart;

    /**
     * 与coros duration不同，gomore含暂停时间
     */
    @JSONField(name = "time_seconds")
    private Long timeSeconds;

    /**
     * coros的指标，要转换kcal单位
     */
    @JSONField(name = "kcal", serializeUsing = KiloIntegerSerializer.class)
    private String cal;

    /**
     * gomoreSDK的指标，单位已经配套了
     */
    @JSONField(name = "kcal_max")
    private String kcalMax;

    @JSONField(name = "distance_km", serializeUsing = KiloDoubleSerializer.class)
    private String distance;

    @JSONField(name = "distance_km_max", serializeUsing = KiloDoubleSerializer.class)
    private String distanceMax;

    /**
     * gomore弃用字段，固定值
     */
    @JSONField(name = "question_rpe")
    @Deprecated
    private String questionRpe = "RPE_L_EASY";

    @JSONField(name = "sdk_version")
    private String sdkVersion;

    @JSONField(name = "device_id")
    private String gmDevice;

    @JSONField(name = "mission_status")
    private String missionStatus = "-1";

    @JSONField(name = "user_movement")
    private String userMovement = "2";
}
