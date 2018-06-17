parser grammar BreezeParser;

options {
    tokenVocab=BreezeLexer;
}

program
    : statement* EOF
    ;

statement
    : expression ';'        # expressionStatement
    | declaration ';'       # expressionDeclaration
    ;

// EXPRESSION STATEMENT

expressionSequence
    : expression (',' expression)*
    ;

expression
    : expression '[' member=expression ']'                                      # memberIndexExpression
    | expression '.' member=expression                                          # membreDotExpression
    | expression '(' arguments=expressionSequence ')'                           # callExpression
    | unary='++' expression                                                     # unaryExpression
    | unary='--' expression                                                     # unaryExpression
    | unary='+' expression                                                      # unaryExpression
    | unary='-' expression                                                      # unaryExpression
    | unary='!' expression                                                      # unaryExpression
    | left=expression op=('*' | '/' | '%') right=expression                     # calcExpression
    | left=expression op=('+' | '-') right=expression                           # calcExpression
    //| expression ('<<' | '>>') expression
    | left=expression op=('<' | '>' | '<=' | '>=') right=expression             # calcExpression
    | left=expression op=('!=' | '==' | '!==' | '===') right=expression         # calcExpression
    | left=expression op='&&' right=expression                                  # calcExpression
    | left=expression op='||' right=expression                                  # calcExpression
    | IDENT                                                                     # identityExpression
    | STRING                                                                    # stringExpression
    | INTEGER                                                                   # integerExpression
    | FLOAT                                                                     # floatExpression
    | '(' expression ')'                                                        # parenthesizedExpression
    ;

// DECLARATION STATEMENT

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
    : (expression ';')*
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
    : (declaration ';')*
    ;
