package com.dubbo.demo.logs.service;


import com.dubbo.demo.common.service.BaseService;
import com.dubbo.demo.common.vo.ResponseVO;
import com.dubbo.demo.logs.vo.LogFileResponseVO;
import com.dubbo.demo.logs.vo.LogResponseVO;

/**
 * 概述:日志模块属于公共服务接口,业务模块需要记录用户操作记录或者系统异常时需要记录错误信息,就要调用日志模块<br>
 * 背景:新框架设计,将真个系统按模块划分,日志模块也是独立出来的一个模块。<br>
 * 使用示例:使用dubbo框架注册并调用<br>
 * 跨模块相关:用户相关模块、运动统计模块、数据同步模块、表盘相关模块，固件相关模块<br>
 *
 * @author Infi
 */
public interface LogService extends BaseService {

    /**
     * 概述:保存日志,文件和数据库都存。业务模块用户做任何操作都要记录用户的操作日志,业务模块系统错误时调用日志模块记录错误详细信息<br>
     * 背景:<br>
     * 调用方法:logService.call("logService.saveLog", jsonParam)<br>
     *
     * @param jsonParam 参数的json字符串<br>
     *                  1. JSON参数包含下面内容:<br>
     *                  userId         用户ID<br>
     *                  logLevel       日志级别:使用枚举类LogLevelEnum,
     *                  枚举详情:<br>
     *                  OPERATE表示用户操作日志,<br>
     *                  ERROR表示系统错误日志,<br>
     *                  DUBUG表示debug级别的日志,<br>
     *                  INFOE表示info级别的日志<br>
     *                  <br>
     *                  moduleType     模块类型:使用枚举类ModuleTypeEnum,
     *                  枚举详情:<br>
     *                  ACCOUNT 表示账户模块<br>
     *                  USER 表示用户模块<br>
     *                  MOTION_STAT 表示统计模块<br>
     *                  DATA_SYNC 表示数据同步模块<br>
     *                  FIRMWARE_WATCHFACE 表示表盘和固件模块<br>
     *                  EXTERNAL 表示第三方接口模块<br>
     *                  PROMOTION 表示活动模块<br>
     *                  COMMUNITY 表示公共模块<br>
     *                  ACCESS_LAYER 表示接入层模块<br>
     *                  <br>
     *                  operateCode    操作编码:使用OperateCodes类,比如说登录的操作编码是1<br>
     *                  operateContent 操作内容:使用OperateCodes类,比如说登录操作<br>
     *                  operateDesc    描述说明,比如说本次登录是第三方帐号登录<br>
     *                  paramJson      参数的json字符串(当系统错误时传递)<br>
     *                  exceptionInfo  系统异常信息,只有error级别的日志才会有异常信息<br>
     *                  operateTime    操作时间<br>
     *                  ipAddress      用户IP地址<br>
     *                  fileUrlList    文件url地址列表,数组形式的参数<br>
     *                  <p>
     *                  json参数比如:<br>
     *                  {<br>
     *                  "exceptionInfo":"空指针",<br>
     *                  "fileUrlList":[<br>
     *                  "/usl/file.log",<br>
     *                  "/usl/file2.log"<br>
     *                  ],<br>
     *                  "id":0,<br>
     *                  "ipAddress":"193.23.11.44",<br>
     *                  "logLevel":2,<br>
     *                  "moduleType":5,<br>
     *                  "operateCode":"30",<br>
     *                  "operateContent":"ota上传",<br>
     *                  "operateDesc":"上传一个ota文件",<br>
     *                  "operateTime":1489042594297,<br>
     *                  "paramJson":"json参数",<br>
     *                  "userId":2<br>
     *                  }<br>
     * @return 输出参数, 日志保存结果, true表示保存成功, false表示保存失败<br>
     * result: 返回编码<br>
     * message: 返回信息<br>
     * {<br>
     * "result":"0000",<br>
     * "message":"ok"<br>
     * }<br>
     */
    ResponseVO saveLog(String jsonParam);


    /**
     * 概述:查询用户操作日志信息<br>
     * 背景:以前查询系统错误就要去服务器查看log文件,日志模块可以通过系统管理后台通过页面的形式查询错误日志<br>
     * <p>
     * <p>
     *
     * @param jsonParam json参数<br>
     *                  json参数说明:<br>
     *                  userId    用户ID<br>
     *                  startTime 日志查询时间段,开始时间<br>
     *                  endTime   日志查询时间段,结束时间<br>
     * @return 日志列表信息<br>
     */
    LogResponseVO findOperateLogs(String jsonParam);

    /**
     * 概述:查询错误日志信息<br>
     * 背景:以前查询系统错误就要去服务器查看log文件,日志模块可以通过系统管理后台通过页面的形式查询错误日志<br>
     * <p>
     * <p>
     *
     * @param jsonParam json参数<br>
     *                  json参数说明:<br>
     *                  userId  用户ID<br>
     *                  startTime 日志查询时间段,开始时间<br>
     *                  endTime 日志查询时间段,结束时间<br>
     * @return 日志列表信息<br>
     */
    LogResponseVO findErrorLogs(String jsonParam);

    /**
     * 概述:查询错误日志信息<br>
     * 背景:以前查询系统错误就要去服务器查看log文件,日志模块可以通过系统管理后台通过页面的形式查询错误日志<br>
     * <p>
     * <p>
     *
     * @param jsonParam json参数<br>
     *                  json参数说明:<br>
     *                  logId  日志ID<br>
     *                  logType 日志类型,1:表示用户操作日志,2:表示系统错误日志<br>
     *                  json参数示例:
     *                  {
     *                  "logId":1234,
     *                  "logType":1
     *                  }
     * @return 日志列表信息<br>
     * id: 主键ID<br>
     * logType: 日志类型,1:表示用户操作日志,2:表示系统错误日志<br>
     * logId: 日志ID<br>
     * fileUrl: 日志url<br>
     */
    LogFileResponseVO findLogFilesByLogId(String jsonParam);

}
