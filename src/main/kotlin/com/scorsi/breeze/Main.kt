package com.scorsi.breeze

import com.scorsi.breeze.parser.ParserFacade

fun main(args: Array<String>) {
    val parserResult = ParserFacade.parse("""
        [a = 1, b = 2, c = 3]
    """.trimIndent())
    println(parserResult)
}
