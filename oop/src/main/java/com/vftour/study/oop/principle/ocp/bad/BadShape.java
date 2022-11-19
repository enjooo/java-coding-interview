package com.vftour.study.oop.principle.ocp.bad;

import lombok.extern.slf4j.Slf4j;

/**
 * BadShape
 *
 * @author 东东 | d@tke.store
 * @date 2022/11/18 18:48
 * @since 1.0
 */
@Slf4j
public class BadShape {
    public void draw(ShapeType type) {
        switch (type) {
            case circle:
                log.info("BadShape --》 {}", "画circle");
                break;
            case square:
                log.info("BadShape --》 {}", "画square");
                break;
            case triangle:
                log.info("BadShape --》 {}", "画triangle");
                break;
            default:
                log.info("BadShape --》 {}", "画rectangle");

        }
    }
}
