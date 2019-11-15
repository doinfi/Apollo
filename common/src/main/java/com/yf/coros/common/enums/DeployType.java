package com.yf.coros.common.enums;

/**
 * 打包类型
 */
public interface DeployType {

    /**
     * 现场正式环境
     */
    String PRODUCTION = "production";

    String PRODUCTION_CN = "productionCN";

    /**
     * 测试环境
     */
    String TEST = "test";
    /**
     * 开发环境
     */
    String DEVELOP = "develop";
}
