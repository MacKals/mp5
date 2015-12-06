package ca.ece.ubc.cpen221.mp5;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import ca.ece.ubc.cpen221.mp5.statlearning.Coordinate;

// TODO: Use this class to represent a restaurant.
// State the rep invariant and abs

public class Restaurant {

    private String url;
    private String photoURL;

    private String city;
    private String fullAddress;
    private ArrayList<String> neighbourhoods;
    private String state;
    private ArrayList<String> schools;

    private String name;
    private String businessID;
    private boolean open;

    private ArrayList<String> categories;
    //private String type; // all are "business"??

    private double stars;
    private int reviewCount;
    private int price;
    
    private Coordinate coordinate;
    
    private JSONObject restaurantObject;

    /**
     * Constructs a new restaurant object with the data provided by Yelp.
     * 
     * @param url
     * @param photoURL
     * @param longitude
     * @param latitude
     * @param city
     * @param fullAddress
     * @param neighbourhoods
     * @param state
     * @param schools
     * @param name
     * @param businessID
     * @param open
     * @param categories
     * @param stars
     * @param reviewCount
     * @param price
     * @param restaurantObject
     */
    public Restaurant ( String url,
                        String photoURL,
                        double longitude,
                        double latitude,
                        String city,
                        String fullAddress,
                        ArrayList<String> neighbourhoods,
                        String state,
                        ArrayList<String> schools,

                        String name,
                        String businessID,
                        boolean open,

                        ArrayList<String> categories,

                        double stars,
                        int reviewCount,
                        int price,
                        
                        JSONObject restaurantObject){
        
        this.city = city;
        this.fullAddress = fullAddress;
        this.neighbourhoods = neighbourhoods;
        this.state = state;
        this.schools = schools;

        this.name = name;
        this.businessID = businessID;
        this.open = open;

        this.categories = categories;
        

        this.stars = stars;
        this.reviewCount = reviewCount;
        this.price = price;
        
        this.coordinate = new Coordinate(longitude, latitude);
        
        this.restaurantObject = restaurantObject;
      
    }
    
    public Coordinate getLocation(){
        return this.coordinate;
    }
    
    public int getPrice() {
        return this.price;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getLocationName() {
        return city + fullAddress + neighbourhoods + state; // returns strings matching names describing location, for NodeIn // check this one out.
    }

    public double getStars() {
        return this.stars;
    }
    
    public String getBusinessID(){
        return this.businessID;
    }
    
    public ArrayList<String> getCategories(){
        return this.categories;
    }
    
    
    public String representationInJSON() throws IOException {
        
        return restaurantObject.toJSONString();

        
    }
    
    @Override
    public boolean equals(Object object) {
        
        if (object instanceof Restaurant) {
            if ( this.businessID == ((Restaurant) object).businessID ) {
                return true;
            }
        }
        
        return false;
    }
    
    @Override 
    public int hashCode() {
        return this.businessID.hashCode();
    }
}