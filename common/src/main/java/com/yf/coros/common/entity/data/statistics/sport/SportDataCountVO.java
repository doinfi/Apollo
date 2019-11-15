package com.yf.coros.common.entity.data.statistics.sport;

import com.yf.coros.common.entity.data.SportData;
import com.yf.coros.common.entity.data.statistics.daily.SportDataCount;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Infi
 */
@Data
public class SportDataCountVO implements Serializable {
    private static final long serialVersionUID = 1003612252492866164L;
    private Integer firstHappenDay;
    private List<Statistic2SportData> statisticList;
}
