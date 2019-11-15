package com.yf.coros.common.entity.user.profile;

import java.io.Serializable;
import lombok.Data;

/**
 * 配置项参数 TODO 产品定义延期
 * @author Infi
 */
@Data
public class ProfileVO implements Serializable {

    private static final long serialVersionUID = 5320621874687349289L;
    private AlgorithmProfileVO algorithmProfile;
    private DeviceProfile deviceProfile;

}
