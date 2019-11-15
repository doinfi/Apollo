package com.yf.coros.common.entity.device.appversion;

import lombok.Data;

import java.io.Serializable;


/**
 * APP版本说明
 *
 * @author Infi
 */
@Data
public class AppVersionDetail implements Serializable {
    private static final long serialVersionUID = -5875668834231620909L;
    private Long id;
    private String language;
    private String detail;
    private Integer isDefault;
}
