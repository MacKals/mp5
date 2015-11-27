// Generated from Query.g4 by ANTLR 4.4

package ca.ece.ubc.cpen221.mp5.query;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QueryLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__5=1, T__4=2, T__3=3, T__2=4, T__1=5, T__0=6, START=7, END=8, STRING=9, 
		OR=10, AND=11, LP=12, RP=13, WHITESPACE=14;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'"
	};
	public static final String[] ruleNames = {
		"T__5", "T__4", "T__3", "T__2", "T__1", "T__0", "START", "END", "STRING", 
		"OR", "AND", "LP", "RP", "WHITESPACE"
	};


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


	public QueryLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Query.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\20Z\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\n\6\nF\n\n\r\n\16\n"+
		"G\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\6\17U\n\17\r\17\16"+
		"\17V\3\17\3\17\2\2\20\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27"+
		"\r\31\16\33\17\35\20\3\2\5\3\2\63\67\4\2>>@@\5\2\13\f\17\17\"\"[\2\3\3"+
		"\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2"+
		"\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3"+
		"\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\3\37\3\2\2\2\5&\3\2\2\2\7+\3\2\2\2\t"+
		"\61\3\2\2\2\13\64\3\2\2\2\r=\3\2\2\2\17@\3\2\2\2\21B\3\2\2\2\23E\3\2\2"+
		"\2\25I\3\2\2\2\27L\3\2\2\2\31O\3\2\2\2\33Q\3\2\2\2\35T\3\2\2\2\37 \7t"+
		"\2\2 !\7c\2\2!\"\7v\2\2\"#\7k\2\2#$\7p\2\2$%\7i\2\2%\4\3\2\2\2&\'\7p\2"+
		"\2\'(\7c\2\2()\7o\2\2)*\7g\2\2*\6\3\2\2\2+,\7r\2\2,-\7t\2\2-.\7k\2\2."+
		"/\7e\2\2/\60\7g\2\2\60\b\3\2\2\2\61\62\7k\2\2\62\63\7p\2\2\63\n\3\2\2"+
		"\2\64\65\7e\2\2\65\66\7c\2\2\66\67\7v\2\2\678\7g\2\289\7i\2\29:\7q\2\2"+
		":;\7t\2\2;<\7{\2\2<\f\3\2\2\2=>\7\60\2\2>?\7\60\2\2?\16\3\2\2\2@A\t\2"+
		"\2\2A\20\3\2\2\2BC\t\2\2\2C\22\3\2\2\2DF\n\3\2\2ED\3\2\2\2FG\3\2\2\2G"+
		"E\3\2\2\2GH\3\2\2\2H\24\3\2\2\2IJ\7~\2\2JK\7~\2\2K\26\3\2\2\2LM\7(\2\2"+
		"MN\7(\2\2N\30\3\2\2\2OP\7*\2\2P\32\3\2\2\2QR\7+\2\2R\34\3\2\2\2SU\t\4"+
		"\2\2TS\3\2\2\2UV\3\2\2\2VT\3\2\2\2VW\3\2\2\2WX\3\2\2\2XY\b\17\2\2Y\36"+
		"\3\2\2\2\5\2GV\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}