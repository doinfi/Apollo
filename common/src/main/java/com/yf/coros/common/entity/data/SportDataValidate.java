package com.yf.coros.common.entity.data;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 运动数据对象校验，对应t_sport_data表
 *
 * @author dinghui
 * @date 19/6/12
 */
@Data
public class SportDataValidate implements Serializable {

    private static final long serialVersionUID = -7261514473253405800L;
    private Long labelId;
    private Long userId;
    private Integer happenDay;
    private Integer happenMonth;
    private Integer mode;
    private Integer subMode;
    private String deviceId;
    private Long startTime;
    private Long endTime;
    private Integer startTimezone;
    private Integer endTimezone;
    /**
     * 运动数据校验的结果,0：正常， 1：存在心率为0的数据
     */
    private Integer state = 0;
    private Date createTime;
}
