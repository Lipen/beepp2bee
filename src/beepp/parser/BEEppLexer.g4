lexer grammar BEEppLexer;

// Keywords
INT_KEYWORD:        'int';
DUAL_INT_KEYWORD:   'dual_int';
BOOL_KEYWORD:       'bool';

// Integer operations
PLUS:       '+';
TIMES:      '*';
DIV:        '/';
MOD:        '%';
MIN:        'min';
MAX:        'max';
MINUS:      '-';

// Integer to boolean operations
LESS:       '<';
LESS_EQ:    '<=';
GREATER:    '>';
GREATER_EQ: '>=';
NOT_EQ:     '!=';

// Boolean operations
NOT:        '!';
OR:         '|';
AND:        '&';
XOR:        '^';
EQ:         '=';
IFF:        '<=>';
ARROW:      '->';
ALO:        'ALO';
AMO:        'AMO';

// Boolean operations: alternative notation
ALT_OR:     'or' -> type(OR);
ALT_AND:    'and' -> type(AND);
ALT_XOR:    'xor' -> type(XOR);
ALT_IFF:    'iff' -> type(IFF);
ALT_ARROW:  '=>' -> type(ARROW);

// Control sequences
COLON:          ':';
DOUBLE_DOT:     '..';
COMMA:          ',';
LEFT_BRACKET:   '(';
RIGHT_BRACKET:  ')';

// Base tokens
INT_CONST:  '0' | '-'?[1-9][0-9]*;
BOOL_CONST: 'true' | 'false';
ID:         [a-zA-Z_]+ [a-zA-Z0-9_]*;

// Ignored characters -- used only as a delimiters
WS:           [ \t\n\r]+ -> channel(HIDDEN);
LINE_COMMENT: '//' ~'\n'*;
EXPLICIT:     '@' ~'\n'*;
