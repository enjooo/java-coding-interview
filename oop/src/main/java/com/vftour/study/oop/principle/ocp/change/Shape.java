package com.vftour.study.oop.principle.ocp.change;

/**
 * Shape
 *
 * @author 东东 | d@tke.store
 * @date 2022/11/18 18:40
 * @since 1.0
 */
public interface Shape extends Comparable<Shape> {
    void draw();

    int getSort();
}
