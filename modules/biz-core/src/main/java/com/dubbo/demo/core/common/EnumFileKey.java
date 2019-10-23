/*
 * Copyright (c) 2014-2018 yftech Co.Ltd. All rights reserved.
 */

package com.dubbo.demo.core.common;

import com.dubbo.demo.core.utils.Tools;
import lombok.Getter;

import java.io.File;
import java.util.EnumSet;

/**
 * EnumFileKey:文件类型枚举类
 *
 * @author dinghui 2016-05-30
 */
public enum EnumFileKey {

    // OSS单用户最多能创建10个BUKET，为节省BUKET，部分类似文件放在同一BUKET下
    /**
     * 原始数据，二进制流
     */
    LABEL_ORI("labelorifile", "rawdata"), // 标签数据,二进制流
    /**
     * 运动轨迹数据，二进制流
     */
    LABEL_LOCUS("labellocusfile", "rawdata"), // 运动轨迹数据，二进制流

    /**
     * 动态心率数据，二进制流
     */
    LABEL_HEART("labelheartfile", "rawdata"), // 运动轨迹数据，二进制流

    /**
     * 运动轨迹数据，二进制流
     */
    MINUTES("minutesDate", "rawdata"), // 分钟数据，二进制流
    /**
     * 固件包,APP或手环用到
     */
    FIRMWARE("firmware", "firmware"), // APP固件，bin文件
    /**
     * 表盘固件，bin文件
     */
    WATCH_FACE("watchface", "firmware"), // 表盘固件，bin文件
    /**
     * 骚扰数据，zip压缩包
     */
    CALL_HARESS("callharess", "firmware"), // 骚扰数据，zip压缩包
    /**
     * 用户头像，png图片
     */
    HEAD_PIC("headpic", "headpic");// 用户头像，png图片

    // 成员变量
    private
    @Getter
    String name;// 这里的name可以看作根目录
    private
    @Getter
    String dataType;
    private
    @Getter
    String bucketName;
    private
    @Getter
    String upLoadPath;// 文件上传根目录
    private
    @Getter
    String httpRoot;

    private String contentType = "application/octet-stream";

    public final static String FILE_LOCAL = "local";
    public final static String FILE_OSS = "oss";

    // 构造方法
    private EnumFileKey(String name, String dataType) {
        this(name, dataType, "application/octet-stream");

    }

    // 构造方法
    private EnumFileKey(String name, String dataType, String contentType) {
        this.name = name;
        this.bucketName = AppConfig.BUCKET_PREFIX + dataType;
        this.dataType = dataType;
        this.contentType = contentType;
        this.upLoadPath = AppConfig.getProperty(dataType + ".path");
        this.httpRoot = AppConfig.getProperty(dataType + ".http");
    }


    public String getLocalPath() {
        return getLocalPath("");
    }

    /**
     * 本地路径
     *
     * @param parentPath：父目录
     * @return
     */
    public String getLocalPath(String parentPath) {
        String filePath = Tools.trimEnd(this.getUpLoadPath(), "/") + File.separator + getName() + File.separator;
        //需要创建父目录的文件类型
        EnumSet<EnumFileKey> sets = EnumSet.of(EnumFileKey.LABEL_ORI, EnumFileKey.LABEL_LOCUS, EnumFileKey.LABEL_HEART);
        if (!Tools.isEmpty(parentPath)) {
            if (sets.contains(this)) {
                int subIndex = parentPath.length();
                if (parentPath.length() > 7) {
                    subIndex = parentPath.length() < 12 ? 4 : 7;
                }
                filePath += Tools.trim(parentPath.substring(0, subIndex), "/") + File.separator;
            }
            filePath += Tools.trim(parentPath, "/") + File.separator;
        }
        return filePath;
    }

    /**
     * @return LOCAL/OSS
     */
    private String getFileSaveType() {
        EnumSet<EnumFileKey> set = EnumSet.of(EnumFileKey.LABEL_ORI, EnumFileKey.LABEL_LOCUS, EnumFileKey.MINUTES);
        return set.contains(this) ? FILE_LOCAL : FILE_OSS;
    }

    /**
     * 文件是否存在本地
     *
     * @return
     */
    public boolean isSaveOnLocal() {
        return FILE_LOCAL.equals(getFileSaveType());
    }

    /**
     * 文件是否存在阿里云OSS
     *
     * @return
     */
    public boolean isSaveOnOss() {
        return FILE_OSS.equals(getFileSaveType());
    }

    /**
     * 获取http访问目录，尾部已经包含"/"
     *
     * @return
     */
    public String getHttpPath() {
        return Tools.trim(this.httpRoot, "/") + "/" + getName() + "/";
        // if (FILE_LOCAL.equals(this.getFileSaveType()))
        // return Tools.sideTrim(ApplicationConstants.FILE_HTTP_URL, "/") + "/"
        // + getName() + "/";
        // return getOssHttpPath("") + "/";
    }

    public String getOssParentPath(String parentPath) {
        if (Tools.isEmpty(parentPath))
            return this.getName();
        // 结尾不用斜线
        return this.getName() + "/" + Tools.trim(parentPath, "/");
    }

    // 获取OSS 文件访问地址
    public String getOssHttpPath(String parentPath) {
        String channel = "http://" + this.getBucketName() + ".";
        // 判断是否是图片http://
        // 域名规则：http://channel.<endpoint>/object@format
        String endpoint = this == EnumFileKey.HEAD_PIC ? AppConfig.OSS_IMG_ENDPOINT : AppConfig.ENDPOINT;
        endpoint = endpoint.replace("http://", "").replace("/", "");
        String httpRoot = channel + endpoint + "/" + getOssParentPath(parentPath);
        return Tools.trim(httpRoot, "/");
    }

    public static void main(String[] args) {
        // File file = new File("D:/appCache/java.jpg");
        // System.out.println(file.getPath());
        // printContentType("D:/appCache/java.bin");
        // printContentType(file.getPath());
        // printContentType("D:/appCache/java.png");
        // printContentType("D:/appCache/java.zip");
        // printContentType("D:/appCache/java");

        String parentPath = "////aaaa/////";
        String teString = EnumFileKey.CALL_HARESS.getHttpPath() + "/" + "aaaa.zip";

        parentPath = Tools.trim(parentPath, "/");
        System.out.println(parentPath);
        System.out.println(teString);

    }
}
