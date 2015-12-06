package ca.ece.ubc.cpen221.mp5.query;

import java.util.Set;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.RestaurantDB;

public interface Query {
    
    /**
     * db Database from which restaurants are found
     * Returns a set of restaurants matching the rating
     */
    public Set<Restaurant> result(RestaurantDB db);
    
    public String stringRepresentation();
    
}
