package com.yf.coros.common.entity.open.fitpush;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * fit文件推送任务类
 *
 * @author Infi
 */
@Data
public class FitPushTask implements Serializable {
    private static final long serialVersionUID = 8370900478222937992L;
    private Integer openType;
    private Long labelId;
    private Integer mode;
    private Integer subMode;
    private Integer subIndex;
    private Integer workerId;
    private Integer status;
    private Date createTime;
    private Date updateTime;
    private String fitUrl;
    private Long userId;
    private String sportName;
}
