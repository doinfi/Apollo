package com.yf.coros.common.entity.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * 日常数据返回类
 *
 * @author Infi
 * @date 17/4/1
 */
@Data
public class DailyDataVO implements Serializable {

    private static final long serialVersionUID = -8118157177245715756L;
    /**
     * 这个名称不能修改,接口返回的json是用该名称
     */
    private List<DailyData> dailyDatas = new ArrayList<>();
    private String saveParseInfo;
    private Integer rhr;
}
