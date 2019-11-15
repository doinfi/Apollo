package com.yf.coros.common.entity.data;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户目标值记录
 *
 * @author Infi
 * @date 17/3/23
 */
@Data
public class DailyTarget implements Serializable {

    private static final long serialVersionUID = 421161596627691749L;
    private Long id;
    private Long userId;
    private Integer targetType;
    private Integer targetValue;
    private Integer happenDate;
    private Date createDate;
    private Integer status;
}
