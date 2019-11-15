package com.yf.coros.common.entity.user.profile;

import java.io.Serializable;
import lombok.Data;

/**
 * 算法配置vo TODO 产品定义延期
 *
 * @author Infi
 */
@Data
public class AlgorithmProfileVO implements Serializable {
    private static final long serialVersionUID = -8714576676570737363L;
    private Integer strokeLength;
    private Integer strideLength;
    private Integer vo2max;
    private Integer rhr;
}
