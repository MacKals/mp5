package ca.ece.ubc.cpen221.mp5;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONObject;

public class User {
        
    private String url;
    private Object votes;
    private int reviewCount;
    private String userID;
    private String name;
    private double averageStars;
    
    private JSONObject userObject;
    
    /**
     * Constructs a User object based on Yelp data.
     * @param url
     * @param votes
     * @param reviewCount
     * @param userID
     * @param name
     * @param averageStars
     * @param userObject
     */
    public User(String url,
                 Object votes,
                 int reviewCount,
                 String userID,
                 String name,
                 double averageStars,
                 JSONObject userObject){
        
        this.url = url;
        this.votes = votes;
        this.reviewCount = reviewCount;
        this.userID = userID;
        this.name = name;
        this.averageStars = averageStars;
        
        this.userObject = userObject;
    }
    
    public int getReviewCount(){
        return this.reviewCount;
    }
    
    public double getAvgStars(){
        return this.averageStars;
    }
    
    public String getUserName(){
        return this.name;
    }
    
    public String getUserID(){
        return this.userID;
    }
    
    public String representationInJSON() throws IOException {
        return userObject.toJSONString();
    }
    
    @Override
    public boolean equals(Object object) {
        
        if (object instanceof User) {
            if ( this.userID == ((User) object).userID ) {
                return true;
            }
        }       
        return false;
    }
    
    @Override 
    public int hashCode() {
        return this.userID.hashCode();
    }
 
}
