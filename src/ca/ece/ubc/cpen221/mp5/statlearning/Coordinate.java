package ca.ece.ubc.cpen221.mp5.statlearning;


import java.util.List;
import java.util.Set;

public class Coordinate {
    
    public double xCoord;
    public double yCoord;
    
    public Coordinate (double xCoord, double yCoord){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }
    
    public double computeDistance (double x, double y){      
        return Math.sqrt( Math.pow((xCoord-x), 2) + Math.pow((yCoord-y), 2) );
    }
    
    //returns the index within the list of the closest coordinate
    public int findClosestNeighbour(List<Coordinate> coordList){
        
        int indexClosest = 0;
        double closestDistance = coordList.get(0).computeDistance(this.xCoord, this.yCoord);
        
        for (Coordinate coordinate : coordList){
            
            if (coordinate.computeDistance(this.xCoord, this.yCoord) < closestDistance){
                indexClosest = coordList.indexOf(coordinate);
            }
            
        }
        
        return indexClosest;
    }
    
    @Override
    public boolean equals(Object coordinate){
        
        if (coordinate instanceof Coordinate){
            if (((Coordinate) coordinate).xCoord == this.xCoord && ((Coordinate) coordinate).yCoord == this.yCoord) return true;
        }
        return false;
    }

    //TODO: May be hashcode needs to be implemented..
}
