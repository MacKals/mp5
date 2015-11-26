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
		T__7=1, T__6=2, T__5=3, T__4=4, T__3=5, T__2=6, T__1=7, T__0=8, RANGE=9, 
		STRING=10, OR=11, AND=12, WHITESPACE=13;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'"
	};
	public static final String[] ruleNames = {
		"T__7", "T__6", "T__5", "T__4", "T__3", "T__2", "T__1", "T__0", "RANGE", 
		"STRING", "OR", "AND", "WHITESPACE"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\17Y\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3"+
		"\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\6\13I"+
		"\n\13\r\13\16\13J\3\f\3\f\3\f\3\r\3\r\3\r\3\16\6\16T\n\16\r\16\16\16U"+
		"\3\16\3\16\2\2\17\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\3\2\5\3\2\63\67\4\2>>@@\5\2\13\f\17\17\"\"Z\2\3\3\2\2\2\2\5"+
		"\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2"+
		"\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33"+
		"\3\2\2\2\3\35\3\2\2\2\5$\3\2\2\2\7)\3\2\2\2\t+\3\2\2\2\13\61\3\2\2\2\r"+
		"\64\3\2\2\2\17=\3\2\2\2\21?\3\2\2\2\23A\3\2\2\2\25H\3\2\2\2\27L\3\2\2"+
		"\2\31O\3\2\2\2\33S\3\2\2\2\35\36\7t\2\2\36\37\7c\2\2\37 \7v\2\2 !\7k\2"+
		"\2!\"\7p\2\2\"#\7i\2\2#\4\3\2\2\2$%\7p\2\2%&\7c\2\2&\'\7o\2\2\'(\7g\2"+
		"\2(\6\3\2\2\2)*\7$\2\2*\b\3\2\2\2+,\7r\2\2,-\7t\2\2-.\7k\2\2./\7e\2\2"+
		"/\60\7g\2\2\60\n\3\2\2\2\61\62\7k\2\2\62\63\7p\2\2\63\f\3\2\2\2\64\65"+
		"\7e\2\2\65\66\7c\2\2\66\67\7v\2\2\678\7g\2\289\7i\2\29:\7q\2\2:;\7t\2"+
		"\2;<\7{\2\2<\16\3\2\2\2=>\7*\2\2>\20\3\2\2\2?@\7+\2\2@\22\3\2\2\2AB\t"+
		"\2\2\2BC\7\60\2\2CD\7\60\2\2DE\3\2\2\2EF\t\2\2\2F\24\3\2\2\2GI\n\3\2\2"+
		"HG\3\2\2\2IJ\3\2\2\2JH\3\2\2\2JK\3\2\2\2K\26\3\2\2\2LM\7~\2\2MN\7~\2\2"+
		"N\30\3\2\2\2OP\7(\2\2PQ\7(\2\2Q\32\3\2\2\2RT\t\4\2\2SR\3\2\2\2TU\3\2\2"+
		"\2US\3\2\2\2UV\3\2\2\2VW\3\2\2\2WX\b\16\2\2X\34\3\2\2\2\5\2JU\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}