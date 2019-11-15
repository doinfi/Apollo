package com.yf.coros.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author lihuaijin
 */
public class FirmwareTypeUtil {

    private static final String COROS = "COROS";

    private static final String PACE = "PACE";

    private static final String M1 = "M1";

    private static final String OMNI = "OMNI";

    private static final String HELM = "HELM";

    private static final String HELMET = "HELMET";

    private static final String HA05 = "HA05";

    private static final String HA06 = "HA06";

    private static final String HA07 = "HA07";

    private static final String APEX = "APEX";

    /**
     * 也是B13
     */
    private static final String APEXS = "APEXS";

    /**
     * 用大写作比较
     */
    private static final String APEX_42MM = APEX + " 42MM";

    private static final String APEX_46MM = APEX + " 46MM";

    private static final String APEX_PRO = APEX + " PRO";

    private static final String VERTIX = "VERTIX";

    private static final String B13 = "B13";

    private static final String B15 = "B15";

    private static final String B16 = "B16";

    private static final String B17 = "B17";

    private static final String FD01 = "FD01";

    /**
     * HA05
     */
    private static final String SAFESOUND_R = "SAFESOUND-R";

    /**
     * HA06
     */
    private static final String SAFESOUND_U = "SAFESOUND-U";

    /**
     * HA07
     */
    private static final String SAFESOUND_M = "SAFESOUND-M";

    private static final int MIN_SPORT_DATA_DEVICE_ID_LENGTH = 6;

    /**
     * 将别名还原为与激活表一致的产品类型标志
     */
    private static final Map<String, String> MERGE_TRANSFER_MAP = new HashMap<String, String>(){{
        put(PACE, COROS + " " + M1);
        put(M1, COROS + " " + M1);

        put(OMNI, COROS + " " + OMNI);

        put(HELM, COROS + " " + HELMET);

        put(HA05, COROS + " " + HA05);
        put(SAFESOUND_R, COROS + " " + HA05);
        put(HA06, COROS + " " + HA06);
        put(SAFESOUND_U, COROS + " " + HA06);
        put(HA07, COROS + " " + HA07);
        put(SAFESOUND_M, COROS + " " + HA07);

        // 防止device_id包含 B13字符串时的误判
        put(COROS + " " + B13, COROS + " " + B13);
        put(APEX_42MM, COROS + " " + B13);
        put(APEXS, COROS + " " + B13);

        put(COROS + " " + B15, COROS + " " + B15);
        put(APEX_46MM, COROS + " " + B15);

        put(APEX_PRO, COROS + " " + B17);

        put(COROS + " " + B16, COROS + " " + B16);
        put(VERTIX, COROS + " " + B16);

        put(COROS + " " + FD01, COROS + " " + FD01);
    }};

    public static Set<String> getFirmwareTypeList() {
        return new HashSet<>(MERGE_TRANSFER_MAP.values());
    }

    public static String trimForSportData(String deviceInfo) {
        if (StringUtils.isBlank(deviceInfo) || deviceInfo.length() <= MIN_SPORT_DATA_DEVICE_ID_LENGTH) {
            return null;
        }
        deviceInfo = deviceInfo.trim();
        String[] split = deviceInfo.split(" ");
        if (split.length <= 1) {
            return null;
        }
        int index = deviceInfo.lastIndexOf(" ");
        String firmwareTypeSrc = deviceInfo.substring(0, index).trim();
        return trimFirmwareType(firmwareTypeSrc);
    }

    private static String trimFirmwareType(String src) {
        if (StringUtils.isBlank(src)) {
            return null;
        }
        String upperSrc = src.trim().toUpperCase();
        for (Map.Entry<String, String> entry : MERGE_TRANSFER_MAP.entrySet()) {
            if (upperSrc.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return src;
    }

    public static void main(String[] args) {
        System.out.println(trimForSportData("COROS APEX 42mm 123925"));
        System.out.println(trimForSportData("COROS APEX 46mm 123925"));
        System.out.println(trimForSportData("COROS APEXs 123925"));
        System.out.println(trimForSportData("COROS PACE 123925"));
        System.out.println(trimForSportData("COROS VERTIX 123925"));
        System.out.println(trimForSportData("COROS FD01 123925"));
        System.out.println(trimForSportData("COROS SAFESOUND-R 123925"));
        String[] deviceIds = StringUtils.split("COROS SAFESOUND-R 123925",' ');
        //只保存6位的设备ID
        if(deviceIds!=null && deviceIds.length > 0){
            System.out.println(deviceIds[deviceIds.length-1]);
        }
        System.out.println(trimForSportData("COROS APEX PRO 123925"));
    }
}
