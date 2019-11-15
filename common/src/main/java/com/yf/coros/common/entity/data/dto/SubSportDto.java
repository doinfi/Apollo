package com.yf.coros.common.entity.data.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 修改运动数据条件，子运动数据信息
 * @author Infi
 */
@Data
public class SubSportDto implements Serializable {
    private Integer index;
    private Double distance;
}
