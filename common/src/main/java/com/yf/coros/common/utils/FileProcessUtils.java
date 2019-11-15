package com.yf.coros.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author infi
 */
@Slf4j
public class FileProcessUtils {

    public static void init() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            builder.newDocument();
        } catch (ParserConfigurationException e) {
            log.warn("FileProcess初始化失败", e);
        }
    }

    /**
     * 文件保存到本地磁盘路径，阿里云oss挂载到本地磁盘路径<br> 将byte[]保存到文件<br>
     *
     * @param fileData 文件数据
     * @param fileName 文件名
     * @param filePath 文件路径
     */
    public static void save2AliOssOrLocalDisk(byte[] fileData, String fileName, String filePath) {
        if (ArrayUtils.isEmpty(fileData)) {
            log.warn("文件为空不能写入磁盘，文件名： {}，文件路径 {}", fileName, filePath);
            return;
        }
        File dir = new File(filePath);
        File file = new File(dir, fileName);

        //判断文件目录是否存在
        if (!dir.exists() && dir.isDirectory()) {
            log.warn("1.目录不存在,准备创建" + filePath);
            boolean result = dir.mkdirs();
        }
        if (!file.getParentFile().exists()) {
            log.warn("2.目录不存在,再次创建" + file.getParentFile().getAbsolutePath());
            boolean result = file.getParentFile().mkdirs();
        }
        try (FileOutputStream fos = new FileOutputStream(file);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            bos.write(fileData);
        } catch (Exception e) {
            log.error("写入文件出错：", e);
        }
    }

    /**
     * 文件写入云盘
     *
     * @param fileData 文件数据
     * @param fileName 文件名
     * @param filePath 目录
     */
    public static void save2OssAndS3(byte[] fileData, String fileName, String filePath) {
        File file = new File(filePath + File.separator + fileName);
        try {
            FileUtils.writeByteArrayToFile(file, fileData);
        } catch (IOException e) {
            log.error("文件写入oss出错", e);
        }
    }

}
