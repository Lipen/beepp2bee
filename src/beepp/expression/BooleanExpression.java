package beepp.expression;

import beepp.util.Pair;

import java.util.Map;

/**
 * Original author: Vyacheslav Moklev
 * Last maintainer: Konstantin Chukharev (lipen00@gmail.com)
 */
public interface BooleanExpression extends Expression {
    default String holds() {
        Pair<String, String> compiled = compile();
        if (compiled.b != null) {
            return compiled.a + (compiled.a.isEmpty() ? "" : "\n") + "bool_eq(" + compiled.b + ", true)";
        } else {
            return compiled.a;
        }
    }

    default BooleanExpression not() {
        return new NegateBooleanExpression(this);
    }

    default BooleanExpression and(BooleanExpression... exprs) {
        return new UniformBooleanOperation("and", this, exprs);
    }

    default BooleanExpression or(BooleanExpression... exprs) {
        return new UniformBooleanOperation("or", this, exprs);
    }

    default BooleanExpression xor(BooleanExpression... exprs) {
        return new UniformBooleanOperation("xor", this, exprs);
    }

    default BooleanExpression iff(BooleanExpression... exprs) {
        return new UniformBooleanOperation("iff", this, exprs);
    }

    default BooleanExpression then(BooleanExpression expr) {
        return new ThenBooleanOperation(this, expr);
    }

    boolean eval(Map<String, Object> vars);
}
