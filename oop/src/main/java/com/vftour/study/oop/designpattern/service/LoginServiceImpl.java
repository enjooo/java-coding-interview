package com.vftour.study.oop.designpattern.service;

import com.vftour.study.oop.designpattern.pattern.strategy.ILoginHandler;
import com.vftour.study.oop.designpattern.pattern.strategy.LoginHandlerFactory;
import com.vftour.study.oop.designpattern.pattern.strategy.LoginRequest;
import com.vftour.study.oop.designpattern.pattern.strategy.LoginType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * LoginServiceImpl
 *
 * @version 1.0
 * @author: 东东 | d@tke.store
 * @date :  2022/10/26 1:07 上午
 */
@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private LoginHandlerFactory loginHandlerFactory;

    @Override
    public String login(LoginRequest request) {
        LoginType loginType = LoginType.of(request.getLoginType());

        // 根据 loginType 找到对应的提交处理器
        ILoginHandler<Serializable> submitHandler = loginHandlerFactory.getHandler(loginType);

        // 判断 loginType 对应的 handler 是否存在
        if (submitHandler == null) {
            return "非法的提交类型: " + loginType;
        }

        // 处理提交
        return submitHandler.handle(request);
    }
}
