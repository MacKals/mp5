package ca.ece.ubc.cpen221.mp5;

public class ReturnMessages {
    
    /**
     * For all these requests, a suitable JSON formatted string should be returned if the request is illegal/invalid/incorrect. 
     * For the methods that update the database, also generate a suitable response.
     */
    
    static final String successful = "true";
    
    static final String emptyQueryError = "Error: empty query";

    static final String alreadyExistsError = "Error: allready exists";
    static final String malformedExpressionError = "Error: mallformed expresion";
    
    static final String notFoundError = "Error: not found";
    static final String noMatches = "Error: no matches";
}
