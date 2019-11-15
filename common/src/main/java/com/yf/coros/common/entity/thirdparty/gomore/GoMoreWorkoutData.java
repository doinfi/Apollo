package com.yf.coros.common.entity.thirdparty.gomore;

import com.alibaba.fastjson.annotation.JSONField;
import com.yf.coros.common.entity.thirdparty.gomore.json.DateTimeDeserializer;
import com.yf.coros.common.entity.thirdparty.gomore.json.KiloStringDeserializer;
import lombok.Data;

import java.io.Serializable;

/**
 * GoMoreWorkoutData
 * 反序列化时，fastjson可以搞定下划线转驼峰，所以不需要额外处理
 * @author lihuaijin
 */
@Data

public class GoMoreWorkoutData implements Serializable {

    private static final long serialVersionUID = 2124206018816327292L;

    @JSONField(deserialize = false)
    private Long userId;

    @JSONField(deserialize = false)
    private Long labelId;

    private String userWorkoutId;

    @JSONField(alternateNames = "user_id")
    private String gmUserId;

    private String typeId;

    private String flagCalc;

    @JSONField(deserializeUsing = DateTimeDeserializer.class)
    private Long timeStart;
    @JSONField(deserializeUsing = DateTimeDeserializer.class)
    private Long timeEnd;
    private String timeSeconds;
    private String timeSecondsRecovery;
    private String staminaLevel;
    private String aerobicLevel;
    private String anaerobicLevel;
    private String targetTrainingEffect;
    private String trainingEffectStamina;
    private String trainingEffectAerobic;
    private String trainingEffectAnaerobic;
    private String vo2max;
    private String heartrateMax;
    private String heartrateAvg;

    private String fileRouteHrVerifiedLite;
    private String staminaMaxUse;
    private String staminaAerobicMaxUse;
    private String staminaAnaerobicMaxUse;
    private String staminaEnd;
    private String staminaAerobicEnd;
    private String staminaAnaerobicEnd;

    @JSONField(alternateNames = "kcal", deserializeUsing = KiloStringDeserializer.class)
    private String cal;

    @JSONField(alternateNames = "kcal_max", deserializeUsing = KiloStringDeserializer.class)
    private String calMax;
    @JSONField(alternateNames = "distance_km", deserializeUsing = KiloStringDeserializer.class)
    private String distance;
    @JSONField(alternateNames = "distance_km_max", deserializeUsing = KiloStringDeserializer.class)
    private String distanceMax;

    private String filePaceKm;

    private String fileHrzone;
    private String appVersion;
    private String paceAvg;
    private String paceMax;
    private String geoInclineMax;
    private String geoElevationGain;
    private String geoInclineAvg;
    private String speedAvgKmh;
    private String speedMaxKmh;
    private String powerAvg;
    private String powerMax;
    private String powerZoneSec;
    private String trimp;

    private String lactateThresholdHeartrate1;

    /**
     * 乳酸阈心率
     */
    private String lactateThresholdHeartrate2;

    private String lactateThresholdSpeed1;

    /**
     * 乳酸阈配速
     */
    private String lactateThresholdSpeed2;
    private Integer happenDay;

}
