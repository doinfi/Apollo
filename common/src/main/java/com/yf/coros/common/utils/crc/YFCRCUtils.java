package com.yf.coros.common.utils.crc;

import java.util.zip.CRC32;

/**
 * CRC校验码工具类
 *
 * @author Infi
 * @date 17/6/30
 */
public class YFCRCUtils {


    /**
     * 获取crc16校验码
     *
     * @param data 数据
     * @return 校验码
     */
    public static int crc16Int(byte[] data) {
        return crc16(data, data.length, (short) 0xffff) & 0xffff;
    }

    /**
     * 获取crc16校验码字符串
     *
     * @param data 数据
     * @return 校验码字符串
     */
    public static String crc16Str(byte[] data) {
        short crcShort = crc16(data, data.length, (short) 0xffff);
        return String.format("%x", crcShort);
    }

    /**
     * crc16校验码
     *
     * @param data 数据
     * @param size 数据长度
     * @param initCrc 默认initCrc传的是0xffff
     * @return 校验码
     */
    public static short crc16(byte[] data, int size, short initCrc) {
        return crc16(data, 0, size, initCrc);
    }

    /**
     * crc16校验码
     *
     * @param data 数据
     * @param offset 开始位置
     * @param size 数据长度
     * @param initCrc 默认initCrc传的是0xffff
     * @return 校验码
     */
    public static short crc16(byte[] data, int offset, int size, short initCrc) {
        int crc = 0xffff & initCrc;
        size += offset;
        for (int i = offset; i < size; i++) {
            crc = ((0xff00 & crc) >> 8) | ((0xff & crc) << 8);
            crc ^= 0xff & data[i];
            crc ^= (crc & 0xff) >> 4;
            crc ^= 0xffff & ((crc << 8) << 4);
            crc ^= ((crc & 0xff) << 4) << 1;
        }
        return (short) (0xffff & crc);
    }

    /**
     * 获得bytes对象的crc校验码
     *
     * @param bytes bytes数据
     * @return 校验码
     */
    public static String getCRC32Code(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        CRC32 c32 = new CRC32();
        c32.update(bytes);
        return Long.toHexString(c32.getValue());
    }
}
