call antlr4 -o gen/beepp/parser -package beepp.parser -lib src/beepp/parser src/beepp/parser/BEEppLexer.g4
call antlr4 -o gen/beepp/parser -package beepp.parser -lib src/beepp/parser src/beepp/parser/BEEppParser.g4 src/beepp/parser/BEEppLexer.g4
rem done
