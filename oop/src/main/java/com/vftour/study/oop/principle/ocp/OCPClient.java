package com.vftour.study.oop.principle.ocp;

import com.google.common.collect.Lists;
import com.vftour.study.oop.principle.ocp.bad.BadShape;
import com.vftour.study.oop.principle.ocp.bad.ShapeType;
import com.vftour.study.oop.principle.ocp.change.Cicle;
import com.vftour.study.oop.principle.ocp.change.Rectangle;
import com.vftour.study.oop.principle.ocp.change.Shape;
import com.vftour.study.oop.principle.ocp.change.Square;

import java.util.Collections;
import java.util.List;

/**
 * OcpClient
 *
 * @author 东东 | d@tke.store
 * @date 2022/11/18 19:00
 * @since 1.0
 */
public class OCPClient {

    public static void main(String[] args) {
        /**
         * 第一种方式
         */
        new BadShape().draw(ShapeType.circle);

        /**
         * 优化方式
         */
//        List<Shape> shapes = Lists.newArrayList();
//        shapes.add(new Cicle());
//        shapes.add(new Square());
//        shapes.add(new Rectangle());
//        drawShape(shapes);

        /**
         * 迭代新功能
         * 1. 按顺序绘制
         */
        List<Shape> shapes = Lists.newArrayList();
        shapes.add(new Cicle(3));
        shapes.add(new Square(1));
        shapes.add(new Rectangle(2));
        drawShape(shapes);
    }

    /**
     * 优化方式
     *
     * @param shapes
     */
    public static void drawShape(List<Shape> shapes) {
        Collections.sort(shapes);
        shapes.forEach(shape -> {
            shape.draw();
        });
    }
}
