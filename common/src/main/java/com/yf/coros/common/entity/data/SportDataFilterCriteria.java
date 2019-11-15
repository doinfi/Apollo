package com.yf.coros.common.entity.data;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 运动数据查询过滤实体
 *
 * @author lihuaijin
 */
@Data
public class SportDataFilterCriteria implements Serializable {
    private static final long serialVersionUID = -5616731871148407064L;
    @JSONField(name = "modeFilter")
    private List<SportDataModePair> modePairList;
    private List<SportDataMode> modeList;
}
