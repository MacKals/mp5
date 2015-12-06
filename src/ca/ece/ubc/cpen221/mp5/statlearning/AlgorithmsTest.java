package ca.ece.ubc.cpen221.mp5.statlearning;

import static org.junit.Assert.*;

import java.io.FileWriter;
import java.io.IOException;
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
import ca.ece.ubc.cpen221.mp5.User;

public class AlgorithmsTest {

    @Test
    public void testKMeans() {

        RestaurantDB db = new RestaurantDB("restaurantsTest.json", "reviews.json", "users.json");
        boolean found = true;

        // eight restaurants:
        // Coordinates: (1,2), (5,5), (3,3), (5,10), (4,13), (3,15),
        // (15,1),(20,20)

        ArrayList<Restaurant> restaurants = db.getRestaurantList();

        List<Set<Restaurant>> kMeansSet = new ArrayList<Set<Restaurant>>();

        kMeansSet = Algorithms.kMeansClustering(3, db);

        // check that all restaurants are within the return list set

        for (Restaurant restaurant : restaurants) {

            for (Set<Restaurant> restaurantSet : kMeansSet) {
                found = false;

                if (restaurantSet.contains(restaurant)) {
                    found = true;
                    break;
                }

            }

            if (!found) {
                fail("at least one restaurant is not associated with any node");
            }

        }

        // check that there are no restaurants associated with two or more
        // nodes.
        int numRestStored = 0;
        List<Restaurant> restStored = new ArrayList<>();

        for (Set<Restaurant> restaurantSet : kMeansSet) {

            numRestStored += restaurantSet.size();

            for (Restaurant restaurant : restaurantSet) {
                restStored.add(restaurant);
            }

        }

        assertEquals(numRestStored, 8);
        assertEquals(restStored.size(), 8);
        assert(restStored.containsAll(restaurants));

        // check that every node has at least one restaurant associated with it
        for (Set<Restaurant> restaurantSet : kMeansSet) {
            if (restaurantSet.isEmpty()) {
                fail("It is not possible for a node to have no restaurants");
            }
        }
    }

    @Test
    public void testCentroid() {
        // compute the centroid of 3 points

        ArrayList<Restaurant> coordList = new ArrayList<>();
        RestaurantDB db = new RestaurantDB("restaurants.json", "reviews.json", "users.json");

        for (int i = 0; i < 3; i++) {
            coordList.add(db.getRestaurantList().get(i));
        }

        assert(Algorithms.computeCentroidOfRestaurants(coordList).equals(new Coordinate(37.86909917, -122.2598820)));

        // The centroid of one point is that point
        coordList.clear();

        coordList.add(db.getRestaurantList().get(0));

        assert(coordList.get(0).getLocation().equals(Algorithms.computeCentroidOfRestaurants(coordList)));

    }

    @Test
    public void testGetPredictor() throws Exception {

        RestaurantDB db = new RestaurantDB("restaurants.json", "reviews.json", "users.json");
        MP5Function price = new RestaurantPriceScale();
        MP5Function latitude = new RestaurantLatitude();
        MP5Function returnedFunction1 = Algorithms.getPredictor(db.getUserList().get(8), db, price);

        double prediction = returnedFunction1.f(db.getRestaurantList().get(12), db);

        // System.out.println("I predict the user will give this restaurant a
        // rating of " + prediction + " stars");

        // if the user has only reviewed one restaurant, should have a flat
        // regression function and return same rating every time.
        // lets take user 1. cat u. has only one review in the database, and she
        // gave it 5 stars.

        MP5Function returnedFunctionCat = Algorithms.getPredictor(db.getUserList().get(1), db, price);
        MP5Function returnedFunctionCat2 = Algorithms.getPredictor(db.getUserList().get(1), db, price);

        assert(returnedFunctionCat.f(db.getRestaurantList().get(10), db) == 5.0);
        assert(returnedFunctionCat.f(db.getRestaurantList().get(15), db) == 5.0);
    }

    @Test
    public void testGetBestPredictor() throws Exception {

        RestaurantDB db = new RestaurantDB("restaurants.json", "reviews.json", "users.json");

        MP5Function func1 = new RestaurantPriceScale();
        MP5Function func2 = new RestaurantLatitude();
        MP5Function func3 = new RestaurantLongitude();
        MP5Function func4 = new RestaurantCategory();
        MP5Function func5 = new RestaurantMeanRating();

        List<MP5Function> funcList = new ArrayList<>();
        funcList.add(func1);
        funcList.add(func2);
        funcList.add(func3);
        funcList.add(func4);
        funcList.add(func5);

        MP5Function bestFunc = Algorithms.getBestPredictor(db.getUserList().get(8), db, funcList);

        ArrayList<Double> coefficients1 = Algorithms.computeRegressionCoefficients(func1, db.getUserList().get(8), db);
        ArrayList<Double> coefficients2 = Algorithms.computeRegressionCoefficients(func2, db.getUserList().get(8), db);
        ArrayList<Double> coefficients3 = Algorithms.computeRegressionCoefficients(func3, db.getUserList().get(8), db);
        ArrayList<Double> coefficients4 = Algorithms.computeRegressionCoefficients(func4, db.getUserList().get(8), db);
        ArrayList<Double> coefficients5 = Algorithms.computeRegressionCoefficients(func5, db.getUserList().get(8), db);

        ArrayList<Double> bestFuncCoeff = Algorithms.computeRegressionCoefficients(bestFunc, db.getUserList().get(8),
                db);

        // no R2 should be greater than the bestFunc
        assert(bestFuncCoeff.get(0) >= coefficients1.get(0));
        assert(bestFuncCoeff.get(0) >= coefficients2.get(0));
        assert(bestFuncCoeff.get(0) >= coefficients3.get(0));
        assert(bestFuncCoeff.get(0) >= coefficients4.get(0));
        assert(bestFuncCoeff.get(0) >= coefficients5.get(0));

    }

    @Test
    public void writeTheVoronoiVisualization() throws IOException {

        RestaurantDB db = new RestaurantDB("restaurants.json", "reviews.json", "users.json");
        

        String testString = Algorithms.convertClustersToJSON(Algorithms.kMeansClustering(3, db));
        // System.out.println(testString);

        FileWriter out = new FileWriter("visualize/voronoi.json");
        out.write(testString);
        out.close();

    }

}
