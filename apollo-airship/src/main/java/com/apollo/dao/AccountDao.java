package com.apollo.dao;

import com.apollo.common.entity.Account;
import org.apache.ibatis.annotations.Param;

/**
 * @author Infi
 * @date 17/3/22
 */
public interface AccountDao {
    /**
     * 根据用户id查询帐号信息
     *
     * @param userId 用户ID
     * @return 帐号信息
     */
    Account findAccountByUserId(@Param("userId") Long userId);
}
