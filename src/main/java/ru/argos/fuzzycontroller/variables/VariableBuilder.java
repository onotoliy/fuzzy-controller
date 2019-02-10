package ru.argos.fuzzycontroller.variables;

import ru.argos.fuzzycontroller.mf.MembershipFunction;
import ru.argos.fuzzycontroller.operators.Operator;

import java.util.List;
import java.util.Map;

import static ru.argos.fuzzycontroller.utils.Utils.getOrThrow;
import static ru.argos.fuzzycontroller.utils.Utils.unmodifiableList;

/**
 * Статический класс в котором реализовано создание лингвистической переменной.
 *
 * @author a.k.pohresnyi
 */
public final class VariableBuilder {

    /**
     * Начала интервала.
     */
    private static final double START_WITH = 0.0;

    /**
     * Конец интервала.
     */
    private static final double END_WITH = 1.0;

    /**
     * Шаг.
     */
    private static final double STEP = 0.0001;

    /**
     * Приветный конструктор.
     */
    private VariableBuilder() {

    }

    /**
     * Создает лингвистическую переменную.
     *
     * @param name Название лингвистической переменной
     * @param terms Список термов, где {@link Map.Entry#getKey()} - название
     *             терма, а {@link Map.Entry#getValue()} - функция
     *             принадлежности.
     * @return Лингвистическая переменная.
     */
    public static Variable of(final String name,
                              final Map<String, MembershipFunction> terms) {
        return of(name, START_WITH, END_WITH, STEP, terms);
    }

    /**
     * Создает лингвистическую переменную.
     *
     * @param name Название лингвистической переменной
     * @param startWith Начала интервала.
     * @param endWith Начала интервала.
     * @param step Шаг.
     * @param terms Список термов, где {@link Map.Entry#getKey()} - название
     *             терма, а {@link Map.Entry#getValue()} - функция
     *              принадлежности.
     * @return Лингвистическая переменная.
     */
    public static Variable of(final String name,
                              final double startWith,
                              final double endWith,
                              final double step,
                              final Map<String, MembershipFunction> terms) {
        return new Variable() {
            @Override
            public Operator is(final String term) {
                return new Operator() {
                    @Override
                    public double calc(final Map<String, Double> map) {
                        return getOrThrow(terms, term)
                                .calc(getOrThrow(map, name));
                    }

                    @Override
                    public List<Term> getTerms() {
                        return unmodifiableList(get(term));
                    }

                    @Override
                    public String toString() {
                        return name + " IS " + term;
                    }
                };
            }

            @Override
            public Term get(final String term) {
                return TermBuilder.of(this, term, getOrThrow(terms, term));
            }

            @Override
            public String name() {
                return name;
            }

            @Override
            public double startWith() {
                return startWith;
            }

            @Override
            public double endWith() {
                return endWith;
            }

            @Override
            public double step() {
                return step;
            }
        };
    }
}
