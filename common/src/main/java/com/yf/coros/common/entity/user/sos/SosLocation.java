package com.yf.coros.common.entity.user.sos;

import java.io.Serializable;
import lombok.Data;

/**
 * sos地理位置信息
 *
 * @author Infi
 */
@Data
public class SosLocation implements Serializable {

    private static final long serialVersionUID = 22558077862588300L;
    private Long userId;
    private Double longitude;
    private Double latitude;
    private Integer timestamp;
    private Integer timezone;
}
