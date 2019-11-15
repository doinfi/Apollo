package com.yf.coros.common.entity.data;

import java.io.Serializable;
import lombok.Data;

/**
 * 个人职业生涯运动数据统计模板
 *
 * @author Infi
 * @date 17/6/7
 */
@Data
public class CareerSportData implements Serializable {

    private static final long serialVersionUID = 725610059703208774L;
    private Integer mode;
    private double distance;
}
