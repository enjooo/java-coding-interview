package com.vftour.study.oop.designpattern.pattern.strategy;

import com.vftour.study.oop.designpattern.api.Result;

import java.io.Serializable;

/**
 * 登录策略接口
 *
 * @version 1.0
 * @author: 东东 | d@tke.store
 * @date :  2022/10/26 12:42 上午
 */
public interface ILoginHandler<R extends Serializable> {
    /**
     * 获得 登录类型
     *
     * @return 登录类型
     */
    LoginType getLoginType();

    /**
     * 处理登录提交请求
     *
     * @param request 请求
     * @return 响应
     */
    Result<R> handle(LoginRequest request);
}
