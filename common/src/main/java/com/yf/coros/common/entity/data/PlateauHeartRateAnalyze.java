package com.yf.coros.common.entity.data;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 高原海拔心率
 *
 * @author Infi
 */
@Data
public class PlateauHeartRateAnalyze implements Serializable {
    private static final long serialVersionUID = 6896146860965845199L;
    private Date startDate;
    private Date endDate;
    private Long userId;
    private Integer happenDay;
    private Integer happenDate;
    private Date date;
    private Integer heartRateSize;
    private Integer heartRateCount;
    private String downloadUrl;
    private String email;
    private String userIdStr;
    private Date createTime;
    private Date updateTime;
}
