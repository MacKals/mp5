package ca.ece.ubc.cpen221.mp5;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.statlearning.Algorithms;

public class RestaurantDBTest {

    @Test
    public void test() {
        RestaurantDB db = new RestaurantDB("restaurants.json", "reviews.json", "users.json");
        db.getRestaurantList();
        System.out.print(db.toString());
        
        Algorithms.kMeansClustering(10,db);
    }
}
