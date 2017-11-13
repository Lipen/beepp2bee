package beepp;

import beepp.expression.Variable;

import java.util.Map;

/**
 * Original author: Vyacheslav Moklev
 * Last maintainer: Konstantin Chukharev (lipen00@gmail.com)
 */
public class StaticStorage {
    private static int lastTempVar;
    public static Map<String, Variable> vars;

    static void resetVarCounter() {
        lastTempVar = 1;
    }

    public static String newVar() {
        int val = lastTempVar++;
        StringBuilder sb = new StringBuilder();
        while (val > 0) {
            int mod = val % 26;
            val /= 26;
            sb.append((char) ('a' + mod));
        }
        return sb.toString();
    }
}
