package com.yf.coros.common.entity.user.profile;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 算法配置数据库对应字段
 * @author Infi
 */
@Data
public class AlgorithmProfile implements Serializable {

    private static final long serialVersionUID = 5059940729046989267L;
    private Long userId;
    private Integer strideLengthHigh;
    private Integer strideLengthLow;
    private Integer strideLengthFreestyle;
    private Integer strideLengthBreaststroket;
    private Integer strideLengthButterfly;
    private Integer strideLengthBackstroke;
    private Integer strideLengthMix;
}
