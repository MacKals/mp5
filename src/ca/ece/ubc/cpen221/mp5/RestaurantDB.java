package ca.ece.ubc.cpen221.mp5;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import ca.ece.ubc.cpen221.mp5.query.QueryFactory;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Stream;

// TODO: This class represents the Restaurant Database.
// Define the internal representation and 
// state the rep invariant and the abstraction function.

public class RestaurantDB {

    private ArrayList<Object> restaurants = new ArrayList<Object>();
    private ArrayList<Object> reviews = new ArrayList<Object>();
    private ArrayList<Object> users = new ArrayList<Object>();

    /**
     * Create a database from the Yelp dataset given the names of three files:
     * <ul>
     * <li>One that contains data about the restaurants;</li>
     * <li>One that contains reviews of the restaurants;</li>
     * <li>One that contains information about the users that submitted reviews.
     * </li>
     * </ul>
     * The files contain data in JSON format.
     * 
     * @param restaurantJSONfilename
     *            the filename for the restaurant data
     * @param reviewsJSONfilename
     *            the filename for the reviews
     * @param usersJSONfilename
     *            the filename for the users
     */
    public RestaurantDB(String restaurantJSONfilename, String reviewsJSONfilename, String usersJSONfilename) {

        restaurants = addFromFile(restaurantJSONfilename, FileKind.Restaurant);
        reviews = addFromFile(reviewsJSONfilename, FileKind.Review);
        users = addFromFile(usersJSONfilename, FileKind.User);

    }

    public ArrayList<Object> getRestaurantList() { // Perhaps make a copy?
        return this.restaurants;
    }

    public ArrayList<Object> getReviewList() {// Perhaps make a copy?
        return this.reviews;
    }

    public ArrayList<Object> getUserList() {// Perhaps make a copy?
        return this.users;
    }

    /**
     * // Write specs, etc.
     * 
     * @param queryString
     * @return
     */
    public Set<Restaurant> query(String queryString) {
        return QueryFactory.parse(queryString).result(this);
    }

    public enum FileKind {
        Restaurant, Review, User;
    }

    private ArrayList<Object> addFromFile(String filename, FileKind fileKind) {

        ArrayList<Object> returnList = new ArrayList<Object>();
        JSONParser parser = new JSONParser();
        Object obj = new Object();

        try {
            Iterator<String> fileEntries = new BufferedReader(new FileReader("data/" + filename)).lines().iterator();

            while (fileEntries.hasNext()) {
                
                obj = parser.parse(fileEntries.next());
                
                if (fileKind == FileKind.Restaurant) {

                    System.out.println("reading a restaurant");

                    JSONObject restaurant = (JSONObject) obj;

                    Object newRestaurant = new Restaurant((String) restaurant.get("url"),
                            (String) restaurant.get("photo_url"), (double) restaurant.get("longitude"),
                            (double) restaurant.get("latitude"), (String) restaurant.get("city"),
                            (String) restaurant.get("full_address"), (ArrayList<String>) restaurant.get("neighborhoods"),
                            (String) restaurant.get("state"), (ArrayList<String>) restaurant.get("schools"),
                            (String) restaurant.get("name"), (String) restaurant.get("business_id"),
                            (boolean) restaurant.get("open"), (ArrayList<String>) restaurant.get("categories"),
                            (double) restaurant.get("stars"),
                            RestaurantDB.safeLongToInt((long) restaurant.get("review_count")),
                            RestaurantDB.safeLongToInt((long) restaurant.get("price")));
                    returnList.add(newRestaurant);
                    restaurants.add(newRestaurant);

                } else if (fileKind == FileKind.Review) {

                    System.out.println("reading a review");
                    JSONObject review = (JSONObject) obj;
                    Object votes = review.get("votes");

                    Object newReview = new Review((String) review.get("business_id"), (String) review.get("user_id"),
                            (Object) review.get("votes"), (String) review.get("review_id"), (String) review.get("text"),
                            RestaurantDB.safeLongToInt((long) review.get("stars")), (String) review.get("date"));

                    returnList.add(newReview);
                    reviews.add(newReview);

                } else if (fileKind == FileKind.User) {

                    System.out.println("reading a user");
                    JSONObject user = (JSONObject) obj;

                    Object newUser = new User((String) user.get("url"), (Object) user.get("votes"),
                            RestaurantDB.safeLongToInt((long) user.get("review_count")), (String) user.get("user_id"),
                            (String) user.get("name"), (double) user.get("average_stars"));

                    returnList.add(newUser);
                    users.add(newUser);

                }

                System.out.println("Now we're here");
                System.out.println();
            }

            

        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnList;

    }

    private static int safeLongToInt(long l) {
        if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
            throw new IllegalArgumentException(l + " cannot be cast to int without changing its value.");
        }
        return (int) l;
    }

}
