lexer grammar YaulLexer;

WS: [ \t\u000C\r\n]+ -> skip;
NUMBER: ('0' .. '9') + ('.' ('0' .. '9') +)?;
STRING: '"' ~('"')* '"';
IDENT: [a-zA-Z][a-zA-Z0-9]*;

COMMA: ',';
COLON: ':';
LPAREN: '(';
RPAREN: ')';
LBRACK: '{';
RBRACK: '}';
EXCLAMATION: '!';
QUESTION: '?';

OP_ADD: '+';
OP_SUB: '-';
OP_MUL: '*';
OP_DIV: '/';
OP_EQUAL: '=';
OP_LESS: '<';
OP_ELESS: '<=';
OP_MORE: '>';
OP_EMORE: '>=';