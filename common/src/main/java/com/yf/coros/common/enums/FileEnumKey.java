/*
 */

package com.yf.coros.common.enums;

import com.yf.coros.common.config.ApplicationContants;
import com.yf.coros.common.utils.YfTools;
import java.io.File;
import java.util.EnumSet;
import org.apache.commons.lang3.StringUtils;

/**
 * EnumFileKey:文件类型枚举类
 *
 * @author dinghui 2016-05-30
 */
public enum FileEnumKey {
    // OSS单用户最多能创建10个BUKET，为节省BUKET，部分类似文件放在同一BUKET下
    /**
     * 原始数据，二进制流
     */
    LABEL_ORI("labelorifile", "rawdata"), // 标签数据,二进制流

    /**
     * 原始数据:运动轨迹缩率图,二进制流
     */
    LABEL_IMAGEDATA("imagedata", "rawdata"), // 标签数据,二进制流

    /**
     * 原始数据:运动数据,二进制流
     */
    LABEL_SPORTDATA("sportdata", "rawdata"), // 标签数据,二进制流
    /**
     * 运动轨迹数据，二进制流
     */
    LABEL_LOCUS("labellocusfile", "rawdata"), // 运动轨迹数据，二进制流

    /**
     * 动态心率数据，二进制流
     */
    LABEL_HEART("labelheartfile", "rawdata"), // 运动轨迹数据，二进制流
    /**
     * 运动轨迹缩率图,保存在oss的labellocusimage文件夹，png图片
     */
    LABEL_LOCUS_IMAGE("locusimage", "locusimage"),
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
    AVATAR("avatar", "avatar"),// 用户头像，png图片
    /**
     * 问题反馈日志
     */
    FEEDBACK_FILE("feedback", "feedback");// 用户头像，png图片


    public final static String FILE_LOCAL = "local";
    public final static String FILE_OSS = "oss";
    // 成员变量
    private String name;// 这里的name可以看作根目录
    private String dataType;
    private String bucketName;
    private String upLoadPath;// 文件上传根目录
    private String httpRoot;
    private String contentType = "application/octet-stream";

    // 构造方法
    FileEnumKey(String name, String dataType) {
        this(name, dataType, "application/octet-stream");
    }

    /**
     * 构造方法
     *
     * @param name 文件类型名称
     * @param dataType 文件类型
     * @param contentType 数据流类型
     */
    FileEnumKey(String name, String dataType, String contentType) {
        this.name = name;
        this.bucketName = ApplicationContants.OSS_BUCKET_NAME + dataType;
        this.dataType = dataType;
        this.contentType = contentType;
    }

    /**
     * 根据value获取枚举
     *
     * @param value 值
     * @return 枚举
     */
    public static FileEnumKey getByValue(String value) {
        for (FileEnumKey fileKey : values()) {
            if (value.equals(fileKey.getDataType())) {
                return fileKey;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public String getDataType() {
        return dataType;
    }

    public String getBucketName() {
        return bucketName;
    }

    public String getUpLoadPath() {
        return upLoadPath;
    }

    public String getHttpRoot() {
        return httpRoot;
    }

    /**
     * 本地路径
     *
     * @param upLoadPath 本地路径
     */
    public String getLocalPath(String parentPath, String upLoadPath) {
        String filePath =
            YfTools.trimEnd(upLoadPath, "/") + File.separator + getName() + File.separator;
        //需要创建父目录的文件类型
        EnumSet<FileEnumKey> sets = EnumSet
            .of(FileEnumKey.LABEL_ORI, FileEnumKey.LABEL_LOCUS, FileEnumKey.LABEL_HEART);
        if (StringUtils.isNotBlank(parentPath)) {
            if (sets.contains(this)) {
                int subIndex = parentPath.length();
                if (parentPath.length() > 7) {
                    subIndex = parentPath.length() < 12 ? 4 : 7;
                }
                filePath += YfTools.trim(parentPath.substring(0, subIndex), "/") + File.separator;
            }
            filePath += YfTools.trim(parentPath, "/") + File.separator;
        }
        return filePath;
    }

    /**
     * @return LOCAL/OSS
     */
    private String getFileSaveType() {
        EnumSet<FileEnumKey> set = EnumSet
            .of(FileEnumKey.LABEL_ORI, FileEnumKey.LABEL_LOCUS, FileEnumKey.MINUTES);
        return set.contains(this) ? FILE_LOCAL : FILE_OSS;
    }

    /**
     * 文件是否存在本地
     */
    public boolean isSaveOnLocal() {
        return FILE_LOCAL.equals(getFileSaveType());
    }

    /**
     * 文件是否存在阿里云OSS
     */
    public boolean isSaveOnOss() {
        return FILE_OSS.equals(getFileSaveType());
    }

    /**
     * 获取http访问目录，尾部已经包含"/"
     */
    public String getHttpPath(String upLoadPath) {
        return YfTools.trim(upLoadPath, "/") + "/" + getName() + "/";
    }

    public String getOssParentPath(String parentPath) {
        if (YfTools.isEmpty(parentPath)) {
            return this.getName();
        }
        // 结尾不用斜线
        return this.getName() + "/" + YfTools.trim(parentPath, "/");
    }

}
