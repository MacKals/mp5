package ca.ece.ubc.cpen221.mp5;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.sun.javafx.scene.paint.GradientUtils.Parser;

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
     * <li>One that contains data abonewUserut the restaurants;</li>
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

        addFromFile(restaurantJSONfilename, FileKind.Restaurant);
        addFromFile(reviewsJSONfilename, FileKind.Review);
        addFromFile(usersJSONfilename, FileKind.User);

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

    public enum FileKind {
        Restaurant, Review, User;
    }

    /**
     * Adds all objects, be it restaurants, reviews, or users from a file to the
     * RestaurantDB
     * 
     * @param filename
     *            the name of the JSON file, including the .json file extension.
     *            file must be within the "data" folder.
     * @param fileKind
     *            The type of file we wish to add to our database
     * @return true if the objects were successfully added from the file.
     */
    private boolean addFromFile(String filename, FileKind fileKind) {

        try {
            Iterator<String> fileEntries = new BufferedReader(new FileReader("data/" + filename)).lines().iterator();

            while (fileEntries.hasNext()) {

                if (fileKind == FileKind.Restaurant) {

                    System.out.println("reading a restaurant");

                    addRestaurant(fileEntries.next());

                } else if (fileKind == FileKind.Review) {

                    System.out.println("reading a review");

                    addReview(fileEntries.next());

                } else if (fileKind == FileKind.User) {

                    System.out.println("reading a user");

                    addUser(fileEntries.next());

                }

                System.out.println("Now we're here");
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;

    }

    /**
     * Converts a long to an int, provided the long can be converted without
     * losing any of its value
     * 
     * @param l
     *            long value we wish to convert to
     * @return the int corresponding to the long passed in
     * @throws IllegalArgumentException
     *             if the long cannot fit into the size of an int.
     */
    private static int safeLongToInt(long l) {
        if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
            throw new IllegalArgumentException(l + " cannot be cast to int without changing its value.");
        }
        return (int) l;
    }

    /**
     * Adds a restaurant to the restaurantDB
     * 
     * @param restaurantString
     *            a string in JSON format corresponding to a restaurant in the
     *            Yelp database.
     * @return true if restaurant add was successful, false otherwise.
     */

    public String addRestaurant(String restaurantString) {
        
        JSONParser parser = new JSONParser();
        Object obj = new Object();

        try {
            obj = parser.parse(restaurantString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JSONObject restaurant = (JSONObject) obj;

        Object newRestaurant = new Restaurant((String) restaurant.get("url"), (String) restaurant.get("photo_url"),
                (double) restaurant.get("longitude"), (double) restaurant.get("latitude"),
                (String) restaurant.get("city"), (String) restaurant.get("full_address"),
                (ArrayList<String>) restaurant.get("neighborhoods"), (String) restaurant.get("state"),
                (ArrayList<String>) restaurant.get("schools"), (String) restaurant.get("name"),
                (String) restaurant.get("business_id"), (boolean) restaurant.get("open"),
                (ArrayList<String>) restaurant.get("categories"), (double) restaurant.get("stars"),
                RestaurantDB.safeLongToInt((long) restaurant.get("review_count")),
                RestaurantDB.safeLongToInt((long) restaurant.get("price")));

        restaurants.add(newRestaurant);
        
        return ReturnMessages.successfull;
    }

    /**
     * Adds a user to the restaurantDB
     * 
     * @param usertString
     *            a string in JSON format corresponding to a user in the Yelp
     *            database.
     * @return true if user add was successful, false otherwise.
     */
    public String addUser(String userString) {
        
        JSONParser parser = new JSONParser();
        
        try {
           
            Object obj = new Object();            
            obj = parser.parse(userString);
            
            JSONObject user = (JSONObject) obj;
            
            User newUser = new User((String) user.get("url"), (Object) user.get("votes"),
                    RestaurantDB.safeLongToInt((long) user.get("review_count")), (String) user.get("user_id"),
                    (String) user.get("name"), (double) user.get("average_stars"));
            
            //Check weather exists already
            for (Object object : users) {
                
                User userInstance = (User) object;
                
                if (userInstance.getUserID() == newUser.getUserID()) { //TODO: Searching for dupliation based on user id only - more needed?
                    return ReturnMessages.allreadyExistsError;
                }
                    
            }
            
            users.add(newUser);
            
        } catch (ParseException e) {
            e.printStackTrace();
            return ReturnMessages.mallformedExpressionError;
        }
        
        return ReturnMessages.successfull;
    }

    /**
     * Adds a review to the restaurantDB
     * 
     * @param reviewString
     *            a string in JSON format corresponding to a review in the Yelp
     *            database.
     * @return true if review add was successful, false otherwise.
     */ 
    public String addReview(String reviewString) {
        
        JSONParser parser = new JSONParser();
        
        try {
            Object obj = new Object();
            obj = parser.parse(reviewString);
            
            JSONObject review = (JSONObject) obj;

            Review newReview = new Review((String) review.get("business_id"), (String) review.get("user_id"),
                    (Object) review.get("votes"), (String) review.get("review_id"), (String) review.get("text"),
                    RestaurantDB.safeLongToInt((long) review.get("stars")), (String) review.get("date"));

            //Check if already exists
            for (Object objectInstance : reviews) {
                
                Review reviewInstance = (Review) objectInstance;
                
                if (reviewInstance.reviewID == newReview.reviewID) {
                    return ReturnMessages.allreadyExistsError;
                }
            }
            
            reviews.add(newReview);
            
        } catch (ParseException e) {
            e.printStackTrace();
            return ReturnMessages.mallformedExpressionError;
        }
        
        return ReturnMessages.successfull;
    }
}
