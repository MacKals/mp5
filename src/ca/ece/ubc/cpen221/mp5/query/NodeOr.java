package ca.ece.ubc.cpen221.mp5.query;

import java.util.Set;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.RestaurantDB;

public class NodeOr implements Query {

    private Query firstNode;
    private Query secondNode;
    
    public NodeOr(Query first, Query second) {
        firstNode = first;
        secondNode = second;
    }
    
    @Override
    public Set<Restaurant> result(RestaurantDB db) {
        return Sets.union(firstNode.result(db), secondNode.result(db));
    }

    @Override
    public String stringRepresentation() {
        return "(" + firstNode.stringRepresentation() + " || " + secondNode.stringRepresentation() + ")";
    }
}
