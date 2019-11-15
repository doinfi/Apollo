package com.yf.coros.common.entity.data;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author Infi
 * @date 17/5/9 标签对象
 */
@Data
public class LabelDataResultVO implements Serializable {

    private static final long serialVersionUID = 5997439640593164303L;
    private Long labelId;
    private Integer userId;
    private Date happenDate;
    private Long startTime;
    private Long endTime;
    private Integer startTimezone;
    private Integer endTimezone;
    private Integer mode;
    private Integer step;
    private Integer calorie;
    private String distance;
    private Integer state;
    private String graphValue;
    private Date createTime;
    private byte[] imageData;
    private String uuid;
    private byte[] sportData;
    private String deviceId;
    private Integer happenMonth;
    private Long duration;
    private String imageUrl;
    private Double kValue;
    private Double bValue;
    private Integer kbValidity;
    private Integer extraType;
    private Integer taskStatus;
}
