package com.yf.coros.user.config;


import com.amazonaws.services.s3.model.ObjectMetadata;
import com.yf.coros.common.enums.DeployType;
import com.yf.coros.common.enums.StorageType;
import com.yf.coros.common.utils.FileProcessUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;

/**
 * 简单文件存储
 *
 * @author yangyueming
 */
public class SimpleStorageUtils {

    public static void putObject2Server(String storageType, byte[] storageBytes,
                                        String uploadPath, String tmpfileName,
                                        String headPicFullPath) {
        FileProcessUtils.save2AliOssOrLocalDisk(storageBytes, tmpfileName, uploadPath);
    }

    public static String getEcsOrOssUrl(String storageType) {
        String prefix = StringUtils.EMPTY;
        String tmpPath = File.separator + storageType + File.separator;
        if (StringUtils
                .equalsIgnoreCase(DeployType.PRODUCTION, Constants.DEPLOY_TYPE)) {
            switch (storageType) {
                case StorageType.AVATAR:
                case StorageType.FIRMWARE:
                case StorageType.PATH:
                    prefix = Constants.AWS_S3_URL + tmpPath;
                    break;
                case StorageType.DAILY_DATA:
                case StorageType.IMAGE_DATA:
                case StorageType.SPORT_DATA:
                    prefix = Constants.ECS_URL + tmpPath;
                    break;
                default:
                    break;
            }
            return prefix;
        } else {

            switch (storageType) {
                case StorageType.AVATAR:
                case StorageType.FIRMWARE:
                case StorageType.PATH:
                    prefix = Constants.OSS_URL + tmpPath;
                    break;
                case StorageType.DAILY_DATA:
                case StorageType.IMAGE_DATA:
                case StorageType.SPORT_DATA:
                    prefix = Constants.ECS_URL + tmpPath;
                    break;
                default:
                    break;
            }
            return prefix;
        }
    }

    public static void putObject2AwsS3(String storageType, byte[] bytes, String fileFullName) {
        InputStream inputStream = new ByteArrayInputStream(bytes);
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentLength(bytes.length);
        AwsS3Client.amazonS3().putObject(Constants.AWS_BUCKET_NAME, storageType + "/" + fileFullName, inputStream,
                meta);
    }

    public static String getEcsOrOssPath(String storageType, String subPath) {
        String localPath = StringUtils.EMPTY;
        String tmpPath = File.separator + storageType + File.separator + subPath + File.separator;

        switch (storageType) {
            case StorageType.AVATAR:
            case StorageType.FIRMWARE:
            case StorageType.PATH:
                localPath = Constants.OSS_PATH + tmpPath;
                break;
            case StorageType.DAILY_DATA:
            case StorageType.IMAGE_DATA:
            case StorageType.SPORT_DATA:
                localPath = Constants.ECS_PATH + tmpPath;
                break;
            default:
                break;
        }
        return localPath;
    }
}
