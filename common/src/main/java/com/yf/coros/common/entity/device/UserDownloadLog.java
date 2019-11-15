package com.yf.coros.common.entity.device;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户下载固件记录实体类
 *
 * @author Infi
 * @date 17/6/28
 */
@Data
public class UserDownloadLog implements Serializable {

    private static final long serialVersionUID = 1923240714218857292L;
    private Long id;
    private Long userId;
    private String appVersion;
    private Byte status;
    private Date createTime;
}
