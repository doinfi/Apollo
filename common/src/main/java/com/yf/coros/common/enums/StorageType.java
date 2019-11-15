package com.yf.coros.common.enums;


/**
 * 文件类型
 */
public interface StorageType {

    String AVATAR = "avatar";
    /**
     * 轨迹缩率图
     */
    String PATH = "path";
    String FIRMWARE = "firmware";
    String DAILY_DATA = "dailyData";
    String IMAGE_DATA = "imageData";
    String SPORT_DATA = "sportData";
    String MESSAGE = "message";
    String EPO_FILE = "epo";
    String SONY_CEP_FILE = "cep";
    String WATCH_FACE = "watchface";
    /**
     * 轨迹文件
     */
    String TRACK = "track";
    /**
     * 轨迹缩率图
     */
    String TRACK_IMAGE = "trackImage";
    /**
     * 轨迹原始文件
     */
    String TRACK_LOCAL = "trackData";
    /**
     * 轨迹缩率图原始文件
     */
    String TRACK_IMAGE_LOCAL = "trackImageData";
    /**
     * 问题反馈
     */
    String FEEDBACK = "feedback";
    /**
     * .fit格式的运动数据
     */
    String FIT_SPORT_DATA = "fit";

    /**
     * .fit格式运动数据压缩包
     */
    String FIT_ZIP = "fit_zip";

    /**
     * 数据分析路径
     */
    String ANALYSE = "analyse";
    /**
     * apk文件保存路径
     */
    String APK = "apk";
}
