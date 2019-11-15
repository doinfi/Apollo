package com.yf.coros.common.entity.profile;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 固件开关灰度用户实体类
 * @author Infi
 */
@Data
public class DataCollectProfile implements Serializable {
    private static final long serialVersionUID = -6054409596784804527L;
    private Long userId;
    private Integer dataCollectSwitch;
    private String creator;
    private Date createTime;
    private Date updateTime;
    private String userIdString;
}
