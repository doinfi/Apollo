package com.yf.coros.common.entity.device;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 固件包含文件信息
 *
 * @author Infi
 * @date 17/7/28
 */
@Data
public class FirmwareFile implements Serializable {
    private static final long serialVersionUID = 5452996892763741213L;
    @JSONField(serialize = false)
    private Long firmwareId;
    private Integer type;
    private String fileUrl;
    private Integer fileCRC;
    private Integer fileSize;
    private byte[] fileBytes;
    private String fileName;
}
