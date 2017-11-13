package beepp;

import beepp.parser.BEEppLexer;
import beepp.parser.BEEppParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Original author: Vyacheslav Moklev
 * Last maintainer: Konstantin Chukharev (lipen00@gmail.com)
 */
public class BEEppCompiler {
    public static boolean compileFromFile(String filename_input, String filename_output) {
        return compileFromFile(filename_input, filename_output, 1);
    }

    public static boolean compileFromFile(String filename_input, String filename_output, int numberOfSolutions) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename_input))) {
            System.out.printf("[*] Reading formula from <%s>...%n", filename_input);
            List<String> clauses = br.lines().collect(Collectors.toList());

            System.out.printf("[*] Compiling formula (%d clauses) into <%s>...%n", clauses.size(), filename_output);
            return compileClauses(clauses, filename_output);
        } catch (FileNotFoundException e) {
            System.err.println("[!] No such file: " + filename_input);
        } catch (IOException e) {
            System.err.println("[!] So sad: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public static boolean compileClauses(List<String> clauses, String filename_output) {
        return compileClauses(clauses, filename_output, 1);
    }

    public static /*synchronized*/ boolean compileClauses(List<String> clauses, String filename_output, int numberOfSolutions) {
        StaticStorage.resetVarCounter();
        StaticStorage.vars = new HashMap<>();

        try (PrintWriter pw = new PrintWriter(filename_output)) {
            for (String clause : clauses) {
                CharStream inputStream = CharStreams.fromString(clause);
                BEEppLexer lexer = new BEEppLexer(inputStream);
                TokenStream tokens = new CommonTokenStream(lexer);
                BEEppParser parser = new BEEppParser(tokens);

                BEEppParser.LineContext ctx = parser.line();
                if (ctx.variable != null) {
                    pw.println(ctx.variable.getDeclaration());
                    StaticStorage.vars.put(ctx.variable.getName(), ctx.variable);
                } else if (ctx.expr != null) {
                    pw.println(ctx.expr.holds());
                } else if (ctx.comment != null) {
                    String comment = "% " + ctx.comment.replaceFirst("^//\\s*(.*)", "$1");
                    pw.println(comment);
                } else if (ctx.explicit != null) {
                    String explicit = ctx.explicit.replaceFirst("^@\\s*(.*)", "$1");
                    pw.println(explicit);
                } else {
                    System.err.printf("[!] Couldn't parse: %s%n", clause);
                }
            }

            if (numberOfSolutions == 1)
                pw.println("solve satisfy");
            else
                pw.printf("solve satisfy(%d)\n", numberOfSolutions);
            pw.flush();
        } catch (FileNotFoundException e) {
            System.err.println("[!] Couldn't open <" + filename_output + ">: " + e.getMessage());
            return false;
        }

        return true;
    }

    public static void main(String... argv) {
        if (argv.length < 1)
            throw new RuntimeException("Please, pass path to input file with bee++ formula as first argument");
        if (argv.length < 2)
            throw new RuntimeException("Please, pass path to output file with bee formula as second argument");
        String filename_input = argv[0];
        String filename_output = argv[1];

        boolean ok = compileFromFile(filename_input, filename_output);
        if (ok)
            System.out.println("[+] OK");
        else
            System.out.println("[-] FAIL");
    }
}
