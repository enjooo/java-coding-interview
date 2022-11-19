package com.vftour.study.oop.designpattern.pattern.strategy.handler;

import com.vftour.study.oop.api.Result;
import com.vftour.study.oop.designpattern.pattern.strategy.ILoginHandler;
import com.vftour.study.oop.designpattern.pattern.strategy.LoginRequest;
import com.vftour.study.oop.designpattern.pattern.strategy.LoginType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * PhoneLoginHandler
 *
 * @version 1.0
 * @author: 东东 | d@tke.store
 * @date :  2022/10/26 1:33 上午
 */
@Slf4j
@Component
public class PhoneLoginHandler implements ILoginHandler<Serializable> {
    @Override
    public LoginType getLoginType() {
        return LoginType.PHONE;
    }

    @Override
    public Result<Serializable> handle(LoginRequest request) {
        log.info("手机模式登录：formInput={}", request.getFormInput());

        return Result.success("");
    }
}
