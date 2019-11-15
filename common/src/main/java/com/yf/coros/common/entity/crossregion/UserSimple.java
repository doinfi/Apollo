package com.yf.coros.common.entity.crossregion;

import com.yf.coros.common.entity.user.Account;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * 用户概要信息，用于服务端跨区域缓存<br/>
 * <b>不能去掉@Data，因为本类需要重写equals和hashcode方法来确保对象可以判断，如果去掉，则要重写下这2个方法</b>
 * @author lihuaijin
 */
@Data
@NoArgsConstructor

public class UserSimple implements Serializable, Cloneable {

    private Long userId;
    private String mobile;
    private String email;
    private String facebookId;
    private String weixinId;
    private Integer registerType;

    /**
     * 基于Account类生成
     * @param account 帐号信息实体
     */
    public UserSimple(Account account) {
        this.setUserId(account.getUserId());
        this.setEmail(account.getEmail());
        this.setMobile(account.getMobile());
        this.setFacebookId(account.getFacebookId());
        this.setWeixinId(account.getWeixinId());
        this.setRegisterType(account.getRegisterType());
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            UserSimple cloneObj = new UserSimple();
            BeanUtils.copyProperties(this, cloneObj);
            return cloneObj;
        }
    }
}
