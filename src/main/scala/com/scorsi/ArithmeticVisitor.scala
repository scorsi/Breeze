package com.scorsi

import scala.util.Try

class ArithmeticVisitor extends ArithmeticParserBaseVisitor[Double] {
  override def visitExpr(ctx: ArithmeticParser.ExprContext): Double = {
    val operands = ctx.NUMBER()
    val operation = ctx.operation().getText
    calculate(
      parseDouble(ctx.NUMBER(0).getText).getOrElse(0.0),
      parseDouble(ctx.NUMBER(1).getText).getOrElse(0.0),
      ctx.operation().getText)
    match {
      case Some(result) => result
      case None => 0.0
    }
  }

  def parseDouble(s: String): Option[Double] = Try(s.toDouble).toOption

  def calculate(val1: Double, val2: Double, op: String): Option[Double] = {
    op match {
      case "+" => Some(val1 + val2)
      case "-" => Some(val1 - val2)
      case "*" => Some(val1 * val2)
      case "/" => Try(val1 / val2).toOption
      case _ =>
        println("Unsupported operator")
        None
    }
  }
}
