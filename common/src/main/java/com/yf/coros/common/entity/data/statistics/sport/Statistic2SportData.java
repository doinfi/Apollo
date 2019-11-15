package com.yf.coros.common.entity.data.statistics.sport;
import com.yf.coros.common.entity.data.statistics.daily.SportDataCount;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 运动数据统计对象
 * @author Infi
 */
@Data
public class Statistic2SportData implements Serializable {
    private static final long serialVersionUID = 1923321049787674149L;
    private Integer happenDay;
    private Integer happenMonth;
    private List<SportDataCount> sportDataList;
}
