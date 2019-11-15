package com.yf.coros.common.entity.data;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 标签状态实体
 *
 * @author Infi
 * @date 17/6/1
 */
@Data
public class LabelStatus implements Serializable {

    private static final long serialVersionUID = -1149070375281655917L;
    private Long userId;
    private Long labelId;
    private Integer mode;
    private byte[] statusData;
    private Date createTime;
    private Long lastMinuteTimestamp;
    private String maxVo;
    private Date updateTime;
}
