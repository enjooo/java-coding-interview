package com.vftour.study.oop.principle.ocp;

import com.vftour.study.oop.principle.ocp.bad.BadShape;
import com.vftour.study.oop.principle.ocp.bad.ShapeType;
import com.vftour.study.oop.principle.ocp.better.Cicle;
import com.vftour.study.oop.principle.ocp.better.Rectangle;
import com.vftour.study.oop.principle.ocp.better.Shape;

/**
 * OcpClient
 *
 * @author 东东 | d@tke.store
 * @date 2022/11/18 19:00
 * @since 1.0
 */
public class OCPClient {

    public static void main(String[] args) {
        new BadShape().draw(ShapeType.circle);

        Shape shape = new Cicle();
        shape.draw();

        shape = new Rectangle();
        shape.draw();
    }
}
