package ca.ece.ubc.cpen221.mp5.statlearning;

import java.util.ArrayList;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.RestaurantDB;

public class RestaurantCategory implements MP5Function {

    @Override
    public double f(Restaurant yelpRestaurant, RestaurantDB db) {

       ArrayList<Double> mappedCats = db.mapCategories(yelpRestaurant.getCategories());
       
       return mappedCats.get(0);
    }

}
