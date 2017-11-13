package beepp.expression;

import beepp.util.Pair;

import java.util.Map;

/**
 * Original author: Vyacheslav Moklev
 * Last maintainer: Konstantin Chukharev (lipen00@gmail.com)
 */
public class NegateExpression implements IntegerExpression {
    private final IntegerExpression expr;

    public NegateExpression(IntegerExpression expr) {
        this.expr = expr;
    }

    @Override
    public int lowerBound() {
        // x ∈ [a, b] ⇒ -x ∈ [-b, -a]
        return -expr.upperBound();
    }

    @Override
    public int upperBound() {
        return -expr.lowerBound();
    }

    @Override
    public Pair<String, String> compile() {
        if (expr instanceof NegateExpression) {
            return ((NegateExpression) expr).expr.compile();
        } else {
            Pair<String, String> compiled = expr.compile();
            return new Pair<>(compiled.a, "-" + compiled.b);
        }
    }

    @Override
    public int eval(Map<String, Object> vars) {
        return -expr.eval(vars);
    }
}
