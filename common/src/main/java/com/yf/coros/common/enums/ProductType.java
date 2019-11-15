package com.yf.coros.common.enums;

/**
 * @author infi
 */
public enum ProductType {
    PACE(1, "PACE"),

    M1(1, "M1"),

    OMNI(2, "OMNI"),

    LINX(3, "LINX"),

    APEX_42MM(4, "B13"),

    APEX_46MM(5, "B15"),

    HA05(6, "HA05"),

    HA06(7, "HA06"),

    HA07(8, "HA07"),

    FD01(9, "FD01"),

    B16(10, "B16"),

    APEX_PRO(11, "APEX Pro");

    private final Integer status;

    private final String desc;

    ProductType(final Integer status, final String desc) {
        this.status = status;
        this.desc = desc;
    }

    public static Integer getValue(String value) {
        ProductType[] businessModeEnums = values();
        for (ProductType businessModeEnum : businessModeEnums) {
            if (businessModeEnum.desc().equals(value)) {
                return businessModeEnum.status();
            }
        }
        return null;
    }

    public static String getDesc(Integer value) {
        ProductType[] businessModeEnums = values();
        for (ProductType businessModeEnum : businessModeEnums) {
            if (businessModeEnum.status().equals(value)) {
                return businessModeEnum.desc();
            }
        }
        return null;
    }

    public Integer status() {
        return this.status;
    }

    public String desc() {
        return this.desc;
    }
}
