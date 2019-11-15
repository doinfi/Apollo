package com.yf.coros.user.service;

import com.yf.coros.common.entity.user.Account;
import com.yf.coros.common.entity.user.UserInfoVO;
import com.yf.coros.common.entity.user.UserSimpleInfo;
import com.yf.coros.common.exception.YfException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 帐号信息接口
 *
 * @author Infi
 * @date 17/3/22
 */
public interface AccountService {

    /**
     * 新增帐号信息
     *
     * @param userInfoVO 新增帐号信息,包括account信息和userinfo信息
     * @param timezone   时区
     * @param language   语言
     * @return 帐号ID
     */
    Long save(UserInfoVO userInfoVO, Integer timezone, String language) throws IOException, YfException;

    /**
     * 更新用户token信息,登录时候更新
     *
     * @param accessToken  token
     * @param validityDate token 过期时间
     * @param userId       用户ID
     * @param loginType    登录类型,1:mobile,2:email,3:Facebook,4:微信扥登录
     * @param clientType   登录设备类型,1:android,2:ios
     */
    void updateToken(String accessToken, Date validityDate, Long userId, Integer loginType,
                     Integer clientType);

    /**
     * 更新用户的gomore用户同步状态，状态标志：0：正常，1：gomore用户创建异常
     * @param userId userId
     * @param gomoreUserStatus 状态值
     */
    void updateGomoreUserStatus(Long userId, int gomoreUserStatus);

    /**
     * 查询电话号码是否已经注册
     *
     * @param mobile 电话号码
     * @return 查到的电话号码的行数
     */
    Integer findMobileIsExist(String mobile);

    /**
     * 查询邮箱地址是否已经注册
     *
     * @param email 邮箱地址
     * @return 检查结果
     */
    Integer findEmailIsExist(String email);

    /**
     * 查询用户帐号概要信息
     *
     * @param account     帐号
     * @param accountType 帐号类型,1:mobile,2:email,3:facebookId,4:weixinId
     * @return 帐号概要信息
     */
    Account findAccountSummary(String account, Integer accountType);

    /**
     * 验证accessToken是否有效
     *
     * @param accessToken accessToken
     * @return 用户ID, token过期时间信息
     */
    Account findAccessTokenIsValid(String accessToken, Date validityDate) throws YfException;

    /**
     * 根据accessToken查询用户信息
     *
     * @param accessToken accessToken
     * @return 用户账户信息
     */
    Account findAccountByAccessToken(String accessToken) throws YfException;

    /**
     * 根据accessToken查询用户信息,包括密码
     *
     * @param accessToken token
     * @return 用户帐号信息
     */
    Account findAccountAndPwdByAccessToken(String accessToken) throws YfException;

    /**
     * 根据用户id查询帐号信息
     *
     * @param userId 用户ID
     * @return 帐号信息
     */
    Account findAccountByUserId(Long userId);

    /**
     * 根据用户ID列表查找帐号信息
     * @param userIdList 用户ID列表
     * @return 帐号信息列表
     */
    List<Account> findAccountByUserIdList(List<Long> userIdList);

    /**
     * 通过邮箱地址查询用户信息
     *
     * @param email 邮箱地址
     * @return 用户帐号信息
     */
    Account findAccountByEmail(String email);

    /**
     * 用户登出是修改帐号信息
     *
     * @param accessToken accessToken
     */
    void updateAccessTokenByLogout(String accessToken);

    /**
     * 用户帐号绑定
     *  @param account    数据库中的帐号信息
     * @param userInfoVO 用户帐号
     * @param language
     */
    void bind(Account account, UserInfoVO userInfoVO, String language) throws YfException;

    /**
     * 用户帐号解除绑定
     *
     * @param account    数据库中的帐号信息
     * @param userInfoVO 用户帐号信息
     */
    void unbind(Account account, UserInfoVO userInfoVO) throws YfException;


    /**
     * 密码重置<br> 1. 使用mobile重置密码,APP要对密码进行aes加密<br> 2. 使用email重置密码,邮件中的html页面,只对密码进行md5加密<br>
     *
     * @param account     电话号码
     * @param accountType 邮箱地址
     * @param pwd         密码
     * @param checkCode   验证码
     * @param appKey      加密密钥
     * @param ipAddress   用户IP地址
     * @param timezone    时区
     * @return 返回用户信息
     */
    UserInfoVO resetPassword(String account, Integer accountType, String pwd, String checkCode,
                             String appKey, String ipAddress, Integer timezone)
            throws YfException;

    /**
     * 检查用户邮箱地址是否已经激活
     *
     * @param userId 用户ID
     * @param email  邮箱地址
     * @return 邮箱激活状态
     */
    boolean checkEmailIsActivated(Long userId, String email);

    /**
     * 查询用户简要信息,提供给其他模块使用
     *
     * @param accessToken 用户token
     * @return 返回用户简要信息
     */
    UserSimpleInfo findSimpleUserInfoByToken(String accessToken) throws YfException;

    /**
     * 邮箱激活修改邮箱地址和激活状态
     *
     * @param userId 用户ID
     * @param email  邮箱地址
     * @param pwd    密码
     */
    void updateEmailActivateStatus(Long userId, String email, String pwd);

    /**
     * 用于设备激活页面调用
     *
     * @param account 用户帐号
     * @return 用户信息
     * @throws YfException 异常抛出
     */
    Account authLogin(String account) throws YfException;

    /**
     * web页面注册用户信息
     *
     * @param firstName first name
     * @param lastName  last name
     * @param email     邮箱
     * @param password  密码
     * @param mobile    电话
     * @param nickName
     */
    void saveAcountByWeb(String firstName, String lastName, String email, String password, String mobile,
                         String nickName)
            throws YfException;

    /**
     * 重新发送激活邮件
     *
     * @param userId      用户ID
     * @param account     要激活的有限个地址
     * @param accountType 要激活的帐号类型
     * @param appKey      密钥
     * @param ipAddress   用户IP地址
     */
    void bindSendEmail(Long userId, String account, Integer accountType, String appKey, String ipAddress)
            throws YfException;
}
