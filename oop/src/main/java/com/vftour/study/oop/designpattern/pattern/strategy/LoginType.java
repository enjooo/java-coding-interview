package com.vftour.study.oop.designpattern.pattern.strategy;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 登录类型
 *
 * @version 1.0
 * @author: 东东 | d@tke.store
 * @date :  2022/10/26 12:49 上午
 */
@Getter
@AllArgsConstructor
public enum LoginType {
    Password("password", "密码"),
    PHONE("phone", "手机号"),
    WxMiniapp("WxMiniapp", "微信小程序"),
    WxMx("WxMx", "微信公众号"),
    AlipayMiniapp("AlipayMiniapp", "支付宝小程序"),
    DOUYIN("DOUYIN", "抖音小程序"),
    XIAOHONGSHU("XIAOHONGSHU", "小红书"),
    WEIBO("WEIBO", "微薄"),
    Slien("Slien", "静默登录"),
    ;
    private String value;

    private String name;

    public static LoginType of(String value) {
        for (LoginType type : LoginType.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
