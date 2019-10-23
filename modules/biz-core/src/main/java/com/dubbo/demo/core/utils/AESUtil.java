package com.dubbo.demo.core.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.bouncycastle.crypto.CryptoException;
import org.cryptonode.jncryptor.AES256JNCryptor;
import org.cryptonode.jncryptor.JNCryptor;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.*;

/**
 * 通过AES算法对文本进行加密解密
 *
 * @author xuming 2015-03-03
 */
public class AESUtil {

    public static JNCryptor cryptor = new AES256JNCryptor(100);

    private static final Logger log = Logger.getLogger(AESUtil.class.getClass());
    private static Cipher cipher; // 加密算法
    private static String ARITHMETIC = "AES";// 加密算法
    private static String CIPHER_ARITHMETIC = "AES/CBC/PKCS5Padding";// "算法/模式/补码方式"
    private static String IV_PARAMETER = "dubbo-demo-test-aes";// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
    private static String CONTENT_TYPE = "UTF-8";

    // 加密
    public static String encrypt(String content, String strKey) {

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
            IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(content.getBytes(CONTENT_TYPE));
            return new Base64().encodeToString(encrypted);// 此处使用BASE64做转码功能，同时能起到2次加密的作用。
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    // 解密
    public static String decrypt(String content, String strKey) {

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
            SecretKeySpec skeySpec = new SecretKeySpec(byteKey, ARITHMETIC);
            cipher = Cipher.getInstance(CIPHER_ARITHMETIC);
            IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted = new Base64().decode(content);// 先用base64解密
            byte[] original = cipher.doFinal(encrypted);
            String originalString = new String(original, CONTENT_TYPE);
            return originalString;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    /**
     * 加密
     *
     * @param plaintext
     * @param password
     * @return
     * @throws CryptoException
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
            ciphertext = cryptor.decryptData(Hex.decodeHex(plaintext.toCharArray()), password.toCharArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(ciphertext);
    }

    // 对原始字符串先进行异或运算
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
     *
     * @param buf
     * @return
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

    static public Date decodeBongTime(byte[] bytes) {
        Calendar calendar = GregorianCalendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        bb.order(ByteOrder.BIG_ENDIAN);

        int time = 0xffffffff & bb.getInt();

        calendar.set(Calendar.MONTH, ((time >> 28) & 0xf) - 1);
        calendar.set(Calendar.DATE, (time >> 23) & 0x1f);
        calendar.set(Calendar.HOUR_OF_DAY, (time >> 18) & 0x1f);
        calendar.set(Calendar.MINUTE, (time >> 12) & 0x3f);
        calendar.set(Calendar.YEAR, 2000 + ((time >> 4) & 0x3f));
        return calendar.getTime();
    }

    static public int getstep0(byte[] bytes, byte[] v1) {
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        bb.order(ByteOrder.BIG_ENDIAN);

        ByteBuffer b1 = ByteBuffer.wrap(v1);
        b1.order(ByteOrder.BIG_ENDIAN);
        int step = 0xffffffff & bb.getInt();
        int value0 = 0xffffffff & b1.getInt();

        int a = step >> 27 & 0x1f;
        int b = step >> 22 & 0x1f;
        int c = step >> 17 & 0x1f;
        int d = step >> 12 & 0x1f;
        int e = step >> 7 & 0x1f;
        int f = (step & 0x7f) | ((value0 >> 4) & 0x80); // | ((step << 3) &
        // 0x80);
        // System.out.println(f);
        return f;
    }

    static public int getstep1(byte[] bytes, byte[] v1) {
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        bb.order(ByteOrder.BIG_ENDIAN);
        ByteBuffer b1 = ByteBuffer.wrap(v1);
        b1.order(ByteOrder.BIG_ENDIAN);
        int step = 0xffffffff & bb.getInt();
        int value0 = 0xffffffff & b1.getInt();
        int a = step >> 27 & 0x1f;
        int b = step >> 22 & 0x1f;
        int c = step >> 17 & 0x1f;
        int d = step >> 12 & 0x1f;
        int e = step >> 7 & 0x1f;
        int f = step & 0x7f | ((value0 >> 3) & 0x80); // (step & 0x7f) | ((step
        // << 3) & 0x80);
        // System.out.println(f);
        return f;
    }

    public static void main(String[] args) {
    /*
     * key需要为16位。
	 */
        String strKey = "1234567890123456";
        // 需要加密的字串
        String content = "13670100827";// 最原始的字符串

        System.out.println("动态的加密Key：" + strKey);// 动态的Key，由APP提交给服务器
        // 对原始字符串进行异或运算
        System.out.println("最原始字符串：" + content);
        String xorStr = encrypt4InitStr(content, strKey);// 异或后的字符串
        System.out.println("异或运算后字符串：" + xorStr);

        // AES加密
        String enString = AESUtil.encrypt(xorStr, strKey);// AES加密后的字符串
        System.out.println("AES加密后的字串是：" + enString);

        // AES解密
        String DeString = AESUtil.decrypt(enString, strKey);// AES解密字符串
        System.out.println("AES解密后的字串是：" + DeString);

        System.out.println("最后得到的最原始的字符串：" + encrypt4InitStr(DeString, strKey));

        // String str
        // ="0301afa56271e8a0365746774f6526ec21e3d90118caeb1091437f85354a56439ad9afe14ec1e0b35ef85f3a3f3f401984472a41abb3d67d83c012973545883ab83309f4605d3d1140b5228115ae9d0c250c";
        // String retr =decryptData(str,"CdFzeUoOvGptXwkR");
        // System.out.println(retr);
        String aa = "8d3830f0c1420000d8c00000015d0159"; // 85b814f500000f0c00000f1d05f10358
        // 85b7b0f5a08013b159421637027e07af
        // 85b814f500000f0c00000f1d05f10358,
        // 85b7b0f5a08013b159421637027e07af,
        String bb = aa.substring(0, 8);
        String cc = aa.substring(8, 16);
        String dd = aa.substring(16, 24);

        byte[] b = toBytes(bb);
        byte[] c = toBytes(cc);
        byte[] d = toBytes(dd);
        getstep0(c, b);
        getstep1(d, b);
        Date e = decodeBongTime(b);
        System.out.println(DateUtil.getString(e, "yyyy-MM-dd HH:mm"));

    }

}
