package ca.ece.ubc.cpen221.mp5;

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
		
	}
	    
	/** 
	 * Takes a query  and searches the Restaurant Database for Restaurants that match the query.
	 * @param queryString A query can be based on a combination of restaurant names, 
	 * neighbourhood, categories, rating and price level
	 * @return a set of Restaurants that fulfill the query.
	 */
	public Set<Restaurant> query(String queryString) {
		// TODO: Implement this method
		// Write specs, etc.
		return null;
	}

}
