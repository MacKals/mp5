package ca.ece.ubc.cpen221.mp5;

import java.util.ArrayList;

// TODO: Use this class to represent a Yelp user.

public class User {
        
    private String url;
    private ArrayList<Integer> votes;
    private int reviewCount;
    private String userID;
    private String name;
    private double averageStars;
    
    
    public User(String url,
            ArrayList<Integer> votes,
                 int reviewCount,
                 String userID,
                 String name,
                 double averageStars){
        
        this.url = url;
        this.votes = votes;
        this.reviewCount = reviewCount;
        this.userID = userID;
        this.name = name;
        this.averageStars = averageStars;
        
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
