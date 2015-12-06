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
   
        
    }
    
    @Test
    public void testCentroid(){
        //compute the centroid of 3 points
        
        ArrayList<Restaurant> coordList = new ArrayList<>();
        RestaurantDB db = new RestaurantDB("restaurants.json", "reviews.json", "users.json");
                
        for (int i = 0; i < 3; i++){
             coordList.add(db.getRestaurantList().get(i));
        }
        System.out.println(Algorithms.computeCentroidOfRestaurants(coordList).xCoord + " , " + Algorithms.computeCentroidOfRestaurants(coordList).yCoord);
        assert(Algorithms.computeCentroidOfRestaurants(coordList).equals(new Coordinate(37.86909917 , -122.2598820)));
    }
     
    @Test
    public void testGetPredictor(){
        
        RestaurantDB db = new RestaurantDB("restaurants.json", "reviews.json", "users.json");
        MP5Function price = new RestaurantPriceScale();
        MP5Function returnedFunction1 = Algorithms.getPredictor(db.getUserList().get(8), db, price);
        
        double prediction = returnedFunction1.f(db.getRestaurantList().get(12), db);
        
        System.out.println("I predict user 8 will give restaurant 0 a rating of " + prediction + " stars");
        
    }
    
  
    

}
