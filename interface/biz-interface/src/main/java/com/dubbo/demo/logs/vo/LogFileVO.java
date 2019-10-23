package com.dubbo.demo.logs.vo;

import java.io.Serializable;

/**
 * 概述: 日志对应的文件信息,比如说原始数据文件地址信息<br>
 * 背景: <br>
 * Created by Infi on 17/3/15.
 */
public class LogFileVO implements Serializable {

    private static final long serialVersionUID = 4902040648209602411L;
    private Long id;
    private Integer logType;
    private Long logId;
    private String fileUrl;


    /**
     * 获取主键
     *
     * @return id 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取日志类型，1：表示用户操作日志，2：表示错误日志
     *
     * @return logType 日志类型，1：表示用户操作日志，2：表示错误日志
     */
    public Integer getLogType() {
        return logType;
    }

    /**
     * 设置日志类型，1：表示用户操作日志，2：表示错误日志
     *
     * @param logType 日志类型，1：表示用户操作日志，2：表示错误日志
     */
    public void setLogType(Integer logType) {
        this.logType = logType;
    }

    /**
     * 获取日志ID，对应errorLog和operateLog的日志ID，错误日志和操作日志
     *
     * @return logId 日志ID，对应errorLog和operateLog的日志ID，错误日志和操作日志
     */
    public Long getLogId() {
        return logId;
    }

    /**
     * 设置日志ID，对应errorLog和operateLog的日志ID，错误日志和操作日志
     *
     * @param logId 日志ID，对应errorLog和operateLog的日志ID，错误日志和操作日志
     */
    public void setLogId(Long logId) {
        this.logId = logId;
    }

    /**
     * 获取文件路径，目前原始数据保存的服务器本机，可以在tomcat中配置，这样可以在web页面下载
     *
     * @return fileUrl 文件路径，目前原始数据保存的服务器本机，可以在tomcat中配置，这样可以在web页面下载
     */
    public String getFileUrl() {
        return fileUrl;
    }

    /**
     * 设置文件路径，目前原始数据保存的服务器本机，可以在tomcat中配置，这样可以在web页面下载
     *
     * @param fileUrl 文件路径，目前原始数据保存的服务器本机，可以在tomcat中配置，这样可以在web页面下载
     */
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
