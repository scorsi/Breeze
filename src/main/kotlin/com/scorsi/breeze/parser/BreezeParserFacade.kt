package com.scorsi.breeze.parser

import com.scorsi.breeze.ast.Node
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream

object BreezeParserFacade {
    fun parse(input: String) : Node? {
        val lexer = BreezeLexer(CharStreams.fromString(input)!!)
        val parser = BreezeParser(CommonTokenStream(lexer))
        return try {
            ImplBreezeVisitor().visit(parser.exprList())
        } catch (e: NullPointerException) {
            null
        }
    }
}
