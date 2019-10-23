package com.dubbo.demo.logs.vo;

import com.dubbo.demo.common.vo.ResponseVO;
import com.dubbo.demo.common.vo.StatusResultEnum;

import java.util.List;

/**
 * 概述: 日志返回VO<br>
 * 背景: <br>
 * Created by Infi on 17/3/15.
 */
public class LogResponseVO extends ResponseVO {
    private static final long serialVersionUID = 4629856388428247576L;
    private List<LogVO> logs;

    public LogResponseVO(StatusResultEnum result) {
        super(result);
    }


    /**
     * 获取日志列表
     *
     * @return logs 日志列表
     */
    public List<LogVO> getLogs() {
        return logs;
    }

    /**
     * 设置日志列表
     *
     * @param logs 日志列表
     */
    public void setLogs(List<LogVO> logs) {
        this.logs = logs;
    }
}
