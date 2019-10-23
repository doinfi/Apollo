package com.dubbo.demo.logs.test;

import com.alibaba.fastjson.JSON;
import com.dubbo.demo.common.vo.ModuleTypeEnum;
import com.dubbo.demo.logs.dto.LogDTO;
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
@ContextConfiguration("classpath:application.xml")
public class LogServerTest {

    private static final Logger logger = LoggerFactory.getLogger(LogServerTest.class);

    @Autowired
    private com.dubbo.demo.logs.dao.LogDao LogDao;

    @Test
    public void test() {
        try {

            LogDTO logDTO = new LogDTO();
            logDTO.setUserId(77777);
            logDTO.setModuleType(NumberUtils.toInt(ModuleTypeEnum.ACCESS_LAYER.getValue()));
            logDTO.setOperateCode("30");
            logDTO.setOperateContent("ota上传");
            logDTO.setOperateDesc("上传一个ota文件");
            logDTO.setOperateTime(new Date());
            logDTO.setIpAddress("193.23.11.44");
            List<String> fileUrls = new ArrayList<String>();
            fileUrls.add("/usl/file4.log");
            fileUrls.add("/usl/file5.log");
            logDTO.setFileUrlList(fileUrls);
            LogDTO result = LogDao.addOperateLog(logDTO);
            logger.info(JSON.toJSONString(result));

            List<LogDTO> logs = LogDao.findOperateLogs(1123123, DateUtils.addDays(new Date(), -3), new Date());
            logger.info("操作日志:" + JSON.toJSONString(logs));
            synchronized (LogServerTest.class) {
                while (true) {
                    try {
                        LogServerTest.class.wait();
                    } catch (InterruptedException e) {
                    }
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
