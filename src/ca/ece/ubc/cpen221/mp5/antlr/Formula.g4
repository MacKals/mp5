grammar Formula;

// This puts "package formula;" at the top of the output Java files.
@header {
package formula;
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

/*
 * These are the lexical rules. They define the tokens used by the lexer.
 *   *** Antlr requires tokens to be CAPITALIZED, like START_ITALIC, END_ITALIC, and TEXT.
 */

START : '{';
FINISH : '}';

COMMA : ',';
COLON : ': ';

TITLE : ('a'..'z'|'_') + ;
STRING : ( 'a'..'z' | 'A'..'Z' | '0'..'9' | '/' | ':' | WHITESPACE ) + ;

TRUE : 'true';
FALSE : 'false';

NUMBER : '-'? ('0'..'9')+ ('.' ('0'..'9')+ )? ; 

WHITESPACE : [ \t \r \n ' ' ] + ;


/*
 * These are the parser rules. They define the structures used by the parser.
 *    *** Antlr requires grammar nonterminals to be lowercase, like html, normal, and italic.
 */
 
 file : text EOF ;
 text : (START restaurant FINISH) + ;
 restaurant : (property COMMA) + ;
 property : title COLON value ;
 title : '"' TITLE '"' ;
 value : ( '"' STRING '"' ) | TRUE | FALSE | NUMBER ;
 