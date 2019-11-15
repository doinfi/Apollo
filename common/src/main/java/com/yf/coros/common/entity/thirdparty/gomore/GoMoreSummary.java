package com.yf.coros.common.entity.thirdparty.gomore;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author lihuaijin
 */
@Data
public class GoMoreSummary implements Serializable {

    private static final long serialVersionUID = 1074466736236085728L;

    @JSONField(alternateNames = "hrzone_ptc")
    private Map<String, Integer> heartRateZone;

    @JSONField(alternateNames = "data")
    private List<GoMoreSummaryDetail> summary;
}
