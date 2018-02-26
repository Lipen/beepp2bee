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
public class SumOperation implements IntegerExpression {
    private final List<BooleanExpression> list;

    public SumOperation(List<BooleanExpression> list) {
        this.list = list;
    }

    @Override
    public int lowerBound() {
        return 0;
    }

    @Override
    public int upperBound() {
        return list.size();
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

        return new Pair<>(constraints.stream().collect(Collectors.joining("\n")), newSumVar);
    }

    @Override
    public int eval(Map<String, Object> vars) {
        return (int) list.stream()
                .map(e -> e.eval(vars))
                .filter(v -> v)
                .count();
    }
}
