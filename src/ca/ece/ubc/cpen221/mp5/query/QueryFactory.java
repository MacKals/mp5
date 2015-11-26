package ca.ece.ubc.cpen221.mp5.query;

import java.util.Stack;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class QueryFactory {

    /**
     * @param string
     *            must contain a well-formed query string of boolean literals
     *            and operators..
     * @return Query corresponding to the string
     */
    public static Query parse(String string) {
        // Create a stream of tokens using the lexer.
        CharStream stream = new ANTLRInputStream(string);
        QueryFactory lexer = new QueryFactory(stream);
        lexer.reportErrorsAsExceptions();
        TokenStream tokens = new CommonTokenStream(lexer);

        // Feed the tokens into the parser.
        QueryFactory parser = new QueryFactory(tokens);
        parser.reportErrorsAsExceptions();

        // Generate the parse tree using the starter rule.
        ParseTree tree = parser.query(); // "root" is the starter rule.

        // debugging option #1: print the tree to the console
        // System.err.println(tree.toStringTree(parser));

        // debugging option #2: show the tree in a window
        ((RuleContext) tree).inspect(parser);

        // debugging option #3: walk the tree with a listener
        // new ParseTreeWalker().walk(new QueryListener_PrintEverything(),
        // tree);

        // Finally, construct a Document value by walking over the parse tree.
        ParseTreeWalker walker = new ParseTreeWalker();
        QueryListener_QueryCreator listener = new QueryListener_QueryCreator();
        walker.walk(listener, tree);

        // return the Document value that the listener created
        return listener.getQuery();
    }

    public static String toString(Query query) {

        return null;
    }

    private static class QueryListener_QueryCreator extends QueryBaseListener {
        private Stack<Query> stack = new Stack<Query>();

        @Override
        public void exitLiteral(QueryParser.LiteralContext ctx) {
            Query literal = new BooleanLiteral(ctx.start.getType() == QueryLexer.TRUE);
            stack.push(literal);
        }

        @Override
        public void exitConjunction(QueryParser.ConjunctionContext ctx) {
            if (ctx.AND() != null) {
                // we matched the AND rule
                Query right = stack.pop();
                Query left = stack.pop();
                Query and = new And(left, right);
                stack.push(and);
            }
        }
        
        @Override
        public void exitTerm(QueryParser.TermContext ctx) {
            if (ctx.NOT() != null) {
                Query term = stack.pop();
                Query not = new Not(term);
                stack.push(not);
            }
        }
        
        @Override
        public void exitQuery(QueryParser.QueryContext ctx) {
            // do nothing, because the top of the stack should have the node
            // already in it
            assert stack.size() == 1;
        }

        public Query getQuery() {
            return stack.get(0);
        }
    }

    private static class QueryListener_PrintEverything extends QueryBaseListener {
        public void enterQuery(QueryParser.QueryContext ctx) {
            System.err.println("entering query " + ctx.getText());
        }

        public void exitQuery(QueryParser.QueryContext ctx) {
            System.err.println("exiting query " + ctx.getText());
        }

        public void enterConjunction(QueryParser.ConjunctionContext ctx) {
            System.err.println("entering conjunction " + ctx.getText());
        }

        public void exitConjunction(QueryParser.ConjunctionContext ctx) {
            System.err.println("exiting conjunction " + ctx.getText());
        }

        public void enterLiteral(QueryParser.LiteralContext ctx) {
            System.err.println("entering literal " + ctx.getText());
        }

        public void exitLiteral(QueryParser.LiteralContext ctx) {
            System.err.println("exiting literal " + ctx.getText());
        }
    }
}
