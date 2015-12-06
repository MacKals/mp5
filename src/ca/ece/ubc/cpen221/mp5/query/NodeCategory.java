package ca.ece.ubc.cpen221.mp5.query;

import java.util.HashSet;
import java.util.Set;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.RestaurantDB;

public class NodeCategory implements Query {

    private String cathegory;

    public NodeCategory(String cathegory) {
        this.cathegory = cathegory;
    }

    @Override
    public Set<Restaurant> result(RestaurantDB db) {

        Set<Restaurant> result = new HashSet<>();

        for (Object object : db.getRestaurantList()) {

            if (!(object instanceof Restaurant)) throw new RuntimeException();
            Restaurant restaurant = (Restaurant) object;

            if (restaurant.getName().contains(cathegory)) { 
                result.add(restaurant);
            }
        }

        return result;
    }

    @Override
    public String stringRepresentation() {
        return "cathegory: " + cathegory;
    }
}
