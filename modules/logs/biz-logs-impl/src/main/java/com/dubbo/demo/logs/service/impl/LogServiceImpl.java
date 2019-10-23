package com.dubbo.demo.logs.service.impl;

import com.alibaba.fastjson.JSON;
import com.dubbo.demo.common.vo.ResponseVO;
import com.dubbo.demo.common.vo.StatusResultEnum;
import com.dubbo.demo.core.service.DefaultBaseService;
import com.dubbo.demo.logs.dao.LogDao;
import com.dubbo.demo.logs.dto.LogDTO;
import com.dubbo.demo.logs.dto.LogFileDTO;
import com.dubbo.demo.logs.enums.LogLevelEnum;
import com.dubbo.demo.logs.service.LogService;
import com.dubbo.demo.logs.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.alibaba.fastjson.JSON.parseArray;

/**
 * 概述:日志模块属于公共服务接口,业务模块需要记录用户操作记录或者系统异常时需要记录错误信息,就要调用日志模块<br>
 * 背景:新框架设计,将真个系统按模块划分,日志模块也是独立出来的一个模块。<br>
 * 使用示例:使用dubbo框架注册并调用<br>
 * 跨模块相关:用户相关模块、运动统计模块、数据同步模块、表盘相关模块，固件相关模块<br>
 *
 * @author Infi
 */
@Service("logService")
public class LogServiceImpl extends DefaultBaseService implements LogService {
    private static final Logger log = LoggerFactory.getLogger(LogServiceImpl.class);

    @Autowired
    private LogDao logDao;


    @Override
    public ResponseVO saveLog(String jsonParam) {
        if (StringUtils.isBlank(jsonParam)) {
            return new ResponseVO(StatusResultEnum.PARAMETER_ERROR);
        }
        try {
            LogDTO logDTO = JSON.parseObject(jsonParam, LogDTO.class);

            // 2. 日志级别如果是日常操作,就保存到用户日常操作日志记录表
            LogDTO resultLogDTO = new LogDTO();
            if (logDTO.getLogLevel() == LogLevelEnum.OPERATE.getValue()) {
                resultLogDTO = logDao.addOperateLog(logDTO);

            } else {
                resultLogDTO = logDao.addErrorLog(logDTO);
            }
            // 3. 新增日志文件地址信息,包括原始数据文件等
            if (logDTO.getFileUrlList() != null && !logDTO.getFileUrlList().isEmpty()) {
                // 日志类型,1:表示用户操作日志,2:表示错误日志
                Integer logType = logDTO.getLogLevel() == LogLevelEnum.ERROR.getValue() ? 1 : 2;
                List<LogFileDTO> logFiles = new ArrayList<LogFileDTO>();
                for (String fileUrl : logDTO.getFileUrlList()) {
                    LogFileDTO logFile = new LogFileDTO();
                    logFile.setLogId(resultLogDTO.getId());
                    logFile.setLogType(logType);
                    logFile.setFileUrl(fileUrl);
                    logFiles.add(logFile);
                }
                logDao.addLogFiles(logFiles);
            }
            return new ResponseVO();
        } catch (Exception ex) {
            log.error("日志模块异常:" + ex.toString());
            return new ResponseVO(StatusResultEnum.LOG_SAVE_FAILED);
        }
    }

    @Override
    public LogResponseVO findOperateLogs(String jsonParam) {
        if (StringUtils.isBlank(jsonParam)) {
            return null;
        }
        LogCriteriaEntity logCriteriaDTO = JSON.parseObject(jsonParam, LogCriteriaEntity.class);
        List<LogDTO> logs = logDao.findOperateLogs(logCriteriaDTO.getUserId(), logCriteriaDTO.getStartTime(), logCriteriaDTO.getEndTime());
        String logJson = JSON.toJSONString(logs);
        List<LogVO> logVOs = JSON.parseArray(logJson, LogVO.class);
        LogResponseVO logResponseVO = new LogResponseVO(StatusResultEnum.OK);
        logResponseVO.setLogs(logVOs);
        return logResponseVO;
    }

    @Override
    public LogResponseVO findErrorLogs(String jsonParam) {
        if (StringUtils.isBlank(jsonParam)) {
            return null;
        }
        LogCriteriaEntity logCriteriaDTO = JSON.parseObject(jsonParam, LogCriteriaEntity.class);
        List<LogDTO> logs = logDao.findErrorLogs(logCriteriaDTO.getUserId(), logCriteriaDTO.getStartTime(), logCriteriaDTO.getEndTime());
        String logJson = JSON.toJSONString(logs);
        List<LogVO> logVOs = parseArray(logJson, LogVO.class);
        LogResponseVO logResponseVO = new LogResponseVO(StatusResultEnum.OK);
        logResponseVO.setLogs(logVOs);
        return logResponseVO;
    }

    @Override
    public LogFileResponseVO findLogFilesByLogId(String jsonParam) {
        LogFileVO logFileCriteria = JSON.parseObject(jsonParam, LogFileVO.class);
        List<LogDTO> logFiles = logDao.findLogFilesByLogId(logFileCriteria.getLogId(), logFileCriteria.getLogType());
        String logFilesJson = JSON.toJSONString(logFiles);
        List<LogFileVO> logFileVOs = parseArray(logFilesJson, LogFileVO.class);
        LogFileResponseVO logFileResponseVO = new LogFileResponseVO(StatusResultEnum.OK);
        logFileResponseVO.setLogFiles(logFileVOs);
        return logFileResponseVO;
    }

}
