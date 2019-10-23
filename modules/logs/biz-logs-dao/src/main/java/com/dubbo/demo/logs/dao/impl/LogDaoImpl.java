package com.dubbo.demo.logs.dao.impl;

import com.dubbo.demo.logs.dao.LogDao;
import com.dubbo.demo.logs.dao.mapper.LogMapperService;
import com.dubbo.demo.logs.dto.LogDTO;
import com.dubbo.demo.logs.dto.LogFileDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 概述: dao接口实现层<br>
 * 背景: 为了把mybatis新增以后产生的自增长ID返回到service层<br>
 * Created by Infi on 17/3/15.
 */
@Service("logDao")
public class LogDaoImpl implements LogDao {
    @Autowired
    private LogMapperService logMapperService;

    @Override
    public LogDTO addOperateLog(LogDTO logDTO) {
        logMapperService.addOperateLog(logDTO);
        return logDTO;
    }

    @Override
    public List<LogDTO> findOperateLogs(@Param("userId") int userId, @Param("startTime") Date startTime, @Param("endTime") Date endTime) {
        return logMapperService.findOperateLogs(userId, startTime, endTime);
    }

    @Override
    public LogDTO addErrorLog(LogDTO logDTO) {
        logMapperService.addErrorLog(logDTO);
        return logDTO;
    }

    @Override
    public List<LogDTO> findErrorLogs(@Param("userId") int userId, @Param("startTime") Date startTime, @Param("endTime") Date endTime) {
        return logMapperService.findErrorLogs(userId, startTime, endTime);
    }

    @Override
    public List<LogDTO> findLogFilesByLogId(@Param("logId") Long logId, @Param("logType") Integer logType) {
        return logMapperService.findLogFilesByLogId(logId, logType);
    }

    @Override
    public void addLogFiles(@Param("list") List<LogFileDTO> logFiles) {
        logMapperService.addLogFiles(logFiles);
    }
}
