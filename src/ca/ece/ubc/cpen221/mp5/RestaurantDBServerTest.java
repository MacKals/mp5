package ca.ece.ubc.cpen221.mp5;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class RestaurantDBServerTest {

    RestaurantDBServer server;
    
    @Before
    public void lauchServer() {
        try {
            server = new RestaurantDBServer(4949, "restaurants.json", "reviews.json", "users.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testRandomReview() {
        
        String output = server.randomReview("La Val's Pizza");
        
        String test = output;
    }

    @Test
    public void testGetRestaurant() {
        
        
        
    }
    
    @Test
    public void testAddRestaurant() {
        
        
        
    }
    
    @Test
    public void testAddUser() {
        
        
    }
    
    @Test
    public void testAddReview() {
        
        
    }
    
    @Test
    public void testSearchQuery() {
        
        
    }
    
    
}
