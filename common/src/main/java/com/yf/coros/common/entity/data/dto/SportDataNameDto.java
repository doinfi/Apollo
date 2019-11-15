package com.yf.coros.common.entity.data.dto;

import com.yf.coros.common.entity.data.LabelData;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 修改运动数据名称dto
 * @author Infi
 */
@Data
public class SportDataNameDto implements Serializable {
    private static final long serialVersionUID = -8786956913574657407L;
    private List<LabelData> labelList;
}
