parser grammar YaulParser;

options {
    tokenVocab=YaulLexer;
}

program
    : statement*
    ;

statement
    : declarationStatement
    | expressionStatement
    ;

declarationStatement
    : declaration ';'
    ;

expressionStatement
    : expression ';'
    ;

expressionSequence
    : expression (',' expression)*
    ;

expression
    : expression '[' expressionSequence ']'                     // ARRAY ACCESS
    | expression '.' expression                                 // PROTYPE ATTRIBUTE/METHOD ACCESS
    | expression '(' expressionSequence ')'                     // FUNCTION CALL
    | '++' expression
    | '--' expression
    | '+' expression
    | '-' expression
    | '!' expression
    | expression ('*' | '/' | '%') expression
    | expression ('+' | '-') expression
    //| expression ('<<' | '>>') expression
    | expression ('<' | '>' | '<=' | '>=' | '==') expression
    | expression '&&' expression
    | expression '||' expression
    | IDENT
    | STRING
    | INTEGER
    | DOUBLE
    | '(' expression ')'
    ;

declaration
    : variableDeclaration
    | functionDeclaration
    | prototypeDeclaration
    ;

// --====================================================================--

/*                      --- VARIABLE DECLARATION ---
myVariable = 1
 */
variableDeclaration
    : name=IDENT '=' value=expression
    ;

/*                      --- FUNCTION DECLARATION ---
myFunction = () { ... }
myFunction = (a) { ... }
myFunction = (a, b) { ... }
...
 */
functionDeclaration
    : name=IDENT '=' '(' args=functionDeclarationArguments ')' '{' body=functionDeclarationBody '}'
    ;

functionDeclarationArguments
    : (IDENT (',' IDENT)*)?
    ;

functionDeclarationBody
    : expressionStatement*
    ;

/*                      --- PROTOTYPE DECLARATION ---
myProto = {
    myAttribute = 0
    myMethod = () { self.myAttribute }
    ...
}
 */
prototypeDeclaration
    : name=IDENT '=' '{' body=prototypeDeclarationBody '}'
    ;

prototypeDeclarationBody
    : declarationStatement*
    ;
