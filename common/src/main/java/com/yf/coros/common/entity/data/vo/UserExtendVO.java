package com.yf.coros.common.entity.data.vo;

import com.yf.coros.common.entity.thirdparty.gomore.GoMoreWorkoutInitial;
import com.yf.coros.common.entity.thirdparty.gomore.GomoreDeviceKey;
import com.yf.coros.common.entity.user.profile.UserProfile;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 用户扩展信息VO
 */
@Data
public class UserExtendVO implements Serializable {

    private static final long serialVersionUID = -2604188875995004117L;
    private Integer maxVo;
    private Integer maxVoChange;
    private Long maxVoTimestamp;
    private Integer maxVoOri;
    private Integer maxVoChangeOri;
    private Integer rhr;
    private Double kValue;
    private Double bValue;
    private Integer kbValidity;

    private UserProfile userProfile;
    private String algorithmCalibration;

    /**
     * gomore device信息
     */
    private List<GomoreDeviceKey> gomoreDevice;

    /**
     * gomore 运动模型信息
     */
    private List<GoMoreWorkoutInitial> gomoreWorkoutInitial;

    /**
     * gomore 用户信息
     */
    private Map<String, Object> gomoreUserMetric;
}
