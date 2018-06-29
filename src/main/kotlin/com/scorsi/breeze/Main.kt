package com.scorsi.breeze

import com.scorsi.breeze.parser.BreezeParserFacade

fun main(args: Array<String>) {
    val parserResult = BreezeParserFacade.parse("""
        [a = 1, b = 2, c = 3]
    """.trimIndent())
    println(parserResult)
}
