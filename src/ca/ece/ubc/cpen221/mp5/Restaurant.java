package ca.ece.ubc.cpen221.mp5;

import ca.ece.ubc.cpen221.mp5.statlearning.Coordinate;

// TODO: Use this class to represent a restaurant.
// State the rep invariant and abs

public class Restaurant {

    private String url;
    private String photoURL;

//    private double longitude;
//    private double latitude;
    private String city;
    private String fullAddress;
    private String[] neighbourhoods;
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
    
    private Coordinate coordinate;
    
    public Restaurant ( String url,
                        String photoURL,
                        double longitude,
                        double latitude,
                        String city,
                        String fullAddress,
                        String[] neighbourhoods,
                        String state,
                        String[] schools,

                        String name,
                        String businessID,
                        boolean open,

                        String[] categories,

                        float stars,
                        int reviewCount,
                        int price){
        
        this.city = city;
        this.fullAddress = fullAddress;
        this.neighbourhoods = neighbourhoods;
        this.state = state;
        this.schools = schools;

        this.name = name;
        this.businessID = businessID;
        this.open = open;

        this.categories = categories;
        

        this.stars = stars;
        this.reviewCount = reviewCount;
        this.price = price;
        
        this.coordinate = new Coordinate(latitude, longitude);
      
    }
    
    public Coordinate getLocation(){
        return this.coordinate;
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
    
    
}