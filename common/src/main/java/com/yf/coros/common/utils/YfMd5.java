package com.yf.coros.common.utils;

/**
 * MD5的算法在RFC1321 中定义 在RFC 1321中，给出了Test suite用来检验你的实现是否正确： MD5 ("") =
 * d41d8cd98f00b204e9800998ecf8427e MD5 ("a") = 0cc175b9c0f1b6a831c399e269772661 MD5 ("abc") =
 * 900150983cd24fb0d6963f7d28e17f72 MD5 ("message digest") = f96b697d7cb7938d525a2f31aaf161d0 MD5
 * ("abcdefghijklmnopqrstuvwxyz") = c3fcd3d76192e4007dfb496cca67e13b
 *
 * @author ok <p> 传入参数：一个字节数组 传出参数：字节数组的 MD5 结果字符串
 */

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public final class YfMd5 {

    /**
     * 获取md5加密字符串
     *
     * @param bytes byte数组
     * @return 校验码
     */
    public static String getMD5(byte[] bytes) {
        String reStr = null;
        try {
            // 创建具有指定算法名称的信息摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要。
            md.update(bytes);
            // 通过执行诸如填充之类的最终操作完成哈希计算
            byte ss[] = md.digest();
            reStr = bytes2String(ss);
        } catch (Exception e) {
            log.error("计算md5出错：", e);
        }
        return reStr;
    }

    /**
     * 获取md5加密字符串
     *
     * @param str
     * @return
     */
    public static String getMD5(String str) {
        String reStr = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");// 创建具有指定算法名称的信息摘要
            md.update(str.getBytes("utf-8"));// 使用指定的字节更新摘要。
            byte ss[] = md.digest();// 通过执行诸如填充之类的最终操作完成哈希计算
            reStr = bytes2String(ss);
        } catch (Exception e) {

        }
        return reStr;
    }

    // 获取文件的MD5 code
    public static String getMD5CodeFromFile(File file) {
        byte[] b = createChecksum(file);
        if (null == b) {
            log.error("Error:create md5 string failure!");
            return null;
        }
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < b.length; i++) {
            result.append(Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1));
        }
        return result.toString();

    }

    private static byte[] createChecksum(File file) {
        InputStream fis = null;
        try {
            fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            MessageDigest complete = MessageDigest.getInstance("MD5");
            int numRead = -1;

            while ((numRead = fis.read(buffer)) != -1) {
                complete.update(buffer, 0, numRead);
            }
            return complete.digest();
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(), e);
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                if (null != fis) {
                    fis.close();
                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
        return null;

    }

    private static String bytes2String(byte[] aa) {// 将字节数组转换为字符串
        String hash = "";
        for (int i = 0; i < aa.length; i++) {// 循环数组
            int temp;
            if (aa[i] < 0) // 如果小于零，将其变为正数
            {
                temp = 256 + aa[i];
            } else {
                temp = aa[i];
            }
            if (temp < 16) {
                hash += "0";
            }
            hash += Integer.toString(temp, 16);// 转换为16进制
        }
        hash = hash.toLowerCase();// 全部转换为小写
        return hash;
    }

}
