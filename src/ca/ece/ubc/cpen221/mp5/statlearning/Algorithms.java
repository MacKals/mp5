package ca.ece.ubc.cpen221.mp5.statlearning;

import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import ca.ece.ubc.cpen221.mp5.*;

public class Algorithms {

	/**
	 * Use k-means clustering to compute k clusters for the restaurants in the
	 * database.
	 * 
	 * @param db
	 * @return
	 */
	public static List<Set<Restaurant>> kMeansClustering(int k, RestaurantDB db ) {
	    
		List<Set<Restaurant>> allClusters = new ArrayList<Set<Restaurant>>();
		
		List<Coordinate> kNodes = new ArrayList<Coordinate>();
		List<Restaurant> restaurants = new ArrayList<Restaurant>();
		
		boolean calibrating = true;
				
		double maxX = 0;
		double minX = 0;
		double maxY = 0;
		double minY = 0;
		
		for (Object restaurant : db.getRestaurantList()){ //add all restaurants to a list. Attain min and max latitudes/longitudes.
		    
		    if (restaurant instanceof Restaurant){
		        
		        if (((Restaurant) restaurant).getLocation().xCoord > maxX) maxX = ((Restaurant) restaurant).getLocation().xCoord;
		         
		        if (((Restaurant) restaurant).getLocation().xCoord < minX) minX = ((Restaurant) restaurant).getLocation().xCoord;
		        
		        if (((Restaurant) restaurant).getLocation().yCoord > maxY) maxY = ((Restaurant) restaurant).getLocation().yCoord;
		        
		        if (((Restaurant) restaurant).getLocation().yCoord < minY) minY = ((Restaurant) restaurant).getLocation().yCoord;
		       	
		        restaurants.add( (Restaurant) restaurant); // internal list of restaurants;
		    }
		    	    
		}
		
		// initialize the k nodes at "random" positions within the max and minimum coordinates
		for (int i = 0; i < k; i++){		    
		    kNodes.add(  new Coordinate(Math.random() * (maxX - minX) + minX , Math.random() * (maxY - minY) + minY)  );
		}

		 // assign all restaurants to a kNode
        for (Restaurant restaurant : restaurants){
            
            allClusters.get(restaurant.getLocation().findClosestNeighbour(kNodes)).add(restaurant);
            
        }
        
		while (calibrating){
		     calibrating = false;
		    //recompute the location of the nodes as the centroid.
		    for (Coordinate node : kNodes){
		        
		        int index = kNodes.indexOf(node);
		        kNodes.remove(index);
		        
		        if (!node.equals(computeCentroidOfRestaurants(allClusters.get(index)))){
		            calibrating = true;
		        }
		        
		        kNodes.add( index, computeCentroidOfRestaurants(allClusters.get(index)) );  
		    }
		    
		    if (!calibrating) break;
		    
		    // reassign all restaurants to the kNodes
		    allClusters.clear();
		    
	        for (Restaurant restaurant : restaurants){
	            
	            allClusters.get(restaurant.getLocation().findClosestNeighbour(kNodes)).add(restaurant);
	            
	        }
	        
		    
		}
		
		return allClusters;
	}
	

	public static String convertClustersToJSON(List<Set<Restaurant>> clusters) {
		// TODO: Implement this method
		return null;
	}

	public static MP5Function getPredictor(User u, RestaurantDB db, MP5Function featureFunction) {
	    
	    double sumOfInput = 0;
	    double sumOfOutput = 0;
	    
	    ArrayList<Double> inputs = new ArrayList<Double>();
	    ArrayList<Double> outputs = new ArrayList<Double>();
	    int numDataPairs = 0;
	    double r_squared;
	    double meanInput;
	    double meanOutput;
	    
	    for (Object review : db.getReviewList()){ //loop through all reviews to find the review from a particular user
	        
	        if (review instanceof Review){
	            
	            if(((Review) review).getUserID().equals(u.getUserID())){ //this review was written by user U
	                
	                //find the associated restaurant
	                for (Object restaurant : db.getRestaurantList()){
	                    if (restaurant instanceof Restaurant && ((Restaurant) restaurant).getBusinessID().equals(((Review) review).getBusinessID())){
	                        
	                        sumOfInput += featureFunction.f((Restaurant) restaurant, db);
	                        sumOfOutput += ((Review) review).getStars();
	                        numDataPairs++;
	                        break;
	                        
	                    }	                    
	                }	                	                
	            }   
	        }    
	    }
	    
	    meanInput = sumOfInput/numDataPairs;
	    meanOutput = sumOfOutput/numDataPairs;
	    
	    double sxx = 0;
	    double syy = 0;
	    double sxy = 0;
	    
	    for (int i = 0; i < numDataPairs; i++ ){
	        sxx += (inputs.get(i) - meanInput) * (inputs.get(i) - meanInput);
	        syy += (outputs.get(i) - meanOutput) * (outputs.get(i) - meanOutput);
	        sxy += (inputs.get(i) - meanInput) * (outputs.get(i) - meanOutput);
	    }
	   
	    r_squared = (sxy * sxy)/(sxx * syy);
		
		return null;
	}

	public static MP5Function getBestPredictor(User u, RestaurantDB db, List<MP5Function> featureFunctionList) {
		// TODO: Implement this method
		return null;
	}
	
    private static Coordinate computeCentroidOfRestaurants(Set<Restaurant> coordinates){
        
        double newX = 0;
        double newY = 0;
        
        for (Restaurant coordinate : coordinates){
            newX += coordinate.getLocation().xCoord;
            newY += coordinate.getLocation().yCoord;
        }
        
        newX = newX/(coordinates.size());
        newY = newY/(coordinates.size());
 
        return new Coordinate(newX, newY);
    }
}