package ca.ece.ubc.cpen221.mp5.query;

import java.util.Stack;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class QueryFactory {

    /**
     * @param string
     *            must contain a well-formed query string of literals
     *            and operators..
     * @return Query corresponding to the string
     */
    public static Query parse(String string) {
        // Create a stream of tokens using the lexer.
        CharStream stream = new ANTLRInputStream(string);
        QueryLexer lexer = new QueryLexer(stream);
        lexer.reportErrorsAsExceptions();
        TokenStream tokens = new CommonTokenStream(lexer);

        // Feed the tokens into the parser.
        QueryParser parser = new QueryParser(tokens);
        parser.reportErrorsAsExceptions();

        // Generate the parse tree using the starter rule.
        ParseTree tree = parser.file(); // "root" is the starter rule.

        // debugging option #1: print the tree to the console
//        System.err.println(tree.toStringTree(parser));
        
        // debugging option #2: show the tree in a window
//        ((RuleContext) tree).inspect(parser);

        // debugging option #3: walk the tree with a listener
//         new ParseTreeWalker().walk(new QueryListener_PrintEverything(), tree);

        // Finally, construct a Document value by walking over the parse tree.
        ParseTreeWalker walker = new ParseTreeWalker();
        QueryListener_QueryCreator listener = new QueryListener_QueryCreator();
        walker.walk(listener, tree);

        // return the Document value that the listener created
        return listener.getQuery();
    }
    
    private static class QueryListener_QueryCreator extends QueryBaseListener {
        
        private Stack<Query> stack = new Stack<Query>();

        private int orCount = 0;
        
        @Override
        public void exitOrExpression(QueryParser.OrExpressionContext ctx) {
            
            if (ctx.OR(orCount++) != null) {
                Query right = stack.pop();
                Query left = stack.pop();
                Query or = new NodeOr(right, left);
                stack.push(or);
            }
        }
        
        private int andCount = 0;
        
        @Override 
        public void exitAndExpression(@NotNull QueryParser.AndExpressionContext ctx) { 
            
            if (ctx.AND(andCount++) != null) {
                Query right = stack.pop();
                Query left = stack.pop();
                Query and = new NodeAnd(right, left);
                stack.push(and);
            }
        }
        
        @Override
        public void exitLocation(@NotNull QueryParser.LocationContext ctx) {
            Query location = new NodeIn(ctx.string().STRING().getText());
            stack.push(location);
        }
      
        @Override 
        public void exitName(@NotNull QueryParser.NameContext ctx) { 
            Query name = new NodeName(ctx.string().STRING().getText());
            stack.push(name);
        }

        @Override 
        public void exitCategory(@NotNull QueryParser.CategoryContext ctx) { 
            Query category = new NodeCategory(ctx.string().STRING().getText());
            stack.push(category);
        }

        @Override 
        public void exitRating(@NotNull QueryParser.RatingContext ctx) {
            String start = ctx.range().INT(0).getText();
            String end = ctx.range().INT(1).getText();

            Query rating = new NodePrice(Integer.parseInt(start), Integer.parseInt(end));
            stack.push(rating);
        }

        @Override 
        public void exitPrice(@NotNull QueryParser.PriceContext ctx) {
            
            String start = ctx.range().INT(0).getText();
            String end = ctx.range().INT(1).getText();

            Query price = new NodePrice(Integer.parseInt(start), Integer.parseInt(end));
            stack.push(price);
        }

        public Query getQuery() {
            return stack.get(0);
        }
    }
}
