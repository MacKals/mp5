package ca.ece.ubc.cpen221.mp5.statlearning;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.RestaurantDB;

public class RegressionFunction implements MP5Function {

    private double a = 0.0;
    private double b = 0.0;
    
    @SuppressWarnings("unused")
    private double r_squared = 0.0;
    
    private MP5Function featureFunction;
    
    
    RegressionFunction(double a, double b, double r_squared, MP5Function featureFunction){
        this.a = a;
        this.b = b;
        this.r_squared = r_squared;
        this.featureFunction = featureFunction;
    }

    @Override
    public double f(Restaurant yelpRestaurant, RestaurantDB db) {
        
        double prediction = (b* featureFunction.f(yelpRestaurant, db)) + a; //output = b*(output) + a 
        
        if (prediction < 0.0){
            prediction = 0.0;
        } else if (prediction > 5.0){
            prediction = 5.0;
        }
        
        return prediction;
    }

}
