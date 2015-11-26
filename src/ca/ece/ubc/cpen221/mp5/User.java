package ca.ece.ubc.cpen221.mp5;

// TODO: Use this class to represent a Yelp user.

public class User {
    private String url;
    private int[] votes;
    private int reviewCount;
    private String userID;
    private String name;
    private double averageStars;
    
    
    public User(String url,
                 int[] votes,
                 int reviewCount,
                 String userID,
                 String name,
                 double averageStars){
        
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
