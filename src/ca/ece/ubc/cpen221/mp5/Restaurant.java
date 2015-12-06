package ca.ece.ubc.cpen221.mp5;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import ca.ece.ubc.cpen221.mp5.statlearning.Coordinate;

// TODO: Use this class to represent a restaurant.
// State the rep invariant and abs

/**
 * This class represents a restaurant
 * 
 * Rep invariant: 
 * <ul> 
 * <li> //TODO: implement </li>
 * </ul>
 * 
 * @author MacLennan & Kals
 *
 */

public class Restaurant {
    
    private JSONObject restaurant;
    
    /**
     * Constructs a new restaurant object with the data provided by Yelp.
     * @param restaurant
     */
    public Restaurant ( JSONObject restaurant){
        this.restaurant = restaurant;
    }
    
    public Coordinate getLocation() {
        
        double longitude = (double) restaurant.get("longitude");
        double latitude = (double) restaurant.get("latitude");

        return  new Coordinate(latitude, longitude);
    }
    
    public int getPrice() {
        return UtilityMethods.safeLongToInt((long) restaurant.get("price"));
    }
    
    public String getName() {
        return (String) restaurant.get("name");
    }
    
    public String getLocationName() {
        
        @SuppressWarnings("unchecked")
        String neighbourhoods = ((ArrayList<String>) restaurant.get("neighborhoods")).toString();
        String city = (String) restaurant.get("city");
        String fullAddress = (String) restaurant.get("full_address");
        String state = (String) restaurant.get("state");
        
        return city + fullAddress + neighbourhoods + state; // returns strings matching names describing location, for NodeIn // check this one out.
    }

    public double getStars() {
        return (double) restaurant.get("stars");
    }
    
    public String getBusinessID(){
        return (String) restaurant.get("business_id");
    }
    
    @SuppressWarnings("unchecked")
    public ArrayList<String> getCategories(){
        return (ArrayList<String>) restaurant.get("categories");
    }
    
    public String representationInJSON() throws IOException {
        return restaurant.toJSONString();
    }
    
    @Override
    public boolean equals(Object object) {
        
        if (object instanceof Restaurant) {
            if ( this.getBusinessID() == ((Restaurant) object).getBusinessID() ) {
                return true;
            }
        }
        
        return false;
    }
    
    @Override 
    public int hashCode() {
        return this.getBusinessID().hashCode();
    }
}