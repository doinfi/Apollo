package com.yf.coros.common.entity.user;

import com.alibaba.fastjson.annotation.JSONField;
import com.yf.coros.common.entity.thirdparty.gomore.GoMoreWorkoutInitial;
import com.yf.coros.common.entity.thirdparty.gomore.GomoreDeviceKey;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户信息返回对象
 *
 * @author Infi
 * @date 17/3/22
 */
@Data
public class UserInfoVO implements Serializable {

    private static final long serialVersionUID = 4982773357158290575L;
    private String mobile;
    private String email;
    private String facebookId;
    private String weixinId;
    private String accessToken;
    private Integer clientType;
    private Long userId;
    private String nickname;
    private String weixinName;
    private String facebookName;
    private Integer sex;
    private Float stature;
    private Float weight;
    private Integer birthday;
    private String headPic;
    private String longitude;
    private String latitude;
    private Integer registerType;
    private String pwd;
    private String appKey;
    private String countryCode;
    private Integer unit;
    private Integer targetMotionTime;
    private Integer targetCalorie;
    private String ipAddress;
    private byte[] headPicFile;
    private String checkCode;
    private Integer activateStatus;
    private String newEmail;
    private Long registerDate;
    private String account;
    private Integer accountType;
    private Integer loginType;
    private Integer registerTimezone;
    @JSONField(serialize = false)
    private Long registerTimestamp;
    private Integer timezone;
    private Integer activateType;

    //弃用字段  暂时保留，默认赋值最后一个紧急联系人信息 by max.hu
    private String phoneCode;
    private String contactNote;
    private String contactsMobile;
    private String contactCountryCode;
    //新增字段 多个紧急联系人 by max.hu
    private List<?> contactList;

    private String openNickname;
    private String unactivatedEmail;
    private Integer registerClientType;
    private String saveParseInfo;
    private String maxVo;
    private Integer heartRateZone;
    private Integer lactateThreshold;
    private Integer maxHeartRate;
    private Integer rhr;
    private String maxHeartRateInterval;
    private String heartRateReserveInterval;
    private String lactateThresholdInterval;
    private String firstName;
    private String lastName;
    private String region;
    private Integer manualRhr;

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

    /**
     * login增加的入参，只作为入参使用
     */
    @JSONField(serialize = false)
    private List<String> deviceList;

    private String sleepStartTime;
    private String sleepEndTime;

    @JSONField(serialize = false)
    private Date maxHrUpdateTime;
    @JSONField(serialize = false)
    private Date rhrUpdateTime;
}
