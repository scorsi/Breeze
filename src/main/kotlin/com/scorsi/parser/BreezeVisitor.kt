package com.scorsi.parser

import com.scorsi.BreezeParser
import com.scorsi.BreezeParserBaseVisitor
import com.scorsi.ast.*
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.Token

fun Token.startPosition() = Position(line, charPositionInLine)
fun Token.endPosition() = Position(line, charPositionInLine + (if (type == Token.EOF) 0 else text.length))
fun ParserRuleContext.createSelection() = Selection(start.startPosition(), stop.startPosition())

class BreezeVisitor : BreezeParserBaseVisitor<Node?>() {
    override fun visitProgram(ctx: BreezeParser.ProgramContext?): Program? =
            Program(ctx!!.createSelection(), ctx.statement()!!.map { visit(it!!)!! as Expression })

    override fun visitExpressionStatement(ctx: BreezeParser.ExpressionStatementContext?): Node? =
            visit(ctx!!.expression()!!)!!

    override fun visitExpressionDeclaration(ctx: BreezeParser.ExpressionDeclarationContext?): Node? =
            visit(ctx!!.declaration()!!)!!

    override fun visitParenthesizedExpression(ctx: BreezeParser.ParenthesizedExpressionContext?): Node? =
            visit(ctx!!.expression()!!)!!

    override fun visitMemberIndexExpression(ctx: BreezeParser.MemberIndexExpressionContext?): MemberIndexExpression? =
            MemberIndexExpression(
                    ctx!!.createSelection(),
                    visit(ctx.expression(0)!!)!! as Expression,
                    visit(ctx.member!!)!! as Expression
            )

    override fun visitMembreDotExpression(ctx: BreezeParser.MembreDotExpressionContext?): MemberDotExpression? =
            MemberDotExpression(
                    ctx!!.createSelection(),
                    visit(ctx.expression(0)!!)!! as Expression,
                    visit(ctx.member!!)!! as Expression
            )

    override fun visitUnaryExpression(ctx: BreezeParser.UnaryExpressionContext?): Node? =
            UnaryExpression(
                    ctx!!.createSelection(),
                    visit(ctx.expression()!!)!! as Expression,
                    ctx.unary!!.text
            )

    override fun visitCalcExpression(ctx: BreezeParser.CalcExpressionContext?): Node? =
            CalcExpression(
                    ctx!!.createSelection(),
                    visit(ctx.left!!)!! as Expression,
                    visit(ctx.right!!)!! as Expression,
                    ctx.op!!.text!!
            )

    override fun visitCallExpression(ctx: BreezeParser.CallExpressionContext?): Node? =
            CallExpression(
                    ctx!!.createSelection(),
                    visit(ctx.expression()!!)!! as Expression,
                    (visit(ctx.arguments!!)!! as ListNode).list as List<Expression>
            )

    override fun visitExpressionSequence(ctx: BreezeParser.ExpressionSequenceContext?): ListNode? =
            ListNode(ctx!!.createSelection(), ctx.expression().map { visit(it!!)!! as Expression })

    override fun visitStringExpression(ctx: BreezeParser.StringExpressionContext?): StringVal? =
            StringVal(ctx!!.createSelection(), ctx.STRING()!!.text!!)

    override fun visitIntegerExpression(ctx: BreezeParser.IntegerExpressionContext?): IntegerVal? =
            IntegerVal(ctx!!.createSelection(), ctx.INTEGER()!!.text!!.toIntOrNull()!!)

    override fun visitFloatExpression(ctx: BreezeParser.FloatExpressionContext?): FloatVal? =
            FloatVal(ctx!!.createSelection(), ctx.FLOAT()!!.text!!.toFloatOrNull()!!)

    override fun visitIdentityExpression(ctx: BreezeParser.IdentityExpressionContext?): IdentifierVal? =
            IdentifierVal(ctx!!.createSelection(), ctx.IDENT()!!.text!!)

    override fun visitDeclaration(ctx: BreezeParser.DeclarationContext?): Node? =
            ctx!!.let {
                when {
                    ctx.variableDeclaration() != null -> visit(ctx.variableDeclaration())!!
                    ctx.functionDeclaration() != null -> visit(ctx.functionDeclaration())!!
                    else -> visit(ctx.prototypeDeclaration()!!)!!
                }
            }

    override fun visitVariableDeclaration(ctx: BreezeParser.VariableDeclarationContext?): VariableDeclaration? =
            VariableDeclaration(ctx!!.createSelection(), visit(ctx.left!!)!! as Expression, visit(ctx.value!!)!! as Expression)

    override fun visitFunctionDeclaration(ctx: BreezeParser.FunctionDeclarationContext?): Node? =
            FunctionDeclaration(ctx!!.createSelection(), visit(ctx.left!!)!! as Expression, (visit(ctx.args!!)!! as ListNode).list.map { (it as StringVal).value }, (visit(ctx.body!!)!! as ListNode).list)

    override fun visitFunctionDeclarationArguments(ctx: BreezeParser.FunctionDeclarationArgumentsContext?): Node? =
            ListNode(ctx!!.createSelection(), ctx.IDENT().map { StringVal(ctx.createSelection(), it.text!!) })

    override fun visitFunctionDeclarationBody(ctx: BreezeParser.FunctionDeclarationBodyContext?): Node? =
            ListNode(ctx!!.createSelection(), ctx.expression()!!.map { visit(it!!)!! })

    override fun visitPrototypeDeclaration(ctx: BreezeParser.PrototypeDeclarationContext?): Node? =
            PrototypeDeclaration(ctx!!.createSelection(), visit(ctx.left!!)!! as Expression, (visit(ctx.body!!)!! as ListNode).list.map { it as Declaration })

    override fun visitPrototypeDeclarationBody(ctx: BreezeParser.PrototypeDeclarationBodyContext?): Node? =
            ListNode(ctx!!.createSelection(), ctx.declaration()!!.map { visit(it!!)!! })
}
