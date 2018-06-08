package com.scorsi

class ArithmeticVisitor : ArithmeticParserBaseVisitor<Double?>() {
    override fun visitExpr(ctx: ArithmeticParser.ExprContext?): Double? {
        val operands = ctx!!.NUMBER()
        val value1 = operands[0].toString().toDouble()
        val value2 = operands[1].toString().toDouble()
        val op = ctx.operation().text
        return when (op) {
            "+" -> value1 + value2
            "-" -> value1 - value2
            "*" -> value1 * value2
            "/" -> when (value2) {
                0.0 -> null
                else -> value1 / value2
            }
            else -> null
        }
    }
}