package beepp.expression;

import beepp.util.Pair;

import java.util.Map;

/**
 * Original author: Vyacheslav Moklev
 * Last maintainer: Konstantin Chukharev (lipen00@gmail.com)
 */
public class NegateBooleanExpression implements BooleanExpression {
    private final BooleanExpression expr;

    public NegateBooleanExpression(BooleanExpression expr) {
        this.expr = expr;
    }

    @Override
    public Pair<String, String> compile() {
        if (expr instanceof NegateBooleanExpression) {
            return ((NegateBooleanExpression) expr).expr.compile();
        } else {
            Pair<String, String> compiled = expr.compile();
            return new Pair<>(compiled.a, "-" + compiled.b);
        }
    }

    @Override
    public boolean eval(Map<String, Object> vars) {
        return !expr.eval(vars);
    }
}
