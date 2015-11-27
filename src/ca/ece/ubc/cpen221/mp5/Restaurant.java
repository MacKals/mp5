package ca.ece.ubc.cpen221.mp5;

import ca.ece.ubc.cpen221.mp5.statlearning.Coordinate;

// TODO: Use this class to represent a restaurant.
// State the rep invariant and abs

public class Restaurant {

    private String url;
    private String photoURL;

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
    
    private Restaurant(Restaurant old) {
        this.city = old.city;
        this.fullAddress = old.fullAddress;
        this.neighbourhoods = old.neighbourhoods;
        this.state = old.state;
        this.schools = old.schools;

        this.name = old.name;
        this.businessID = old.businessID;
        this.open = old.open;

        this.categories = old.categories;

        this.stars = old.stars;
        this.reviewCount = old.reviewCount;
        this.price = old.price;
        
        this.coordinate = old.coordinate;
    }
    
    public Coordinate getLocation(){
        return this.coordinate;
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

    public double getStars() {
        return stars;
    }
    
    public Restaurant copy() {
        return new Restaurant(this);
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