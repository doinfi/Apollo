package com.yf.coros.rest.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 常量类
 *
 * @author Infi
 * @date 17/10/18
 */
@Configuration
@Slf4j
public class Constants {

    public static final String MODULE_USER = "user";
    public static final String MODULE_DATA = "data";
    public static final String MODULE_DEVICE = "device";

    public final static byte IS_TRUE = 1;
    public final static int IS_TRUE_INT = 1;
    public final static byte IS_FASLE = 0;
    public static final String REQUEST_ATTR_USER_ID = "userId";
    public static final String REQUEST_ATTR_USER_EMAIL = "email";
    public static final String REQUEST_ATTR_MODULE = "module";

    public static final String YF_HEADER_NAME = "YFheader";
    public static final String AUTH_TIMESTAMP = "authTimestamp";
    public static final String AUTH_CODE = "authCode";
    public static final String AUTH_RESULT = "authResult";
    public static final String AUTH_PREFIX = "auth:";
    public static final String AUTH_ACCEPT_PREFIX = "accept:";
    public static final int MIN_AUTHCODE_CONTENT_LENGTH = 3;
    public static final String REDIRECT_CROSS_REGION = "redirectCrossRegion";
    public static final int TIMEZONE_BEIJING = 32;
    public static final String AUTO_FEEDBACK = "autoFeedback";
    public static final String AUTO_FEEDBACK_INTERVAL_NAME = "autoFeedbackInterval";
    public static boolean ELEVATION_CALIBRATION_AREA;

    public static String CROSS_REGION_RESET_PASSWORD_URL;

    public static Integer FIRMWARE_FILE_MAX_SIZE;
    public static Long AVATAR_FILE_MAX_SIZE;
    public static Integer MAX_TIMEZONE;
    public static Integer MESSAGE_FILE_MAX_SIZE;
    public static Long WORK_ID;

    public static String REGION;
    public static Boolean ENABLE_CROSS_REGION_CHECK;

    public static String PRODUCT_NAME;
    public static Boolean ENABLE_BIGDATA_ACTIVE_MSG;
    public static String ACTIVE_MSG_TOPIC_NAME;

    public static Boolean ENABLE_BIGDATA_DEVICE_INFO_MSG;
    public static String DEVICE_INFO_MSG_TOPIC_NAME;

    public static Integer AUTO_FEEDBACK_INTERVAL;

    /**
     * 轨迹文件上传的大小限制
     */
    public static Integer TRACK_FILE_MAX_SIZE;

    /**
     * 头盔固件类型列表
     */
    public static List<String> HELMET_FIRMWARE_TYPE_LIST;
    /**
     * 头盔固件，低于该版本，就不能升级
     */
    public static String HELMETS_START_FIRMWARE_VERSION;

    @Value("${coros.elevationCalibrationArea}")
    public void setElevationCalibrationArea(boolean elevationCalibrationArea) {
        ELEVATION_CALIBRATION_AREA = elevationCalibrationArea;
    }

    @Value("${coros.helmetFirmwareTypeList}")
    public void setHelmetFirmwareTypeList(String helmetFirmwareTypeList) {
        HELMET_FIRMWARE_TYPE_LIST = new ArrayList<>();
        if (StringUtils.isNotBlank(helmetFirmwareTypeList)) {
            Collections.addAll(HELMET_FIRMWARE_TYPE_LIST, StringUtils.split(helmetFirmwareTypeList, ","));
        }
    }

    @Value("${coros.helmetsStartFirmwareVersion}")
    public void setHelmetsStartFirmwareVersion(String helmetsStartFirmwareVersion) {
        HELMETS_START_FIRMWARE_VERSION = helmetsStartFirmwareVersion;
    }

    @Value("${coros.trackFileMaxSize}")
    public void setTrackFileMaxSize(Integer trackFileMaxSize) {
        TRACK_FILE_MAX_SIZE = trackFileMaxSize;
    }

    @Value("${region.crossRegionResetPasswordUrl}")
    public void setCrossRegionResetPasswordUrl(String crossRegionResetPasswordUrl) {
        Constants.CROSS_REGION_RESET_PASSWORD_URL = crossRegionResetPasswordUrl;
    }

    @Value("${primaryKey.workerid}")
    public void setWorkId(Long workId) {
        WORK_ID = workId;
    }

    @Value("${coros.messageFileMaxSize}")
    public void setMessageFileMaxSize(Integer messageFileMaxSize) {
        MESSAGE_FILE_MAX_SIZE = messageFileMaxSize;
    }

    @Value("${coros.maxTimezone}")
    public void setMaxTimezone(Integer maxTimezone) {
        MAX_TIMEZONE = maxTimezone;
    }

    @Value("${avatar.fileMaxSize}")
    public void setAvatarFileMaxSize(Long avatarFileMaxSize) {
        Constants.AVATAR_FILE_MAX_SIZE = avatarFileMaxSize;
    }

    @Value("${firmware.fileMaxSize}")
    public void setFirmwareFileMaxSize(Integer firmwareFileMaxSize) {
        Constants.FIRMWARE_FILE_MAX_SIZE = firmwareFileMaxSize;
    }

    @Value("${bigdata.product.name}")
    public void setProductName(String productName) {
        PRODUCT_NAME = productName;
    }

    @Value("${bigdata.activeMsg.enabled}")
    public void setEnableBigdataActiveMsg(Boolean enableBigdataActiveMsg) {
        ENABLE_BIGDATA_ACTIVE_MSG = enableBigdataActiveMsg;
    }

    @Value("${bigdata.deviceInfo.enabled}")
    public void setEnableBigdataDeviceInfoMsg(Boolean enableBigdataDeviceInfoMsg) {
        ENABLE_BIGDATA_DEVICE_INFO_MSG = enableBigdataDeviceInfoMsg;
    }

    @Value("${bigdata.activeMsg.topic.name}")
    public void setActiveMsgTopicName(String activeMsgTopicName) {
        ACTIVE_MSG_TOPIC_NAME = activeMsgTopicName;
    }

    @Value("${bigdata.deviceInfo.topic.name}")
    public void setDeviceInfoMsgTopicName(String deviceInfoMsgTopicName) {
        DEVICE_INFO_MSG_TOPIC_NAME = deviceInfoMsgTopicName;
    }

    @Value("${region.name}")
    public void setRegion(String region) {
        REGION = region;
    }

    @Value("${region.enableCrossRegionCheck}")
    public void setEnableCrossRegionCheck(Boolean enableCrossRegionCheck) {
        ENABLE_CROSS_REGION_CHECK = enableCrossRegionCheck;
    }

    @Value("${coros.autoFeedbackInterval}")
    public void setAutoFeedbackInterval(Integer autoFeedbackInterval) {
        AUTO_FEEDBACK_INTERVAL = autoFeedbackInterval;
    }

}
