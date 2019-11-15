/*

 */

package com.yf.coros.rest.web;

import com.yf.coros.common.annotation.DisableAuth;
import com.yf.coros.common.config.MonitorMethod;
import com.yf.coros.common.entity.user.UserInfoVO;
import com.yf.coros.common.enums.MessageKey;
import com.yf.coros.rest.response.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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


    /**
     * 官网找回密码
     *
     * @param request request
     * @param userInfo 用户信息
     * @return
     * @throws Exception
     */
    @DisableAuth
    @RequestMapping(value = "/web/sendResetPasswordEmail")
    @MonitorMethod
    public ResponseVO sendResetPasswordEmail(HttpServletRequest request, @RequestBody UserInfoVO userInfo)
            throws Exception {
        return new ResponseVO(MessageKey.RETURN_OK);
    }
}
