package com.yf.coros.common.entity.data.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 修改运动数据名称dto
 * @author Infi
 */
@Data
public class SportDataUpdateDto implements Serializable {
    private static final long serialVersionUID = -4247377925637248703L;
    private List<SportDataDto> labelList;
}
