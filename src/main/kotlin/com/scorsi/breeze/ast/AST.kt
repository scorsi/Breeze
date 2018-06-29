package com.scorsi.breeze.ast

sealed class Node {
    data class ListNode(val list: ArrayList<Node>) : Node()
    data class DotAccessExpr(val left: Node, val right: Node) : Node()
    data class ArrayAccessExpr(val left: Node, val index: Node) : Node()
    data class CallExpr(val left: Node, val arguments: ArrayList<Node>) : Node()
    data class VarDeclaration(val left: Node, val right: Node) : Node()
    data class MapDeclaration(val elements: ArrayList<Pair<String, Node>>) : Node()
    data class ArrayDeclaration(val elements: ArrayList<Node>) : Node()
    data class FunctionDeclaration(val arguments: ArrayList<String>, val body: ArrayList<Node>) : Node()
    data class CalcExpr(val left: Node, val right: Node, val op: String) : Node()
    data class CompExpr(val left: Node, val right: Node, val op: String) : Node()
    data class ReturnExpr(val expr: Node) : Node()
    data class NumberExpr(val value: String) : Node()
    data class StringExpr(val value: String) : Node()
    data class IdentExpr(val value: String) : Node()
}
