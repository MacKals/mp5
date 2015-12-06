package ca.ece.ubc.cpen221.mp5.statlearning;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class CoordinateTest {

    @Test
    public void testClosestDistance() {
        Coordinate pos1 = new Coordinate(20,20);
        Coordinate pos2 = new Coordinate(5,6);
        Coordinate pos3 = new Coordinate(2,1);
        
        assert(pos1.computeDistance(20, 20) == 0);
        assert(pos1.computeDistance(21, 21) == Math.sqrt(2.0));
        
        ArrayList<Coordinate> testList = new ArrayList<Coordinate>();
        
        testList.add(new Coordinate(2,1));
        testList.add(new Coordinate(5,5));
        testList.add(new Coordinate(10,5));
        testList.add(new Coordinate(15,3));
        testList.add(new Coordinate(13,4));
       

        assertEquals(pos2.findClosestNeighbour(testList), 1);
        assertEquals(pos3.findClosestNeighbour(testList), 0);
        
    }
    
}
