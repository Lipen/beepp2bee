package beepp.expression;

/**
 * Original author: Vyacheslav Moklev
 * Last maintainer: Konstantin Chukharev (lipen00@gmail.com)
 */
public abstract class Variable {
    final String name;

    Variable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract String getDeclaration();
}
