package com.scorsi

import io.kotlintest.data.forall
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row
import org.antlr.v4.runtime.CharStreams
import java.util.*

object LexerTestHelper {
    private fun fullTokens(lexer: BreezeLexer) =
            LinkedList<Pair<String, String?>>().apply {
                do {
                    val t = lexer.nextToken()
                    when (t.type) {
                        -1 -> add(Pair("EOF", ""))
                        else -> add(Pair(lexer.ruleNames[t.type - 1], t.text))
                    }
                } while (t.type != -1)
            }

    private fun nameTokens(lexer: BreezeLexer) =
            LinkedList<String>().apply {
                do {
                    val t = lexer.nextToken()
                    when (t.type) {
                        -1 -> add("EOF")
                        else -> add(lexer.ruleNames[t.type - 1])
                    }
                } while (t.type != -1)
            }

    fun fullTokensForCode(code: String) = fullTokens(BreezeLexer(CharStreams.fromString(code)))
    fun nameTokensForCode(code: String) = nameTokens(BreezeLexer(CharStreams.fromString(code)))

    fun fullTokensForResource(resourceName: String) = fullTokens(BreezeLexer(CharStreams.fromStream(this.javaClass.getResourceAsStream("/$resourceName.breeze"))))
}

class LexerTest : StringSpec({
    "can tokenize integers" {
        LexerTestHelper.fullTokensForCode("4") shouldBe listOf(Pair("INTEGER", "4"), Pair("EOF", ""))
        LexerTestHelper.fullTokensForCode("9876652") shouldBe listOf(Pair("INTEGER", "9876652"), Pair("EOF", ""))
    }
    "can tokenize floats" {
        LexerTestHelper.fullTokensForCode("3.3") shouldBe listOf(Pair("FLOAT", "3.3"), Pair("EOF", ""))
        LexerTestHelper.fullTokensForCode("123.308") shouldBe listOf(Pair("FLOAT", "123.308"), Pair("EOF", ""))
        LexerTestHelper.fullTokensForCode("0.0") shouldBe listOf(Pair("FLOAT", "0.0"), Pair("EOF", ""))
    }
    "can tokenize string" {
        LexerTestHelper.fullTokensForCode("\"\"") shouldBe listOf(Pair("STRING", ""), Pair("EOF", ""))
        LexerTestHelper.fullTokensForCode("\"test\"") shouldBe listOf(Pair("STRING", "test"), Pair("EOF", ""))
        LexerTestHelper.fullTokensForCode("\"0\"") shouldBe listOf(Pair("STRING", "0"), Pair("EOF", ""))
    }
    "can tokenize identifier" {
        LexerTestHelper.fullTokensForCode("test") shouldBe listOf(Pair("IDENT", "test"), Pair("EOF", ""))
        LexerTestHelper.fullTokensForCode("toto") shouldBe listOf(Pair("IDENT", "toto"), Pair("EOF", ""))
    }
    "can tokenize unique token" {
        LexerTestHelper.nameTokensForCode("""
                ( { [ ] } )
                . , ; : ! ?
                % & && | ||
                + ++ - -- * /
                = == === != !==
                < > <= >=
            """.trimIndent()) shouldBe listOf(
                "LEFT_PARENTHESIS", "LEFT_CURLY_BRACKET", "LEFT_BRACKET", "RIGHT_BRACKET", "RIGHT_CURLY_BRACKET", "RIGHT_PARENTHESIS",
                "DOT", "COMMA", "SEMICOLON", "COLON", "EXCLAMATION", "QUESTION",
                "PERCENTAGE", "AND", "DOUBLE_AND", "PIPE", "DOUBLE_PIPE",
                "PLUS", "DOUBLE_PLUS", "MINUS", "DOUBLE_MINUS", "STAR", "SLASH",
                "EQUAL", "DOUBLE_EQUAL", "TRIPLE_EQUAL", "NOT_EQUAL", "NOT_DOUBLE_EQUAL",
                "LEFT_CHEVRON", "RIGHT_CHEVRON", "LEFT_CHEVRON_EQUAL", "RIGHT_CHEVRON_EQUAL",
                "EOF")
    }
    "can tokenize variable declaration/assignment" {
        forall(
                row("a = 2", mutableListOf(Pair("IDENT", "a"), Pair("EQUAL", "="), Pair("INTEGER", "2"))),
                row("a=2", mutableListOf(Pair("IDENT", "a"), Pair("EQUAL", "="), Pair("INTEGER", "2"))),
                row("\t\t\ta\n=\t \r\n2      \r\n", mutableListOf(Pair("IDENT", "a"), Pair("EQUAL", "="), Pair("INTEGER", "2")))
        ) { source, match ->
            LexerTestHelper.fullTokensForCode(source) shouldBe match.apply { add(Pair("EOF", "")) }
        }
    }
    "can tokenize calculation expression" {
        forall(
                row("a + 2 * 3.0", mutableListOf(Pair("IDENT", "a"), Pair("PLUS", "+"), Pair("INTEGER", "2"), Pair("STAR", "*"), Pair("FLOAT", "3.0")))
        ) { source, match ->
            LexerTestHelper.fullTokensForCode(source) shouldBe match.apply { add(Pair("EOF", "")) }
        }
    }
    "can tokenize function declaration" {
        forall(
                row("b = () { 42 }", mutableListOf(Pair("IDENT", "b"), Pair("EQUAL", "="), Pair("LEFT_PARENTHESIS", "("), Pair("RIGHT_PARENTHESIS", ")"), Pair("LEFT_CURLY_BRACKET", "{"), Pair("INTEGER", "42"), Pair("RIGHT_CURLY_BRACKET", "}")))
        ) { source, match ->
            LexerTestHelper.fullTokensForCode(source) shouldBe match.apply { add(Pair("EOF", "")) }
        }
    }
    "can tokenize prototype declaration" {
        forall(
                row("""
                    test = {
                        a = 42
                        getA = () { a }
                    }
                """.trimIndent(), mutableListOf(Pair("IDENT", "test"), Pair("EQUAL", "="), Pair("LEFT_CURLY_BRACKET", "{"), Pair("IDENT", "a"), Pair("EQUAL", "="), Pair("INTEGER", "42"), Pair("IDENT", "getA"), Pair("EQUAL", "="), Pair("LEFT_PARENTHESIS", "("), Pair("RIGHT_PARENTHESIS", ")"), Pair("LEFT_CURLY_BRACKET", "{"), Pair("IDENT", "a"), Pair("RIGHT_CURLY_BRACKET", "}"), Pair("RIGHT_CURLY_BRACKET", "}"))),
                row("""test={a=42getA=(){a}}""", mutableListOf(Pair("IDENT", "test"), Pair("EQUAL", "="), Pair("LEFT_CURLY_BRACKET", "{"), Pair("IDENT", "a"), Pair("EQUAL", "="), Pair("INTEGER", "42"), Pair("IDENT", "getA"), Pair("EQUAL", "="), Pair("LEFT_PARENTHESIS", "("), Pair("RIGHT_PARENTHESIS", ")"), Pair("LEFT_CURLY_BRACKET", "{"), Pair("IDENT", "a"), Pair("RIGHT_CURLY_BRACKET", "}"), Pair("RIGHT_CURLY_BRACKET", "}")))
        ) { source, match ->
            LexerTestHelper.fullTokensForCode(source) shouldBe match.apply { add(Pair("EOF", "")) }
        }
    }
})
