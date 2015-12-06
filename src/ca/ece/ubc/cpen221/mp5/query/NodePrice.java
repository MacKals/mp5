package ca.ece.ubc.cpen221.mp5.query;

import java.util.HashSet;
import java.util.Set;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.RestaurantDB;

public class NodePrice implements Query {
   
    private int lowerPrice;
    private int higherPrice;
    
    public NodePrice(int lower, int higher) {
        this.lowerPrice = lower;
        this.higherPrice = higher;
    }
   
    @Override
    public Set<Restaurant> result(RestaurantDB db) {
        
        Set<Restaurant> result = new HashSet<>();
            
            for (Object object : db.getRestaurantList()) {
                
                if (!(object instanceof Restaurant)) throw new RuntimeException();
                Restaurant restaurant = (Restaurant) object;
                
                if (lowerPrice <= restaurant.getPrice() && restaurant.getPrice() <= higherPrice) { //TODO: check if it is inclusinve
                    result.add(restaurant.copy());
                }
            }
        
        return result;
    }

    @Override
    public String stringRepresentation() {
        return "price: " + lowerPrice + "..." + higherPrice;
    }

}
