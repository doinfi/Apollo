package com.dubbo.demo.logs.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 概述:日志查询条件实体对象<br>
 * 数据传输层DTO<br>
 * 用户系统管理后台查询日志信息<br>
 * Created by Infi on 17/2/20.<br>
 */
public class LogCriteriaEntity implements Serializable {
    private static final long serialVersionUID = 123941495579796549L;
    private Integer userId;
    private Date startTime;
    private Date endTime;

    /**
     * 获取用户userId
     *
     * @return userId 用户userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户userId
     *
     * @param userId 用户userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取查询条件,开始时间
     *
     * @return startTime 查询条件,开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置查询条件,开始时间
     *
     * @param startTime 查询条件,开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取查询条件,结束时间
     *
     * @return endTime 查询条件,结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置查询条件,结束时间
     *
     * @param endTime 查询条件,结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
