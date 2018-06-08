package com.scorsi

class ArithmeticVisitor extends ArithmeticParserBaseVisitor[String] {
  override def visitExpr(ctx: ArithmeticParser.ExprContext): String = {
    print(ctx)
    "test"
  }
}
