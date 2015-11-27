package ca.ece.ubc.cpen221.mp5;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import ca.ece.ubc.cpen221.mp5.query.QueryFactory;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

// TODO: This class represents the Restaurant Database.
// Define the internal representation and 
// state the rep invariant and the abstraction function.

public class RestaurantDB {

    private ArrayList<Object> restaurants;
    private ArrayList<Object> reviews;
    private ArrayList<Object> users;

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
        reviews = addFromFile(reviewsJSONfilename,FileKind.Review);
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

    public enum FileKind{
        Restaurant, Review, User;
    }
    private ArrayList<Object> addFromFile(String filename, FileKind fileKind) {

        JSONParser parser = new JSONParser();
        ArrayList<Object> returnList = new ArrayList<Object>();

        try {
            JSONArray a = (JSONArray) parser.parse(new FileReader("data/" + filename));
            // NOT SURE if file extension will be included??

            if (fileKind == FileKind.Restaurant) {

                for (Object o : a) {

                    JSONObject restaurant = (JSONObject) o;

                    Object newRestaurant = new Restaurant((String) restaurant.get("url"),
                            (String) restaurant.get("photo_url"), (double) restaurant.get("longitude"),
                            (double) restaurant.get("latitude"), (String) restaurant.get("city"),
                            (String) restaurant.get("full_address"), (String[]) restaurant.get("neighborhoods"),
                            (String) restaurant.get("state"), (String[]) restaurant.get("schools"),
                            (String) restaurant.get("name"), (String) restaurant.get("business_id"),
                            (boolean) restaurant.get("open"), (String[]) restaurant.get("categories"),
                            (float) restaurant.get("stars"), (int) restaurant.get("review_count"),
                            (int) restaurant.get("price"));
                    returnList.add(newRestaurant);

                }
                
            } else if (fileKind == FileKind.Review) {

                for (Object o : a) {

                    JSONObject review = (JSONObject) o;

                    Object newReview = new Review((String) review.get("business_id"), (String) review.get("user_id"),
                            (int[]) review.get("votes"), // PROBLEMS WILL ARISE
                                                         // HERE.
                            (String) review.get("review_id"), (String) review.get("text"), (float) review.get("stars"),
                            (String) review.get("date"));

                    returnList.add(newReview);

                }

            } else if (fileKind == FileKind.User) {

                for (Object o : a) {

                    JSONObject user = (JSONObject) o;

                    Object newUser = new User((String) user.get("url"), (int[]) user.get("votes"), // PROBLEMS
                                                                                                   // WILL
                                                                                                   // ARISE
                                                                                                   // HERE.
                            (int) user.get("review_count"), (String) user.get("user_id"), (String) user.get("name"),
                            (double) user.get("average_stars"));

                    returnList.add(newUser);

                }

            }

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException p) {
            p.printStackTrace();
        } catch (IOException s) {
            s.printStackTrace();
        }

        return returnList;

    }

}
