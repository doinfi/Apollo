package com.dubbo.demo.sample.test;

import com.alibaba.fastjson.JSON;
import com.dubbo.demo.common.exception.RpcException;
import com.dubbo.demo.common.vo.ModuleTypeEnum;
import com.dubbo.demo.logs.dto.LogDTO;
import com.dubbo.demo.logs.enums.LogLevelEnum;
import com.dubbo.demo.logs.operate.OperateCodes;
import com.dubbo.demo.logs.service.LogService;
import com.dubbo.demo.logs.vo.LogCriteriaEntity;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-web.xml")
public class LogControllerTest {
    private static final Logger log = LoggerFactory.getLogger(LogControllerTest.class);

    @Autowired
    private LogService logService;

    @Test
    public void test() throws RpcException {
        LogDTO logDTO = new LogDTO();
        logDTO.setUserId(675675888);
        logDTO.setLogLevel(LogLevelEnum.ERROR.getValue());
        logDTO.setModuleType(NumberUtils.toInt(ModuleTypeEnum.ACCESS_LAYER.getValue()));
        logDTO.setOperateCode(String.valueOf(OperateCodes.ADD_FRIENDS_CODE));
        logDTO.setOperateContent(OperateCodes.ADD_FRIENDS_CONTENT);
        logDTO.setOperateDesc("上传一个ota文件");
        logDTO.setOperateTime(new Date());
        logDTO.setIpAddress("193.23.11.44");
        List<String> fileUrls = new ArrayList<String>();
        fileUrls.add("/usl/file4.log");
        fileUrls.add("/usl/file5.log");
        logDTO.setFileUrlList(fileUrls);
        Object result = logService.call("logService.saveLog", JSON.toJSONString(logDTO));
        log.info(String.valueOf(result));

        LogCriteriaEntity logCriteriaDTO = new LogCriteriaEntity();
        logCriteriaDTO.setUserId(5556);
        logCriteriaDTO.setStartTime(DateUtils.addDays(new Date(), -3));
        logCriteriaDTO.setEndTime(new Date());
        Object logs = logService.call("logService.findOperateLogs", JSON.toJSONString(logCriteriaDTO));
        log.info("操作日志:" + logs);
    }

}
