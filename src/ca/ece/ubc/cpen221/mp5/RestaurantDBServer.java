package ca.ece.ubc.cpen221.mp5;

// TODO: Implement a server that will instantiate a database, 
// process queries concurrently, etc.

public class RestaurantDBServer {

    /**
     * Constructor
     * 
     * @param port the port number of the server
     * @param restaurantDetails JSON Format of the restaurant details
     * @param userReviews JSON Format of the user reviews
     * @param userDetails JSON Format of the user details
     */
    public RestaurantDBServer(int port, String restaurantDetails, String userReviews, String userDetails) {
        
        new RestaurantDB(restaurantDetails, userReviews, userDetails);
        
    }
    
    /**
     * To this request, the server should respond by providing a random review (in JSON format) for the 
     * restaurant that matches the provided name. If more than one restaurant matches the name then any 
     * restaurant that satisfies the match can be selected.
     * @param restaurantName
     */
    public String randomReview(String restaurantName){
       
        return "not created random review yet";
    }
    
    /**
     * the server responds with the restaurant details in JSON format
     * for the restaurant that has the provided business identifier.
     * @param businessID
     */
    public String getRestaurant(String businessID){
        return " ";
    }
    
    /**
     *  The server adds a new restaurant to the database if it 
     *  does not exist already.
     */
    public boolean addRestaurant(){
        return false;
    }
    
    /**
     * 
     * @param userDetails
     */
    public boolean addUser(String userDetails){
        return false;
    }
    
    /**
     * 
     * @param reviewDetails
     */
    public boolean addReview(String reviewDetails){
        return false;
    }
    
    
	
}
