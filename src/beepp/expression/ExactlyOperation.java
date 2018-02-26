package beepp.expression;

import beepp.StaticStorage;
import beepp.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Original author: Vyacheslav Moklev
 * Last maintainer: Konstantin Chukharev (lipen00@gmail.com)
 */
public class ExactlyOperation implements BooleanExpression {
    private final int m;
    private final List<BooleanExpression> list;

    public ExactlyOperation(int m, List<BooleanExpression> list) {
        this.m = m;
        this.list = list;
    }

    @Override
    public Pair<String, String> compile() {
        List<String> constraints = new ArrayList<>();
        List<String> names = new ArrayList<>();
        for (BooleanExpression expr : list) {
            Pair<String, String> compiled = expr.compile();
            if (!compiled.a.isEmpty())
                constraints.add(compiled.a);
            names.add(compiled.b);
        }

        String newSumVar = StaticStorage.newVar();
        constraints.add("new_int_dual(" + newSumVar + ", 0, " + names.size() + ")");
        constraints.add("bool_array_sum_eq(" + names + ", " + newSumVar + ")");
        String newVar = StaticStorage.newVar();
        constraints.add("new_bool(" + newVar + ")");
        constraints.add("int_eq_reif(" + newSumVar + ", " + m + ", " + newVar + ")");

        return new Pair<>(constraints.stream().collect(Collectors.joining("\n")), newVar);
    }

    @Override
    public boolean eval(Map<String, Object> vars) {
        return list.stream()
                .map(e -> e.eval(vars))
                .filter(v -> v)
                .count() == m;
    }
}
