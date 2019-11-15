/*
 * Copyright (c) 2014-2018 Chenlaisoft Co.Ltd. All rights reserved.
 */

package com.yf.coros.common.entity.user;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * sos短信记录表
 *
 * @author Infi
 */
@Data
public class SmsRecord implements Serializable {

    private static final long serialVersionUID = 3667212133265557601L;
    private Long sosId;
    private Long userId;
    private String sosLogLat;
    private String sosAddress;
    private String msgContent;
    private Date createTime;
    private String nickname;
    private String contactsMobile;
    private String accessToken;
    private Long tumbleTime;
    private Integer smsType;
    private Integer tumbleTimezone;
    private Date tumbleDate;
    private String timezoneName;
    private String sosShowTime;
}
