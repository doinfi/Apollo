package com.yf.coros.common.entity.data.dto;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.yf.coros.common.entity.data.SportDataFilterCriteria;
import com.yf.coros.common.entity.data.dto.SubSportDto;
import lombok.Data;

/**
 * 运动数据的查询条件
 *
 * @author Infi
 * @date 17/5/11
 */
@Data
public class SportDataDto implements Serializable {

    private static final long serialVersionUID = -6870749375368189869L;
    private Integer month;
    private Integer mode;
    private Long labelId;
    private Integer subMode;
    private Integer size;
    private List<Long> labelIdList;
    /**
     * 查询方向 1 小于，2 大于
     */
    private Integer direction;
    /**
     * 是否包含本月， 1 包含，0 不包含
     */
    private Integer containsMonth;
    private String name;
    private Double distance;
    private List<SubSportDto> subDistanceList;
    private byte[] sportData;
    private byte[] sportDataModify;
    /**
     * 子运动序号，使用int类型，默认值为0
     */
    private int sportIndex;

    /**
     * 复合过滤器
     */
    @JSONField(name = "filter")
    private SportDataFilterCriteria sportDataFilterCriteria;
}
