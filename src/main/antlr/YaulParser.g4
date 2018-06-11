parser grammar YaulParser;

options { tokenVocab=YaulLexer; }

rootExpressions
    :
    | variableDeclaration rootExpressions
    | functionDeclaration rootExpressions
    | expression rootExpressions
    ;

expressions
    : expression+
    ;

expression
    : left=expression operator=('/'|'*') right=expression
    | left=expression operator=('+'|'-') right=expression
    | '(' expression ')'
    | unary=('+'|'-') expression
    | methodCall
    | variableDeclaration
    | IDENT
    | STRING
    | NUMBER
    ;

variableDeclaration
    : '!' '(' name=IDENT ':' type=IDENT ')' '{' expressions '}'
    ;

functionDeclaration
    : '!' '(' name=IDENT ':' type=IDENT ':' '(' functionArguments ')' ')' '{' expressions '}'
    ;

functionArguments
    :
    | name=IDENT ':' type=IDENT (',' functionArguments)*?
    ;

methodCall
    : name=IDENT '(' methodCallArguments ')'
    ;

methodCallArguments
    :
    | expression (',' expression)*
    ;