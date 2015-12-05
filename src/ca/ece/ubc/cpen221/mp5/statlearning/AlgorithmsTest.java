package ca.ece.ubc.cpen221.mp5.statlearning;

import static org.junit.Assert.*;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.RestaurantDB;

public class AlgorithmsTest {

    @Test
    public void testKMeans() {
        
        RestaurantDB db = new RestaurantDB("restaurantsTest.json", "reviews.json", "users.json");
        boolean found = true;
        
        // eight restaurants:
        // Coordinates: (1,2), (5,5), (3,3), (5,10), (4,13), (3,15), (15,1), (20,20)
                        
        ArrayList<Restaurant> restaurants = db.getRestaurantList();
        
        List<Set<Restaurant>> kMeansSet = new ArrayList<Set<Restaurant>>();
        
        kMeansSet = Algorithms.kMeansClustering(3, db);
        
        //check that all restaurants are within the return list set
        
        for (Restaurant restaurant : restaurants){
            
            for (Set<Restaurant> restaurantSet : kMeansSet){
                found = false;
                
                if (restaurantSet.contains(restaurant)){
                    found = true;
                    break;
                }
                
            }
            
            if (!found){
                fail("at least one restaurant is not associated with any node");
            }
              
        }
        
      //check that no restaurant appears twice in list set
   
        
    }
    
    @Test
    public void testCentroid(){
        
    }
     
    @Test
    public void testGetPredictor(){
        
    }
    
  
    

}
