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
		T__0=1, INT=2, IN=3, CATEGORY=4, NAME=5, RATING=6, PRICE=7, OR=8, AND=9, 
		START=10, END=11, STRING=12, LP=13, RP=14, QUOTE=15, WHITESPACE=16;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'"
	};
	public static final String[] ruleNames = {
		"T__0", "INT", "IN", "CATEGORY", "NAME", "RATING", "PRICE", "OR", "AND", 
		"START", "END", "STRING", "LP", "RP", "QUOTE", "WHITESPACE"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\22b\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2\3"+
		"\2\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6"+
		"\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3"+
		"\t\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\r\6\rR\n\r\r\r\16\rS\3\16\3\16\3\17"+
		"\3\17\3\20\3\20\3\21\6\21]\n\21\r\21\16\21^\3\21\3\21\2\2\22\3\3\5\4\7"+
		"\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22"+
		"\3\2\5\3\2\63\67\4\2>>@@\5\2\13\f\17\17\"\"c\2\3\3\2\2\2\2\5\3\2\2\2\2"+
		"\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2"+
		"\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2"+
		"\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\3#\3\2\2\2\5&\3\2\2\2\7(\3\2\2\2"+
		"\t+\3\2\2\2\13\64\3\2\2\2\r9\3\2\2\2\17@\3\2\2\2\21F\3\2\2\2\23I\3\2\2"+
		"\2\25L\3\2\2\2\27N\3\2\2\2\31Q\3\2\2\2\33U\3\2\2\2\35W\3\2\2\2\37Y\3\2"+
		"\2\2!\\\3\2\2\2#$\7\60\2\2$%\7\60\2\2%\4\3\2\2\2&\'\t\2\2\2\'\6\3\2\2"+
		"\2()\7k\2\2)*\7p\2\2*\b\3\2\2\2+,\7e\2\2,-\7c\2\2-.\7v\2\2./\7g\2\2/\60"+
		"\7i\2\2\60\61\7q\2\2\61\62\7t\2\2\62\63\7{\2\2\63\n\3\2\2\2\64\65\7p\2"+
		"\2\65\66\7c\2\2\66\67\7o\2\2\678\7g\2\28\f\3\2\2\29:\7t\2\2:;\7c\2\2;"+
		"<\7v\2\2<=\7k\2\2=>\7p\2\2>?\7i\2\2?\16\3\2\2\2@A\7r\2\2AB\7t\2\2BC\7"+
		"k\2\2CD\7e\2\2DE\7g\2\2E\20\3\2\2\2FG\7~\2\2GH\7~\2\2H\22\3\2\2\2IJ\7"+
		"(\2\2JK\7(\2\2K\24\3\2\2\2LM\t\2\2\2M\26\3\2\2\2NO\t\2\2\2O\30\3\2\2\2"+
		"PR\n\3\2\2QP\3\2\2\2RS\3\2\2\2SQ\3\2\2\2ST\3\2\2\2T\32\3\2\2\2UV\7*\2"+
		"\2V\34\3\2\2\2WX\7+\2\2X\36\3\2\2\2YZ\7$\2\2Z \3\2\2\2[]\t\4\2\2\\[\3"+
		"\2\2\2]^\3\2\2\2^\\\3\2\2\2^_\3\2\2\2_`\3\2\2\2`a\b\21\2\2a\"\3\2\2\2"+
		"\5\2S^\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}