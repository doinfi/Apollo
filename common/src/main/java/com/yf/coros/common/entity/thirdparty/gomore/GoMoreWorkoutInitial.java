package com.yf.coros.common.entity.thirdparty.gomore;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lihuaijin
 */
@Data
public class GoMoreWorkoutInitial implements Serializable {

    private static final long serialVersionUID = 735302083436420986L;

    @JSONField(deserialize = false)
    private Long id;

    @JSONField(deserialize = false)
    private Long userId;

    @JSONField(alternateNames = "type_id")
    private String workoutType;
    @JSONField(alternateNames = "heartrate_max")
    private String maxHeartRate;
    @JSONField(alternateNames = "stamina_level")
    private String staminaLevel;

    @JSONField(alternateNames = "prev_aerobic_ptc")
    private String prevAerobicPtc;

    @JSONField(alternateNames = "prev_anaerobic_ptc")
    private String prevAnaerobicPtc;

    @JSONField(alternateNames = "checksum", serialize = false)
    private String mobileChecksum;

    @JSONField(alternateNames = "checksum_01")
    private String firmwareChecksum;

    @JSONField(deserialize = false)
    private Integer taskRunning;

    @JSONField(deserialize = false)
    private Long createTime;
}
