package com.yf.coros.common.entity.data;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserExtend implements Serializable {

    private static final long serialVersionUID = 1376364193098032649L;

    private Long userId;
    private Integer maxVo;
    private Long maxVoTimestamp;
    private Integer maxVoTimezone;
    private Date updateTime;
    private Integer maxVoPrev;
    private Long maxVoTimestampPrev;
}
