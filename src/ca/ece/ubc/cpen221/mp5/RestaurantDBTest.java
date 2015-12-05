package ca.ece.ubc.cpen221.mp5;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.statlearning.Algorithms;
import ca.ece.ubc.cpen221.mp5.statlearning.Coordinate;

public class RestaurantDBTest {

    @Test
    public void test() throws IOException {
        
        RestaurantDB db = new RestaurantDB("restaurants.json", "reviews.json", "users.json");
        db.getRestaurantList();
        System.out.print(db.toString());
        
        String testString = Algorithms.convertClustersToJSON(Algorithms.kMeansClustering(3,db));
        System.out.println(testString);
        
        FileWriter out = new FileWriter("visualize/voronoi.json");
        out.write(testString);
        out.close();
        
        
    }
    
    
}
