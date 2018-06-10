package com.scorsi

class YaulVisitor : YaulParserBaseVisitor<Node?>() {
    override fun visitExpression(ctx: YaulParser.ExpressionContext?): Node? =
            when (ctx) {
                null -> null
                else -> ctx.methodCall().let {
                    when (it) {
                        null -> ctx.STRING().let {
                            when (it) {
                                null -> null
                                else -> Node.Expression(Node.String(it.text))
                            }
                        }
                        else -> visit(it).let {
                            when (it) {
                                null -> null
                                else -> Node.Expression(it)
                            }
                        }
                    }
                }
            }

    override fun visitMethodCall(ctx: YaulParser.MethodCallContext?): Node? =
            when (ctx) {
                null -> null
                else -> visit(ctx.methodCallArguments()).let {
                    when (it) {
                        is Node.MethodCallArguments -> Node.MethodCall(ctx.IDENT().text, it)
                        else -> null
                    }
                }
            }

    override fun visitMethodCallArguments(ctx: YaulParser.MethodCallArgumentsContext?): Node? =
            when (ctx) {
                null -> null
                else -> when (ctx.ruleIndex) {
                    0 -> Node.MethodCallArguments(listOf())
                    else -> ctx.expression().let top@{ contextList ->
                        when (contextList) {
                            null -> null
                            else -> Node.MethodCallArguments(mutableListOf<Node.Expression>().apply {
                                contextList.forEach { context ->
                                    when (context) {
                                        null -> return@top null
                                        else -> visit(context).let {
                                            when (it) {
                                                is Node.Expression -> add(it)
                                                else -> return@top null
                                            }
                                        }
                                    }
                                }
                            }.toList())
                        }
                    }
                }
            }
}