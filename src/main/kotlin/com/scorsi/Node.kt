package com.scorsi

sealed class Node {
    data class Expression constructor(val node: Node) : Node()
    data class MethodCall constructor(val name: kotlin.String, val arguments: MethodCallArguments) : Node()
    data class MethodCallArguments constructor(val arguments: List<Expression>) : Node()
    data class String constructor(val value: kotlin.String) : Node()
}