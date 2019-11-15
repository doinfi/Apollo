package com.yf.coros.common.entity.data;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * 运动数据的查询条件
 *
 * @author Infi
 * @date 17/5/11
 */
@Data
public class SportDataDtoWillDelete implements Serializable {

    private static final long serialVersionUID = -6870749375368189869L;
    private Integer month;
    private Integer mode;
    private Long labelId;
    private Integer subMode;
    private Integer size;
    private List<Long> labelIdList;

    //TODO by max.hu 2018-5-15
    private Integer direction;//查询方向 1 小于，2 大于
    private Integer containsMonth;//是否包含本月， 1 包含，0 不包含

}
