package com.vftour.study.oop.principle.ocp.change;

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

    int sort;

    public Rectangle(int sort) {
        this.sort = sort;
    }

    @Override
    public int getSort() {
        return sort;
    }

    @Override
    public int compareTo(Shape o) {
        //1表示第二个对象比前一个打，0表示其相等，-1表示比前一个小
        return o.getSort() - sort;
    }

    @Override
    public void draw() {
        log.info("better --》 {}", "画Rectangle");
    }
}
