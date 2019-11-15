package com.yf.coros.common.enums;

/**
 * 规则键列表，按低位到高位的顺序，由于int为4字节，signed int最多保存15种规则，即最大值为0x7FFFFFFF，
 * 符号位不算在内，如需增加，请扩展
 * @author lihuaijin
 */
public enum DeviceRuleType {

    /**
     * 样机控制规则
     */
    demoDevice(0, "样机控制规则", "设备是否为样机，设置后该设备无法同步运动数据，无法查看运动数据。");

    private int ruleIndex;
    private String ruleName;
    private String description;

    DeviceRuleType(int ruleIndex, String ruleName, String description) {
        this.ruleIndex = ruleIndex;
        this.ruleName = ruleName;
        this.description = description;
    }

    public int getRuleIndex() {
        return ruleIndex;
    }
    public String getRuleName() {
        return ruleName;
    }
    public String getDescription() {
        return description;
    }

}
