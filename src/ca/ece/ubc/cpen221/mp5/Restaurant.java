package ca.ece.ubc.cpen221.mp5;

// TODO: Use this class to represent a restaurant.
// State the rep invariant and abs

public class Restaurant {

    private String url;
    private String photoURL;

    private double longitude;
    private double latitude;
    private String city;
    private String fullAddress;
    private String[] neigbourhoods;
    private String state;
    private String[] schools;

    private String name;
    private String businessID;
    private boolean open;

    private String[] categories;
    //private String type; // all are "business"??

    private float stars;
    private int reviewCount;
    private int price;
    
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