package com.dubbo.demo.logs.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 概述:日志模块entity<br>
 * 日志模块数据传输对象<br>
 * 数据层和service层交互的实体对象<br>
 * Created by Infi on 17/2/19.<br>
 */
public class LogDTO implements Serializable {

    private static final long serialVersionUID = -3708728733389225740L;
    private long id;
    private int userId;
    private Integer logLevel;
    private Integer moduleType;
    private String operateCode;
    private String operateContent;
    private String paramJson;
    private String exceptionInfo;
    private Date operateTime;
    private String ipAddress;
    private List<String> fileUrlList;
    private String operateDesc;

    /**
     * 获取操作描述说明
     *
     * @return operateDesc 操作描述说明
     */
    public String getOperateDesc() {
        return operateDesc;
    }

    /**
     * 设置操作描述说明
     *
     * @param operateDesc 操作描述说明
     */
    public void setOperateDesc(String operateDesc) {
        this.operateDesc = operateDesc;
    }


    /**
     * 获取模块类型。1：用户相关模块,2：运动统计模块,3：数据同步模块,4：表盘相关模块,5：固件相关模块
     *
     * @return moduleType 模块类型。1：用户相关模块,2：运动统计模块,3：数据同步模块,4：表盘相关模块,5：固件相关模块
     */
    public Integer getModuleType() {
        return moduleType;
    }

    /**
     * 设置模块类型。1：用户相关模块,2：运动统计模块,3：数据同步模块,4：表盘相关模块,5：固件相关模块
     *
     * @param moduleType 模块类型。1：用户相关模块,2：运动统计模块,3：数据同步模块,4：表盘相关模块,5：固件相关模块
     */
    public void setModuleType(Integer moduleType) {
        this.moduleType = moduleType;
    }

    /**
     * 获取日志级别。1：表示用户操作日志,2：表示系统错误日志,3：表示debug级别的日志,4：表示info级别的日志。错误日志表中， 保存error、debug、info级别的日志
     *
     * @return logLevelInt 日志级别。1：表示用户操作日志,2：表示系统错误日志,3：表示debug级别的日志,4：表示info级别的日志。错误日志表中， 保存error、debug、info级别的日志
     */
    public Integer getLogLevel() {
        return logLevel;
    }

    /**
     * 设置日志级别。1：表示用户操作日志,2：表示系统错误日志,3：表示debug级别的日志,4：表示info级别的日志。错误日志表中， 保存error、debug、info级别的日志
     *
     * @param logLevel 日志级别。1：表示用户操作日志,2：表示系统错误日志,3：表示debug级别的日志,4：表示info级别的日志。错误日志表中， 保存error、debug、info级别的日志
     */
    public void setLogLevel(Integer logLevel) {
        this.logLevel = logLevel;
    }

    /**
     * 获取用户IP地址
     *
     * @return ipAddress 用户IP地址
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * 设置用户IP地址
     *
     * @param ipAddress 用户IP地址
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * 获取主键
     *
     * @return id 主键
     */
    public long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * 获取文件地址列表
     *
     * @return fileUrlList 文件地址列表
     */
    public List<String> getFileUrlList() {
        return fileUrlList;
    }

    /**
     * 设置文件地址列表
     *
     * @param fileUrlList 文件地址列表
     */
    public void setFileUrlList(List<String> fileUrlList) {
        this.fileUrlList = fileUrlList;
    }

    /**
     * 获取操作时间
     *
     * @return operateTime 操作时间
     */
    public Date getOperateTime() {
        return operateTime;
    }

    /**
     * 设置操作时间
     *
     * @param operateTime 操作时间
     */
    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    /**
     * 获取操作编码,具体操作动作的编码,比如说登录操作code是1
     *
     * @return operateCode 操作编码,具体操作动作的编码,比如说登录操作code是1
     */
    public String getOperateCode() {
        return operateCode;
    }

    /**
     * 设置操作编码,具体操作动作的编码,比如说登录操作code是1
     *
     * @param operateCode 操作编码,具体操作动作的编码,比如说登录操作code是1
     */
    public void setOperateCode(String operateCode) {
        this.operateCode = operateCode;
    }

    /**
     * 获取操作内容
     *
     * @return operateContent 操作内容
     */
    public String getOperateContent() {
        return operateContent;
    }

    /**
     * 设置操作内容
     *
     * @param operateContent 操作内容
     */
    public void setOperateContent(String operateContent) {
        this.operateContent = operateContent;
    }

    /**
     * 获取系统异常信息,只有error级别的日志才会有异常信息
     *
     * @return exceptionInfo 系统异常信息,只有error级别的日志才会有异常信息
     */
    public String getExceptionInfo() {
        return exceptionInfo;
    }

    /**
     * 设置系统异常信息,只有error级别的日志才会有异常信息
     *
     * @param exceptionInfo 系统异常信息,只有error级别的日志才会有异常信息
     */
    public void setExceptionInfo(String exceptionInfo) {
        this.exceptionInfo = exceptionInfo;
    }


    /**
     * 获取参数的json字符串
     *
     * @return paramJson 参数的json字符串
     */
    public String getParamJson() {
        return paramJson;
    }

    /**
     * 设置参数的json字符串
     *
     * @param paramJson 参数的json字符串
     */
    public void setParamJson(String paramJson) {
        this.paramJson = paramJson;
    }

    /**
     * 获取用户ID
     *
     * @return userId 用户ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }


}
