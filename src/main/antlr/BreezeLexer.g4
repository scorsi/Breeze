lexer grammar BreezeLexer;

WS: [ \t\u000C\r\n]+ -> skip;
INTEGER: ('0' .. '9')+;
FLOAT: ('0' .. '9')+ ('.' ('0' .. '9')+)?;
STRING: '"' ~('"')* '"';
IDENT: [a-zA-Z][a-zA-Z0-9]*;

DOT: '.';
COMMA: ',';
SEMICOLON: ';';
COLON: ':';
LEFT_PARENTHESIS: '(';
RIGHT_PARENTHESIS: ')';
LEFT_CURLY_BRACKET: '{';
RIGHT_CURLY_BRACKET: '}';
LEFT_BRACKET: '[';
RIGHT_BRACK: ']';
PERCENTAGE: '%';
AND: '&';
DOUBLE_AND: '&&';
PIPE: '|';
DOUBLE_PIPE: '||';
EXCLAMATION: '!';
QUESTION: '?';
PLUS: '+';
DOUBLE_PLUS: '++';
MINUS: '-';
DOUBLE_MINUS: '--';
STAR: '*';
SLASH: '/';
EQUAL: '=';
NOT_EQUAL: '!=';
DOUBLE_EQUAL: '==';
NOT_DOUBLE_EQUAL: '!==';
TRIPLE_EQUAL: '===';
LEFT_CHEVRON: '<';
LEFT_CHEVRON_EQUAL: '<=';
RIGHT_CHEVRON: '>';
RIGHT_CHEVRON_EQUAL: '>=';