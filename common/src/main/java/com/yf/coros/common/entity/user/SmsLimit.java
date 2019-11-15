/*
 * Copyright (c) 2014-2018 Chenlaisoft Co.Ltd. All rights reserved.
 */

package com.yf.coros.common.entity.user;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * sos短信限制表
 *
 * @author Infi
 */
@Data
public class SmsLimit implements Serializable {

    private static final long serialVersionUID = 6547395971569298477L;
    private Long userId;
    private Date lastTime;
    private Integer count;
    private Integer historyCount;
    private Integer smsType;
}
