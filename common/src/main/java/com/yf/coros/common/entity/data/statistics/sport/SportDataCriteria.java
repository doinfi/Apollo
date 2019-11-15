package com.yf.coros.common.entity.data.statistics.sport;

import lombok.Data;

import java.io.Serializable;

/**
 * 运动数据查询条件
 *
 * @author Infi
 */
@Data
public class SportDataCriteria implements Serializable {
    private static final long serialVersionUID = -7325160838230685409L;
    private Long labelId;
    private Integer mode;
}
