package com.vftour.study.oop.principle.ocp.better;

import lombok.extern.slf4j.Slf4j;

/**
 * Square
 *
 * @author 东东 | d@tke.store
 * @date 2022/11/18 18:42
 * @since 1.0
 */
@Slf4j
public class Square implements Shape {
    @Override
    public void draw() {
        log.info("better --》 {}", "画Square");
    }
}
