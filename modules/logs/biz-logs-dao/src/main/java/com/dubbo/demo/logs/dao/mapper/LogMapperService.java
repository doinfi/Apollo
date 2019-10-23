package com.dubbo.demo.logs.dao.mapper;

import com.dubbo.demo.logs.dto.LogDTO;
import com.dubbo.demo.logs.dto.LogFileDTO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 概述: 日志模块dao层,mybatis的接口<br>
 * 背景: 因为<br>
 */
public interface LogMapperService {
    /**
     * 概述: 新增操作日志<br>
     * 背景:<br>
     *
     * @param logDTO 日志对象<br>
     *               参数说明:<br>
     *               userId         用户ID<br>
     *               moduleType     模块类型。1:账户模块,2:用户模块,3:统计模块,4:数据同步模块,5:表盘和固件模块,6:第三方接口模块,7:活动模块,8:公共模块,9:接入层模块<br>
     *               operateCode    操作编码,比如说登录的操作编码是1<br>
     *               operateContent 操作内容,比如说登录操作<br>
     *               operateDesc    描述说明,比如说本次登录是第三方帐号登录<br>
     *               operateTime    操作时间<br>
     *               ipAddress      用户IP地址<br>
     *               fileUrls       文件url地址列表<br>
     * @return 返回logDTO对象
     */
    void addOperateLog(LogDTO logDTO);

    /**
     * 概述:查询用户操作日志<br>
     * 背景:<br>
     *
     * @param userId    用户ID<br>
     * @param startTime 查询条件开始时间<br>
     * @param endTime   查询条件结束时间<br>
     * @return 输出参数:用户操作日志列表<br>
     */
    List<LogDTO> findOperateLogs(@Param("userId") int userId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /**
     * 概述: 新增异常日志<br>
     * 背景:<br>
     *
     * @param logDTO 日志对象<br>
     *               参数说明:<br>
     *               userId        用户ID<br>
     *               logLevel       日志级别。1：表示用户操作日志,2：表示系统错误日志,3：表示debug级别的日志,4：表示info级别的日志。错误日志表中， 保存error、debug、info级别的日志<br>
     *               moduleType     模块类型。1:账户模块,2:用户模块,3:统计模块,4:数据同步模块,5:表盘和固件模块,6:第三方接口模块,7:活动模块,8:公共模块,9:接入层模块<br>
     *               operateCode    操作编码,比如说登录的操作编码是1<br>
     *               operateContent 操作内容,比如说登录操作<br>
     *               operateDesc    描述说明,比如说本次登录是第三方帐号登录<br>
     *               paramJson      参数的json字符串<br>
     *               exceptionInfo  异常信息的字符串<br>
     *               operateTime    操作时间<br>
     *               ipAddress      用户IP地址<br>
     *               fileUrls       文件url地址列表<br>
     * @return 返回logDTO对象, 为了在新增以后获得logDTO的ID
     */
    void addErrorLog(LogDTO logDTO);

    /**
     * 概述:查询异常日志<br>
     * 背景:<br>
     *
     * @param userId    用户ID<br>
     * @param startTime 查询条件开始时间<br>
     * @param endTime   查询条件结束时间<br>
     * @return 输出参数:用户操作日志列表<br>
     */
    List<LogDTO> findErrorLogs(@Param("userId") int userId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /**
     * 根据日志ID查询对应的日志文件地址<br>
     *
     * @param logId   日志ID<br>
     * @param logType 日志类型,1:错误日志,2:用户操作日志<br>
     * @return 日志文件地址信息<br>
     */
    List<LogDTO> findLogFilesByLogId(@Param("logId") Long logId, @Param("logType") Integer logType);

    /**
     * 批量新增日志文件地址,包括原始数据,轨迹文件等<br>
     *
     * @param logFiles 日志文件列表<br>
     */
    void addLogFiles(@Param("list") List<LogFileDTO> logFiles);
}
