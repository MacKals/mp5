package ca.ece.ubc.cpen221.mp5.statlearning;


import java.util.List;

public class Coordinate {
    
    public double xCoord;
    public double yCoord;
    
    public Coordinate (double xCoord, double yCoord){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }
    
    public double computeDistance (double x, double y){      
        return Math.sqrt( Math.pow((this.xCoord-x), 2) + Math.pow((this.yCoord-y), 2) );
    }
    
    //returns the index within the list of the closest coordinate
    public int findClosestNeighbour(List<Coordinate> coordList){
        
        int indexClosest = 0;
        double closestDistance = coordList.get(0).computeDistance(this.xCoord, this.yCoord);
        //System.out.println("distance" + closestDistance);
        
        
        for (Coordinate coordinate : coordList){
            
            if (coordinate.computeDistance(this.xCoord, this.yCoord) < closestDistance){
                indexClosest = coordList.indexOf(coordinate);
                closestDistance = coordinate.computeDistance(this.xCoord, this.yCoord);
                //System.out.println("index" + indexClosest);
            }
            
        }
        
        return indexClosest;
    }
    
    @Override
    public boolean equals(Object coordinate){
        
        if (coordinate instanceof Coordinate){
            //to account for floating point errors
            if (((Coordinate) coordinate).xCoord <= this.xCoord + 0.00000001 && ((Coordinate) coordinate).xCoord >= this.xCoord - 0.00000001 ){
                
                    if (((Coordinate) coordinate).yCoord <= this.yCoord + 00000001 && ((Coordinate) coordinate).yCoord >= this.yCoord - 00000001) {
                        return true;
                    }
                
            }
        }
        return false;
    }

    @Override
    public int hashCode(){
        return (int) (Math.pow(this.xCoord, 2) + Math.pow(this.yCoord, 2));
    }
}
