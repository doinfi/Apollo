package com.yf.coros.common.entity.profile;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 全局配置
 * @author Infi
 */
@Data
public class GlobalProfile implements Serializable {
    private static final long serialVersionUID = -8355865220855166206L;
    private Long profileId;
    private Integer dataCollectSwitch;
    private String creator;
    private Date createTime;
    private Date updateTime;
}
