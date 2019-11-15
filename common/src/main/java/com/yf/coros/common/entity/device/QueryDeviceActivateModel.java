package com.yf.coros.common.entity.device;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 查询设备激活的条件模型
 */
@Data
public class QueryDeviceActivateModel implements Serializable {


    private static final long serialVersionUID = -768891574649048798L;
    private String account;
    private Long userId;
    private String uuid;
    private String deviceId;
    private Date beginDate;
    private Date endDate;


}
