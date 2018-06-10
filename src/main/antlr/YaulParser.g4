parser grammar YaulParser;

options { tokenVocab=YaulLexer; }

expression
    : methodCall
    | STRING
    ;

methodCall
    : IDENT '(' methodCallArguments ')'
    ;

methodCallArguments
    : // No arguments
    | expression (',' expression)*  // Some arguments
    ;