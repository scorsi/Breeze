package com.scorsi

import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream

fun main(args: Array<String>) {
    val inputStream = ANTLRInputStream("toto(\"titi\")")
    val markupLexer = YaulLexer(inputStream)
    val commonTokenStream = CommonTokenStream(markupLexer)
    val markupParser = YaulParser(commonTokenStream)
    val exprContext = markupParser.expression()
    val visitor = YaulVisitor()
    println(visitor.visit(exprContext))
}