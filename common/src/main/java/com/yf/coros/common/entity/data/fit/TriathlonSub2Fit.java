package com.yf.coros.common.entity.data.fit;

import lombok.Data;

import java.io.Serializable;

/**
 * 组合运动子运动对应的sportData
 *
 * @author Infi
 */
@Data
public class TriathlonSub2Fit implements Serializable {
    private static final long serialVersionUID = 9220528240363611110L;
    private Long labelId;
    private Long userId;
    private Integer mode;
    private Integer index;
    private byte[] sportData;
}
