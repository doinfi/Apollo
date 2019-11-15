package com.yf.coros.common.entity.data;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 运动数据对象，对应t_run,t_cycle,t_swim表
 *
 * @author Infi
 * @date 17/5/9 标签对象
 */
@Data
public class LabelData implements Serializable {

    private static final long serialVersionUID = 2792814737862550610L;

    private Long labelId;
    private Long userId;
    private Integer happenDay;
    private Long startTime;
    private Long endTime;
    private Integer startTimezone;
    private Integer endTimezone;
    private Integer mode;
    private Integer step = 0;
    private Integer calorie = 0;
    private Double distance = 0.0;
    private Double distanceModify;
    private Integer state;
    private String graphValue;
    private Date createTime;
    private byte[] imageData;
    private String sportFileName;
    private String uuid;
    private byte[] sportData;
    private String deviceId;
    private Integer happenMonth;
    private String imageDataStr;
    private Long duration = 0L;
    private Integer laps = 0;
    private Integer subMode;
    private String imageUrl;
    private Integer happenWeek;
    private Integer happenYear;
    private Integer avgCadence = 0;
    private List<UserMedal> medalList;
    private Integer count;
    /**
     * 为了兼容以前版本
     */
    private Integer happenDate;
    private Integer unit;
    /**
     * 用于区分铁三的单项跑步、骑行、游泳数据被用户手动开启的跑步、骑行、游泳数据
     */
    private Integer parentMode;
    private Integer avgPace;
    private Integer avgSpeed;

    /**
     * gomore标志，0：无gomore指标，1：含gomore指标
     */
    private Integer extraType;

    /**
     * gomore任务状态标志，仅在extraType为1时有意义，0、初始状态，1、任务执行中，2、任务失败，100、完成
     */
    private Integer taskStatus;
    /**
     * 运动数据自定义名称，比如"广州晨跑"
     */
    private String name;
    @JSONField(serialize = false)
    private Date updateTime;
    private Long modifyTime;
    private Integer totalElevation = 0;
    private Integer totalDecline = 0;
    private Integer index;
    private String fitUrl;
    private Integer avgHeartRate;
    /**
     * 数据来源，1:佳明
     */
    private Integer source;
    /**
     * 是否已经上传过缩率图
     */
    private Integer uploadedImageData;
    private Date fitCreateTime;
}
