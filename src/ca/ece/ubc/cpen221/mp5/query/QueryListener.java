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
	 * Enter a parse tree produced by {@link QueryParser#in}.
	 * @param ctx the parse tree
	 */
	void enterIn(@NotNull QueryParser.InContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#in}.
	 * @param ctx the parse tree
	 */
	void exitIn(@NotNull QueryParser.InContext ctx);
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
	 * Enter a parse tree produced by {@link QueryParser#query}.
	 * @param ctx the parse tree
	 */
	void enterQuery(@NotNull QueryParser.QueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#query}.
	 * @param ctx the parse tree
	 */
	void exitQuery(@NotNull QueryParser.QueryContext ctx);
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
	 * Enter a parse tree produced by {@link QueryParser#parenExpression}.
	 * @param ctx the parse tree
	 */
	void enterParenExpression(@NotNull QueryParser.ParenExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#parenExpression}.
	 * @param ctx the parse tree
	 */
	void exitParenExpression(@NotNull QueryParser.ParenExpressionContext ctx);
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