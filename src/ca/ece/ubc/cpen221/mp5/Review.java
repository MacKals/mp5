package ca.ece.ubc.cpen221.mp5;

// TODO: Use this class to represent a Yelp review.

public class Review {
   // private String type; //appears that all are "review"
    
    private String businessID;
    private int[] votes; //3 values, "cool", "useful", "funny"
    private String reviewID;
    private String text;
    private float stars;
    private String userID;
    
    public Review(){
        
    }
}
