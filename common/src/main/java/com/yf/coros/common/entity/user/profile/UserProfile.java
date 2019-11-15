package com.yf.coros.common.entity.user.profile;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户配置对象，目前只有sos开关项配置
 *
 * @author Infi
 */
@Data
public class UserProfile implements Serializable {
    private static final long serialVersionUID = -2167769163787714562L;
    private Long userId;
    private Integer sosSwitch;
    private String algorithmCalibration;
    private Integer dataCollectSwitch;
}
