package beepp.expression;

import beepp.util.Pair;

import java.util.Map;

/**
 * Original author: Vyacheslav Moklev
 * Last maintainer: Konstantin Chukharev (lipen00@gmail.com)
 */
public enum BooleanConstant implements BooleanExpression {
    FALSE(false), TRUE(true);

    private final boolean value;

    BooleanConstant(boolean value) {
        this.value = value;
    }

    @Override
    public Pair<String, String> compile() {
        return new Pair<>("", value + "");
    }


    @Override
    public boolean eval(Map<String, Object> vars) {
        return value;
    }
}
