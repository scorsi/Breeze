package com.scorsi

import com.scorsi.parser.BreezeParserFacade

fun main(args: Array<String>) {
    val parserResult = BreezeParserFacade.parse("""
        test;
        test;
    """.trimIndent())
    println(parserResult.errors)
    println(parserResult.root)
}