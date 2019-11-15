package com.yf.coros.common.entity.data.fit;

import jdk.net.SocketFlow;
import lombok.Data;

import java.util.Date;

/**
 * fit文件创建任务对象
 * @author Infi
 */
@Data
public class FitCreateTask {
    private Long labelId;
    private Integer mode;
    private Integer workId;
    private Integer status;
    private Date createTime;
    private Date updateTime;
}
