package com.vftour.study.oop.designpattern.pattern.strategy;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录策略上下文
 *
 * @version 1.0
 * @author: 东东 | d@tke.store
 * @date :  2022/10/26 1:08 上午
 */
@Component
public class LoginHandlerFactory implements InitializingBean, ApplicationContextAware {

    private static final Map<LoginType, ILoginHandler<Serializable>> LOGIN_HANDLER_MAP = new HashMap<>(8);

    private ApplicationContext appContext;

    /**
     * 根据登录类型获取对应的处理器
     *
     * @param loginType 登录类型
     * @return 登录类型对应的处理器
     */
    public ILoginHandler<Serializable> getHandler(LoginType loginType) {
        return LOGIN_HANDLER_MAP.get(loginType);
    }

    @Override
    public void afterPropertiesSet() {
        // 将 Spring 容器中所有的 LoginHandler 注册到 LOGIN_HANDLER_MAP
        appContext.getBeansOfType(ILoginHandler.class)
                .values()
                .forEach(handler -> LOGIN_HANDLER_MAP.put(handler.getLoginType(), handler));
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) {
        appContext = applicationContext;
    }
}
