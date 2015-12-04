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
        
        this.coordinate = new Coordinate(latitude, longitude);
      
    }
    
    private Restaurant(Restaurant old) {
        this.city = old.city;
        this.fullAddress = old.fullAddress;
        this.neighbourhoods = old.neighbourhoods;
        this.state = old.state;
        this.schools = old.schools;

        this.name = old.name;
        this.businessID = old.businessID;
        this.open = old.open;

        this.categories = old.categories;

        this.stars = old.stars;
        this.reviewCount = old.reviewCount;
        this.price = old.price;
        
        this.coordinate = old.coordinate;
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
    
    public Restaurant copy() {
        return new Restaurant(this);
    }
    
    
    public String representationInJSON() throws IOException {
        
        return restaurantObject.toJSONString();

        /*JSONObject obj = new JSONObject();
        
        obj.put("url", url);
        obj.put("photo_url", photoURL);
        obj.put("longitude", coordinate.xCoord);
        obj.put("latitude", coordinate.yCoord);
        obj.put("city", city);
        obj.put("full_address", fullAddress);
        obj.put("neighborhoods", neighbourhoods);
        obj.put("state", state);
        obj.put("schools", schools);
        obj.put("name", name);
        obj.put("business_id", businessID);
        obj.put("open", open);
        obj.put("categories", categories);
        obj.put("stars", stars);
        obj.put("review_count", reviewCount);
        obj.put("price", price);
        
        StringWriter out = new StringWriter();
        obj.writeJSONString(out);
        out.close();
        return out.toString();*/
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