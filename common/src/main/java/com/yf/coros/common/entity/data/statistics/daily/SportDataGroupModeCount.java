package com.yf.coros.common.entity.data.statistics.daily;

import lombok.Data;

import java.io.Serializable;

/**
 * 运动数据个数统计
 *
 * @author Infi
 */
@Data
public class SportDataGroupModeCount implements Serializable {
    private static final long serialVersionUID = 3451932879546344870L;
    private Integer mode;
    private Integer subMode;
    private Integer count;
}
