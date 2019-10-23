/**
 * @author administrator
 * @date 2008-9-1
 * @project COM.lingdian.TOOLS
 * @package com.lingdian.tools.util
 * @class Tools.java
 */
package com.dubbo.demo.core.utils;

import org.springframework.beans.BeanUtils;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具类.
 *
 * @author administrator
 * @version V100R100C01
 * @package com.lingdian.tools.util
 * @class Tools.java
 */
public class Tools {
    /**
     * 私有构造方法，不允许实例化
     */
    private Tools() {
    } // End Tools

    /**
     * 判断一个对象是否为空 字符串则去除前后的空格进行比较 数组则按长度为0进行比较
     *
     * @param object 数据对象
     * @return true:数据对象为空;false:数据对象非空.
     */
    public static boolean isEmpty(Object object) {
        return DataType.isEmpty(object);
    } // End isEmpty

    /**
     * 输出错误信息
     *
     * @param ex 异常对象
     */
    public static void logError(Exception ex) {
        System.err.println("==>>[" + ex + "]");
    } // End logError

    /**
     * 输出信息到控制台
     *
     * @param object 输出的数据对象
     */
    public static void log(Object object) {
        System.out.println(object);
    } // End log

    /**
     * 显示Map中的数据
     *
     * @param map 数据对象
     */
    @SuppressWarnings("rawtypes")
    public static void showMap(Map map) {
        if (!isEmpty(map)) {
            Iterator iterator = map.entrySet().iterator();

            // 遍历所有的数据显示
            while (iterator.hasNext()) {
                @SuppressWarnings("unchecked")
                Entry<Object, Object> entry = (Entry<Object, Object>) iterator.next();
                log(DataType.getString(entry.getKey()) + " : " + DataType.getString(entry.getValue()));
            }
        }
    } // End showMap

    /**
     * 连接两个byte数组
     *
     * @param startArray:开始数组
     * @param endArray:结束数组
     * @return byte[]:组合后的数组
     */
    public static byte[] catArray(byte[] startArray, byte[] endArray) {
        byte[] data;

        // 判断数组不为空时，连接数组
        if (!isEmpty(startArray) && !isEmpty(endArray)) {
            data = new byte[startArray.length + endArray.length];
            System.arraycopy(startArray, 0, data, 0, startArray.length);
            System.arraycopy(endArray, 0, data, startArray.length, endArray.length);
        } else {
            data = isEmpty(startArray) ? endArray : startArray;
        }

        return data;
    } // End catArray

    /**
     * 从一个byte[]数组中截取一部分
     *
     * @param src
     * @param begin
     * @param count
     * @return
     */
    public static byte[] subBytes(byte[] src, int begin, int count) {
        byte[] bs = new byte[count];
        for (int i = begin; i < begin + count; i++)
            bs[i - begin] = src[i];
        return bs;
    }

    /**
     * 获取操作系统的类型
     *
     * @return String 操作系统类型
     */
    public static String osType() {
        return System.getProperty("os.name");
    } // End osType

    /**
     * 获取操作系统版本
     *
     * @return String 版本号
     */
    public static String osVersion() {
        return System.getProperty("os.version");
    } // End osVersion

    /**
     * 方法描述：
     *
     * @Title: isNullOrEmpty @param @param object @param @return @return
     * boolean @user Administrator 2015年3月28日 @throws
     */
    public static boolean isNullOrEmpty(Object object) {
        return isEmpty(object);
    }

    /**
     * @Title: printInfo @Description: TODO(这里用一句话描述这个方法的作用) @param @param
     * e @param @return @return String @throws
     */
    public static String printInfo(Exception e) {

        StringBuffer bf = new StringBuffer();
        bf.append("\n---------------------------------" + new Date() + "---------------------------------------------------\n\n");
        for (StackTraceElement s : e.getStackTrace()) {
            bf.append(e.getClass() + ":  " + e.getMessage() + "\n");
            bf.append("\t at " + s.getClassName() + "." + s.getMethodName() + "(" + s.getFileName() + ":" + s.getLineNumber() + ")");
        }
        bf.append("\n\n-------------------------------------------------------------------------------------\n");
        return bf.toString();
    }

    // 将byte数组bRefArr转为一个整数,字节数组的低位是整型的低字节位
    public static int toHexInt(byte[] bRefArr) {
        int iOutcome = 0;
        byte bLoop;

        for (int i = 0; i < bRefArr.length; i++) {
            bLoop = bRefArr[i];
            iOutcome += (bLoop & 0xFF) << (8 * i);
        }
        return iOutcome;
    }

    //int 与 byte转换
    public static byte[] toHexByte(int iSource, int iArrayLen) {
        byte[] bLocalArr = new byte[iArrayLen];
        for (int i = 0; (i < 4) && (i < iArrayLen); i++) {
            bLocalArr[i] = (byte) (iSource >> 8 * i & 0xFF);
        }
        return bLocalArr;
    }

    public static String toHexString(byte[] bytes) {
        return toHexString(bytes, 0, bytes.length);
    }

    public static String toHexString(byte[] bytes, int start, int end) {
        StringBuffer sb = new StringBuffer();
        for (; start < end; ++start) {
            sb.append(String.format("%02x", bytes[start]));
        }
        return sb.toString();
    }

    /**
     * 将byte[]转为各种进制的字符串
     *
     * @param bytes:byte[]
     * @param radix:基数可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX， 超出范围后变为10进制
     * @return 转换后的字符串
     */
    public static String lHex2String(byte[] bytes, int radix) {
        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
    }

    public static byte[] lString2Hex(String hex, int radix) {
        BigInteger bigInteger = new BigInteger(hex, radix);
        return bigInteger.toByteArray();
    }

    public static byte[] lString2Hex(String hex, int radix, int arrayLength) {
        byte[] value = lString2Hex(hex, radix);
        int sourceLen = value.length;
        if (arrayLength <= sourceLen) {
            return value;
        }
        byte[] bLocalArr = new byte[arrayLength];
        for (int i = 1; i <= arrayLength; i++) {
            if (i <= sourceLen) {
                bLocalArr[arrayLength - i] = value[sourceLen - i];
            }
        }
        return bLocalArr;
    }


    public static String byte2hex(byte[] b) {
        StringBuffer sb = new StringBuffer();
        String tmp = "";
        for (int i = 0; i < b.length; i++) {
            tmp = Integer.toHexString(b[i] & 0XFF);
            if (tmp.length() == 1) {
                sb.append("0" + tmp);
            } else {
                sb.append(tmp);
            }
        }
        return sb.toString();
    }

    public static byte[] hexToByteArray(String s, int start, int end) {
        int len = (end - start) / 2 * 2;
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(start + i), 16) << 4) + Character.digit(s.charAt(start + i + 1), 16));
        }
        return data;
    }

    public static byte[] hexToByteArray(String s) {
        return hexToByteArray(s, 0, s.length());
    }

    // 判断输入的字符串是不是合法的数字
    public static boolean isDigit(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            try {
                Float.parseFloat(s);
                return true;
            } catch (NumberFormatException e1) {
                try {
                    Double.parseDouble(s);
                    return true;
                } catch (NumberFormatException e2) {
                    return false;
                }
            }
        }
    }

    public static String trimEnd(String stream, String trimstr) {
        // null或者空字符串的时候不处理
        if (stream == null || stream.length() == 0 || trimstr == null || trimstr.length() == 0) {
            return stream;
        }

        // 结束位置
        int epos = 0;

        // 正规表达式
        String regpattern = "[" + trimstr + "]*+";
        Pattern pattern = Pattern.compile(regpattern, Pattern.CASE_INSENSITIVE);

        // 去掉结尾的指定字符
        StringBuffer buffer = new StringBuffer(stream).reverse();
        Matcher matcher = pattern.matcher(buffer);
        if (matcher.lookingAt()) {
            epos = matcher.end();
            stream = new StringBuffer(buffer.substring(epos)).reverse().toString();
        }
        // 返回处理后的字符串
        return stream;
    }

    public static String trimStart(String stream, String trimstr) {
        // null或者空字符串的时候不处理
        if (stream == null || stream.length() == 0 || trimstr == null || trimstr.length() == 0) {
            return stream;
        }

        // 结束位置
        int epos = 0;

        // 正规表达式
        String regpattern = "[" + trimstr + "]*+";
        Pattern pattern = Pattern.compile(regpattern, Pattern.CASE_INSENSITIVE);

        // 去掉开头的指定字符
        Matcher matcher = pattern.matcher(stream);
        if (matcher.lookingAt()) {
            epos = matcher.end();
            stream = stream.substring(epos);
        }

        // 返回处理后的字符串
        return stream;
    }

    /**
     * 去掉指定字符串的开头和结尾的指定字符
     *
     * @param stream:要处理的字符串
     * @param trimstr:要去掉的字符串
     * @return 处理后的字符串
     */
    public static String trim(String stream, String trimstr) {
        // null或者空字符串的时候不处理
        if (stream == null || stream.length() == 0 || trimstr == null || trimstr.length() == 0) {
            return stream;
        }

        // 结束位置
        int epos = 0;

        // 正规表达式
        String regpattern = "[" + trimstr + "]*+";
        Pattern pattern = Pattern.compile(regpattern, Pattern.CASE_INSENSITIVE);

        // 去掉结尾的指定字符
        StringBuffer buffer = new StringBuffer(stream).reverse();
        Matcher matcher = pattern.matcher(buffer);
        if (matcher.lookingAt()) {
            epos = matcher.end();
            stream = new StringBuffer(buffer.substring(epos)).reverse().toString();
        }

        // 去掉开头的指定字符
        matcher = pattern.matcher(stream);
        if (matcher.lookingAt()) {
            epos = matcher.end();
            stream = stream.substring(epos);
        }

        // 返回处理后的字符串
        return stream;
    }

    public static byte[] bigByte2Little(byte[] src) {
        byte[] result = new byte[src.length];
        ByteBuffer bb = ByteBuffer.wrap(result).order(ByteOrder.LITTLE_ENDIAN);
        bb.put(src);
        return result;
    }

    public static List<Double> getDoubleList(String strList) {
        List<Double> list = new ArrayList<Double>();
        String[] parseList = strList.split(",");
        for (String str : parseList) {
            list.add(Double.valueOf(str));
        }
        return list;
    }

    public static int getIntValue(Integer value) {
        return Tools.isEmpty(value) ? 0 : value.intValue();
    }

    public static int getIntValue(Float value) {
        return Tools.isEmpty(value) ? 0 : value.intValue();
    }

    public static int getIntValue(Double value) {
        return Tools.isEmpty(value) ? 0 : value.intValue();
    }

    public static double getDoubleValue(Double value) {
        return Tools.isEmpty(value) ? 0 : value.doubleValue();
    }

    public static long getLongValue(Long value) {
        return Tools.isEmpty(value) ? 0 : value.longValue();
    }

    public static String getStringValue(String value) {
        return Tools.isEmpty(value) ? "" : value;
    }

    /**
     * 合并byte数组
     *
     * @param source
     * @param append
     * @return
     */
    public static byte[] combine(byte[] source, byte[] append) {
        int oldLength = Tools.isEmpty(source) ? 0 : source.length;
        int newLength = Tools.isEmpty(append) ? 0 : append.length;
        byte[] resultData = new byte[oldLength + newLength];
        if (!Tools.isEmpty(source)) {
            System.arraycopy(source, 0, resultData, 0, oldLength);
        }
        if (!Tools.isEmpty(newLength)) {
            System.arraycopy(append, 0, resultData, oldLength, newLength);
        }
        return resultData;
    }

    public static String subString(String str, int length) {
        if (Tools.isEmpty(str))
            return str;
        if (length < 1 || str.length() <= length)
            return str;
        return str.substring(0, length);
    }

    /**
     * url编码
     *
     * @param s
     * @param enc
     * @return
     */
    public static String encode(String s, String enc) {
        try {
            if (Tools.isEmpty(s)) {
                return "";
            }
            return URLEncoder.encode(s, enc);
        } catch (UnsupportedEncodingException e) {
            return s;
        }
    }

    public static void main(String[] args) {
        byte[] m = Tools.toHexByte(6684672, 3);
        byte[] n = new byte[]{0x00, 0x36, 0x66};
        System.out.println(Tools.byte2hex(n));
        String hex = Tools.lHex2String(n, 10);
        byte[] n2 = Tools.lString2Hex(hex, 10, 6);
        int int_n1 = Integer.parseInt(hex);
        int a = Tools.toHexInt(new byte[]{0x00, 0x00, 0x66});
        System.out.println(m);
        System.out.println(int_n1);
    }

    public static <T> T copy(T c) {
        if (Tools.isEmpty(c))
            return null;
        try {
            T t = (T) c.getClass().newInstance();
            BeanUtils.copyProperties(c, t);
            return t;
        } catch (Exception e) {
            return null;
        }
    }

    public static byte[] ObjectToByte(Object obj) {
        byte[] bytes = null;
        try {
            // object to bytearray  
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);

            bytes = bo.toByteArray();

            bo.close();
            oo.close();
        } catch (Exception e) {
            System.out.println("translation" + e.getMessage());
            e.printStackTrace();
        }
        return bytes;
    }
} // End Tools
