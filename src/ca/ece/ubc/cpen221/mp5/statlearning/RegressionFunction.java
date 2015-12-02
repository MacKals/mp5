package ca.ece.ubc.cpen221.mp5.statlearning;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.RestaurantDB;

public class RegressionFunction implements MP5Function {

    private double a = 0.0;
    private double b = 0.0;
    private MP5Function featureFunction;
    
    RegressionFunction(double a, double b, MP5Function featureFunction){
        this.a = a;
        this.b = b;
        this.featureFunction = featureFunction;
    }
    
    
    
    @Override
    public double f(Restaurant yelpRestaurant, RestaurantDB db) {
        
        return ((a* featureFunction.f(yelpRestaurant, db)) + b);
    }

}
