package com.github.onotoliy.fuzzycontroller.operators;

import com.github.onotoliy.fuzzycontroller.variables.Term;

import java.util.List;
import java.util.Map;

/**
 * Логический оператор.
 *
 * @author Anatoliy Pokhresnyi
 */
public interface Operator {

    /**
     * Преобразование логического оператора в четкое число.
     *
     * @param parameters Входные параметры, где {@link Map.Entry#getKey()} -
     *                  название лингвистической переменной, а
     *                  {@link Map.Entry#getValue()} - входное значение.
     * @return Четкое значение.
     */

    double calc(Map<String, Double> parameters);

    /**
     * Получает термы, содержащиеся в логическом операторе.
     *
     * @return Термы, содержащиеся в логическом операторе.
     */
    List<Term> getTerms();
}
