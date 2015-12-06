package ca.ece.ubc.cpen221.mp5;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

//TODO:
// Define the internal representation and 
// state the rep invariant and the abstraction function.

/**
 * 
 * This Restaurant-Database is a simple in-memory database with restaurants,
 * users and reviews. The information is at launch read from user-specified
 * file. Functionality for search, adding elements and special query information
 * requests are defined
 * 
 * The database is thread-safe, allowing for concurrent call requests.
 * 
 * 
 * The RI for the db:
 * <ul>
 * <li>no 2 restaurants are the same</li>
 * <li>there is at least one restaurant, user, and review</li>
 * </ul>
 * 
 * The AF for the db:
 * <ul>
 * <li>the db represents the data from the given dataset</li> //TODO: update
 * </ul>
 *
 * Thread safety strategy:
 * <ul>
 * <li>methods accessing a list is synchronized on that list</li>
 * <li>thus no two methods can access the same list at the same time</li>
 * <li>There is no direct interdependence between lists, so they can be changed
 * independently of one another</li>
 * </ul>
 *
 * @author MacLennan & Kals
 * 
 */

public class RestaurantDB {

    private ArrayList<Restaurant> restaurants = new ArrayList<>();
    private ArrayList<Review> reviews = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();

    private ArrayList<String> categories = new ArrayList<>();

    private boolean initComplete = false;

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

        for (Restaurant restaurant : restaurants) {

            for (String element : restaurant.getCategories()) {
                if (!categories.contains(element)) {
                    categories.add(element);
                }
            }
        }

        initComplete = true;
    }

    /**
     * Thread safety argument:
     * <ul>
     * <li>locks on restaurants</li>
     * </ul>
     * 
     * @return pointer to list of restaurants
     */
    public ArrayList<Restaurant> getRestaurantList() {
        synchronized (restaurants) {
            return restaurants;
        }
    }

    /**
     * Thread safety argument:
     * <ul>
     * <li>locks on reviews</li>
     * </ul>
     * 
     * @return pointer to list of reviews
     */
    public ArrayList<Review> getReviewList() {
        synchronized (reviews) {
            return reviews;
        }
    }

    /**
     * Thread safety argument:
     * <ul>
     * <li>locks on users</li>
     * </ul>
     * 
     * @return pointer to list of reviews
     */
    public ArrayList<User> getUserList() {
        synchronized (users) {
            return users;
        }
    }

    /**
     * Thread safety argument:
     * <ul>
     * <li>locks on categories</li>
     * </ul>
     * 
     * @return pointer to list of categories
     */
    public ArrayList<String> getCategoryMapping() {
        synchronized (categories) {
            return categories;
        }
    }

    public enum FileKind {
        Restaurant, Review, User;
    }

    /**
     * Adds all objects, be it restaurants, reviews, or users from a file to the
     * RestaurantDB Thread safety argument:
     * <ul>
     * <li>only called from constructor</li>
     * </ul>
     * 
     * @param filename
     *            the name of the JSON file, including the .json file extension.
     *            file must be within the "data" folder.
     * @param fileKind
     *            The type of file we wish to add to our database
     * @return true if the objects were successfully added from the file.
     */
    @SuppressWarnings("resource") // it is handled as pointer fileEntries is local to method
    private boolean addFromFile(String filename, FileKind fileKind) {

        try {
            Iterator<String> fileEntries = new BufferedReader(new FileReader(filename)).lines().iterator();

            while (fileEntries.hasNext()) {

                if (fileKind == FileKind.Restaurant) {
                    addRestaurant(fileEntries.next());

                } else if (fileKind == FileKind.Review) {
                    addReview(fileEntries.next());

                } else if (fileKind == FileKind.User) {
                    addUser(fileEntries.next());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * Maps categories to corresponding doubles for regression
     * @param inputCategories categories as strings
     * @return double representation on input-list
     */
    public ArrayList<Double> mapCategories(ArrayList<String> inputCategories) {
        ArrayList<Double> categoriesDoubles = new ArrayList<Double>();

        for (int i = 0; i < inputCategories.size(); i++) {
            categoriesDoubles.add(categories.indexOf(inputCategories.get(i)) + 0.0);
        }

        return categoriesDoubles;
    }

    /**
     * Adds a restaurant to the restaurantDB. 
     * Thread safety argument:
     * <ul>
     * <li>locks on restaurants when accessing it</li>
     * <li>locks on categories when accessing it</li>
     * </ul>
     * 
     * @param restaurantString
     *            a string in JSON format corresponding to a restaurant in the
     *            Yelp database.
     * @return true if restaurant add was successful, false otherwise.
     */
    public String addRestaurant(String restaurantString) {

        JSONParser parser = new JSONParser();

        try {

            JSONObject restaurant = (JSONObject) parser.parse(restaurantString);

            Restaurant newRestaurant = new Restaurant(restaurant);

            // accessing restaurants, lock on it:
            synchronized (restaurants) {

                // Check for valid restaurant
                if (newRestaurant.getBusinessID().isEmpty()) return ReturnMessages.malformedExpressionError;
                if (newRestaurant.getName().isEmpty()) return ReturnMessages.malformedExpressionError;
                
                // Check for duplication
                if (initComplete) {
                    for (Restaurant restaurantInstance : restaurants) {
                        if (restaurantInstance.getBusinessID() == newRestaurant.getBusinessID()) {
                            return ReturnMessages.alreadyExistsError;
                        }
                    }
                }

                restaurants.add(newRestaurant);
            }

            // accessing categories, lock on it:
            synchronized (categories) {
                
                //add to categories if not there from before
                for (String element : newRestaurant.getCategories()) {
                    if (!categories.contains(element)) {
                        categories.add(element);
                    }
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
            return ReturnMessages.malformedExpressionError;
        }

        return ReturnMessages.successful;
    }

    /**
     * Adds a user to the restaurantDB
     * Thread safety argument:
     * <ul>
     * <li>locks on users when accessing</li>
     * </ul>
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

            User newUser = new User(user);

            //accessing users, lock on it
            synchronized(users) {

                // Check for valid user
                if (newUser.getUserID().isEmpty()) return ReturnMessages.malformedExpressionError;
                
                // Check weather exists already
                if (initComplete) {
                    
                    for (User userInstance : users) {
                        if (userInstance.getUserID() == newUser.getUserID()) {
                            // TODO:Search for duplication based on user id only -
                            // more needed?
                            return ReturnMessages.alreadyExistsError;
                        }
                    }
                }
                
                users.add(newUser);
                
            }
           

        } catch (ParseException e) {
            e.printStackTrace();
            return ReturnMessages.malformedExpressionError;
        }

        return ReturnMessages.successful;
    }

    /**
     * Adds a review to the restaurantDB
     * Thread safety argument:
     * <ul>
     * <li>locks on reviews when accessing</li>
     * </ul>
     * @param reviewString
     *            a string in JSON format corresponding to a review in the Yelp
     *            database.
     * @return true if review add was successful, false otherwise.
     */
    public String addReview(String reviewString) {

        try {

            JSONParser parser = new JSONParser();
            JSONObject review = (JSONObject) parser.parse(reviewString);

            Review newReview = new Review((String) review.get("business_id"), (String) review.get("user_id"),
                    (Object) review.get("votes"), (String) review.get("review_id"), (String) review.get("text"),
                    UtilityMethods.safeLongToInt((long) review.get("stars")), (String) review.get("date"), review);

            synchronized(reviews) {
                

                // Check for valid review
                if (newReview.getBusinessID().isEmpty()) return ReturnMessages.malformedExpressionError;
                
                
                // Check for duplication
                if (initComplete) {
                    for (Review reviewInstance : reviews) {
                        if (reviewInstance.reviewID == newReview.reviewID) {
                            return ReturnMessages.alreadyExistsError;
                        }
                    }
                }

                reviews.add(newReview);
                
            }
            
        } catch (ParseException e) {
            e.printStackTrace();
            return ReturnMessages.malformedExpressionError;
        }

        return ReturnMessages.successful;
    }

}
