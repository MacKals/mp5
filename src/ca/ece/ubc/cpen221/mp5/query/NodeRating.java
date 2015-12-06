package ca.ece.ubc.cpen221.mp5.query;

import java.util.HashSet;
import java.util.Set;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.RestaurantDB;

public class NodeRating implements Query {

    private int lowerRating;
    private int higherRating;

    public NodeRating(int lower, int higher) {
        this.lowerRating = lower;
        this.higherRating = higher;
    }

    @Override
    public Set<Restaurant> result(RestaurantDB db) {

        Set<Restaurant> result = new HashSet<>();

        for (Object object : db.getRestaurantList()) {

            if (!(object instanceof Restaurant))
                throw new RuntimeException();
            Restaurant restaurant = (Restaurant) object;

            if (lowerRating <= restaurant.getStars() && restaurant.getStars() <= higherRating) {
                result.add(restaurant.copy());
            }
        }

        return result;
    }

    @Override
    public String stringRepresentation() {
        return "rating: " + lowerRating + "..." + higherRating;
    }

}
