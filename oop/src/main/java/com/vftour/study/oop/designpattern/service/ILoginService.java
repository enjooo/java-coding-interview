package com.vftour.study.oop.designpattern.service;

import com.vftour.study.oop.designpattern.pattern.strategy.LoginRequest;
import org.springframework.lang.NonNull;

/**
 * ILoginService
 *
 * @version 1.0
 * @author: 东东 | d@tke.store
 * @date :  2022/10/26 1:06 上午
 */
public interface ILoginService {

    String login(@NonNull LoginRequest request);
}
