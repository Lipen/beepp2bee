package beepp.expression;

import beepp.util.Pair;

import java.util.Map;

/**
 * Original author: Vyacheslav Moklev
 * Last maintainer: Konstantin Chukharev (lipen00@gmail.com)
 */
public class IntegerConstant implements IntegerExpression {
    private final int value;

    public IntegerConstant(int value) {
        this.value = value;
    }

    @Override
    public int lowerBound() {
        return value;
    }

    @Override
    public int upperBound() {
        return value;
    }

    @Override
    public Pair<String, String> compile() {
        return new Pair<>("", value + "");
    }

    @Override
    public int eval(Map<String, Object> vars) {
        return value;
    }
}
