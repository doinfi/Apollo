/*
 * Copyright (c) 2014-2018 Chenlaisoft Co.Ltd. All rights reserved.
 */

package com.yf.coros.rest.interceptor;

import com.yf.coros.common.annotation.DisableAuth;
import com.yf.coros.common.entity.user.Account;
import com.yf.coros.common.enums.MessageKey;
import com.yf.coros.rest.config.Constants;
import com.yf.coros.user.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;

/**
 * 用户登录信息过期拦截器
 *
 * @author infi
 */
@Slf4j
public class AuthInterceptor extends YfInterceptor {

    private AccountService accountService;

    public AuthInterceptor(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 判断是否鉴权
     *
     * @param auth 权限注解
     * @return 是否拦截
     */
    private static boolean isDisableAuth(DisableAuth auth) {
        return auth != null;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HandlerMethod method = (HandlerMethod) handler;
        // 1. 判断是否鉴权
        DisableAuth auth = method.getMethod().getAnnotation(DisableAuth.class);
        if (isDisableAuth(auth)) {
            return super.preHandle(request, response, handler);
        }
        // 2. 获取token,检查token是否为空
        String accessToken = getAuthToken(request);
        if (StringUtils.isBlank(accessToken)) {
            setResponse(request, response, MessageKey.ACCESS_TOKEN_IS_NULL);
            return false;
        }

        // 3. 查询token是否正确
        Date nowDate = new Date();
        Account account = accountService.findAccessTokenIsValid(accessToken, nowDate);
        if (account == null) {
            setResponse(request, response, MessageKey.ACCESS_TOKEN_IS_INVALID);
            return false;
        }

        // 将userId写入到request请求中
        request.setAttribute(Constants.REQUEST_ATTR_USER_ID, account.getUserId());
        request.setAttribute(Constants.REQUEST_ATTR_USER_EMAIL, account.getEmail());

        // 4. 查看token是否过期
        Date today = DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
        if (account.getValidityDate() != null && today.compareTo(account.getValidityDate()) > -1) {
            setResponse(request, response, MessageKey.ACCESS_TOKEN_IS_INVALID);
            return false;
        }
        // 异常错误
        if (DispatcherType.ERROR == request.getDispatcherType()) {
            setResponse(request, response, MessageKey.SYSTEM_ERROR);
            return false;
        }

        return super.preHandle(request, response, handler);
    }


    /**
     * 获取http请求头部或者参数中的token值
     *
     * @param request http请求传递的值
     * @return 返回token
     */
    private String getAuthToken(HttpServletRequest request) {
        String token = request.getParameter("accessToken");
        if (token == null) {
            token = request.getHeader("accessToken");
        }
        return token;
    }

}
