package com.vftour.study.oop.principle.ocp.better;

import lombok.extern.slf4j.Slf4j;

/**
 * Rectangle
 *
 * @author 东东 | d@tke.store
 * @date 2022/11/18 18:45
 * @since 1.0
 */
@Slf4j
public class Rectangle implements Shape {
    @Override
    public void draw() {
        log.info("better --》 {}", "画Rectangle");
    }
}
