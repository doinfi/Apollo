package com.yf.coros.common.enums;

/**
 * 规则键列表，按低位到高位的顺序，由于int为4字节，signed int最多保存15种规则，即最大值为0x7FFFFFFF，
 * 符号位不算在内，如需增加，请扩展
 * @author lihuaijin
 */
public enum UserRuleType {

    /**
     * 内部用户白名单
     */
    whiteListInternalUser(0, "白名单内部用户", "用户是否设置为白名单用户，设置后用户不受内部用户限制约束。"),

    blackListBannedUser(1, "黑名单被屏蔽用户", "用户是否设置为黑名单用户，设置后用户的运动数据和日常数据不做解析，会返回一个特定错误码。"),

    whiteListAutoFeedbackUser(2, "自动问题反馈用户", "用户是否设置为自动反馈，设置后用户app会定时触发问题反馈提交。"),

    autoBannedUser(3, "自动屏蔽用户", "用户的运动和日常数据如果触发导致服务器解析崩溃，则会自动被添加为黑名单用户。");

    private int ruleIndex;
    private String ruleName;
    private String description;

    UserRuleType(int ruleIndex, String ruleName, String description) {
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
