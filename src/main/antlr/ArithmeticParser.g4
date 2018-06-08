parser grammar ArithmeticParser;

@header {
package com.scorsi;
}

options { tokenVocab=ArithmeticLexer; }

expr: NUMBER operation NUMBER;

operation: (ADD | SUB | MUL | DIV);