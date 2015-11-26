grammar Query;

// This puts "package formula;" at the top of the output Java files.
@header {
package query;
}

// This adds code to the generated lexer and parser. Do not change these lines.
@members {
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
}

query: andExpression (OR andExpression)* ;
andExpression: atom (AND atom)* ;

atom: 		in | category | name | rating | price;

in: 		'in' parenExpression;
category: 	'category' parenExpression;
name: 		'name' parenExpression;
rating: 	'rating' parenExpression;
price: 		'price' parenExpression; 

parenExpression: '(' (RANGE | ('"' STRING '"') ) ')';

RANGE: 		[1-5] '..' [1-5];
STRING: 	~[<>]+ ;

OR: 	'||';
AND:	'&&';

WHITESPACE: [ \t\r\n]+ -> skip ;

