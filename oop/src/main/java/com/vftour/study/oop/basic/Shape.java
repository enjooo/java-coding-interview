package com.vftour.study.oop.basic;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 形状类
 *
 * @author 东东 | d@tke.store
 * @date 2022/11/24 21:52
 * @since 1.0
 */
@Slf4j
@Data
public class Shape {
    private String name;
    private int type;

    public Shape(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public void draw() {
        log.info("Shape -- name - {},type - {}",name,type);
    }
}
