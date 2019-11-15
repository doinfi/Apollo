package com.yf.coros.common.entity.data;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 运动数据mode与subMode的集合
 *
 * @author lihuaijin
 */
@Data
public class SportDataMode implements Serializable {
    private static final long serialVersionUID = 6107509233953940815L;
    @JSONField(name = "type")
    private Integer mode;
    @JSONField(name = "mode")
    private Integer subMode;
}
