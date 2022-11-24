package net.objecthunter.exp4j;

import lombok.extern.slf4j.Slf4j;
import net.objecthunter.exp4j.function.Function;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ExpressionBuilderTest
 *
 * @author 东东 | d@tke.store
 * @date 2022/11/23 17:28
 * @since 1.0
 */
@Slf4j
public class ExpressionBuilderTest {

    @Test
    void build() {
        Expression e = new ExpressionBuilder("3 * sin(y) - 2 / (x - 2)")
                .variables("x", "y")
                .build()
                .setVariable("x", 2.3)
                .setVariable("y", 3.14);
        double result = e.evaluate();

        log.info("result:{}",result);
    }

    @Test
    void custom() {
        Function avg = new Function("avg", 4) {

            @Override
            public double apply(double... args) {
                double sum = 0;
                for (double arg : args) {
                    sum += arg;
                }
                return sum / args.length;
            }
        };
        double result = new ExpressionBuilder("avg(1,2,3,4)")
                .function(avg)
                .build()
                .evaluate();

        double expected = 2.5d;
        assertEquals(expected, result, 0d);

        Expression e = new ExpressionBuilder("(5*2) - 1").build();
        assertEquals(9d, e.evaluate(),0d);

        log.info("result:{}",e.evaluate());
    }
}