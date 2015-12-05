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
        
        System.out.println(server.randomReview("La Val's Pizza"));
      
    }

    @Test
    public void testGetRestaurant() {
        
        System.out.println(server.getRestaurant("4D7IdtyRjH8qxcsHaz1-GA"));
        
    }
    
//    @Test
    public void testAddRestaurant() {
        
//        System.out.println(server.addRestaurant("La Val's Pizza"));

        
    }
    
//    @Test
    public void testAddUser() {
        
        
    }
    
//    @Test
    public void testAddReview() {
        
//        price:      PRICE       LP INT RP; 

    }
    
    @Test
    public void testSearchQuery() {
        System.out.println(server.query("1"));
        System.out.println(server.query("name(\"Italian\")"));
        System.out.println(server.query("in(test)"));
        
//"in("Telegraph Ave") && (category("Chinese") || category("Italian")) && price(1..2)
        
    }
}
