package com.scorsi.breeze.parser

import com.scorsi.breeze.ast.Node

class ImplBreezeVisitor : BreezeBaseVisitor<Node>() {
    override fun visitExprList(ctx: BreezeParser.ExprListContext?): Node =
            Node.ListNode(ArrayList(ctx!!.expr()!!.map { visit(it)!! }))

    override fun visitDotAccessExpr(ctx: BreezeParser.DotAccessExprContext?): Node =
            Node.DotAccessExpr(visit(ctx!!.left!!)!!, visit(ctx.right!!)!!)

    override fun visitArrayAccessExpr(ctx: BreezeParser.ArrayAccessExprContext?): Node =
            Node.ArrayAccessExpr(visit(ctx!!.left!!)!!, visit(ctx.index!!)!!)

    override fun visitCallExpr(ctx: BreezeParser.CallExprContext?): Node =
            Node.CallExpr(visit(ctx!!.left!!)!!, ArrayList(ctx.expr()!!.drop(1).map { visit(it)!! }))

    override fun visitVarDeclaration(ctx: BreezeParser.VarDeclarationContext?): Node =
            Node.VarDeclaration(visit(ctx!!.left)!!, visit(ctx.right!!)!!)

    override fun visitMapDeclaration(ctx: BreezeParser.MapDeclarationContext?): Node =
            Node.MapDeclaration(ArrayList(mutableListOf<Pair<String, Node>>().apply {
                val nbChild = (ctx!!.childCount - 2).let {
                    when (it) {
                        0 -> 0
                        3 -> 1
                        else -> (it + 1) / 4
                    }
                }
                for (i in 0 until nbChild) {
                    add(Pair(ctx.IDENT(i)!!.text!!, visit(ctx.expr(i)!!)!!))
                }
            }))

    override fun visitArrayDeclaration(ctx: BreezeParser.ArrayDeclarationContext?): Node =
            Node.ArrayDeclaration(ArrayList(ctx!!.expr()!!.map { visit(it)!! }))

    override fun visitFunctionDeclaration(ctx: BreezeParser.FunctionDeclarationContext?): Node =
            Node.FunctionDeclaration(ArrayList(ctx!!.IDENT().map { it.text!! }), ArrayList(ctx.expr()!!.map { visit(it)!! }))

    override fun visitCalcExpr(ctx: BreezeParser.CalcExprContext?): Node =
            Node.CalcExpr(visit(ctx!!.left!!)!!, visit(ctx.right!!)!!, ctx.op!!.text!!)

    override fun visitCompExpr(ctx: BreezeParser.CompExprContext?): Node =
            Node.CompExpr(visit(ctx!!.left!!)!!, visit(ctx.right!!)!!, ctx.op!!.text!!)

    override fun visitReturnExpr(ctx: BreezeParser.ReturnExprContext?): Node =
            Node.ReturnExpr(visit(ctx!!.expr()!!)!!)

    override fun visitIdentExpr(ctx: BreezeParser.IdentExprContext?): Node =
            Node.IdentExpr(ctx!!.IDENT()!!.text!!)

    override fun visitNumberExpr(ctx: BreezeParser.NumberExprContext?): Node =
            Node.NumberExpr(ctx!!.NUMBER()!!.text!!)

    override fun visitStringExpr(ctx: BreezeParser.StringExprContext?): Node =
            Node.StringExpr(ctx!!.STRING()!!.text!!)

    override fun visitParenExpr(ctx: BreezeParser.ParenExprContext?): Node =
            visit(ctx!!.expr()!!)!!
}
