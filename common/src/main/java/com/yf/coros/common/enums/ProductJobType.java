package com.yf.coros.common.enums;

/**
 * 产品任务类型。1：拉取产线入库的产品数据，2：推送产品数据到aws环境，3：推送产品数据到阿里云环境
 *
 * @author Infi
 */
public interface ProductJobType {
    /**
     * 拉取产线入库的产品数据
     */
    int PULL = 1;
    /**
     * 推送产品数据到aws环境
     */
    int PUSH_AWS = 2;
    /**
     * 推送产品数据到阿里云环境
     */
    int PUSH_ALI = 3;
    /**
     * 把产品表的渠道、批次信息更新到设备激活表
     */
    int UPDATE_CHANNEL = 4;
}
