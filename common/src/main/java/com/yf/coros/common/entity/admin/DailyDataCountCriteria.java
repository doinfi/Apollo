package com.yf.coros.common.entity.admin;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 管理后台日常统计数据查询
 *
 * @author Infi
 */
@Data
public class DailyDataCountCriteria implements Serializable {
    private static final long serialVersionUID = 1213136488297755655L;
    private String deviceId;
    private String firmwareType;
    private Long userId;
    private String email;
    private Date startDate;
    private Date endDate;
}
