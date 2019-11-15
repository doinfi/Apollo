package com.yf.coros.common.entity.open;

import lombok.Getter;
import lombok.Setter;

/**
 * 官网fit文件导入功能返回的VO
 *
 * @author dinghui
 * @date 2019/7/5 14:21
 */
@Getter
@Setter
public class ActivityExtVO extends ActivityVO {
    //所属日期
    private Integer happenDay;
}
