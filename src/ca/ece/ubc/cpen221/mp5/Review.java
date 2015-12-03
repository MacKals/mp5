package ca.ece.ubc.cpen221.mp5;

import java.util.ArrayList;

// TODO: Use this class to represent a Yelp review.

public class Review {
    
     String businessID;
     String userID;
     Object votes;
     String reviewID;
     String text;
     int stars;
     String date;
  
    
     public Review ( String businessID,
                    String userID,
                    Object votes,
                    String reviewID,
                    String text,
                    int stars,
                    String date){
         
         this.businessID = businessID;
         this.userID = userID;
         this.votes = votes;
         this.reviewID = reviewID;
         this.text = text;
         this.stars = stars;
         this.date = date;
            
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
