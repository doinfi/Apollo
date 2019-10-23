package com.dubbo.demo;

import com.alibaba.fastjson.JSON;
import com.dubbo.demo.common.vo.ModuleTypeEnum;
import com.dubbo.demo.logs.dao.mapper.LogMapperService;
import com.dubbo.demo.logs.dto.LogDTO;
import com.dubbo.demo.logs.dto.LogFileDTO;
import com.dubbo.demo.logs.enums.LogLevelEnum;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 概述:日志模块dao层junit类<br>
 * 背景:<br>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class LogDaoJunitTest {
    private static final Logger logger = LoggerFactory.getLogger(LogDaoJunitTest.class);

    @Autowired
    private LogMapperService logMapperService;


    @Test
    public void test() {
        synchronized (LogDaoJunitTest.class) {
            while (true) {
                try {
                    LogDaoJunitTest.class.wait();
                } catch (InterruptedException e) {
                }
            }
        }
    }

    @Test
    public void addLogFiles() {
        List<LogFileDTO> logFileDTOs = new ArrayList<LogFileDTO>();
        LogFileDTO logFileDTO = new LogFileDTO();
        logFileDTO.setLogId(new Long("123"));
        logFileDTO.setLogType(1);
        logFileDTO.setFileUrl("/usl/file.log");
        logFileDTOs.add(logFileDTO);
        LogFileDTO logFileDTO2 = new LogFileDTO();
        logFileDTO2.setLogId(new Long("123"));
        logFileDTO2.setLogType(1);
        logFileDTO2.setFileUrl("/usl/file2.log");
        logFileDTOs.add(logFileDTO2);
        logMapperService.addLogFiles(logFileDTOs);
    }

    @Test
    public void addOperateLog() {
        LogDTO logDTO = new LogDTO();
        logDTO.setUserId(1);
        logDTO.setModuleType(NumberUtils.toInt(ModuleTypeEnum.ACCESS_LAYER.getValue()));
        logDTO.setOperateCode("888999");
        logDTO.setOperateContent("ota上传");
        logDTO.setOperateDesc("上传一个ota文件");
        logDTO.setOperateTime(new Date());
        logDTO.setIpAddress("193.23.11.44");
        List<String> fileUrls = new ArrayList<String>();
        fileUrls.add("/usl/file4.log");
        fileUrls.add("/usl/file5.log");
        logDTO.setFileUrlList(fileUrls);
        logMapperService.addOperateLog(logDTO);
        System.out.println(JSON.toJSONString(logDTO));
    }

    @Test
    public void addErrorLog() {
        LogDTO logDTO = new LogDTO();
        logDTO.setUserId(2);
        logDTO.setLogLevel(LogLevelEnum.ERROR.getValue());
        logDTO.setModuleType(NumberUtils.toInt(ModuleTypeEnum.MOTION_STAT.getValue()));
        logDTO.setOperateCode("30");
        logDTO.setOperateContent("ota上传");
        logDTO.setOperateDesc("上传一个ota文件");
        logDTO.setParamJson("json参数");
        logDTO.setExceptionInfo("空指针");
        logDTO.setOperateTime(new Date());
        logDTO.setIpAddress("193.23.11.44");
        List<String> fileUrls = new ArrayList<String>();
        fileUrls.add("/usl/file6.log");
        fileUrls.add("/usl/file7.log");
        logDTO.setFileUrlList(fileUrls);
        logMapperService.addErrorLog(logDTO);
    }

    @Test
    public void findOperateLog() {
        List<LogDTO> logDTOList = logMapperService.findOperateLogs(1, DateUtils.addDays(new Date(), -2), new Date());
        logger.info("操作日志:" + JSON.toJSONString(logDTOList));
    }

    @Test
    public void findErrorLog() {
        List<LogDTO> logDTOList = logMapperService.findErrorLogs(2, DateUtils.addDays(new Date(), -2), new Date());
        logger.info("错误日志:" + JSON.toJSONString(logDTOList));
    }

    @Test
    public void test1() {
        LogDTO logDTO = new LogDTO();
        logDTO.setUserId(2);
        logDTO.setLogLevel(LogLevelEnum.ERROR.getValue());
        logDTO.setModuleType(NumberUtils.toInt(ModuleTypeEnum.ACCESS_LAYER.getValue()));
        logDTO.setOperateCode("30");
        logDTO.setOperateContent("ota上传");
        logDTO.setOperateDesc("上传一个ota文件");
        logDTO.setParamJson("json参数");
        logDTO.setExceptionInfo("空指针");
        logDTO.setOperateTime(new Date());
        logDTO.setIpAddress("193.23.11.44");
        List<String> fileUrls = new ArrayList<String>();
        fileUrls.add("/usl/file3.log");
        fileUrls.add("/usl/file4.log");
        logDTO.setFileUrlList(fileUrls);
        logger.info(JSON.toJSONString(logDTO));
    }

}
