package ca.ece.ubc.cpen221.mp5;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONObject;

public class Review {
    
     String businessID;
     String userID;
     Object votes;
     String reviewID;
     String text;
     int stars;
     String date;
  
     private JSONObject reviewObject;
    
     /**
      * Constructs a Review object based on Yelp data.
      * @param businessID
      * @param userID
      * @param votes
      * @param reviewID
      * @param text
      * @param stars
      * @param date
      * @param reviewObject
      */
     public Review (String businessID,
                    String userID,
                    Object votes,
                    String reviewID,
                    String text,
                    int stars,
                    String date,
                    JSONObject reviewObject){
         
         this.businessID = businessID;
         this.userID = userID;
         this.votes = votes;
         this.reviewID = reviewID;
         this.text = text;
         this.stars = stars;
         this.date = date;
         
         this.reviewObject = reviewObject;      
     }
     
     public String getUserID(){
         return this.userID;
     }
     
     public int getStars(){
         return this.stars;
     }
     public String getBusinessID(){
         return this.businessID;
     }
     
     public String representationInJSON() throws IOException {
         return reviewObject.toJSONString();
     }
     
     @Override
     public boolean equals(Object object) {
         
         if (object instanceof Review) {
             if ( this.reviewID == ((Review) object).reviewID ) {
                 return true;
             }
         }
         
         return false;
     }
     
     @Override 
     public int hashCode() {
         return this.reviewID.hashCode();
     }
     
}
