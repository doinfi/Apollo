package com.yf.coros.common.entity.device;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author lihuaijin
 */
@Data
public class DeviceActivateCriteria implements Serializable {

    private static final long serialVersionUID = -447223123918655291L;

    private String deviceId;
    private String firmwareType;
    private List<Integer> channelList;
    private Date beginDate;
    private Date endDate;
}
