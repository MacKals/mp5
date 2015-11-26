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
	public static List<Set<Restaurant>> kMeansClustering(int k, RestaurantDB db) {
	    
		List<Set<Restaurant>> allClusters = new ArrayList<Set<Restaurant>>();
		
		
		double maxLongitude = 0;
		double minLongitude = 0;
		double minLatitude = 0;
		double maxLatitude = 0;
		
		for (Object restaurant : db.restaurants){ //add all restaurants to a list. Attain min and max latitudes/longitudes.
		    
		    if (restaurant instanceof Restaurant){
		        
		        if (((Restaurant) restaurant).getLocation(true) > maxLatitude) maxLatitude = ((Restaurant) restaurant).getLocation(true);
		         
		        if (((Restaurant) restaurant).getLocation(true) < minLatitude) minLatitude = ((Restaurant) restaurant).getLocation(true);
		        
		        if (((Restaurant) restaurant).getLocation(false) < minLongitude) minLongitude = ((Restaurant) restaurant).getLocation(false);
		        
		        if (((Restaurant) restaurant).getLocation(false) > maxLongitude) maxLongitude = ((Restaurant) restaurant).getLocation(false);
		        
		        
		        
		        
		               
		    }
		    
		    
		    
		    
		}
				
		return allClusters;
	}
	

	public static String convertClustersToJSON(List<Set<Restaurant>> clusters) {
		// TODO: Implement this method
		return null;
	}

	public static MP5Function getPredictor(User u, RestaurantDB db, MP5Function featureFunction) {
		// TODO: Implement this method
		return null;
	}

	public static MP5Function getBestPredictor(User u, RestaurantDB db, List<MP5Function> featureFunctionList) {
		// TODO: Implement this method
		return null;
	}
}