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

OR: 		'||';
AND:		'&&';

LP: 		'(';
RP: 		')';

QUOTE: 		'"';
DOTDOT: 	'..';

IN:			'in';
CATEGORY:	'category';
NAME:		'name';
RATING:		'rating';
PRICE: 		'price';

INT:		[1-5] ;
STRING: 	(('A'..'Z')|('a'..'z')) (('A'..'Z')|('a'..'z')| ' ')*  ;

WS :		( ' ' | '\t' | '\r' | '\n' ) -> skip ;


file:			 	orExpression EOF;

orExpression:  		andExpression (OR andExpression)*;

andExpression: 		atom (AND atom)*;

atom: 		location | category | name | rating | price | (LP orExpression RP);

location: 	IN 			LP string RP;
category: 	CATEGORY 	LP string RP;
name: 		NAME 		LP string RP;
rating: 	RATING		LP range RP;
price:      PRICE       LP range RP; 

range: 		INT DOTDOT INT;
string: 	QUOTE STRING QUOTE;