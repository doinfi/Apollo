package com.yf.coros.common.entity.data;

import lombok.Data;

import java.util.Date;

/**
 * 用户活跃记录表，存储用户最近一次运动记录
 *
 * @author dinghui
 * @date 2019/9/5 14:38
 */
@Data
public class UserActive {
    Long userId; // userId#主键,用户编号,
    Long labelId; // labelId#主键,使用分布是主键生成器生成,
    Integer happenDay; // happenDay#所属日期,
    Integer happenMonth; // happenMonth#标签属于那一个月,
    Integer mode; // mode#标签类型:2:睡眠,8:跑步训练,9:骑行,10:游泳，13：铁人三项，14：登山，15：越野跑，16：徒步，17：滑雪，18：有氧运动。其他类型不存储,
    Integer subMode; // subMode#子类型，1：室外跑步，2：室内跑步。1：公开水域游泳，2：室内游泳。90：头盔骑行,
    String deviceType;// deviceType#设备类型,如B13,B15
    String deviceId; // deviceId#设备类型加上蓝牙名称，用来判断用户是否重复提交运动数据,
    Date createTime; // createTime#创建日期,
    Date updateTime; // 修改时间,
}
