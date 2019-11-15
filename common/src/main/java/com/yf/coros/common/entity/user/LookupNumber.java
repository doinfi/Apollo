/*
 * Copyright (c) 2014-2018 Chenlaisoft Co.Ltd. All rights reserved.
 */

package com.yf.coros.common.entity.user;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * twilio平台电话号码检查
 *
 * @author Infi
 */
@Data
public class LookupNumber implements Serializable {

    private static final long serialVersionUID = 3100930595666558648L;
    private Date createTime;
    private String number;
    private String type;
    private String carrierName;
    private String countryCode;
}
