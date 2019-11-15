package com.yf.coros.common.entity.device;

import java.io.Serializable;
import lombok.Data;

/**
 * 固件版本信息多语言对象
 *
 * @author Infi
 * @date 17/7/28
 */
@Data
public class FirmwareContentLanguage implements Serializable {

    private static final long serialVersionUID = -7655805795690203337L;
    private String content;
    private String language;
}
