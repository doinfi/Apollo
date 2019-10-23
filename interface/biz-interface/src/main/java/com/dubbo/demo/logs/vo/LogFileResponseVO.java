package com.dubbo.demo.logs.vo;

import com.dubbo.demo.common.vo.ResponseVO;
import com.dubbo.demo.common.vo.StatusResultEnum;

import java.util.List;

/**
 * 概述: 日志文件地址返回VO<br>
 * 背景: <br>
 * Created by Infi on 17/3/15.
 */
public class LogFileResponseVO extends ResponseVO {
    private static final long serialVersionUID = 6187130515599173452L;
    private List<LogFileVO> logFiles;

    public LogFileResponseVO(StatusResultEnum result) {
        super(result);
    }

    /**
     * 获取日志文件地址列表
     *
     * @return logFiles 日志文件地址列表
     */
    public List<LogFileVO> getLogFiles() {
        return logFiles;
    }

    /**
     * 设置日志文件地址列表
     *
     * @param logFiles 日志文件地址列表
     */
    public void setLogFiles(List<LogFileVO> logFiles) {
        this.logFiles = logFiles;
    }
}
