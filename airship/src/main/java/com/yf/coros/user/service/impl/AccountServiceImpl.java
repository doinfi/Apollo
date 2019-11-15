package com.yf.coros.user.service.impl;

import com.yf.coros.common.entity.user.Account;
import com.yf.coros.common.entity.user.UserInfo;
import com.yf.coros.common.entity.user.UserInfoVO;
import com.yf.coros.common.entity.user.UserSimpleInfo;
import com.yf.coros.common.enums.AccountTypeEnum;
import com.yf.coros.common.enums.ActivateStatusEnum;
import com.yf.coros.common.enums.ActivateTypeEnum;
import com.yf.coros.common.enums.CheckCodeActionTypeEnum;
import com.yf.coros.common.enums.CheckCodeStatusEnum;
import com.yf.coros.common.enums.ClientType;
import com.yf.coros.common.enums.MessageKey;
import com.yf.coros.common.enums.ServiceModeType;
import com.yf.coros.common.enums.StorageType;
import com.yf.coros.common.exception.YfException;
import com.yf.coros.common.utils.AesUtils;
import com.yf.coros.common.utils.CodeGenerator;
import com.yf.coros.common.utils.YfDateUtils;
import com.yf.coros.user.config.Constants;
import com.yf.coros.user.config.PrimaryKey;
import com.yf.coros.user.config.SimpleStorageUtils;
import com.yf.coros.user.dao.AccountDao;
import com.yf.coros.user.service.AccountService;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户目标值记录接口实现层
 *
 * @author Infi
 * @date 17/3/22
 */
@Service("accountService")
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Override
    public Long save(UserInfoVO userInfoVO, Integer timezone, String language) throws IOException, YfException {
        return null;
    }

    @Override
    public void updateToken(String accessToken, Date validityDate, Long userId, Integer loginType, Integer clientType) {

    }

    @Override
    public void updateGomoreUserStatus(Long userId, int gomoreUserStatus) {

    }

    @Override
    public Integer findMobileIsExist(String mobile) {
        return null;
    }

    @Override
    public Integer findEmailIsExist(String email) {
        return null;
    }

    @Override
    public Account findAccountSummary(String account, Integer accountType) {
        return null;
    }

    @Override
    public Account findAccessTokenIsValid(String accessToken, Date validityDate) throws YfException {
        return null;
    }

    @Override
    public Account findAccountByAccessToken(String accessToken) throws YfException {
        return null;
    }

    @Override
    public Account findAccountAndPwdByAccessToken(String accessToken) throws YfException {
        return null;
    }

    @Override
    public Account findAccountByUserId(Long userId) {
        return null;
    }

    @Override
    public List<Account> findAccountByUserIdList(List<Long> userIdList) {
        return null;
    }

    @Override
    public Account findAccountByEmail(String email) {
        return null;
    }

    @Override
    public void updateAccessTokenByLogout(String accessToken) {

    }

    @Override
    public void bind(Account account, UserInfoVO userInfoVO, String language) throws YfException {

    }

    @Override
    public void unbind(Account account, UserInfoVO userInfoVO) throws YfException {

    }

    @Override
    public UserInfoVO resetPassword(String account, Integer accountType, String pwd, String checkCode, String appKey, String ipAddress, Integer timezone) throws YfException {
        return null;
    }

    @Override
    public boolean checkEmailIsActivated(Long userId, String email) {
        return false;
    }

    @Override
    public UserSimpleInfo findSimpleUserInfoByToken(String accessToken) throws YfException {
        return null;
    }

    @Override
    public void updateEmailActivateStatus(Long userId, String email, String pwd) {

    }

    @Override
    public Account authLogin(String account) throws YfException {
        return null;
    }

    @Override
    public void saveAcountByWeb(String firstName, String lastName, String email, String password, String mobile, String nickName) throws YfException {

    }

    @Override
    public void bindSendEmail(Long userId, String account, Integer accountType, String appKey, String ipAddress) throws YfException {

    }
}
