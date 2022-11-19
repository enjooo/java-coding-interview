package com.vftour.study.oop.principle.ocp.change;

import lombok.extern.slf4j.Slf4j;

/**
 * Cicle
 *
 * @author 东东 | d@tke.store
 * @date 2022/11/18 18:42
 * @since 1.0
 */
@Slf4j
public class Cicle implements Shape {

    int sort;

    public Cicle(int sort) {
        this.sort = sort;
    }

    @Override
    public int getSort() {
        return sort;
    }

    @Override
    public int compareTo(Shape o) {
        //1表示第二个对象比前一个大，0表示其相等，-1表示比前一个小
        return o.getSort() - sort;
    }

    @Override
    public void draw() {
        log.info("better --》 {}", "画Cicle");
    }
}
