package com.vftour.study.oop.designpattern.pattern.strategy;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * 登录参数
 *
 * @version 1.0
 * @author: 东东 | d@tke.store
 * @date :  2022/10/26 12:48 上午
 */
@Getter
@Setter
public class LoginRequest {
    /**
     * 登录类型
     *
     * @see ILoginHandler#getLoginType()
     */
    private String loginType;

    /**
     * 登录提交的值
     */
    private Map<String, Object> formInput;

}
