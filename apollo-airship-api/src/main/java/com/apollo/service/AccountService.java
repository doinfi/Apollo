package com.apollo.service;

import com.apollo.common.entity.Account;

/**
 * 帐号信息接口
 *
 * @author Infi
 * @date 17/3/22
 */
public interface AccountService {

    /**
     * 根据用户id查询帐号信息
     *
     * @param userId 用户ID
     * @return 帐号信息
     */
    Account findAccountByUserId(Long userId);

    void update(Account account);
}
