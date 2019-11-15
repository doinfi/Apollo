package com.yf.coros.common.entity.thirdparty.gomore;

import com.alibaba.fastjson.annotation.JSONField;
import com.yf.coros.common.entity.thirdparty.gomore.json.KiloDoubleSerializer;
import lombok.Data;

/**
 * @author lihuaijin
 */
@Data
public class GoMoreSportDataSlice {

    @JSONField(name = "sec")
    private Long second;

    @JSONField(name = "hr")
    private Integer heartrate;

    @JSONField(name = "pr")
    private Double power = -1D;

    @JSONField(name = "ca")
    private Double cadence = -1D;

    @JSONField(name = "td")
    private String timeDate;

    /**
     * gm要km单位的
     */
    @JSONField(name = "dist", serializeUsing = KiloDoubleSerializer.class)
    private Long distance;

    /**
     * 单位为m/s
     */
    @JSONField(name = "sp")
    private Double speed;

    @JSONField(name = "al")
    private Double altitude;

    @JSONField(name = "lo")
    private Double longitude;

    @JSONField(name = "la")
    private Double latitude;

    @JSONField(name = "inc")
    private Double incline;

}
