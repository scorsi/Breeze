package com.scorsi.parser

import com.scorsi.BreezeLexer
import com.scorsi.BreezeParser
import com.scorsi.ast.Node
import com.scorsi.ast.Position
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.*
import org.antlr.v4.runtime.atn.ATNConfigSet
import org.antlr.v4.runtime.dfa.DFA
import java.io.*
import java.nio.charset.Charset
import java.util.*

data class BreezeError constructor(val msg: String?, val position: Position)

data class ParsingResult(val root: Node?, val errors: List<BreezeError>) {
    fun isCorrect() = errors.isEmpty() && root != null
}

fun String.toStream(charset: Charset = Charsets.UTF_8) = ByteArrayInputStream(toByteArray(charset))

object BreezeParserFacade {
    fun parse(string: String): ParsingResult = parse(CharStreams.fromString(string)!!)

    fun parse(reader: Reader): ParsingResult = parse(CharStreams.fromReader(reader)!!)

    fun parse(stream: CharStream): ParsingResult {
        val lexicalAndSyntaxErrors = LinkedList<BreezeError>()
        val errorListener = object : ANTLRErrorListener {
            override fun reportAmbiguity(recognizer: Parser?, dfa: DFA?, startIndex: Int, stopIndex: Int, exact: Boolean, ambigAlts: BitSet?, configs: ATNConfigSet?) {
                // Ignore for now
            }

            override fun reportAttemptingFullContext(recognizer: Parser?, dfa: DFA?, startIndex: Int, stopIndex: Int, conflictingAlts: BitSet?, configs: ATNConfigSet?) {
                // Ignore for now
            }

            override fun syntaxError(recognizer: Recognizer<*, *>?, offendingSymbol: Any?, line: Int, charPositionInLine: Int, msg: String?, e: RecognitionException?) {
                lexicalAndSyntaxErrors.add(BreezeError(msg, Position(line, charPositionInLine)))
            }

            override fun reportContextSensitivity(recognizer: Parser?, dfa: DFA?, startIndex: Int, stopIndex: Int, prediction: Int, configs: ATNConfigSet?) {
                // Ignore for now
            }
        }
        val lexer = BreezeLexer(stream)
        lexer.removeErrorListeners()
        lexer.addErrorListener(errorListener)
        val parser = BreezeParser(CommonTokenStream(lexer))
        parser.removeErrorListeners()
        parser.addErrorListener(errorListener)
        try {
            val astRoot = BreezeVisitor().visit(parser.program())
            // ADD SEMANTIC ERRORS HERE
            return ParsingResult(astRoot, lexicalAndSyntaxErrors)
        } catch (e: NullPointerException) {
            throw e
            return ParsingResult(null, lexicalAndSyntaxErrors)
        }
    }
}
