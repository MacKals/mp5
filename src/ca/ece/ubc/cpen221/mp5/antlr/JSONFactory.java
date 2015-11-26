package JSON;

import java.util.Stack;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class JSONFactory {

    
    test this is stupid! 
    
    /**
     * @param string
     *            must contain a well-formed JSON string of boolean literals
     *            and operators..
     * @return JSON corresponding to the string
     */
    public static JASON parse(String string) {
        // Create a stream of tokens using the lexer.
        CharStream stream = new ANTLRInputStream(string);
        JSONLexer lexer = new JSONLexer(stream);
        lexer.reportErrorsAsExceptions();
        TokenStream tokens = new CommonTokenStream(lexer);

        // Feed the tokens into the parser.
        JSONParser parser = new JSONParser(tokens);
        parser.reportErrorsAsExceptions();

        // Generate the parse tree using the starter rule.
        ParseTree tree = parser.JSON(); // "root" is the starter rule.

        // debugging option #1: print the tree to the console
        // System.err.println(tree.toStringTree(parser));

        // debugging option #2: show the tree in a window
        ((RuleContext) tree).inspect(parser);

        // debugging option #3: walk the tree with a listener
        // new ParseTreeWalker().walk(new JSONListener_PrintEverything(),
        // tree);

        // Finally, construct a Document value by walking over the parse tree.
        ParseTreeWalker walker = new ParseTreeWalker();
        JSONListener_JSONCreator listener = new JSONListener_JSONCreator();
        walker.walk(listener, tree);

        // return the Document value that the listener created
        return listener.getJSON();
    }

    public static String toString(JSON json) {

        return null;
    }

    private static class JSONListener_JSONCreator extends JSONBaseListener {
        private Stack<JSON> stack = new Stack<JSON>();

        @Override
        public void exitLiteral(JSONParser.LiteralContext ctx) {
            JSON literal = new BooleanLiteral(ctx.start.getType() == JSONLexer.TRUE);
            stack.push(literal);
        }
        
        @Override
        public void exitConjunction(JSONParser.ConjunctionContext ctx) {
            if (ctx.AND() != null) {
                // we matched the AND rule
                JSON right = stack.pop();
                JSON left = stack.pop();
                JSON and = new And(left, right);
                stack.push(and);
            }
        }
        
        @Override
        public void exitTerm(JSONParser.TermContext ctx) {
            if (ctx.NOT() != null) {
                JSON term = stack.pop();
                JSON not = new Not(term);
                stack.push(not);
            }
        }
                
        @Override
        public void exitJSON(JSONParser.JSONContext ctx) {
            // do nothing, because the top of the stack should have the node
            // already in it
            assert stack.size() == 1;
        }

        public JSON getJSON() {
            return stack.get(0);
        }
    }
    
    private static class JSONListener_PrintEverything extends JSONBaseListener {
        public void enterJSON(JSONParser.JSONContext ctx) {
            System.err.println("entering JSON " + ctx.getText());
        }

        public void exitJSON(JSONParser.JSONContext ctx) {
            System.err.println("exiting JSON " + ctx.getText());
        }

        public void enterConjunction(JSONParser.ConjunctionContext ctx) {
            System.err.println("entering conjunction " + ctx.getText());
        }

        public void exitConjunction(JSONParser.ConjunctionContext ctx) {
            System.err.println("exiting conjunction " + ctx.getText());
        }

        public void enterLiteral(JSONParser.LiteralContext ctx) {
            System.err.println("entering literal " + ctx.getText());
        }

        public void exitLiteral(JSONParser.LiteralContext ctx) {
            System.err.println("exiting literal " + ctx.getText());
        }
    }

}
