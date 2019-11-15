package com.yf.coros.common.entity.data.statistics.sport;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 运动数据查询条件
 *
 * @author Infi
 */
@Data
public class SportCountCriteria implements Serializable {
    private static final long serialVersionUID = -6345106990094540670L;
    private Integer statisticType;
    private Integer startTime;
    private Integer endTime;
}
