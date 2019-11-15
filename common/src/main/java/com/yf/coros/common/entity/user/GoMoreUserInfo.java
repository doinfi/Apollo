package com.yf.coros.common.entity.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lihuaijin
 */
@Data
public class GoMoreUserInfo implements Serializable {

    private static final long serialVersionUID = 3231359221569265229L;
    private Long userId;
    private String gmUserId;
    private String gmUserName;
    private String gmDevice;
    private String gmDeviceSecret;

    private String gmStaminaLevel;
    private String gmLastStaminaLevel;
    private String gmAerobicLevel;
    private String gmLastAerobicLevel;
    private String gmAnaerobicLevel;
    private String gmLastAnaerobicLevel;
    private String gmVo2max;
    private String gmLastVo2max;
    private String gmLthr2;
    private String gmLastLthr2;
    private String gmLtsp2;
    private String gmLastLtsp2;

    private String gmToken;
    private Long gmTokenExpire;
    private Long createTime;
    private Long updateTime;
}
