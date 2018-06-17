package com.scorsi.ast

//
// Position and Selection helps retrieve errors in code.
//

data class Position(val line: Int, val column: Int)

data class Selection constructor(val start: Position, val end: Position)

fun createSelection(startLine: Int, startCol: Int, endLine: Int, endCol: Int) =
        Selection(Position(startLine, startCol), Position(endLine, endCol))

//
// Node is the root element for all the others
//

interface Node {
    val selection: Selection?
}

class ListNode(val list: List<Node>, override val selection: Selection? = null) : Node

//
// Second layer of nodes
//

data class Program(override val selection: Selection? = null, val statement: List<Node>) : Node

interface Declaration : Node
interface Expression : Node

//
// Values
//

data class StringVal(override val selection: Selection?, val value: String) : Expression
data class IntegerVal(override val selection: Selection?, val value: Int) : Expression
data class FloatVal(override val selection: Selection?, val value: Float) : Expression
data class IdentifierVal(override val selection: Selection?, val value: String) : Expression

//
// Declarations
//

data class VariableDeclaration(override val selection: Selection?, val identifier: String, val value: Expression) : Declaration
data class FunctionDeclaration(override val selection: Selection?, val identifier: String, val body: List<Node>) : Declaration
data class PrototypeDeclaration(override val selection: Selection?, val identifier: String, val body: List<Node>) : Declaration

//
// Expressions
//

data class CalcExpression(override val selection: Selection?, val left: Expression, val right: Expression, val op: String) : Expression
data class UnaryExpression(override val selection: Selection?, val expr: Expression, val unary: String) : Expression
data class MemberIndexExpression(override val selection: Selection?, val expr: Expression, val member: Expression) : Expression
data class MemberDotExpression(override val selection: Selection?, val expr: Expression, val member: Expression) : Expression
data class CallExpression(override val selection: Selection?, val expr: Expression, val args: List<Expression>) : Expression
