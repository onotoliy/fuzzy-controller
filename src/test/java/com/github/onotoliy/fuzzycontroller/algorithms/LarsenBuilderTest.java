package com.github.onotoliy.fuzzycontroller.algorithms;

import org.junit.Assert;
import org.junit.Test;
import com.github.onotoliy.fuzzycontroller.cm.ClarityMethod;
import com.github.onotoliy.fuzzycontroller.variables.Variable;
import com.github.onotoliy.fuzzycontroller.variables.VariableBuilder;

import java.util.List;
import java.util.Map;

import static com.github.onotoliy.fuzzycontroller.algorithms.LarsenBuilder.rule;
import static com.github.onotoliy.fuzzycontroller.cm.ClarityMethodBuilder.centerOfGravity;
import static com.github.onotoliy.fuzzycontroller.cm.ClarityMethodBuilder.leftMaximum;
import static com.github.onotoliy.fuzzycontroller.cm.ClarityMethodBuilder.rightMaximum;
import static com.github.onotoliy.fuzzycontroller.mf.MembershipFunctionBuilder.bell;
import static com.github.onotoliy.fuzzycontroller.operators.OperatorBuilder.and;

/**
 * Тестирование нечеткого алгоритма логического вывода. Алгоритм Ларсена.
 *
 * @author Anatoliy Pokhresnyi
 */
public class LarsenBuilderTest {

    @Test
    public void testOf() {
        Variable service = VariableBuilder.of(
                "service", Map.of(
                        "service_000", bell(0.2, 1, 0.00),
                        "service_025", bell(0.2, 1, 0.25),
                        "service_050", bell(0.2, 1, 0.50),
                        "service_075", bell(0.2, 1, 0.75),
                        "service_100", bell(0.2, 1, 1.00)));

        Variable food = VariableBuilder.of(
                "food", Map.of(
                        "food_000", bell(0.2, 1, 0.00),
                        "food_050", bell(0.2, 1, 0.50),
                        "food_100", bell(0.2, 1, 1.00)));

        Variable tip = VariableBuilder.of(
                "tip", Map.of(
                        "tip_000", bell(0.2, 1, 0.00),
                        "tip_025", bell(0.2, 1, 0.25),
                        "tip_050", bell(0.2, 1, 0.50),
                        "tip_075", bell(0.2, 1, 0.75),
                        "tip_100", bell(0.2, 1, 1.00)));

        List<Larsen.Rule> rules = List.of(
                rule(and(food.is("food_000"), service.is("service_000")), tip.get("tip_000")),
                rule(and(food.is("food_000"), service.is("service_025")), tip.get("tip_000")),
                rule(and(food.is("food_000"), service.is("service_050")), tip.get("tip_000")),
                rule(and(food.is("food_000"), service.is("service_075")), tip.get("tip_025")),
                rule(and(food.is("food_000"), service.is("service_100")), tip.get("tip_025")),

                rule(and(food.is("food_050"), service.is("service_000")), tip.get("tip_000")),
                rule(and(food.is("food_050"), service.is("service_025")), tip.get("tip_025")),
                rule(and(food.is("food_050"), service.is("service_050")), tip.get("tip_050")),
                rule(and(food.is("food_050"), service.is("service_075")), tip.get("tip_050")),
                rule(and(food.is("food_050"), service.is("service_100")), tip.get("tip_050")),

                rule(and(food.is("food_100"), service.is("service_000")), tip.get("tip_025")),
                rule(and(food.is("food_100"), service.is("service_025")), tip.get("tip_050")),
                rule(and(food.is("food_100"), service.is("service_050")), tip.get("tip_100")),
                rule(and(food.is("food_100"), service.is("service_075")), tip.get("tip_100")),
                rule(and(food.is("food_100"), service.is("service_100")), tip.get("tip_100")));

        Larsen larsen = LarsenBuilder.of(rules);

        calc(0.13186957053691023, larsen, Map.of("food", 0.00, "service", 0.25), centerOfGravity());
        calc(0.00000000000000000, larsen, Map.of("food", 0.00, "service", 0.25), leftMaximum());
        calc(0.00000000000000000, larsen, Map.of("food", 0.00, "service", 0.25), rightMaximum());
        calc(0.21162255509478559, larsen, Map.of("food", 0.25, "service", 0.25), centerOfGravity());
        calc(0.00000000000000000, larsen, Map.of("food", 0.25, "service", 0.25), leftMaximum());
        calc(0.49999999999996125, larsen, Map.of("food", 0.25, "service", 0.25), rightMaximum());
        calc(0.31646267564446290, larsen, Map.of("food", 0.50, "service", 0.25), centerOfGravity());
        calc(0.24999999999998880, larsen, Map.of("food", 0.50, "service", 0.25), leftMaximum());
        calc(0.24999999999998880, larsen, Map.of("food", 0.50, "service", 0.25), rightMaximum());
        calc(0.29701339982473480, larsen, Map.of("food", 0.75, "service", 0.25), centerOfGravity());
        calc(0.00000000000000000, larsen, Map.of("food", 0.75, "service", 0.25), leftMaximum());
        calc(0.99999999999990620, larsen, Map.of("food", 0.75, "service", 0.25), rightMaximum());
        calc(0.52172746828520420, larsen, Map.of("food", 1.00, "service", 0.25), centerOfGravity());
        calc(0.49999999999996125, larsen, Map.of("food", 1.00, "service", 0.25), leftMaximum());
        calc(0.49999999999996125, larsen, Map.of("food", 1.00, "service", 0.25), rightMaximum());
    }

    private void calc(double expected, Larsen larsen, Map<String, Double> parameters, ClarityMethod cm) {
        Assert.assertEquals(expected, cm.calc(larsen.compose(parameters)), 0.0001);
    }
}
