package com.yf.coros.common.entity.data.analyse;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.yf.coros.common.annotation.ExportInfo;
import com.yf.coros.common.entity.data.analyse.json.DoubleFormatScaleSerializer;
import com.yf.coros.common.entity.thirdparty.gomore.json.DateTimeCSTSerializer;
import lombok.Data;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Infi
 * @date 17/5/9 标签对象
 */
@Data
public class LabelDataVO implements Serializable {

    private static final long serialVersionUID = -1731041607020556589L;
    @JSONField(serializeUsing = ToStringSerializer.class)
    @ExportInfo(name = "运动ID", order = 1)
    private Long labelId;

    @ExportInfo(name = "运动类型", order = 3)
    private Integer mode;

    @ExportInfo(name = "子运动类型", order = 4)
    private Integer subMode;

    @JSONField(serializeUsing = ToStringSerializer.class)
    @ExportInfo(name = "用户ID", order = 0)
    private Long userId;

    private Integer happenDay;

    private Integer happenMonth;

    @JSONField(serializeUsing = DateTimeCSTSerializer.class)
    private Long startTime;

    @ExportInfo(name = "运动开始 - 当地时间", order = 5)
    private String startTimeLocal;

    @JSONField(serializeUsing = DateTimeCSTSerializer.class)
    private Long endTime;

    @ExportInfo(name = "时区", order = 6)
    private Integer startTimezone;

    private Integer endTimezone;

    private Integer step;

    @ExportInfo(name = "卡路里", order = 9)
    private Integer calorie;

    @JSONField(serializeUsing = DoubleFormatScaleSerializer.class)
    @ExportInfo(name = "运动距离", order = 7)
    private Double distance;

    private String imageUrl;

    @ExportInfo(name = "设备", order = 2)
    private String deviceId;

    @ExportInfo(name = "运动时长", order = 8)
    private Long duration;

    private Integer state;

    private Integer unit;

    @ExportInfo(name = "平均配速", order = 10)
    private Integer avgPace;

    private Integer avgSpeed;

    private Integer extraType;

    private Integer taskStatus;

    private String name;

    private String distanceModify;

    @JSONField(serializeUsing = DateTimeCSTSerializer.class)
    private Date createTime;

    @JSONField(serializeUsing = DateTimeCSTSerializer.class)
    private Date updateTime;

    private Integer totalElevation;

    private Integer totalDecline;

    private Integer avgCadence;

    private String fitUrl;

    @ExportInfo(name = "平均心率", order = 10)
    private Integer avgHeartRate;

    @JSONField(serializeUsing = DateTimeCSTSerializer.class)
    private Date fitCreateTime;

    private Integer source;

    private Integer uploadedImageData;

    public static LinkedHashMap<String, String> getExportNameTitleMap() {

        Field[] fields = LabelDataVO.class.getDeclaredFields();
        Map<String, String> fieldExportNameMap = new HashMap<>();
        TreeMap<Integer, String> orderNameMap = new TreeMap<>();
        for (Field field : fields) {
            if (field.isAnnotationPresent(ExportInfo.class)) {
                ExportInfo exportInfo = field.getAnnotation(ExportInfo.class);
                orderNameMap.put(exportInfo.order(), field.getName());
                fieldExportNameMap.put(field.getName(), exportInfo.name());
            }
        }
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        orderNameMap.values().forEach(k -> map.put(k, fieldExportNameMap.get(k)));
        return map;
    }

}
