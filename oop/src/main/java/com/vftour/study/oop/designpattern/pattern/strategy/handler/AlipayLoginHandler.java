package com.vftour.study.oop.designpattern.pattern.strategy.handler;

import com.vftour.study.oop.designpattern.api.Result;
import com.vftour.study.oop.designpattern.pattern.strategy.ILoginHandler;
import com.vftour.study.oop.designpattern.pattern.strategy.LoginRequest;
import com.vftour.study.oop.designpattern.pattern.strategy.LoginType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * AlipayLoginHandler
 *
 * @author 东东 | d@tke.store
 * @date 2022/10/26 9:49
 * @since 1.0
 */
@Slf4j
@Component
public class AlipayLoginHandler implements ILoginHandler<Serializable> {
    @Override
    public LoginType getLoginType() {
        return LoginType.AlipayMiniapp;
    }

    @Override
    public Result<Serializable> handle(LoginRequest request) {
        log.info("支付宝模式登录：formInput={}", request.getFormInput());

        return Result.success("");
    }
}
