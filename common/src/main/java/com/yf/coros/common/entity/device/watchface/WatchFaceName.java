package com.yf.coros.common.entity.device.watchface;

import lombok.Data;

import java.io.Serializable;

/**
 * 表盘名称对象
 *
 * @author Infi
 */
@Data
public class WatchFaceName implements Serializable {
    private static final long serialVersionUID = -4347079692713325500L;
    private Long id;
    private String language;
    private String watchFaceName;
    private Integer isDefault;
}
