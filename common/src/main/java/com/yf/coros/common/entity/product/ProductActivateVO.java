package com.yf.coros.common.entity.product;

import com.yf.coros.common.entity.device.DeviceActivate;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lihuaijin
 */
@Data
@NoArgsConstructor
public class ProductActivateVO implements Serializable {
    private String deviceId;
    private String firmwareType;
    private String retroId;
    private String uuid;
    private String baseLine;

    private String orderNumber;
    private String productCode;
    private String baseLineBatch;
    private String batteryBatch;
    private Date createTime;
    private Date modifyTime;
    private Integer channel;
    private String phase;

    private String userIdStr;
    private Date activateTime;
    private Date expiresTime;
    private Boolean otherRegionActivate;

    private List<DeviceActivate> deleteDeviceActivateList;

    public ProductActivateVO(Product product) {
        BeanUtils.copyProperties(product, this);
    }


    /**
     * 生成导出标题头
     * @param separator 字段分隔符
     * @return 标题头的行字符串
     */
    public static String generateExportTitleLine(String separator) {
        StringBuilder sb = new StringBuilder();

        sb.append("追溯ID").append(separator);
        sb.append("设备ID").append(separator);
        sb.append("设备类型").append(separator);
        sb.append("入库时间").append(separator);
        sb.append("激活时间").append(separator);
        sb.append("本区激活").append(separator);
        sb.append("更新时间").append(separator);
        sb.append("渠道号").append(separator);
        sb.append("渠道商名称").append(separator);
        sb.append("订单编号").append(separator);
        sb.append("电池批次").append(separator);
        sb.append("底壳批次").append(separator);
        sb.append("底壳料号").append(separator);
        return sb.toString();
    }

    /**
     * 生成导出数据
     * @param separator 字段分隔符
     * @return 数据行字符串
     */
    public String generateExportDataLine(String separator, Map<Integer, String> channelMap) {
        StringBuilder sb = new StringBuilder();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String activate = null == this.activateTime ? "未激活" : sdf.format(this.activateTime);

        sb.append(this.retroId).append(separator);
        sb.append(this.deviceId).append(separator);
        sb.append(null == this.firmwareType ? this.productCode : this.firmwareType).append(separator);
        sb.append(sdf.format(this.createTime)).append(separator);
        sb.append(activate).append(separator);
        sb.append("未激活".equals(activate) ? "未激活" : (null != this.otherRegionActivate && this.otherRegionActivate ? "另一区域激活" : "是")).append(separator);
        sb.append(sdf.format(this.modifyTime)).append(separator);
        sb.append(this.channel).append(separator);
        sb.append(channelMap.getOrDefault(this.channel, "")).append(separator);
        sb.append(this.orderNumber).append(separator);
        sb.append(this.batteryBatch).append(separator);
        sb.append(this.baseLineBatch).append(separator);
        sb.append(this.baseLine).append(separator);
        return sb.toString();
    }
}
