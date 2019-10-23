package com.dubbo.demo.core.utils;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 随机码生成器
 *
 * @author xuming
 */
public class CodeGenerator {
    // 随机码的长度
    private static int codeLength = 15;
    // 随机码包括的字符
    private static String[] charArray = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C",
            "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
            "Y", "Z"};

    private static String[] intArray = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

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

    // 生成随机int数 2015-02-26(目前仅应用于标示用户信息)
    public static int getInfoId(int id) {
        Random ran = new Random();
        StringBuffer sb = new StringBuffer("1");// System.out.println("==="+Integer.MAX_VALUE);
        // 由于int的最大值是2147483647，为了控制生成的int是合法的
        sb.append(Integer.toString(id));
        int length = 9 - (Integer.toString(id).length());
        for (int i = 0; i < length; i++) {
            int num = ran.nextInt(intArray.length);
            sb.append(intArray[num]);
        }
        int rtnInt = Integer.parseInt(sb.toString());
        return rtnInt;
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
        return CodeGenerator.getCodeString(ApplicationConstants.TOKEN_LENGTH);
    }

    /**
     * 生成15位随机码 0~9，A~Z组成
     *
     * @param count 数量
     * @return
     */
    public static String[] CodeGen(int count) {
        String[] codes = new String[count];
        for (int i = 0; i < count; i++) {
            codes[i] = getCodeString(codeLength);
        }
        return codes;
    }

    /**
     * @param @return
     * @return String
     * @throws
     * @Title: generate
     * @Description: (这里用一句话描述这个方法的作用)
     * @user liyf
     */
    public static String generateUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        // String[] codes = CodeGenerator.CodeGen(10000000);
        // System.out.println(codes.length);

	/*
     * Map<String, String> codeMap = new HashMap<String, String>();
	 * for (int i = 0; i < 14000000; i++) {
	 * String code = getCodeString(codeLength);
	 * codeMap.put(code, code);
	 * if(i%1000000==0){
	 * System.out.println("==="+code);
	 * // System.out.println(" run");
	 * }
	 * }
	 *
	 * codeMap.put("str", "str1");
	 * codeMap.put("str", "str2");
	 *
	 * System.out.println(codeMap.size());
	 */
        // System.out.println("==="+Integer.MAX_VALUE);
        // System.out.println("==="+getRandomInt(9));

        // String[] codes = CodeGenerator.CodeGen(10000000);
        // System.out.println(codes.length);
        System.out.println(getFileNameCodeString());

    }


}
