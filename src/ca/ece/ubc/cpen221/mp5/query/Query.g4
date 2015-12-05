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

file: expression EOF;

expression: ( LP logicExpression RP ) | ( atom );

logicExpression: andExpression | orExpression | expression;

andExpression: expression AND expression;
orExpression: expression OR expression;

atom: 		location | category | name | rating | price;

location: 	IN 			LP string RP;
category: 	CATEGORY 	LP string RP;
name: 		NAME 		LP string RP;
rating: 	RATING		LP range RP;
price: 		INT; 

INT: 		[1-5];

IN:			'in';
CATEGORY:	'category';
NAME:		'name';
RATING:		'rating';
PRICE: 		'price';

range: 		START '..' END ;
string: 	QUOTE STRING QUOTE;

OR: 		'||';
AND:		'&&';

START:		[1-5] ;
END:		[1-5] ;
STRING: 	~[<>]+ ;

LP: 		'(';
RP: 		')';

QUOTE: 		'"';

WHITESPACE: [ \t\r\n]+ -> skip ;

