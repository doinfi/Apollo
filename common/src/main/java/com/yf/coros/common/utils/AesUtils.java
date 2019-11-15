package com.yf.coros.common.utils;

import com.yf.coros.common.enums.MessageKey;
import com.yf.coros.common.exception.YfException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.cryptonode.jncryptor.AES256JNCryptor;
import org.cryptonode.jncryptor.JNCryptor;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 通过AES算法对文本进行加密解密
 * 加密顺序
 * 1. 密码加密要先做md5加密：pwd = md5(pwd)
 * 2. 做异或运算，pwd = encrypt4InitStr(pwd，appkey)
 * 3. 使用aes加密，pwd = encrypt(pwd，appkey)
 * <p>
 * 解密顺序
 * 1. 使用aes解密，pwd = decrypt(pwd，appkey)
 * 2. 再做异或运算，pwd = encrypt4InitStr(pwd，appkey)
 *
 * @author xuming 2015-03-03
 */
@Slf4j
public class AesUtils {

    public static JNCryptor cryptor = new AES256JNCryptor(100);

    private static Cipher cipher; // 加密算法
    private static String ARITHMETIC = "AES";// 加密算法
    private static String CIPHER_ARITHMETIC = "AES/CBC/PKCS5Padding";// "算法/模式/补码方式"
    private static String IV_PARAMETER = "weloop3_2015_03#";// 使用CBC模式，需要一个向量iv，可增加加密算法的强度

    private static String CONTENT_TYPE = "UTF-8";

    /**
     * aes 加密
     *
     * @param content 加密对象
     * @param strKey  密钥
     * @return 加密后的对象
     */
    public static String encrypt(String content, String strKey, String ivStr) throws YfException {

        if (strKey == null) {
            log.info("strKey为空");
            return null;
        }
        // 判断Key是否为16位
        if (strKey.length() != 16) {
            log.info("strKey长度不是16位");
            return null;
        }

        try {
            byte[] byteKey = strKey.getBytes(CONTENT_TYPE);
            SecretKeySpec skeySpec = new SecretKeySpec(byteKey, ARITHMETIC);// 算法
            cipher = Cipher.getInstance(CIPHER_ARITHMETIC);// "算法/模式/补码方式"
            IvParameterSpec iv = new IvParameterSpec(null != ivStr ? ivStr.getBytes() : IV_PARAMETER.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(content.getBytes(CONTENT_TYPE));
            return new Base64().encodeToString(encrypted);// 此处使用BASE64做转码功能，同时能起到2次加密的作用。
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new YfException(MessageKey.PARAMETER_ERROR);
        }
    }

    /**
     * aes 加密
     *
     * @param content 加密对象
     * @param strKey  密钥
     * @return 加密后的内容
     */
    public static String encrypt(String content, String strKey) throws YfException {
        return encrypt(content, strKey, null);
    }

    /**
     * aes 解密
     *
     * @param content 解密对象
     * @param strKey  密钥
     * @return 解密后的对象
     */
    public static String decrypt(String content, String strKey, String ivStr) throws YfException {
        if (StringUtils.isBlank(content) || StringUtils.isBlank(strKey)
                || strKey.length() != 16) {
            log.error("解密出错,密钥为空或者长度不是16位");
            throw new YfException(MessageKey.PARAMETER_ERROR);
        }
        log.debug("解压参数：" + content + "密钥：" + strKey);
        try {
            byte[] byteKey = strKey.getBytes(CONTENT_TYPE);
            SecretKeySpec skeySpec = new SecretKeySpec(byteKey, ARITHMETIC);
            cipher = Cipher.getInstance(CIPHER_ARITHMETIC);
            IvParameterSpec iv = new IvParameterSpec(null != ivStr ? ivStr.getBytes() : IV_PARAMETER.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted = new Base64().decode(content);// 先用base64解密
            byte[] original = cipher.doFinal(encrypted);
            String originalString = new String(original, CONTENT_TYPE);
            return originalString;
        } catch (Exception ex) {
            log.error("解密出错: " + ex.getMessage());
            throw new YfException(MessageKey.PARAMETER_ERROR);
        }
    }

    /**
     * aes 解密
     *
     * @param content 解密对象
     * @param strKey  密钥
     * @return 解密后的对象
     */
    public static String decrypt(String content, String strKey) throws YfException {
        return decrypt(content, strKey, null);
    }

    /**
     * 加密
     */
    public static String encryptData(String plaintext, String password) {
        byte[] ciphertext = null;
        try {
            ciphertext = cryptor.encryptData(plaintext.getBytes(), password.toCharArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Hex.encodeHexString(ciphertext);
    }

    public static String decryptData(String plaintext, String password) {
        byte[] ciphertext = null;
        try {
            ciphertext = cryptor
                    .decryptData(Hex.decodeHex(plaintext.toCharArray()), password.toCharArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(ciphertext);
    }

    /**
     * 对原始字符串先进行异或运算
     *
     * @param initStr 被运算的字符
     * @param strKey  密钥
     * @return 运算之后的字符
     */
    public static String encrypt4InitStr(String initStr, String strKey) {
        byte[] data = initStr.getBytes();
        byte[] keyData = strKey.getBytes();
        int keyIndex = 0;
        for (int x = 0; x < initStr.length(); x++) {
            data[x] = (byte) (data[x] ^ keyData[keyIndex]);
            if (++keyIndex == keyData.length) {
                keyIndex = 0;
            }
        }
        return new String(data);
    }

    /**
     * 将二进制转换成16进制
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    public static String createClientLinkString(Map<String, String> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        return StringUtils.join(keys, ".");
    }

    public static String createClientLinkString2(Map<String, Object> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        return StringUtils.join(keys, ".");
    }

    public static String clientSign(String text, String key) throws Exception {
        text = text + key + text;
        return DigestUtils.md5Hex(text.getBytes("utf-8"));
    }

    public static byte[] toBytes(String hexString) {
        int size = hexString.length() / 2;
        byte[] buf = new byte[size];
        int index = 0;

        for (; index < size; ++index) {
            int value = Integer.valueOf(hexString.substring(index * 2, index * 2 + 2), 16);
            buf[index] = (byte) value;
        }
        return buf;
    }


    /**
     * 解密： 先使用 AesUtils.decrypt 方法解密,再使用AESUtil.encrypt4InitStr 异或运算解密
     *
     * @param content 加密后的字符串
     * @param appKey  解密密钥
     * @return 解密以后的内容
     */
    public static synchronized String decryptAndXOR(String content, String appKey)
            throws YfException {
        content = decrypt(content, appKey);
        return encrypt4InitStr(content, appKey);
    }

    /**
     *  加密：先位移再加密
     *
     * @param content 加密前的字符串
     * @param appKey  密钥
     * @return 加密以后的内容
     */
    public static synchronized String encryptAndXOR(String content, String appKey)
            throws YfException {
        content = encrypt4InitStr(content, appKey);
        return encrypt(content, appKey);
    }

    public static void main(String[] args) throws Exception {



    }
}
