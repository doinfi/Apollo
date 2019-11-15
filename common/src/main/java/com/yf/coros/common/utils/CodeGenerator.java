package com.yf.coros.common.utils;

import com.yf.coros.common.config.ApplicationContants;
import java.util.Date;
import java.util.Random;

/**
 * 随机码生成器
 *
 * @author xuming
 */
public class CodeGenerator {

    // 随机码包括的字符
    private static String[] charArray = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8",
        "9", "A", "B", "C",
        "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
        "V", "W", "X",
        "Y", "Z"};

    private static String[] intArray = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8",
        "9"};

    // 生成数字随机数
    public static String getIntCodeString(int length) {
        Random ran = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int num = ran.nextInt(intArray.length);
            sb.append(intArray[num]);
        }
        return sb.toString();
    }

    public static String getCodeString(int length) {
        Random ran = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int num = ran.nextInt(charArray.length);
            sb.append(charArray[num]);
        }
        return sb.toString();
    }

    public static String getFileNameCodeString() {
        return (new Date()).getTime() / 1000 + getCodeString(20);
    }

    public static String getAccessToken() {
        return CodeGenerator.getCodeString(ApplicationContants.TOKEN_LENGTH);
    }

}
