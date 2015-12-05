// Generated from Query.g4 by ANTLR 4.4

package ca.ece.ubc.cpen221.mp5.query;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QueryParser}.
 */
public interface QueryListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QueryParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull QueryParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull QueryParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(@NotNull QueryParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(@NotNull QueryParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#rating}.
	 * @param ctx the parse tree
	 */
	void enterRating(@NotNull QueryParser.RatingContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#rating}.
	 * @param ctx the parse tree
	 */
	void exitRating(@NotNull QueryParser.RatingContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#range}.
	 * @param ctx the parse tree
	 */
	void enterRange(@NotNull QueryParser.RangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#range}.
	 * @param ctx the parse tree
	 */
	void exitRange(@NotNull QueryParser.RangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#orExpression}.
	 * @param ctx the parse tree
	 */
	void enterOrExpression(@NotNull QueryParser.OrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#orExpression}.
	 * @param ctx the parse tree
	 */
	void exitOrExpression(@NotNull QueryParser.OrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#andExpression}.
	 * @param ctx the parse tree
	 */
	void enterAndExpression(@NotNull QueryParser.AndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#andExpression}.
	 * @param ctx the parse tree
	 */
	void exitAndExpression(@NotNull QueryParser.AndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(@NotNull QueryParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(@NotNull QueryParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#price}.
	 * @param ctx the parse tree
	 */
	void enterPrice(@NotNull QueryParser.PriceContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#price}.
	 * @param ctx the parse tree
	 */
	void exitPrice(@NotNull QueryParser.PriceContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#logicExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicExpression(@NotNull QueryParser.LogicExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#logicExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicExpression(@NotNull QueryParser.LogicExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(@NotNull QueryParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(@NotNull QueryParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#location}.
	 * @param ctx the parse tree
	 */
	void enterLocation(@NotNull QueryParser.LocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#location}.
	 * @param ctx the parse tree
	 */
	void exitLocation(@NotNull QueryParser.LocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(@NotNull QueryParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(@NotNull QueryParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#category}.
	 * @param ctx the parse tree
	 */
	void enterCategory(@NotNull QueryParser.CategoryContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#category}.
	 * @param ctx the parse tree
	 */
	void exitCategory(@NotNull QueryParser.CategoryContext ctx);
}