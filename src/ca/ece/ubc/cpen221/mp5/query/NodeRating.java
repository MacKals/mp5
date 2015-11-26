package ca.ece.ubc.cpen221.mp5.query;

import java.util.HashSet;
import java.util.Set;

import ca.ece.ubc.cpen221.mp5.Restaurant;

public class NodeRating implements Query {

    private int lowerRating;
    private int higherRating;
    
    public NodeRating(int lower, int higher) {
        this.lowerRating = lower;
        this.higherRating = higher;
    }
    
    @Override
    public Set<Restaurant> result() {
        
        Set<Restaurant> result = new HashSet<>();
        
        for (int rating = lowerRating; rating < higherRating; rating++) {
            result.add(null); //TODO: add restaurants that match
        }
        
        return result;
    }

}
