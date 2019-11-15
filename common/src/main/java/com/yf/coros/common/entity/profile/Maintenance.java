package com.yf.coros.common.entity.profile;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统维护实体类
 *
 * @author Infi
 */
@Data
public class Maintenance implements Serializable {
    private static final long serialVersionUID = 1793125144615889319L;
    private Long id;
    private Long startTime;
    private Long endTime;
    private Integer status;
    private String creator;
    private Date createTime;
    private Date updateTime;
    private String remarks;
    private String idStr;
    private Date startDateTime;
    private Date endDateTime;
}
