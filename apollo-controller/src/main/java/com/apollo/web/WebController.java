/*

 */

package com.apollo.web;

import com.apollo.common.annotation.DisableAuth;
import com.apollo.common.entity.Account;
import com.apollo.common.entity.enums.MessageKey;
import com.apollo.response.ResponseVO;
import com.apollo.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户管理控制层
 *
 * @author infi.wang
 */
@RestController
@RequestMapping(value = "/user", method = RequestMethod.POST)
@Slf4j
public class WebController {

    @Autowired
    private AccountService accountService;

    /**
     * 官网找回密码
     *
     * @param request request
     * @return
     */
    @DisableAuth
    @RequestMapping(value = "/find")
    public ResponseVO sendResetPasswordEmail(HttpServletRequest request, @RequestParam("userId") Long userId) {
        Account account = accountService.findAccountByUserId(userId);
        ResponseVO responseVO = new ResponseVO(MessageKey.RETURN_OK);
        responseVO.setData(account);
        return responseVO;
    }
}
