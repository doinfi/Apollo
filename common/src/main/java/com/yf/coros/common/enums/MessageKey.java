package com.yf.coros.common.enums;

/**
 * 状态结果枚举定义
 * 1. 与丁慧 ReturnMessage 重合, 讨论优化。
 * 2. 确保定义的result 与旧版的保持一致。
 * <p> 返回编码结构定义:
 * x-xxx或者xx-xxx,[模块][错误]。
 * 编码结构说明:
 * 1. 如果是四位编码,第一位表示模块;如果是五位编码,前两位表示模块。
 * 2.
 * 在错误编码中模块可以是业务逻辑上的"模块",比如说公共模块,如果有必要可以分为手环绑定"模块",验证码"模块"
 * 3. 最后三位表示错误编号。
 * <p> 返回编号分段定义:
 * 1. 0000: 表示操作成功
 * 2. 1001- 1999: 表示系统错误信息
 * 3. 2001 - 2999: 表示账户模块
 * 4. 3001 - 3999: 表示用户模块
 * 5. 5001 - 5999: 表示数据同步模块
 * 6. 6001 - 6999: 表示用户活动模块
 * 8. 8001 - 8999: 表示公共模块=短信功能,手环激活功能)
 * 9. 9001 - 9999: 表示表盘固件模块
 * 10. 10001 - 10999: 表示数据统计模块
 * 11. 11001-11999: 表示管理控制台错误信息
 * 12. 12001-12999: 表示团队功能错误信息（已经删除）
 * 13. 17001-17999: 标识教练系统错误信息
 * 13. 18001-18999: EPO和CEP文件下载错误信息
 * 8. 80001 - 81999:
 * 20. 20000 - 29999: APP直接使用服务单返回的提示语，服务端支持多语言
 * 20. 30000 - 30999: 用户第三方模块（open）
 * 表示服务器后台页面模块
 *
 * @author hudahua
 */
public interface MessageKey {

    String RETURN_OK = "0000";
    String SYSTEM_ERROR = "1001";
    String REQUEST_MORE_BY_MOBILE = "1002";
    String USER_NAME_IS_NULL = "1003";
    String PASSWORD_IS_NULL = "1004";
    String RAW_DATA_ANALYSIS_ERROR = "1005";
    String LOGIN_ERROR = "1006";
    String SOURCE_DATA_NULL = "1007";
    String FILE_SIZE_OVER_UPPER_LIMIT = "1008";
    String REQUEST_EXCEPTIONS_OR_PARAMETER_ERROR = "1009";
    String USER_NAME_IS_EXIST = "1013";
    String CHECK_CODE_IS_NULL = "1014";
    String CHECK_CODE_IS_INVALID = "1015";
    String NICKNAME_IS_NULL = "1017";
    String SEX_IS_NULL = "1018";
    String ACCESS_TOKEN_IS_INVALID = "1019";
    String STATURE_IS_NULL = "1020";
    String WEIGHT_IS_NULL = "1021";
    String BIRTHDAY_IS_NULL = "1022";
    /**
     * 用户不存在
     */
    String USER_NO_EXIST = "1029";
    /**
     * 用户名或密码错误
     */
    String USER_NAME_OR_PASSWORD_IS_WRONG = "1030";
    /**
     * 参数错误
     */
    String PARAMETER_ERROR = "1031";
    String ACCESS_TOKEN_IS_NULL = "1032";
    /**
     * 用于第三方：找不到对应的记录
     */
    String NO_DATA = "1037";
    /**
     * 当天请求次数已经达到上限
     */
    String UP_ACCESS_COUNT = "1042";
    /**
     * 同一个帐号限制每天发送10次
     */
    String UP_ACCESS_COUNT_REGISTER = "1042";
    /**
     * 同一个邮箱发送激活邮件限制：10次
     */
    String UP_ACCESS_COUNT_ACTIVATE = "1042";
    String APPKEY_IS_INVALID = "1045";

    /**
     * 注册时无法判定account是否在另一区域服务端已注册
     */
    String ACCOUNT_NEED_CKECK = "1046";

    /**
     * 注册、注册校验及注册用户查询传入的密文无效
     */
    String AUTH_CONTENT_IS_INVALID = "1047";

    /**
     * 跨区域请求失败
     */
    String CROSS_REGION_REQUEST_FAILED = "1048";

    /**
     * 邮件发送失败
     */
    String FAILED_TO_SEND_EMAIL = "1080";
    /**
     * 运动数据重复提交
     */
    String SPORT_DATA_REPEAT_SUBMIT = "1082";
    String TIMEZONE_ERROR = "1083";

    /**
     * 密码格式错误，只允许输入以下字符：., ?/\~@!#￥%^&*()_-+{}[]<>'$€￡·|:;"= 数字 字母
     */
    String PWD_FROMAT_ERROR = "1084";
    /**
     * 帐号已经绑定,不能中其他操作
     */
    String ACCOUNT_IS_BIND = "2001";
    /**
     * 只用一种帐号类型存在,不能解除绑定
     */
    String ACCOUNT_IS_ONLY_ONE = "2002";
    /**
     * 帐号没有绑定,比如mobile没有绑定过,不能解除mobile的绑定
     */
    String ACCOUNT_NOT_BIND = "2003";

    /**
     * 帐号已经被别人使用
     */
    String ACCOUNT_HAVE_BEEN_USED = "2004";
    /**
     * 邮箱已经被激活
     */
    String EMAIL_IS_ACTIVATED = "2006";
    /**
     * 电话号码没有注册
     */
    String MOBILE_IS_NOT_REGISTERED = "2007";
    /**
     * 邮箱没有注册
     */
    String EMAIL_IS_NOT_REGISTERED = "2008";
    /**
     * 邮箱已注册,邮件已过期,请重新发送激活邮件
     */
    String EMIL_NOT_ACTIVATED_MAIL_EXPIRED = "2010";
    /**
     * 邮箱已注册,未激活,请打开激活邮件进行激活
     */
    String EMIL_NOT_ACTIVATED_MAIL_NOT_EXPIRED = "2011";
    /**
     * 帐号无效
     */
    String ACCOUNT_IS_INVALID = "2013";
    /**
     * 头像文件大小超过限制
     */
    String HEAD_PIC_FILE_TOO_LARGE = "2016";
    /**
     * 密码错误次数超过上限
     */
    String PWD_ERROR_COUNT_MAX = "2018";
    /**
     * 没有发现比当前版本还高的固件版本信息
     */
    String FIRMWARE_NO_EXIST = "2051";
    /**
     * 时区信息为空
     */
    String TIMEZONE_IS_NULL = "3001";
    /**
     * 紧急联系人电话号码格式错误
     */
    String INCOMPLETE_PHONE_NO = "3002";
    /**
     * 注册时间错误
     */
    String REGISTER_TIME_ERROR = "3003";
    /**
     * 用户没有紧急联系人信息
     */
    String CONTACT_IS_NOT_FOUND = "3004";
    /**
     * 用户已经加入团队
     */
    String USER_JOIN_TEAM = "12001";
    /**
     * 用户没有加入团队
     */
    String USER_NOT_JOIN_TEAM = "12002";
    /**
     * 团队无效,不存在或者已经过期
     */
    String USER_TEAM_IS_INVALID = "12003";
    /**
     * 用户不是团队管理员,不能设置团队信息
     */
    String USER_IS_NOT_ADMIN = "12004";
    /**
     * 管理员不能删除他自己
     */
    String ADMIN_CONNOT_BE_DELETE = "12005";
    /**
     * 路书已经上传
     */
    String ROUTE_IS_EXSIT = "13001";
    /**
     * 找不到路书
     */
    String ROUTE_IS_NOT_FOUND = "13002";
    /**
     * 只有路书作者才能修改路书
     */
    String ONLY_AUTHOR_CAN_ROAD_ROUTE = "13003";
    /**
     * 自行车导航路书文件大小超过上限
     */
    String COROS_ROUTE_FILE_TOO_LARGE = "13004";
    /**
     * 缩率图文件大小超过上限
     */
    String IMAGE_DATA_FILE_TOO_LARGE = "14001";
    /**
     * 运动数据文件大小超过上限
     */
    String SPORT_DATA_FILE_TOO_LARGE = "14002";
    /**
     * （紧急联系人）半小时内只能发送1条短信
     */
    String SMS_SEND_ONLY_30_MINUTE = "15001";
    /**
     * （紧急联系人）一天内只能发送30条短信
     */
    String MAX_CONTACT_SMS_SEND_EVERYDAY = "15002";
    /**
     * 短信发送失败，请检查紧急联系人电话。
     */
    String SEND_SMS_FAILED = "15003";
    /**
     * 固件已经存在,不需要重复发布
     */
    String FIRMWARE_IS_EXIST = "9005";
    /**
     * 固件压缩文件超过上限
     */
    String FIRMWARE_FILE_TOO_LARGE = "9006";

    /**
     * 设备已经激活
     */
    String DEVICE_HAS_BEEN_REGISTERED = "9007";

    /**
     * 帐号或密码错误
     */
    String ACCOUNT_PWD_INVALID = "9008";

    /**
     * 固件类型和文件名称不匹配
     */
    String FIRMWARE_TYPE_AND_FILE_NAME_MISMATCHES = "9009";

    /**
     * 表盘文件大小超出限制
     */
    String WATCH_FACE_FILE_TOO_LARGE = "9010";

    /**
     * 表盘ID已经存在
     */
    String WATCH_FACE_ID_IS_EXIST = "9011";

    /**
     * 表盘ID错误
     */
    String WATCH_FACE_ID_ERROR = "9012";

    /**
     * 灰度设备已经存在
     */
    String TEST_DEVICE_IS_EXIST = "9013";

    /**
     * 灰度用户已经存在
     */
    String TEST_USER_IS_EXIST = "9014";

    /**
     * 设备不存在
     */
    String DEVICE_NOT_EXIST = "9015";


    /**
     * APP版本号不存在
     */
    String APP_VERSION_NOT_EXIST = "9016";


    /**
     * 用于第三方：缺少必须的参数或参数不合法
     */
    String PARAMETER_INVALID = "5001";

    /**
     * 用于第三方：未授权的clientId
     */
    String CLIENT_ID_INVALID = "5002";

    /**
     * 用于第三方：未授权的域名，必须与申请资料里填写的一致
     */
    String DOMAIN_NAME_INVALID = "5003";

    /**
     * 用于第三方：无效的scope，不在文档规定的有效scope范围内
     */
    String SCOPE_INVALID = "5004";

    /**
     * 用于第三方：请求授权失败，调用开放API时token对应的权限(scope)不足
     */
    String AUTH_FAILURE = "5005";

    /**
     * 用于第三方：无效的token，token过期时返回
     */
    String TOKEN_INVALID = "5006";

    /**
     * 用于第三方：无效的code，code被使用过或者超时会返回该错误
     */
    String CODE_INVALID = "5007";

    /**
     * 用于第三方：该请求未被授权，调用的API没有授权给clientId
     */
    String REQUEST_IS_NOT_AUTHORIZED = "5008";

    /**
     * 客户端安全KEY认证失败！
     */
    String KEY_AUTH_FAILED = "5009";

    /**
     * 用于第三方：无效的openId
     */
    String OPEN_ID_INVALID = "5010";

    /**
     * 日期超出范围 The date is out of range
     */
    String DATE_OUT_OF_RANGE = "5011";

    /**
     * 无效请求
     */
    String REQUEST_IS_INVALID = "5012";

    /**
     * 轨迹文件超过限制，不能超过1Mb
     */
    String TRACK_FILE_TOO_LARGE = "5013";

    /**
     * google服务获取海拔失败
     */
    String GOOGLE_MAP_SERVICE_ELEVATION_REQUEST_FAIL = "5014";

    /**
     * 校准海拔失败
     */
    String ALTITUDE_CALIBRATION_FAILURE = "5015";

    /**
     * 运动数据为空
     */
    String SPORT_DATA_IS_EMPTY = "5016";

    /**
     * 运动数据中没有经纬度
     */
    String LATLNG_IS_EMPTY_IN_SPORT_DATA = "5017";

    /**
     * 未找到轨迹文件
     */
    String TRACK_NOT_FOUND = "5018";
    /**
     * 重复提交请求
     */
    String REPEAT_SUBMIT_REQUEST = "5019";

    /**
     * 请求失败
     */
    String REQUEST_FAILED = "5020";

    /**
     * 数据为空
     */
    String DATA_IS_NULL = "5081";

    /**
     * 管理员无此权限
     */
    String ADMIN_HAS_NO_PERMISSION = "11001";
    /**
     * 未找到数据
     */
    String NOT_FOUND_DATA = "11002";

    /**
     * 本环境未找到用户
     */
    String NOT_FOUND_USER = "11003";

    /**
     * 用户未提交密码重置请求
     */
    String NOT_FOUND_RESET_CODE = "11004";

    /**
     * apk文件为空
     */
    String APK_FILE_IS_EMPTY = "11005";

    /**
     * apk文件错误
     */
    String APK_FILE_IS_ERROR = "11006";

    /**
     * apk md5校验码错误
     */
    String APK_MD5_CODE_ERROR = "11007";

    /**
     * cep锁文件存在
     */
    String CURRENT_CEP_LOCK = "18001";
    /**
     * 下载cep文件失败
     */
    String CEP_DOWNLOAD_FAILED = "18002";
    /**
     * cep文件不是当天的文件
     */
    String CEP_FILE_IS_NOT_TODAY = "18003";

    /**
     * 无法获取cep文件的修改时间，不能更新此cep文件
     */
    String CEP_FILE_MODIF_TIME_NOT_FOUND = "18004";
    /**
     * cep文件是和昨天的文件相同，不能下载
     */
    String CEP_FILE_IS_YESTERDAY = "10085";

    /**
     * GoMore服务不可用，需要人介入解决(gomore错误码10001、10010、10011)
     */
    String GOMORE_SERVICE_UNSUPPORTED = "21000";

    /**
     * GoMore服务请求失败（请求异常，超时、不可达、建链错误等等）
     */
    String GOMORE_REQUEST_FAILED = "21001";

    /**
     * GoMore激活sdk失败
     */
    String GOMORE_ACTIVATE_FAILED = "21101";

    /**
     * gomore用户未创建
     */
    String GOMORE_USER_INVALID = "21102";
    /**
     * 第三方token无效
     */
    String OPEN_TOKEN_INVALID = "30001";
    /**
     * 第三方用户未绑定
     */
    String OPEN_USER_UNBOUNDED = "30002";
    /**
     * fit文件上传失败
     */
    String FIT_UPLOAD_FAILED = "30003";
    /**
     * relive 服务异常
     */
    String RELIVE_SERVICE_EXCEPTIONS = "30004";

}
