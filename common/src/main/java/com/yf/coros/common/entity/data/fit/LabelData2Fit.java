package com.yf.coros.common.entity.data.fit;

import com.alibaba.fastjson.annotation.JSONField;
import com.yf.coros.common.entity.data.UserMedal;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 运动数据对应的sportData
 *
 * @author Infi
 */
@Data
public class LabelData2Fit implements Serializable {
    private static final long serialVersionUID = -5562949825398917266L;
    private Long labelId;
//    private Long userId;
    private Integer mode;
    private byte[] sportData;
    private String fitUrl;
    private Integer status;
    private List<TriathlonSub2Fit> triathlonSub2FitList;
}
