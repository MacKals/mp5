package ca.ece.ubc.cpen221.mp5.statlearning;

import java.util.Set;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import ca.ece.ubc.cpen221.mp5.*;

public class Algorithms {

    /**
     * Performs a k-means clustering for the restaurants within the database,
     * based on the restaurants' locations.
     * 
     * @param k
     *            the number of clusters desired
     * @param db
     *            the RestaurantDB containing the restaurants we want to cluster
     * @return
     */
    public static List<Set<Restaurant>> kMeansClustering(int k, RestaurantDB db) {

        List<ArrayList<Restaurant>> allClusters = new ArrayList<ArrayList<Restaurant>>();

        List<Coordinate> kNodes = new ArrayList<Coordinate>();
        List<Restaurant> restaurants = new ArrayList<Restaurant>();

        boolean calibrating = true;
        boolean again = true;
        int counter = 1;

        double maxX = 0;
        double minX = 0;
        double maxY = 0;
        double minY = 0;

        for (Restaurant restaurant : db.getRestaurantList()) { // add all
            // restaurants to a
            // list. Attain min
            // and max
            // latitudes/longitudes.

            if (db.getRestaurantList().indexOf(restaurant) == 0) { // set the
                                                                   // initial
                                                                   // values.
                maxX = restaurant.getLocation().xCoord;
                minX = maxX;
                maxY = restaurant.getLocation().yCoord;
                minY = maxY;

            }

            if (restaurant.getLocation().xCoord > maxX)
                maxX = (restaurant).getLocation().xCoord;

            if (restaurant.getLocation().xCoord < minX)
                minX = (restaurant).getLocation().xCoord;

            if (restaurant.getLocation().yCoord > maxY)
                maxY = (restaurant).getLocation().yCoord;

            if (restaurant.getLocation().yCoord < minY)
                minY = (restaurant.getLocation().yCoord);

            restaurants.add((Restaurant) restaurant); // internal list of
                                                      // restaurants;

        }

        while (again) {
            // initialize the k nodes at "random" positions within the max and
            // minimum coordinates . This algorithm makes sure each node begins
            // with at least one restaurant.

            allClusters.clear();
            kNodes.clear();

            for (int i = 0; i < k; i++) {
                kNodes.add(new Coordinate(Math.random() * (maxX - minX) + minX, Math.random() * (maxY - minY) + minY));
            }

            for (int i = 0; i < k; i++) {
                allClusters.add(new ArrayList<Restaurant>());
            }

            // assign all restaurants to a kNode
            for (Restaurant restaurant : restaurants) {

                allClusters.get(restaurant.getLocation().findClosestNeighbour(kNodes)).add(restaurant);

            }

            // we need to make sure that all nodes have an associated
            // restaurant.
            again = false;

            for (int i = 0; i < k; i++) {
                if (allClusters.get(i).isEmpty()) {
                    again = true;
                    break;
                }
            }
        }
        for (int i = 0; i < k; i++) {
            System.out.println(kNodes.get(i).xCoord + " , " + kNodes.get(i).yCoord);
        }

        // enter the calibration stage.

        while (calibrating) {

            calibrating = false;

            for (int i = 0; i < k; i++) {// if all the nodes are the centroids
                                         // of their respective clusters.

                if (!allClusters.get(i).isEmpty()
                        && !kNodes.get(i).equals(computeCentroidOfRestaurants(allClusters.get(i)))) {
                    calibrating = true;
                    break;
                }
            }

            if (!calibrating)
                break;

            System.out.println("Calibrating... take " + counter);

            // recompute the location of the nodes as the centroid.

            for (int i = 0; i < k; i++) {

                if (!allClusters.get(i).isEmpty()) {

                    Coordinate newCentroid = computeCentroidOfRestaurants(allClusters.get(i));

                    kNodes.remove(i);

                    kNodes.add(i, newCentroid);

                } else {
                    System.out.println("node " + i + " lost its neighbours!");
                }

                System.out.println(kNodes.get(i).xCoord + " , " + kNodes.get(i).yCoord);

            }

            // reassign all restaurants to the kNodes
            allClusters.clear();

            for (int i = 0; i < k; i++) {
                allClusters.add(new ArrayList<Restaurant>());
            }

            for (Restaurant restaurant : restaurants) {

                allClusters.get(restaurant.getLocation().findClosestNeighbour(kNodes)).add(restaurant);

            }

            counter++;

        }

        List<Set<Restaurant>> returnList = new ArrayList<Set<Restaurant>>();

        for (int i = 0; i < k; i++) {

            returnList.add(new HashSet<Restaurant>());

            for (Restaurant restaurant : allClusters.get(i)) {
                returnList.get(i).add(restaurant);
            }
        }

        return returnList;
    }

    /**
     * Converts a list of restaurant clusters to JSON format, in the specific
     * format in voronoi.json
     * 
     * @param clusters
     *            a list of n sets, where each set represents a cluster of
     *            restaurants.
     * @return A string in JSON format of all the restaurants based on their
     *         clustering
     */
    public static String convertClustersToJSON(List<Set<Restaurant>> clusters) {

        String theString = new String();
        theString += "[";

        for (int i = 0; i < clusters.size(); i++) {
            int counter = 0;

            
            for (Restaurant restaurant : clusters.get(i)){
                
                if (counter != 0){
                    theString += ", ";

                }

                JSONObject obj = new JSONObject();


                
                obj.put("x",  restaurant.getLocation().xCoord);
                obj.put("y",  restaurant.getLocation().yCoord);
                obj.put("name",  restaurant.getName());
                obj.put("cluster",  i);
                obj.put("weight",  1.0 );
                

                theString += obj.toString();
                counter++;
            }

        }

        theString += "]";

        return theString;

    }

    /**
     * Based on a particular feature function, returns a function that predicts
     * what rating a user will give a restaurant based on this feature function.
     * These feature function include:
     * 
     * restaurant price scale, restaurant mean rating, restaurant location:
     * latitude, restaurant location: longitude, restaurant category
     * 
     * @param u
     *            The user to be analyzed to predict its ratings.
     * @param db
     *            The RestaurantDB containing the restaurants to analyze
     * @param featureFunction
     *            a function that returns a property of a particular restaurant.
     * @return
     */
    public static MP5Function getPredictor(User u, RestaurantDB db, MP5Function featureFunction) {

        ArrayList<Double> regCoefficients = computeRegressionCoefficients(featureFunction, u, db);

        RegressionFunction returnFunction = new RegressionFunction(regCoefficients.get(1), regCoefficients.get(2),
                featureFunction);

        return returnFunction;
    }

    /**
     * From a list of feature functions, returns the function that is the best
     * predictor of the user. This method returns the feature function resulting
     * in the highest R^2 value from a linear regression.
     * 
     * @param u
     *            the User to predict ratings of
     * @param db
     *            the RestaurantDB
     * @param featureFunctionList
     *            a list of MP5Functions to analyze
     * @return the feature function that best predicts the user's ratings
     */
    public static MP5Function getBestPredictor(User u, RestaurantDB db, List<MP5Function> featureFunctionList) {

        int indexOfGreatestR2 = 0;
        double currentGreatestR2 = 0;
        double r2InQuestion;

        for (MP5Function featureFunction : featureFunctionList) {

            r2InQuestion = computeRegressionCoefficients(featureFunction, u, db).get(0);

            if (r2InQuestion > currentGreatestR2) { // extract the r-squared
                                                    // from the returned list
                currentGreatestR2 = r2InQuestion;
                indexOfGreatestR2 = featureFunctionList.indexOf(featureFunction);
            }
        }

        return featureFunctionList.get(indexOfGreatestR2);

    }

    /**
     * computes the regression coefficients, R^2, a, b, and returns a list of
     * these values in this order.
     * 
     * @param featureFunction
     * @param u
     *            the User
     * @param db
     *            the RestaurantDB
     * @return an ArrayList containing 3 doubles, in the order R^2, a, b.
     */
    private static ArrayList<Double> computeRegressionCoefficients(MP5Function featureFunction, User u,
            RestaurantDB db) {

        double sumOfInput = 0;
        double sumOfOutput = 0;

        ArrayList<Double> inputs = new ArrayList<Double>();
        ArrayList<Double> outputs = new ArrayList<Double>();
        int numDataPairs = 0;
        double meanInput;
        double meanOutput;

        for (Review review : db.getReviewList()) { // loop through all reviews
                                                   // to find the review from a
                                                   // particular user

            if (review instanceof Review) {

                if (review.getUserID().equals(u.getUserID())) { // this
                                                                // review
                                                                // was
                                                                // written
                                                                // by
                                                                // user
                                                                // U

                    // find the associated restaurant
                    for (Restaurant restaurant : db.getRestaurantList()) {
                        
                        if (restaurant.getBusinessID().equals(review.getBusinessID())) {

                            sumOfInput += featureFunction.f(restaurant, db);
                            sumOfOutput += review.getStars();
                            numDataPairs++;
                            break;

                        }
                    }
                }
            }
        }

        meanInput = sumOfInput / numDataPairs;
        meanOutput = sumOfOutput / numDataPairs;

        double sxx = 0;
        double syy = 0;
        double sxy = 0;

        for (int i = 0; i < numDataPairs; i++) {
            sxx += (inputs.get(i) - meanInput) * (inputs.get(i) - meanInput);
            syy += (outputs.get(i) - meanOutput) * (outputs.get(i) - meanOutput);
            sxy += (inputs.get(i) - meanInput) * (outputs.get(i) - meanOutput);
        }

        double b = sxy / sxx;

        ArrayList<Double> returnList = new ArrayList<Double>();
        returnList.add((sxy * sxy) / (sxx * syy));
        returnList.add(meanOutput - b * meanInput);
        returnList.add(b);

        return returnList;

    }

    /**
     * From a set of restaurants, computes the coordinate that is the centroid
     * of all the restaurants in the set.
     * 
     * @param coordinates
     *            set of Restaurants. Must contain at least one coordinate.
     * @return a Coordinate that corresponds to the centroid of the cluster of
     *         restaurants
     * 
     */
    private static Coordinate computeCentroidOfRestaurants(List<Restaurant> coordinates) {

        double newX = 0;
        double newY = 0;

        for (Restaurant coordinate : coordinates) {
            newX += coordinate.getLocation().xCoord;
            newY += coordinate.getLocation().yCoord;
        }

        newX = newX / (coordinates.size());
        newY = newY / (coordinates.size());

        return new Coordinate(newX, newY);
    }
}