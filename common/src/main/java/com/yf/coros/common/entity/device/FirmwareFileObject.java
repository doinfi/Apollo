package com.yf.coros.common.entity.device;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 固件和资源文件信息类
 *
 * @author Infi
 * @date 17/6/29
 */
@Data
@AllArgsConstructor
public class FirmwareFileObject implements Serializable {

    private static final long serialVersionUID = -8779207724170848815L;
    private String fileUrl;
    private Integer fileMd5;
    private Integer fileSize;
    private String version;
    private Integer type;

}
