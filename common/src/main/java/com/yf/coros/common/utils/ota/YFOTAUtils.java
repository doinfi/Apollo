/**
 * @author administrator
 * @date 2011-12-15
 * @package com.lingdian.tools.data
 * @class DataType.java
 */
package com.yf.coros.common.utils.ota;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * ota升级帮助类
 */
public class YFOTAUtils {


    /**
     * 版本号转long 版本号格式x.x.x.x 或者A.B.C.D 字母都表示数字 转64位数字的方法,(A<<48)|(B<<32)|(C<<16)|D
     *
     * @param version 版本号x.x.x.x
     * @return 版本号的64位整数值
     */
    public static Long appVersionToNumber(String version) {
        return getVersion(StringUtils.split(version, "."));
    }

    /**
     * 版本号转long 版本号格式x.x.x.x 或者A.B.C.D 字母都表示数字 转64位数字的方法,(A<<48)|(B<<32)|(C<<16)|D
     *
     * @param version         版本号x.x.x.x
     * @param isGroupFirmware 是否为分组固件
     * @return 版本号的64位整数值
     */
    public static Long versionToNumber(String version, boolean isGroupFirmware) {
        String[] versionStrArr = StringUtils.split(version, ".");
        // 0是默认分组
        if (!isGroupFirmware) {
            return getVersion(versionStrArr);
        }
        // 固件版本号少于3位，就认为不是分组固件类型
        if (versionStrArr.length <= 2) {
            return getVersion(versionStrArr);
        }

        String[] groupVersionStrArr = new String[versionStrArr.length - 1];
        for (int i = 0, count = versionStrArr.length - 1; i < count; i++) {
            groupVersionStrArr[i] = versionStrArr[i];
        }

        return getVersion(groupVersionStrArr);
    }

    /**
     * 固件版本号
     *
     * @param versionStrArr 固件版本号数组
     * @return 固件版本号
     */
    private static Long getVersion(String[] versionStrArr) {
        Long versionNumber = 0L;
        for (int i = 0; i < versionStrArr.length; i++) {
            switch (i) {
                case 0:
                    versionNumber = versionNumber | (NumberUtils.toLong(versionStrArr[i]) << 48);
                    break;
                case 1:
                    versionNumber = versionNumber | (NumberUtils.toLong(versionStrArr[i]) << 32);
                    break;
                case 2:
                    versionNumber = versionNumber | (NumberUtils.toLong(versionStrArr[i]) << 16);
                    break;
                case 3:
                    versionNumber = versionNumber | NumberUtils.toLong(versionStrArr[i]);
                    break;
                default:
                    break;
            }
        }
        return versionNumber;
    }

    /**
     * 获取固件分组
     *
     * @param firmwareVersion 固件版本号
     * @param isGroupFirmware 固件是否有分组功能
     * @return 分组
     */
    public static int getGroup(String firmwareVersion, boolean isGroupFirmware) {
        // 0是默认分组
        if (!isGroupFirmware) {
            return 0;
        }
        // 固件版本号少于3位，就认为不是分组固件类型
        String[] versionStrArr = StringUtils.split(firmwareVersion, ".");
        if (versionStrArr.length <= 2) {
            return 0;
        }
        return NumberUtils.toInt(versionStrArr[versionStrArr.length - 1]);
    }
}
