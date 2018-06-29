grammar Breeze;

WS: [ \t\u000C\r\n]+ -> skip;
COMMENT: '/*' (.)*? '*/' -> skip;

NUMBER: [0-9]+ (. [0-9]+)?;
IDENT: '@'? [a-zA-Z][a-zA-Z0-9]*;
STRING: '"' ~('"')* '"' { setText(getText().substring(1, getText().length()-1)); };

ADD: '+';
SUB: '-';
DIV: '/';
MUL: '*';
MOD: '%';
POW: '^';
EQU: '==';
SEQ: '===';
NEQ: '!=';
NSE: '!==';
LOW: '<';
LOE: '<=';
GTR: '>';
GTE: '>=';
AND: '&&';
OR: '||';

exprList: expr*;

expr
    : left=expr '.' right=expr                              # dotAccessExpr
    | left=expr '[' index=expr ']'                          # arrayAccessExpr
    | left=expr '(' (expr (',' expr)*)? ')'                 # callExpr
    | left=expr '=' right=expr                              # varDeclaration
    | '[' (IDENT '=' expr (',' IDENT '=' expr)*)? ']'       # mapDeclaration
    | '[' (expr (',' expr)*)? ']'                           # arrayDeclaration
    | '{' (IDENT (',' IDENT)* '->')? body=expr* '}'         # functionDeclaration
    | left=expr op=('*' | '/' | '%') right=expr             # calcExpr
    | left=expr op=('+' | '-') right=expr                   # calcExpr
    | left=expr op=('<' | '>' | '<=' | '>=') right=expr     # compExpr
    | left=expr op=('==' | '!=' | '===' | '!==') right=expr # compExpr
    | left=expr op=('&&' | '||') right=expr                 # compExpr
    | '<-' expr                                             # returnExpr
    | NUMBER                                                # numberExpr
    | IDENT                                                 # identExpr
    | STRING                                                # stringExpr
    | '(' expr ')'                                          # parenExpr
    ;
