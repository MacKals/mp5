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
    
    public Restaurant ( String url,
                        String photoURL,
                        double longitude,
                        double latitude,
                        String city,
                        String fullAddress,
                        String[] neigbourhoods,
                        String state,
                        String[] schools,

                        String name,
                        String businessID,
                        boolean open,

                        String[] categories,

                        float stars,
                        int reviewCount,
                        int price){
        
        
        
        
    }
    
    public double getLocation(boolean latitude){
        if (latitude) return this.latitude;
        return this.longitude;
    }
    
    public int getPrice() {
        return price;
    }
    
    public String getName() {
        return name;
    }
    
    public String getLocationName() {
        return city + fullAddress + neigbourhoods + state; // returns strings matching names describing location, for NodeIn
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
    
    public double getStars() {
        return stars;
    }
    
    public Restaurant copy() {
        return new Restaurant(this);
    }
    
    private Restaurant(Restaurant old) {
        //TODO: copy all fields
    }
    
}