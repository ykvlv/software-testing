package softwaretesting.lab1.test1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import softwaretesting.lab1.task1.TgFunc;



public class TestTgFunc {
    double eps = 0.5;

    @ParameterizedTest
    @ValueSource(doubles = { Math.PI/6, Math.PI/4, Math.PI/3, 0, Math.PI/6, Math.PI/4, Math.PI/3 })
    public void baseLogin(double value) {
        Assertions.assertEquals(Math.tan(value), TgFunc.tg(value), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            -Math.PI/2-1,
            -Math.PI/2,
            Math.PI/2,
            Math.PI/2+1,
            Double.NaN,
            Double.NEGATIVE_INFINITY,
            Double.POSITIVE_INFINITY
    })
    public void undefined(double value) {
        Assertions.assertEquals(Double.NaN, TgFunc.tg(value), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = { Math.PI/2-0.1, Math.PI/2-0.01 })
    public void breakPoint(double value) {
        Assertions.assertEquals(Math.tan(value), TgFunc.tg(value), eps*190);
    }
}
