package com.dubbo.demo.logs.operate;

/**
 * 概述: 用户操作日志操作动作编号和内容。用于操作日志记录<br>
 * 背景: 把操作编号迁移到新平台<br>
 * <p>
 * Created by Infi on 17/3/9.
 */
public interface OperateCodes {

    /**
     * 用户注册code
     */
    Integer REGISTER_CODE = 0;
    /**
     * 用户注册content
     */
    String REGISTER_CONTENT = "用户注册";
    /**
     * 登录code
     */
    Integer LOGIN_CODE = 1;
    /**
     * 登录content
     */
    String LOGIN_CONTENT = "登录";
    /**
     * 第三方登录code
     */
    Integer LOGIN_OPEN_CODE = 2;
    /**
     * 第三方登录content
     */
    String LOGIN_OPEN_CONTENT = "第三方登录";
    /**
     * 退出code
     */
    Integer EXIT_CODE = 3;
    /**
     * 退出content
     */
    String EXIT_CONTENT = "退出";
    /**
     * 设置账号信息code
     */
    Integer SET_ACCOUNT_INFO_CODE = 4;
    /**
     * 设置账号信息content
     */
    String SET_ACCOUNT_INFO_CONTENT = "设置账号信息";
    /**
     * 登记个人资料code
     */
    Integer SAVE_USER_INFO_CODE = 5;
    /**
     * 登记个人资料content
     */
    String SAVE_USER_INFO_CONTENT = "登记个人资料";
    /**
     * 获取本人详细信息code
     */
    Integer GET_USER_DATA_CODE = 6;
    /**
     * 获取本人详细信息content
     */
    String GET_USER_DATA_CONTENT = "获取本人详细信息";
    /**
     * 修改个人资料code
     */
    Integer UPDATE_USER_DATA_CODE = 7;
    /**
     * 修改个人资料content
     */
    String UPDATE_USER_DATA_CONTENT = "修改个人资料";
    /**
     * 修改个人头像code
     */
    Integer UPDATE_USER_HEAD_SCULPTURE_CODE = 8;
    /**
     * 修改个人头像content
     */
    String UPDATE_USER_HEAD_SCULPTURE_CONTENT = "修改个人头像";
    /**
     * 隐私设置code
     */
    Integer SET_PRIVACY_CODE = 9;
    /**
     * 隐私设置content
     */
    String SET_PRIVACY_CONTENT = "隐私设置";
    /**
     * 上传经纬度与推送相关信息code
     */
    Integer UPLOAD_LOCATION_AND_PUSH_CODE = 10;
    /**
     * 上传经纬度与推送相关信息content
     */
    String UPLOAD_LOCATION_AND_PUSH_CONTENT = "上传经纬度与推送相关信息";
    /**
     * 登录退出code
     */
    Integer LOGOUT_CODE = 11;
    /**
     * 登录退出content
     */
    String LOGOUT_CONTENT = "登录退出";
    /**
     * 修改密码code
     */
    Integer UPDATE_PASSWORD_CODE = 12;
    /**
     * 修改密码content
     */
    String UPDATE_PASSWORD_CONTENT = "修改密码";
    /**
     * 忘记密码code
     */
    Integer FORGET_PASSWORD_CODE = 13;
    /**
     * 忘记密码content
     */
    String FORGET_PASSWORD_CONTENT = "忘记密码";
    /**
     * 重置密码(Email注册用户)code
     */
    Integer RESET_PASSWORD_EMAIL_CODE = 14;
    /**
     * 重置密码(Email注册用户)content
     */
    String RESET_PASSWORD_EMAIL_CONTENT = "重置密码(Email注册用户)";
    /**
     * 重置密码(手机注册用户)code
     */
    Integer RESET_PASSWORD_MOBILE_CODE = 15;
    /**
     * 重置密码(手机注册用户)content
     */
    String RESET_PASSWORD_MOBILE_CONTENT = "重置密码(手机注册用户)";
    /**
     * 个人首页code
     */
    Integer HOMEPAGE_CODE = 16;
    /**
     * 个人首页content
     */
    String HOMEPAGE_CONTENT = "个人首页";
    /**
     * 获取用户信息code
     */
    Integer GET_USER_INFO_CODE = 17;
    /**
     * 获取用户信息content
     */
    String GET_USER_INFO_CONTENT = "获取用户信息";
    /**
     * 设置运动目标code
     */
    Integer SET_MOVING_TARGET_CODE = 18;
    /**
     * 设置运动目标content
     */
    String SET_MOVING_TARGET_CONTENT = "设置运动目标";
    /**
     * 空气质量信息code
     */
    Integer AIR_QUALITY_INFO_CODE = 19;
    /**
     * 空气质量信息content
     */
    String AIR_QUALITY_INFO_CONTENT = "空气质量信息";
    /**
     * 通知消息code
     */
    Integer MESSAGE_CODE = 20;
    /**
     * 通知消息content
     */
    String MESSAGE_CONTENT = "通知消息";
    /**
     * 通讯录匹配注册用户code
     */
    Integer ADDRESS_BOOK_MATCHING_USER_CODE = 21;
    /**
     * 通讯录匹配注册用户content
     */
    String ADDRESS_BOOK_MATCHING_USER_CONTENT = "通讯录匹配注册用户";
    /**
     * 搜索用户code
     */
    Integer SEARCH_USER_CODE = 22;
    /**
     * 搜索用户content
     */
    String SEARCH_USER_CONTENT = "搜索用户";
    /**
     * 添加好友code
     */
    Integer ADD_FRIENDS_CODE = 23;
    /**
     * 添加好友content
     */
    String ADD_FRIENDS_CONTENT = "添加好友";
    /**
     * 接受好友code
     */
    Integer ACCEPTING_FRIEND_CODE = 24;
    /**
     * 接受好友content
     */
    String ACCEPTING_FRIEND_CONTENT = "接受好友";
    /**
     * 我的好友排行榜(排名)code
     */
    Integer FRIEND_RANKING_CODE = 25;
    /**
     * 我的好友排行榜(排名)content
     */
    String FRIEND_RANKING_CONTENT = "我的好友排行榜(排名)";
    /**
     * 我的好友列表code
     */
    Integer MY_FRIEND_LIST_CODE = 26;
    /**
     * 我的好友列表content
     */
    String MY_FRIEND_LIST_CONTENT = "我的好友列表";
    /**
     * 删除好友code
     */
    Integer DELETE_FRIENDS_CODE = 27;
    /**
     * 删除好友content
     */
    String DELETE_FRIENDS_CONTENT = "删除好友";
    /**
     * 好友请求列表code
     */
    Integer FRIEND_REQUEST_LIST_CODE = 28;
    /**
     * 好友请求列表content
     */
    String FRIEND_REQUEST_LIST_CONTENT = "好友请求列表";
    /**
     * 是否有好友请求code
     */
    Integer WHETHER_FRIEND_REQUEST_CODE = 29;
    /**
     * 是否有好友请求content
     */
    String WHETHER_FRIEND_REQUEST_CONTENT = "是否有好友请求";
    /**
     * 清空好友请求标识code
     */
    Integer EMPTY_FRIEND_REQUEST_CODE = 30;
    /**
     * 清空好友请求标识content
     */
    String EMPTY_FRIEND_REQUEST_CONTENT = "清空好友请求标识";
    /**
     * 获取表盘信息code
     */
    Integer GET_WATCHFACE_INFO_CODE = 31;
    /**
     * 获取表盘信息content
     */
    String GET_WATCHFACE_INFO_CONTENT = "获取表盘信息";
    /**
     * 获取表盘信息列表code
     */
    Integer GET_WATCHFACE_INFO_LIST_CODE = 32;
    /**
     * 获取表盘信息列表content
     */
    String GET_WATCHFACE_INFO_LIST_CONTENT = "获取表盘信息列表";
    /**
     * 表盘下载成功通知code
     */
    Integer DOWNLOAD_SUCCESS_WATCHFACE_CODE = 33;
    /**
     * 表盘下载成功通知content
     */
    String DOWNLOAD_SUCCESS_WATCHFACE_CONTENT = "表盘下载成功通知";
    /**
     * 提交数据同步code
     */
    Integer SUBMIT_DATA_SYNC_CODE = 34;
    /**
     * 提交数据同步content
     */
    String SUBMIT_DATA_SYNC_CONTENT = "提交数据同步";
    /**
     * 获取数据同步code
     */
    Integer GET_DATA_SYNC_CODE = 35;
    /**
     * 获取数据同步content
     */
    String GET_DATA_SYNC_CONTENT = "获取数据同步";
    /**
     * 判断token是否失效code
     */
    Integer CHECK_TOKEN_CODE = 36;
    /**
     * 判断token是否失效content
     */
    String CHECK_TOKEN_CONTENT = "判断token是否失效";
    /**
     * 客户端版本检查code
     */
    Integer CHECK_CLIENT_VERSION_CODE = 37;
    /**
     * 客户端版本检查content
     */
    String CHECK_CLIENT_VERSION_CONTENT = "客户端版本检查";
    /**
     * 获取最新固件信息code
     */
    Integer GET_LATEST_FIRMWARE_INFO_CODE = 38;
    /**
     * 获取最新固件信息content
     */
    String GET_LATEST_FIRMWARE_INFO_CONTENT = "获取最新固件信息";
    /**
     * 手环激活code
     */
    Integer ACTIVATION_WAREBAND_CODE = 39;
    /**
     * 手环激活content
     */
    String ACTIVATION_WAREBAND_CONTENT = "手环激活";
    /**
     * 收藏表盘code
     */
    Integer COLLECT_WATCHFACE_CODE = 40;
    /**
     * 收藏表盘content
     */
    String COLLECT_WATCHFACE_CONTENT = "收藏表盘";
    /**
     * 设置闹钟code
     */
    Integer SET_CLOCK_CODE = 41;
    /**
     * 设置闹钟content
     */
    String SET_CLOCK_CONTENT = "设置闹钟";
    /**
     * 设置勿扰信息code
     */
    Integer SET_NOT_DISTURB_CODE = 42;
    /**
     * 设置勿扰信息content
     */
    String SET_NOT_DISTURB_CONTENT = "设置勿扰信息";
    /**
     * 设置事件提醒code
     */
    Integer SET_EVENT_REMIND_CODE = 43;
    /**
     * 设置事件提醒content
     */
    String SET_EVENT_REMIND_CONTENT = "设置事件提醒";
    /**
     * 设置开关code
     */
    Integer SET_ON_OFF_CODE = 44;
    /**
     * 设置开关content
     */
    String SET_ON_OFF_CONTENT = "设置开关";
    /**
     * 设置消息过滤code
     */
    Integer SET_MESSAGE_FILTER_CODE = 45;
    /**
     * 设置消息过滤content
     */
    String SET_MESSAGE_FILTER_CONTENT = "设置消息过滤";
    /**
     * 获取账号设置信息code
     */
    Integer GET_ACCOUNT_SETTING_CODE = 46;
    /**
     * 获取账号设置信息content
     */
    String GET_ACCOUNT_SETTING_CONTENT = "获取账号设置信息";
}
