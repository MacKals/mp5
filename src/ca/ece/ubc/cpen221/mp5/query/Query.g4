grammar Query;

// This puts "package formula;" at the top of the output Java files.
@header {
package ca.ece.ubc.cpen221.mp5.query;
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

orExpression: 	andExpression (OR andExpression)* ;
andExpression: 	atom (AND atom)* ;

atom: 		location | location | name | rating | price;

location: 	'in' 		LP STRING RP;
category: 	'category' 	LP STRING RP;
name: 		'name' 		LP STRING RP;
rating: 	'rating' 	LP range RP;
price: 		'price' 	LP range RP; 

range: 		START '..' END;

START:		[1-5] ;
END:		[1-5] ;
STRING: 	~[<>]+ ;

OR: 	'||';
AND:	'&&';

LP: 	'(';
RP: 	')';

WHITESPACE: [ \t\r\n]+ -> skip ;

