package com.yf.coros.common.entity.user;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 用户信息
 *
 * @author Infi
 * @date 17/3/22
 */
@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 8861241557453825424L;
    private Long userId;
    private String nickname;
    private Integer sex;
    private Float stature;
    private Float weight;
    private Integer birthday;
    private String headPic;
    private String longitude;
    private String latitude;
    private String countryCode;
    private Integer unit;
    @JSONField(serialize = false)
    private Date updateDate;
    private Date createDate;
    private Integer registerTimezone;
    private Long registerTimestamp;
    private Integer timezone;
    private Integer firstDayOfWeek;
    private String phoneCode;
    private String contactNote;
    private String contactsMobile;
    private String contactCountryCode;
    private Integer heartRateZone;
    private Integer lactateThreshold;
    private Integer maxHeartRate;
    private String maxHeartRateInterval;
    private String heartRateReserveInterval;
    private String lactateThresholdInterval;
    private String firstName;
    private String lastName;
    private Integer manualRhr;
    private String sleepStartTime;
    private String sleepEndTime;
    @JSONField(serialize = false)
    private Date maxHrUpdateTime;
    @JSONField(serialize = false)
    private Date rhrUpdateTime;
}
