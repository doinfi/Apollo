package com.yf.coros.common.entity.user.profile;

import java.io.Serializable;
import lombok.Data;

/**
 * 设备配置，五月份的需求，还没设计完成 TODO 产品定义延期
 *
 * @author Infi
 */
@Data
public class DeviceProfile implements Serializable {

    private static final long serialVersionUID = -1999300062358517604L;
    private Long userId;
    private Integer master;
    private String backlight;

}
