package com.yf.coros.common.entity.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * 个人职业生涯统计
 *
 * @author Infi
 * @date 17/6/7
 */
@Data
public class CareerDataVO implements Serializable {

    private static final long serialVersionUID = -3026112410461166455L;
    private CareerWeekData weekData;
    private List<CareerSportData> sportDatas = new ArrayList<>();
    private List<UserMedal> medalDatas = new ArrayList<>();
}
