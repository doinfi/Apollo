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
public class SosRecord implements Serializable {

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
    private String sosShowTime;//默认英文显示格式 (GTM+08:45 Asia/Shanghai) 16:35:23, 07/11/2017
    private String cnTimeStr;//中文显示格式
    private String enModifyTime;//取消时间
    private String cnModifyTime;//
    private Long modifyTime;//修改时间
    private Integer sosStatus; // SOS 状态  0 有效（默认）  1 取消
}
