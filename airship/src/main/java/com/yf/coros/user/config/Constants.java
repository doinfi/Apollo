package com.yf.coros.user.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 常量类
 *
 * @author Infi
 * @date 17/10/18
 */
@Configuration
@Slf4j
public class Constants {

    /**
     * 短信相关
     */
    public final static int SMS_VERIFY_CODE_LEN = 4;// 手机验证码的长度
    public final static int SMS_VERIFY_CODE_VALID = 30 * 60;// 验证码有效时间，单位秒(30分钟)
    /**
     * 重置密码过期时间48小时,172800,激活邮箱邮件过期时间,48小时
     */
    public final static int SMS_VERIFY_CODE_VALID_BY_RESET_PWD = 172800;
    public final static int TOKEN_VALID_DAY_COUNT = 30;
    public static final String CHINA_NUMBER_CODE = "+86";
    public static final String CHINA_NUMBER_CODE_BRACKETS = "(+86)";
    public static String SYSTEM_EMAIL;
    public static Integer CHECK_CODE_REQUEST_COUNT;
    public static Integer CHECK_CODE_IP_REQUEST_COUNT;
    public static String MESSAGE_SUFFIX;
    public static Integer SOS_NEXT_INTERVAL;
    public static Integer SOS_MAX_NUMBER_TODAY;
    public static Integer SOS_MAX_ADD_CONTACT_NUMBER_TODAY;
    public static String SOS_URL;
    public static String SOS_URL_CN;
    public static String TWILIO_FROM_NUMBER;
    public static String AVATAR_URL;
    public static String ACTIVATE_URL;
    public static String RESET_PASSWORD_URL;
    public static Integer PWD_ERROR_COUNT;
    public static String AWS_ACCESS_KEY;
    public static String AWS_SECRET_KEY;
    public static String AWS_BUCKET_NAME;
    public static String DEPLOY_TYPE;
    public static String ECS_PATH;
    public static String OSS_PATH;
    public static String ECS_URL;
    public static String OSS_URL;
    public static String AWS_S3_URL;
    public static Integer MAX_TIMEZONE;
    public static Long WORK_ID;
    public static Integer DEFAULT_GENDER;
    public static Integer DEFAULT_STATURE;
    public static Integer DEFAULT_WEIGHT;
    public static Integer DEFAULT_BIRTHDAY;
    public static Integer DEFAULT_TIMEZONE;
    public static Integer DEFAULT_TARGET_MOTION_TIME;
    public static Integer DEFAULT_TARGET_CALORIE;
    public static Integer SOS_LOCATION_VALID_TIME_HOURS;
    public static Integer SOS_LOCATION_CLEAR_UP_TIME_DAY;
    public static Integer RHR_DEFAULT;
    public static Integer RHR_MAX;
    public static Integer RHR_MIN;
    public static String REGULAR_INTERNAL_USER_EMAL;
    public static String EMAIL_REGISTER_IMAGEURL_CN;
    public static String EMAIL_REGISTER_IMAGEURL_EN;
    public static String REGION_DEFAULT_LANGUAGE;
    public static String EMAIL_ALL_LANGUAGE;
    public static String RESET_PWD_IMAGE_URL;

    public static String SHORT_URL;
    public static String SHORT_PATH_TAG_CN;
    public static String SHORT_PATH_TAG_EN;

    @Value("${coros.resetPwdImageUrl}")
    public void setResetPwdImageUrl(String resetPwdImageUrl) {
        RESET_PWD_IMAGE_URL = resetPwdImageUrl;
    }

    @Value("${coros.emailAllLanguage}")
    public void setEmailAllLanguage(String emailAllLanguage) {
        EMAIL_ALL_LANGUAGE = emailAllLanguage;
    }

    @Value("${coros.regionDefaultLanguage}")
    public void setRegionDefaultLanguage(String regionDefaultLanguage) {
        REGION_DEFAULT_LANGUAGE = regionDefaultLanguage;
    }

    @Value("${coros.emailRegisterImageurlCn}")
    public void setEmailRegisterImageurlCn(String emailRegisterImageurlCn) {
        EMAIL_REGISTER_IMAGEURL_CN = emailRegisterImageurlCn;
    }

    @Value("${coros.emailRegisterImageurlEn}")
    public void setEmailRegisterImageurlEn(String emailRegisterImageurlEn) {
        EMAIL_REGISTER_IMAGEURL_EN = emailRegisterImageurlEn;
    }

    @Value("${coros.regularInternalUserEmal}")
    public void setRegularInternalUserEmal(String regularInternalUserEmal) {
        REGULAR_INTERNAL_USER_EMAL = regularInternalUserEmal;
    }

    @Value("${coros.rhrDefault}")
    public void setRhrDefault(Integer rhrDefault) {
        RHR_DEFAULT = rhrDefault;
    }

    @Value("${coros.rhrMax}")
    public void setRhrMax(Integer rhrMax) {
        RHR_MAX = rhrMax;
    }

    @Value("${coros.rhrMin}")
    public void setRhrMin(Integer rhrMin) {
        RHR_MIN = rhrMin;
    }

    @Value("${coros.defaultGender}")
    public void setDefaultGender(Integer defaultGender) {
        DEFAULT_GENDER = defaultGender;
    }

    @Value("${coros.defaultStature}")
    public void setDefaultStature(Integer defaultStature) {
        DEFAULT_STATURE = defaultStature;
    }

    @Value("${coros.defaultWeight}")
    public void setDefaultWeight(Integer defaultWeight) {
        DEFAULT_WEIGHT = defaultWeight;
    }

    @Value("${coros.defaultBirthday}")
    public void setDefaultBirthday(Integer defaultBirthday) {
        DEFAULT_BIRTHDAY = defaultBirthday;
    }

    @Value("${coros.defaultTimezone}")
    public void setDefaultTimezone(Integer defaultTimezone) {
        DEFAULT_TIMEZONE = defaultTimezone;
    }

    @Value("${coros.defaultTargetMotionTime}")
    public void setDefaultTargetMotionTime(Integer defaultTargetMotionTime) {
        DEFAULT_TARGET_MOTION_TIME = defaultTargetMotionTime;
    }

    @Value("${coros.defaultTargetCalorie}")
    public void setDefaultTargetCalorie(Integer defaultTargetCalorie) {
        DEFAULT_TARGET_CALORIE = defaultTargetCalorie;
    }

    @Value("${primaryKey.workerid}")
    public void setWorkId(Long workId) {
        WORK_ID = workId;
    }

    @Value("${coros.maxTimezone}")
    public void setMaxTimezone(Integer maxTimezone) {
        MAX_TIMEZONE = maxTimezone;
    }


    @Value("${coros.ecsUrl}")
    public void setEcsUrl(String ecsUrl) {
        ECS_URL = ecsUrl;
    }

    @Value("${coros.ossUrl}")
    public void setOssUrl(String ossUrl) {
        OSS_URL = ossUrl;
    }

    @Value("${coros.awsS3Url}")
    public void setAwsS3Url(String awsS3Url) {
        AWS_S3_URL = awsS3Url;
    }

    @Value("${coros.ecsPath}")
    public void setEcsPath(String ecsPath) {
        ECS_PATH = ecsPath;
    }

    @Value("${coros.ossPath}")
    public void setOssPath(String ossPath) {
        OSS_PATH = ossPath;
    }

    @Value("${aws.bucketName}")
    public void setBucketName(String bucketName) {
        AWS_BUCKET_NAME = bucketName;
    }

    @Value("${deployType}")
    public void setDeployType(String deployType) {
        DEPLOY_TYPE = deployType;
    }

    @Value("${aws.accessKey}")
    public void setAwsAccessKey(String awsAccessKey) {
        AWS_ACCESS_KEY = awsAccessKey;
    }

    @Value("${aws.secretKey}")
    public void setAwsSecretKey(String awsSecretKey) {
        AWS_SECRET_KEY = awsSecretKey;
    }

    @Value("${coros.pwdErrorCount}")
    public void setPwdErrorCount(Integer pwdErrorCount) {
        Constants.PWD_ERROR_COUNT = pwdErrorCount;
    }

    @Value("${coros.activateUrl}")
    public void setActivateUrl(String activateUrl) {
        Constants.ACTIVATE_URL = activateUrl;
    }

    @Value("${coros.resetPasswordUrl}")
    public void setResetPasswordUrl(String resetPasswordUrl) {
        Constants.RESET_PASSWORD_URL = resetPasswordUrl;
    }

    @Value("${avatar.url}")
    public void setAvatarUrl(String avatarUrl) {
        Constants.AVATAR_URL = avatarUrl;
    }


    @Value("${coros.checkCode.ipRequestCount}")
    public void setCheckCodeIpRequestCount(Integer checkCodeIpRequestCount) {
        Constants.CHECK_CODE_IP_REQUEST_COUNT = checkCodeIpRequestCount;
    }


    @Value("${coros.email.systemEmail}")
    public void setSystemEmail(String systemEmail) {
        Constants.SYSTEM_EMAIL = systemEmail;
    }

    @Value("${coros.checkCode.requestCount}")
    public void setCheckCodeRequestCount(Integer checkCodeRequestCount) {
        Constants.CHECK_CODE_REQUEST_COUNT = checkCodeRequestCount;
    }

    @Value("${MESSAGE_SUFFIX}")
    public void setMessageSuffix(String messageSuffix) {
        Constants.MESSAGE_SUFFIX = messageSuffix;
    }

    @Value("${sos.nextInterval}")
    public void setSosNextInterval(Integer sosNextInterval) {
        Constants.SOS_NEXT_INTERVAL = sosNextInterval;
    }

    @Value("${sos.maxNumberToday}")
    public void setSosMaxNumberToday(Integer sosMaxNumberToday) {
        Constants.SOS_MAX_NUMBER_TODAY = sosMaxNumberToday;
    }

    @Value("${coros.sosMaxAddContactNumberToday}")
    public void setMaxAddContactNumberToday(Integer maxAddContactNumberToday) {
        Constants.SOS_MAX_ADD_CONTACT_NUMBER_TODAY = maxAddContactNumberToday;
    }

    @Value("${sos.url}")
    public void setSosUrl(String sosUrl) {
        Constants.SOS_URL = sosUrl;
    }

    @Value("${sos.urlCn}")
    public void setSosUrlCn(String sosUrlCn) {
        Constants.SOS_URL_CN = sosUrlCn;
    }

    @Value("${twilio.fromNumber}")
    public void setTwilioFromNumber(String twilioFromNumber) {
        Constants.TWILIO_FROM_NUMBER = twilioFromNumber;
    }

    @Value("${coros.sosLocationValidTimeHours}")
    public void setSosLocationValidTimeHours(Integer sosLocationValidTimeHours) {
        SOS_LOCATION_VALID_TIME_HOURS = sosLocationValidTimeHours;
    }

    @Value("${coros.sosLocationClearUpTimeDay}")
    public void setSosLocationClearUpTimeDay(Integer sosLocationClearUpTimeDay) {
        SOS_LOCATION_CLEAR_UP_TIME_DAY = sosLocationClearUpTimeDay;
    }

    @Value("${coros.shortUrl}")
    public void setShortUrl(String shortUrl) {
        SHORT_URL = shortUrl;
    }

    @Value("${coros.shortPathTagCn}")
    public void setShortPathTagCn(String shortPathTagCn) {
        SHORT_PATH_TAG_CN = shortPathTagCn;
    }

    @Value("${coros.shortPathTagEn}")
    public void setShortPathTagEn(String shortPathTagEn) {
        SHORT_PATH_TAG_EN = shortPathTagEn;
    }

}
