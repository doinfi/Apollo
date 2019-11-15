package com.yf.coros.common.enums;

/**
 * 算法校正数据类型
 *
 * @author Infi
 * @date 17/2/19
 */
public enum AlgorithmProfileDataType {
    RHR(1, "RHR"),
    VO2MAX(2, "VO2MAX"),
    STRIDE_LENGTH(3, "Stride Length"),
    STROKE_LENGTH(4, "Stroke Length");

    private final Integer id;
    private final String desc;

    AlgorithmProfileDataType(final Integer id, final String desc) {
        this.id = id;
        this.desc = desc;
    }

    public static Integer getValue(String value) {
        AlgorithmProfileDataType[] businessModeEnums = values();
        for (AlgorithmProfileDataType businessModeEnum : businessModeEnums) {
            if (businessModeEnum.desc().equals(value)) {
                return businessModeEnum.id();
            }
        }
        return null;
    }

    public static String getDesc(Integer value) {
        AlgorithmProfileDataType[] businessModeEnums = values();
        for (AlgorithmProfileDataType businessModeEnum : businessModeEnums) {
            if (businessModeEnum.id().equals(value)) {
                return businessModeEnum.desc();
            }
        }
        return null;
    }

    public Integer id() {
        return this.id;
    }

    public String desc() {
        return this.desc;
    }
}
