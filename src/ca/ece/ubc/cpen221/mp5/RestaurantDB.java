package ca.ece.ubc.cpen221.mp5;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import java.util.Set;

// TODO: This class represents the Restaurant Database.
// Define the internal representation and 
// state the rep invariant and the abstraction function.

public class RestaurantDB {

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
        // TODO: Implement this method
    }

    public Set<Restaurant> query(String queryString) {
        // TODO: Implement this method
        // Write specs, etc.
        return null;
    }

    private void parser() {

        JSONParser parser = new JSONParser();
        
        try {

            String s = "[0,{\"1\":{\"2\":{\"3\":{\"4\":[5,{\"6\":7}]}}}}]";
            Object obj = parser.parse(s);
            JSONArray array = (JSONArray) obj;
            System.out.println("======the 2nd element of array======");
            System.out.println(array.get(1));
            System.out.println();

            JSONObject obj2 = (JSONObject) array.get(1);
            System.out.println("======field \"1\"==========");
            System.out.println(obj2.get("1"));

            s = "{}";
            obj = parser.parse(s);
            System.out.println(obj);

            s = "[5,]";
            obj = parser.parse(s);
            System.out.println(obj);

            s = "[5,,2]";
            obj = parser.parse(s);
            System.out.println(obj);
            
        } catch (ParseException e) {

        }

    }

}
