package ca.ece.ubc.cpen221.mp5;

// TODO: Use this class to represent a Yelp review.

public class Review {
    
     String businessID;
     String userID;
     int[] votes;
     String reviewID;
     String text;
     float stars;
     String date;
  
    
     public Review ( String businessID,
                    String userID,
                    int[] votes,
                    String reviewID,
                    String text,
                    float stars,
                    String date){
            
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
