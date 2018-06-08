package com.scorsi

import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream

fun main(args: Array<String>) {
    val inputStream = ANTLRInputStream("12.3 + 4")
    val markupLexer = ArithmeticLexer(inputStream)
    val commonTokenStream = CommonTokenStream(markupLexer)
    val markupParser = ArithmeticParser(commonTokenStream)
    val exprContext = markupParser.expr()
    val visitor = ArithmeticVisitor()
    println(visitor.visit(exprContext))
}
