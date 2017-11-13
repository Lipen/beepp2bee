package beepp.expression;

import beepp.util.Pair;

/**
 * Original author: Vyacheslav Moklev
 * Last maintainer: Konstantin Chukharev (lipen00@gmail.com)
 */
interface Expression {
    Pair<String, String> compile();
}
