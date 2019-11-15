package com.yf.coros.common.entity.data;

import com.yf.coros.common.entity.data.statistics.daily.SportDataGroupModeCount;
import lombok.Data;
import org.apache.poi.ss.formula.functions.Count;

import java.io.Serializable;
import java.util.List;

/**
 * 月统计显示层对象
 * by max.hu
 * Created by hudahua on 2018/5/17.
 */
@Data
public class SportDataMonthVO implements Serializable {
    private static final long serialVersionUID = 1142892234549942619L;

    private Integer month;
    private int totalCount;
    //室内跑步运动次数
    private int indoorRunCount;
    //室外跑步运动次数
    private int outdoorRunCount;
    //室内游泳运动次数
    private int indoorSwimCount;
    //室外游泳运动次数
    private int outdoorSwimCount;
    //骑行运动次数
    private int indoorCycleCount;
    //骑行运动次数
    private int outdoorCycleCount;
    //铁三运动次数
    private int triathlonCount;
    //登山
    private int climbCount;
    //越野跑
    private int trailRunCount;
    //徒步
    private int hikeCount;
    //室内有氧运动
    private int indoorAerobicsCount;
    //室外有氧
    private int outdoorAerobicsCount;
    //越野滑雪（只有室外，sub_mode=1）
    private int skiCount;
    /**
     * 跑圈模式运动数据（只有室外，sub_mode=1）
     */
    private int trackRunCount;
    private int alpineSkiCount;

    private List<SportDataGroupModeCount> sportDataCountList;
}
