package com.yf.coros.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 62进制long类型缩短工具
 * @author lihuaijin
 */
public final class Long2ShortStringUtil {
    /**
     * 10+26+26=62
     * 10进制字符串转换62进制字符串
     */
    private static final String INNER_STR = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 62
     */
    private static final int RULE_NUM = INNER_STR.length();

    private static final String REGEX = "\\w+";

    private static final String MAX_VAR;

    static {
        MAX_VAR = toShortString(Long.MAX_VALUE);
    }

    public static String toShortString(Long input) {
        if (null == input || input <= 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        if (input < RULE_NUM) {
            return sb.append(INNER_STR.charAt(input.intValue())).toString();
        }
        Long rest = input;
        while (rest > 0) {
            Long temp = rest % RULE_NUM;
            rest = rest / RULE_NUM;
            sb.append(INNER_STR.charAt(temp.intValue()));
            if (rest < RULE_NUM) {
                sb.append(INNER_STR.charAt(rest.intValue()));
                rest = 0L;
            }
        }
        sb.reverse();
        return sb.toString();
    }

    public static Long toLong(String shortStr) {
        if (StringUtils.isEmpty(shortStr)) {
            return null;
        }
        String src = shortStr.replaceAll("\\s", "");
        if (!isValidShortString(src)) {
            return null;
        }
        Long result = 0L;
        int length = src.length();
        for (int i = 0; i < length; i++) {
            int index = INNER_STR.indexOf(src.charAt(i));
            result += ((Double) (index * Math.pow(1d * RULE_NUM, 1d * (length - i - 1)))).longValue();
        }
        return result;
    }

    private static boolean isValidShortString(String src) {
        if (!src.matches(REGEX)) {
            return false;
        }
        int srcLength = src.length();
        if (srcLength > MAX_VAR.length()) {
            return false;
        }
        else if (srcLength < MAX_VAR.length()) {
            return true;
        }
        else {
            for (int i = 0; i < srcLength; i++) {
                int i1 = INNER_STR.indexOf(MAX_VAR.charAt(i));
                int i2 = INNER_STR.indexOf(src.charAt(i));
                if (i1 < i2) {
                    return false;
                }
                if (i1 > i2) {
                    return true;
                }
            }
            return true;
        }
    }

//    public static void main(String[] args) {
//        System.out.println(toShortString(403399145196437504L));
//    }

//    public static void main1(String[] args) {
//        int invalidCount = 0;
//        int wrongCount = 0;
//        long t1 = System.currentTimeMillis();
//        System.out.println("start time: " + t1 / 1000);
//        int round = 1000000;
//        for (int i = 0; i < round; i++) {
//            Long src = new Random().nextLong();
//            if (src < 0) {
//                src = -src;
//            }
//            String shortStr = toShortString(src);
//
//            Long transLong = toLong(shortStr);
//
//            if (null == transLong) {
//                invalidCount++;
//            }
//            if (!src.equals(transLong)) {
//                wrongCount++;
//            }
//        }
//        System.out.println("failed count: " + invalidCount);
//        System.out.println("wrong count: " + invalidCount);
//        long t2 = System.currentTimeMillis();
//        System.out.println("end time: " + t2 / 1000);
//        System.out.println("平均每次运算耗时: " + (1D + t2 - t1) / round + "毫秒");
//    }
}
