package com.yf.coros.common.entity.data.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

/**
 * 轨迹数据的查询条件
 *
 * @author Infi
 * @date 17/5/11
 */
@Data
public class TrackDto implements Serializable {

    private static final long serialVersionUID = -7698364868145311338L;
    private Integer month;
    private String trackId;
    private BigInteger trackUnsignedLong;
    private Integer size;
    private List<String> trackIdList;
    private List<Long> idList;
    /**
     * 查询方向 1 小于，2 大于
     */
    private Integer direction;
    /**
     * 是否包含本月， 1 包含，0 不包含
     */
    private Integer containsMonth;
    private Long id;
}
