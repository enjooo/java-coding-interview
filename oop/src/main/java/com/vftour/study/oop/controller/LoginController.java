package com.vftour.study.oop.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vftour.study.core.api.Result;
import com.vftour.study.oop.designpattern.pattern.strategy.LoginRequest;
import com.vftour.study.oop.designpattern.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * LoginController
 *
 * @version 1.0
 * @author: 东东 | d@tke.store
 * @date :  2022/10/26 1:21 上午
 */
@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private ILoginService loginService;

    @PostMapping("/submit")
    public Result<String> submitForm(@RequestParam String loginType,
                                     @RequestParam String formInputJson) {
        JSONObject formInput = JSON.parseObject(formInputJson);

        LoginRequest request = new LoginRequest();
        request.setLoginType(loginType);
        request.setFormInput(formInput);

        return Result.data(loginService.login(request));
    }
}
