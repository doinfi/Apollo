package com.yf.coros.common.enums;

/**
 * sony cep 文件类型
 * 1：1天的数据
 * 2：2天的数据
 * 3：3天的数据
 * 4：一周的数据
 * 5：28天的数据
 * 6：一年的gps数据
 * 7：一年的gln数据
 *
 * @author Infi
 */
public interface SonyCepType {
    int CEP_1DAY = 1;
    int CEP_2DAYS = 2;
    int CEP_3DAYS = 3;
    int CEP_1WEEK = 4;
    int CEP_28DAYS = 5;
    /**
     * 一年的gps
     */
    int CEP_ALM_GPS = 6;
    /**
     * 一年的gln
     */
    int CEP_ALM_GLN = 7;
}
