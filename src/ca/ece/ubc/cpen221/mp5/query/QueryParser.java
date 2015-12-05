// Generated from Query.g4 by ANTLR 4.4

package ca.ece.ubc.cpen221.mp5.query;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QueryParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		OR=1, AND=2, LP=3, RP=4, QUOTE=5, DOTDOT=6, IN=7, CATEGORY=8, NAME=9, 
		RATING=10, PRICE=11, INT=12, STRING=13, WHITESPACE=14;
	public static final String[] tokenNames = {
		"<INVALID>", "'||'", "'&&'", "'('", "')'", "'\"'", "'..'", "'in'", "'category'", 
		"'name'", "'rating'", "'price'", "INT", "STRING", "WHITESPACE"
	};
	public static final int
		RULE_file = 0, RULE_expression = 1, RULE_logicExpression = 2, RULE_andExpression = 3, 
		RULE_orExpression = 4, RULE_atom = 5, RULE_location = 6, RULE_category = 7, 
		RULE_name = 8, RULE_rating = 9, RULE_price = 10, RULE_range = 11, RULE_string = 12;
	public static final String[] ruleNames = {
		"file", "expression", "logicExpression", "andExpression", "orExpression", 
		"atom", "location", "category", "name", "rating", "price", "range", "string"
	};

	@Override
	public String getGrammarFileName() { return "Query.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	    // This method makes the lexer or parser stop running if it encounters
	    // invalid input and throw a RuntimeException.
	    public void reportErrorsAsExceptions() {
	        //removeErrorListeners();
	        
	        addErrorListener(new ExceptionThrowingErrorListener());
	    }
	    
	    private static class ExceptionThrowingErrorListener extends BaseErrorListener {
	        @Override
	        public void syntaxError(Recognizer<?, ?> recognizer,
	                Object offendingSymbol, int line, int charPositionInLine,
	                String msg, RecognitionException e) {
	            throw new RuntimeException(msg);
	        }
	    }

	public QueryParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class FileContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(QueryParser.EOF, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitFile(this);
		}
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_file);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26); expression();
			setState(27); match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public TerminalNode RP() { return getToken(QueryParser.RP, 0); }
		public TerminalNode LP() { return getToken(QueryParser.LP, 0); }
		public LogicExpressionContext logicExpression() {
			return getRuleContext(LogicExpressionContext.class,0);
		}
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_expression);
		try {
			setState(34);
			switch (_input.LA(1)) {
			case LP:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(29); match(LP);
				setState(30); logicExpression();
				setState(31); match(RP);
				}
				}
				break;
			case IN:
			case CATEGORY:
			case NAME:
			case RATING:
			case PRICE:
				enterOuterAlt(_localctx, 2);
				{
				setState(33); atom();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LogicExpressionContext extends ParserRuleContext {
		public AndExpressionContext andExpression() {
			return getRuleContext(AndExpressionContext.class,0);
		}
		public OrExpressionContext orExpression() {
			return getRuleContext(OrExpressionContext.class,0);
		}
		public LogicExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterLogicExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitLogicExpression(this);
		}
	}

	public final LogicExpressionContext logicExpression() throws RecognitionException {
		LogicExpressionContext _localctx = new LogicExpressionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_logicExpression);
		try {
			setState(38);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(36); andExpression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(37); orExpression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AndExpressionContext extends ParserRuleContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode AND() { return getToken(QueryParser.AND, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public AndExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_andExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterAndExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitAndExpression(this);
		}
	}

	public final AndExpressionContext andExpression() throws RecognitionException {
		AndExpressionContext _localctx = new AndExpressionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_andExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40); expression();
			setState(41); match(AND);
			setState(42); expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OrExpressionContext extends ParserRuleContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode OR() { return getToken(QueryParser.OR, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public OrExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterOrExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitOrExpression(this);
		}
	}

	public final OrExpressionContext orExpression() throws RecognitionException {
		OrExpressionContext _localctx = new OrExpressionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_orExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44); expression();
			setState(45); match(OR);
			setState(46); expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AtomContext extends ParserRuleContext {
		public LocationContext location() {
			return getRuleContext(LocationContext.class,0);
		}
		public PriceContext price() {
			return getRuleContext(PriceContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public RatingContext rating() {
			return getRuleContext(RatingContext.class,0);
		}
		public CategoryContext category() {
			return getRuleContext(CategoryContext.class,0);
		}
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitAtom(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_atom);
		try {
			setState(53);
			switch (_input.LA(1)) {
			case IN:
				enterOuterAlt(_localctx, 1);
				{
				setState(48); location();
				}
				break;
			case CATEGORY:
				enterOuterAlt(_localctx, 2);
				{
				setState(49); category();
				}
				break;
			case NAME:
				enterOuterAlt(_localctx, 3);
				{
				setState(50); name();
				}
				break;
			case RATING:
				enterOuterAlt(_localctx, 4);
				{
				setState(51); rating();
				}
				break;
			case PRICE:
				enterOuterAlt(_localctx, 5);
				{
				setState(52); price();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LocationContext extends ParserRuleContext {
		public TerminalNode RP() { return getToken(QueryParser.RP, 0); }
		public TerminalNode LP() { return getToken(QueryParser.LP, 0); }
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public TerminalNode IN() { return getToken(QueryParser.IN, 0); }
		public LocationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_location; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterLocation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitLocation(this);
		}
	}

	public final LocationContext location() throws RecognitionException {
		LocationContext _localctx = new LocationContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_location);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55); match(IN);
			setState(56); match(LP);
			setState(57); string();
			setState(58); match(RP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CategoryContext extends ParserRuleContext {
		public TerminalNode RP() { return getToken(QueryParser.RP, 0); }
		public TerminalNode CATEGORY() { return getToken(QueryParser.CATEGORY, 0); }
		public TerminalNode LP() { return getToken(QueryParser.LP, 0); }
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public CategoryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_category; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterCategory(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitCategory(this);
		}
	}

	public final CategoryContext category() throws RecognitionException {
		CategoryContext _localctx = new CategoryContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_category);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60); match(CATEGORY);
			setState(61); match(LP);
			setState(62); string();
			setState(63); match(RP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NameContext extends ParserRuleContext {
		public TerminalNode RP() { return getToken(QueryParser.RP, 0); }
		public TerminalNode NAME() { return getToken(QueryParser.NAME, 0); }
		public TerminalNode LP() { return getToken(QueryParser.LP, 0); }
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitName(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65); match(NAME);
			setState(66); match(LP);
			setState(67); string();
			setState(68); match(RP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RatingContext extends ParserRuleContext {
		public TerminalNode RP() { return getToken(QueryParser.RP, 0); }
		public TerminalNode LP() { return getToken(QueryParser.LP, 0); }
		public TerminalNode RATING() { return getToken(QueryParser.RATING, 0); }
		public RangeContext range() {
			return getRuleContext(RangeContext.class,0);
		}
		public RatingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rating; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterRating(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitRating(this);
		}
	}

	public final RatingContext rating() throws RecognitionException {
		RatingContext _localctx = new RatingContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_rating);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70); match(RATING);
			setState(71); match(LP);
			setState(72); range();
			setState(73); match(RP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PriceContext extends ParserRuleContext {
		public TerminalNode RP() { return getToken(QueryParser.RP, 0); }
		public TerminalNode PRICE() { return getToken(QueryParser.PRICE, 0); }
		public TerminalNode LP() { return getToken(QueryParser.LP, 0); }
		public RangeContext range() {
			return getRuleContext(RangeContext.class,0);
		}
		public PriceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_price; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterPrice(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitPrice(this);
		}
	}

	public final PriceContext price() throws RecognitionException {
		PriceContext _localctx = new PriceContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_price);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75); match(PRICE);
			setState(76); match(LP);
			setState(77); range();
			setState(78); match(RP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RangeContext extends ParserRuleContext {
		public TerminalNode INT(int i) {
			return getToken(QueryParser.INT, i);
		}
		public TerminalNode DOTDOT() { return getToken(QueryParser.DOTDOT, 0); }
		public List<TerminalNode> INT() { return getTokens(QueryParser.INT); }
		public RangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_range; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterRange(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitRange(this);
		}
	}

	public final RangeContext range() throws RecognitionException {
		RangeContext _localctx = new RangeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_range);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80); match(INT);
			setState(81); match(DOTDOT);
			setState(82); match(INT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(QueryParser.STRING, 0); }
		public List<TerminalNode> QUOTE() { return getTokens(QueryParser.QUOTE); }
		public TerminalNode QUOTE(int i) {
			return getToken(QueryParser.QUOTE, i);
		}
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitString(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_string);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84); match(QUOTE);
			setState(85); match(STRING);
			setState(86); match(QUOTE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\20[\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\5\3%\n\3\3\4"+
		"\3\4\5\4)\n\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\5\7"+
		"8\n\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13"+
		"\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16"+
		"\3\16\3\16\2\2\17\2\4\6\b\n\f\16\20\22\24\26\30\32\2\2S\2\34\3\2\2\2\4"+
		"$\3\2\2\2\6(\3\2\2\2\b*\3\2\2\2\n.\3\2\2\2\f\67\3\2\2\2\169\3\2\2\2\20"+
		">\3\2\2\2\22C\3\2\2\2\24H\3\2\2\2\26M\3\2\2\2\30R\3\2\2\2\32V\3\2\2\2"+
		"\34\35\5\4\3\2\35\36\7\2\2\3\36\3\3\2\2\2\37 \7\5\2\2 !\5\6\4\2!\"\7\6"+
		"\2\2\"%\3\2\2\2#%\5\f\7\2$\37\3\2\2\2$#\3\2\2\2%\5\3\2\2\2&)\5\b\5\2\'"+
		")\5\n\6\2(&\3\2\2\2(\'\3\2\2\2)\7\3\2\2\2*+\5\4\3\2+,\7\4\2\2,-\5\4\3"+
		"\2-\t\3\2\2\2./\5\4\3\2/\60\7\3\2\2\60\61\5\4\3\2\61\13\3\2\2\2\628\5"+
		"\16\b\2\638\5\20\t\2\648\5\22\n\2\658\5\24\13\2\668\5\26\f\2\67\62\3\2"+
		"\2\2\67\63\3\2\2\2\67\64\3\2\2\2\67\65\3\2\2\2\67\66\3\2\2\28\r\3\2\2"+
		"\29:\7\t\2\2:;\7\5\2\2;<\5\32\16\2<=\7\6\2\2=\17\3\2\2\2>?\7\n\2\2?@\7"+
		"\5\2\2@A\5\32\16\2AB\7\6\2\2B\21\3\2\2\2CD\7\13\2\2DE\7\5\2\2EF\5\32\16"+
		"\2FG\7\6\2\2G\23\3\2\2\2HI\7\f\2\2IJ\7\5\2\2JK\5\30\r\2KL\7\6\2\2L\25"+
		"\3\2\2\2MN\7\r\2\2NO\7\5\2\2OP\5\30\r\2PQ\7\6\2\2Q\27\3\2\2\2RS\7\16\2"+
		"\2ST\7\b\2\2TU\7\16\2\2U\31\3\2\2\2VW\7\7\2\2WX\7\17\2\2XY\7\7\2\2Y\33"+
		"\3\2\2\2\5$(\67";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}