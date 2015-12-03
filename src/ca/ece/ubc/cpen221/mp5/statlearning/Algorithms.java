package ca.ece.ubc.cpen221.mp5.statlearning;

import java.util.Set;
import java.io.IOException;
import java.util.ArrayList;
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

        List<Set<Restaurant>> allClusters = new ArrayList<Set<Restaurant>>();

        List<Coordinate> kNodes = new ArrayList<Coordinate>();
        List<Restaurant> restaurants = new ArrayList<Restaurant>();

        boolean calibrating = true;

        double maxX = 0;
        double minX = 0;
        double maxY = 0;
        double minY = 0;

        for (Object restaurant : db.getRestaurantList()) { // add all
                                                           // restaurants to a
                                                           // list. Attain min
                                                           // and max
                                                           // latitudes/longitudes.

            if (restaurant instanceof Restaurant) {

                if (((Restaurant) restaurant).getLocation().xCoord > maxX)
                    maxX = ((Restaurant) restaurant).getLocation().xCoord;

                if (((Restaurant) restaurant).getLocation().xCoord < minX)
                    minX = ((Restaurant) restaurant).getLocation().xCoord;

                if (((Restaurant) restaurant).getLocation().yCoord > maxY)
                    maxY = ((Restaurant) restaurant).getLocation().yCoord;

                if (((Restaurant) restaurant).getLocation().yCoord < minY)
                    minY = ((Restaurant) restaurant).getLocation().yCoord;

                restaurants.add((Restaurant) restaurant); // internal list of
                                                          // restaurants;
            }

        }

        // initialize the k nodes at "random" positions within the max and
        // minimum coordinates
        for (int i = 0; i < k; i++) {
            kNodes.add(new Coordinate(Math.random() * (maxX - minX) + minX, Math.random() * (maxY - minY) + minY));
        }

        // assign all restaurants to a kNode
        for (Restaurant restaurant : restaurants) {

            allClusters.get(restaurant.getLocation().findClosestNeighbour(kNodes)).add(restaurant);

        }

        while (calibrating) {
            calibrating = false;
            // recompute the location of the nodes as the centroid.
            for (Coordinate node : kNodes) {

                int index = kNodes.indexOf(node);
                kNodes.remove(index);

                if (!node.equals(computeCentroidOfRestaurants(allClusters.get(index)))) {
                    calibrating = true;
                }

                kNodes.add(index, computeCentroidOfRestaurants(allClusters.get(index)));
            }

            if (!calibrating)
                break;

            // reassign all restaurants to the kNodes
            allClusters.clear();

            for (Restaurant restaurant : restaurants) {

                allClusters.get(restaurant.getLocation().findClosestNeighbour(kNodes)).add(restaurant);

            }

        }

        return allClusters;
    }

    /**
     * Converts a list of restaurant clusters to JSON format.
     * 
     * @param clusters
     *            a list of n sets, where each set represents a cluster of
     *            restaurants.
     * @return A string in JSON format of all the restaurants based on their
     *         clustering
     */
    public static String convertClustersToJSON(List<Set<Restaurant>> clusters) {
        String theString = new String();
        try {
            
            for (Set<Restaurant> set : clusters) {
                for (Restaurant restaurant : set) {

                    theString += restaurant.representationInJSON();

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
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
            RestaurantDB db) { // list of r_squared, a ,b in that order

        double sumOfInput = 0;
        double sumOfOutput = 0;

        ArrayList<Double> inputs = new ArrayList<Double>();
        ArrayList<Double> outputs = new ArrayList<Double>();
        int numDataPairs = 0;
        double meanInput;
        double meanOutput;

        for (Object review : db.getReviewList()) { // loop through all reviews
                                                   // to find the review from a
                                                   // particular user

            if (review instanceof Review) {

                if (((Review) review).getUserID().equals(u.getUserID())) { // this
                                                                           // review
                                                                           // was
                                                                           // written
                                                                           // by
                                                                           // user
                                                                           // U

                    // find the associated restaurant
                    for (Object restaurant : db.getRestaurantList()) {
                        if (restaurant instanceof Restaurant && ((Restaurant) restaurant).getBusinessID()
                                .equals(((Review) review).getBusinessID())) {

                            sumOfInput += featureFunction.f((Restaurant) restaurant, db);
                            sumOfOutput += ((Review) review).getStars();
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
     *            set of Restaurants
     * @return a Coordinate that corresponds to the centroid of the cluster of
     *         restaurants
     * 
     */
    private static Coordinate computeCentroidOfRestaurants(Set<Restaurant> coordinates) {

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