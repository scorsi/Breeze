package com.scorsi

import com.scorsi.parser.BreezeParserFacade

fun main(args: Array<String>) {
    val parserResult = BreezeParserFacade.parse("""
        (3 * 4.5) - test.toto;
    """.trimIndent())
    println(parserResult.errors)
    println(parserResult.root)
}