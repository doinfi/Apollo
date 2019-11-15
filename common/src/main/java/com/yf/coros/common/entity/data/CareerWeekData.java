package com.yf.coros.common.entity.data;

import java.io.Serializable;
import lombok.Data;

/**
 * 个人职业生涯周统计
 *
 * @author Infi
 * @date 17/6/7
 */
@Data
public class CareerWeekData implements Serializable {

    private static final long serialVersionUID = -9205136201699054126L;
    private int weekCalorie;
    private int weekCalorieTarget;
}
