package ca.ece.ubc.cpen221.mp5;

import java.io.IOException;

import org.json.simple.JSONObject;

public class User {
    
    private int reviewCount;
    private String userID;
    private String name;
    private double averageStars;
    
    private JSONObject user;
    
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
    public User(JSONObject user){

        this.user = user;

        this.reviewCount = UtilityMethods.safeLongToInt((long) user.get("review_count"));
        this.userID = (String) user.get("user_id");
        this.name = (String) user.get("name");
        this.averageStars = (double) user.get("average_stars");
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
        return user.toJSONString();
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
