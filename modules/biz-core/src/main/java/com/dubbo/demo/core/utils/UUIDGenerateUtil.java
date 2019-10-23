/*
 * 文件名：UUIDGenerateUtil.java
 * 时 间：下午3:46:06
 * 作 者：liyf
 * 版 权： 2014-2022 智慧园区, 公司保留所有权利.
 * 联 系：www.szyungu.com
 */
package com.dubbo.demo.core.utils;

import java.util.UUID;

/**
 * @author liyf
 * @ClassName: UUIDGenerateUtil
 * @Description: (统一生成UUID序列)
 * @date 2014年8月12日 下午7:01:38
 */
public class UUIDGenerateUtil {
    private UUIDGenerateUtil() {

    }

    /**
     * @param @return
     * @return String
     * @throws
     * @Title: generate
     * @Description: (这里用一句话描述这个方法的作用)
     * @user liyf
     */
    public static String generate() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * @return
     * @方法名称: createOrderSn
     * @描述: 生成订单号
     * @参数:
     * @返回类型: String
     * @作者:chenyi
     * @可能抛出异常:
     */
    public static String createOrderSn(int sn) {
        sn++;
        String snstr = String.valueOf(sn);
        if (snstr.length() < 2) {
            snstr = "SN00000" + sn;
        } else if (snstr.length() < 3) {
            snstr = "SN0000" + sn;
        } else if (snstr.length() < 4) {
            snstr = "SN000" + sn;
        } else if (snstr.length() < 5) {
            snstr = "SN00" + sn;
        } else if (snstr.length() < 6) {
            snstr = "SN0" + sn;
        } else {
            snstr = "SN" + sn;
        }
        return snstr;
    }

}
