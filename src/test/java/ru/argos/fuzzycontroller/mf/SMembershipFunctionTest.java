package ru.argos.fuzzycontroller.mf;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Тестовый класс, тестирующий S-образную функцую принадлежности.
 *
 * @author a.k.pohresnyi
 */
public class SMembershipFunctionTest extends MembershipFunctionTest {

    @Override
    protected Map<Double, Double> getCorrectData() {
        Map<Double, Double> map = new HashMap<>();

        map.put(100.0, 1.0);
        map.put(90.0, 1.0);
        map.put(80.0, 1.0);
        map.put(70.0, 0.98);
        map.put(60.0, 0.82);
        map.put(50.0, 0.5);
        map.put(40.0, 0.18);
        map.put(30.0, 0.02);
        map.put(20.0, 0.0);
        map.put(10.0, 0.0);
        map.put(0.0, 0.0);

        return map;
    }

    @Override
    protected MembershipFunction getCorrectMF() {
        return MembershipFunctionBuilder.s(25, 75);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongParametersTT(){
        MembershipFunctionBuilder.s(75, 25);
    }
}