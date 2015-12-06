package ca.ece.ubc.cpen221.mp5.query;

import java.util.HashSet;
import java.util.Set;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.RestaurantDB;

public class NodeName implements Query {

    private String name;

    public NodeName(String name) {
        this.name = name;
    }

    @Override
    public Set<Restaurant> result(RestaurantDB db) {

        Set<Restaurant> result = new HashSet<>();

        for (Object object : db.getRestaurantList()) {

            if (!(object instanceof Restaurant))
                throw new RuntimeException();
            Restaurant restaurant = (Restaurant) object;

            if (restaurant.getName().contains(name)) {
                result.add(restaurant);
            }
        }

        return result;
    }

    @Override
    public String stringRepresentation() {
        return "name: " + name;
    }
}
