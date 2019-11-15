package com.yf.coros.common.entity.data;

import java.io.Serializable;

import lombok.Data;

/**
 * 查询条件
 *
 * @author Infi
 * @date 17/6/1
 */
@Data
public class DailyDataCriteria implements Serializable {
    private static final long serialVersionUID = -6296318726396702563L;
    private Long userId;
    private Integer startDate;
    private Integer endDate;
}
