package com.apollo.service.impl;

import com.apollo.common.entity.Account;
import com.apollo.dao.AccountDao;
import com.apollo.service.AccountService;
import lombok.extern.slf4j.Slf4j;
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
    @Autowired
    private AccountDao accountDao;

    @Override
    public Account findAccountByUserId(Long userId) {
        return accountDao.findAccountByUserId(userId);
    }
}
