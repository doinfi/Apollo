package com.yf.coros.common.entity.data;

import java.io.Serializable;
import java.security.PrivateKey;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * 运动数据天粒度统计对象
 *
 * @author Infi
 * @date 17/6/4
 */
@Data
public class SportDataDay implements Serializable {

    private static final long serialVersionUID = 7085414899215355518L;
    private Long userId;
    private Integer happenDay;
    private Integer happenYear;
    private Integer happenMonth;
    private Integer happenWeekDay;
    private Integer mode;
    private Integer subMode;
    private int count;
    private int calorie;
    private double distance;
    @JSONField(alternateNames = "time")
    private long duration;
    private Date createTime;
    private Integer maxVo;
    private int step;
    private int totalElevation;
    private int totalDecline;

    private int calorieTotal;
    private double distanceTotal;
    private int motionDurationTotal;
    private int stepTotal;

}
