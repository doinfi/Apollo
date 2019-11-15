package com.yf.coros.common.entity.data;

import java.io.Serializable;
import lombok.Data;

/**
 * Coros运动数据结构,包括sportData和imageData
 *
 * @author Infi
 * @date 17/5/10
 */
@Data
public class SportDataImageData implements Serializable {

    private static final long serialVersionUID = -7385647374583495180L;
    private String uuid;
    private byte[] data;
    private Integer mode;
    private Integer dataLength;
}
