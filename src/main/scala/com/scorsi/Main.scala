package com.scorsi

import org.antlr.v4.runtime.{ANTLRInputStream, CommonTokenStream}

object Main {

  def main(args: Array[String]): Unit = {
    val expressions = List(
      "127.1 + 2717",
      "2674 - 4735",
      "47 * 74.1",
      "271 / 281",
      "12 ^ 3" // unsupported expression
    )
    expressions.foreach(parse)
  }

  def parse(input: String): Unit = {
    println("\nEvaluating expression " + input)
    val charStream = new ANTLRInputStream(input)
    val lexer = new ArithmeticLexer(charStream)
    val tokens = new CommonTokenStream(lexer)
    val parser = new ArithmeticParser(tokens)
    val exprContext = parser.expr()
    val arithmeticVisitor = new ArithmeticVisitor()
    val res = arithmeticVisitor.visit(exprContext)
    println(res)
  }
}
