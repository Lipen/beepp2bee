package beepp.util;

/**
 * Original author: Vyacheslav Moklev
 * Last maintainer: Konstantin Chukharev (lipen00@gmail.com)
 */
public class Pair<U, V> {
    public final U a;
    public final V b;

    public Pair(U a, V b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return "(" + a + ", " + b + ")";
    }
}
