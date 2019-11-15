package com.yf.coros.common.entity.data;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 运动数据mode与subMode的集合
 * @author lihuaijin
 */
@Data
public class SportDataModePair implements Serializable {
    private static final long serialVersionUID = 1330163098812406399L;
    private Integer mode;
    private List<Integer> subModeList;
}
