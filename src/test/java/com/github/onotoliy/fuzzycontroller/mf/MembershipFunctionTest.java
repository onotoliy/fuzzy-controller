package com.github.onotoliy.fuzzycontroller.mf;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * Тестирование стандартной функции принадлежности.
 *
 * @author Anatoliy Pokhresnyi
 */
public abstract class MembershipFunctionTest {

    /**
     * Результат функции {@see MembershipFunction#toString}.
     *
     * @return Результат {@see MembershipFunction#toString}.
     */
    protected abstract String getCorrectToString();

    /**
     * Данные ручных рассчетов функции принадлежности, где {@link Map.Entry#getKey()} - величина, относительно которой
     * сформирована функция принадлежности, а {@link Map.Entry#getValue()} - ожидаемый результат функции принадлежности.
     *
     * @return Данные ручных рассчетов функции принадлежности.
     */
    protected abstract Map<Double, Double> getCorrectData();

    /**
     * Функция принадлежности с корректными прараметрами.
     *
     * @return функция принадлежности.
     */
    protected abstract MembershipFunction getCorrectMF();

    @Test
    public void testCalc() {
        MembershipFunction mf = getCorrectMF();

        getCorrectData().forEach((key, value) -> Assert.assertEquals(value, mf.calc(key), 0.00001));
    }

    @Test
    public void testToString() {
        Assert.assertEquals(getCorrectToString(), getCorrectMF().toString());
    }
}
