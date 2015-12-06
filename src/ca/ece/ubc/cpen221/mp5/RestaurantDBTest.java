package ca.ece.ubc.cpen221.mp5;

import static org.junit.Assert.*;

//import static org.junit.Assert.*;

import java.io.FileWriter;
import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.statlearning.Algorithms;

public class RestaurantDBTest {

    @Test
    public void DuplicateTest() throws IOException {
        
        RestaurantDB db = new RestaurantDB("data/restaurants.json", "data/reviews.json", "data/users.json");

        List<Restaurant> restaurants = db.getRestaurantList();
        List<User> users = db.getUserList();
        List<Review> reviews = db.getReviewList();
        
        for (int i = 0; i < restaurants.size(); i++){
            for (int j = 0; j < i; j++){
                if(restaurants.get(i).equals(restaurants.get(j))) fail("duplicate!");
            }
            for (int l = i+1; l < restaurants.size(); l++){
                if(restaurants.get(i).equals(restaurants.get(l))) fail("duplicate!");
            }
        }

        for (int i = 0; i < users.size(); i++){
            for (int j = 0; j < i; j++){
                if(users.get(i).equals(users.get(j))) fail("duplicate!");
            }
            for (int l = i+1; l < users.size(); l++){
                if(users.get(i).equals(users.get(l))) fail("duplicate!");
            }
        }
        
        for (int i = 0; i < reviews.size(); i++){
            for (int j = 0; j < i; j++){
                if(reviews.get(i).equals(reviews.get(j))) fail("duplicate!");
            }
            for (int l = i+1; l < reviews.size(); l++){
                if(reviews.get(i).equals(reviews.get(l))) fail("duplicate!");
            }
        }
        
       
    }
    
    

}
