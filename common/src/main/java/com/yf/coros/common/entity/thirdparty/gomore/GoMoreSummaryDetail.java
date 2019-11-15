package com.yf.coros.common.entity.thirdparty.gomore;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author lihuaijin
 */
@Data
public class GoMoreSummaryDetail implements Serializable {

    private static final long serialVersionUID = 1418296091831633162L;

    /**
     * run(跑步) or cycle(骑行)
     */
    @JSONField(alternateNames = "type_id")
    private String sportType;

    @JSONField(alternateNames = "summary_file_stamina_level")
    private String staminaFileUrl;

    /**
     * 跑步才有
     */
    @JSONField(alternateNames = "summary_file_pace")
    private String paceFileUrl;

    @JSONField(alternateNames = "summary_stamina_level")
    private List<String> staminaList;

    @JSONField(alternateNames = "summary_vo2max")
    private List<String> vo2maxList;

    /**
     * 乳酸阈值
     */
    @JSONField(alternateNames = "summary_lthr2")
    private List<String> lthr2List;

    /**
     * 乳酸阈值变化速度？（speed of lthr2）
     */
    @JSONField(alternateNames = "summary_ltsp2")
    private List<String> ltsp2List;

    /**
     * 骑行才有
     */
    @JSONField(alternateNames = "summary_ftp_power")
    private List<String> ftpPowerList;
}
