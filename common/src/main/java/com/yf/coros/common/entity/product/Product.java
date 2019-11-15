package com.yf.coros.common.entity.product;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.support.spring.annotation.FastJsonFilter;
import com.alibaba.fastjson.support.spring.annotation.FastJsonView;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 产线入库的产品信息
 *
 * @author Infi
 */
@Data
public class Product implements Serializable {
    private static final long serialVersionUID = 4021588289548963281L;
    @JSONField(alternateNames = "jobnumber")
    private String jobNumber;
    @JSONField(alternateNames = "ordernumber")
    private String orderNumber;
    @JSONField(alternateNames = "productcode")
    private String productCode;
    @JSONField(alternateNames = "clientcode")
    private String clientCode;
    @JSONField(alternateNames = "retroid")
    private String retroId;
    @JSONField(alternateNames = "baseline")
    private String baseLine;
    private String uuid;
    private String deviceId;
    private String deviceMac;
    private String deviceUuid;
    @JSONField(alternateNames = "BaseLineBatch")
    private String baseLineBatch;
    @JSONField(alternateNames = "BatteryBatch")
    private String batteryBatch;
    @JSONField(alternateNames = "createtime")
    private Date createTime;
    @JSONField(alternateNames = "modifytime")
    private Date modifyTime;
    @JSONField(alternateNames = "Channel")
    private Integer channel;
    private String phase;
    private Date createTimeInCoros;
    private Date updateTimeInCoros;
    @JSONField(alternateNames = "MachineType")
    private String machineType;
    private String firmwareType;
    private Long updateTimestamp;
}
