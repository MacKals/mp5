package ca.ece.ubc.cpen221.mp5.query;

import java.util.HashSet;
import java.util.Set;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.RestaurantDB;

public class NodeIn implements Query {

    private String locationName;

    public NodeIn(String locationName) {
        this.locationName = locationName;
    }
    
    @Override
    public Set<Restaurant> result(RestaurantDB db) {

        Set<Restaurant> result = new HashSet<>();

        for (Object object : db.getRestaurantList()) {

            if (!(object instanceof Restaurant))
                throw new RuntimeException();
            Restaurant restaurant = (Restaurant) object;

            if (restaurant.getLocationName().contains(locationName)) {
                result.add(restaurant.copy());
            }
        }

        return result;
    }

}
