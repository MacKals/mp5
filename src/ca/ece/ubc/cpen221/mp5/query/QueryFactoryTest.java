package ca.ece.ubc.cpen221.mp5.query;

import static org.junit.Assert.*;

import org.junit.Test;

public class QueryFactoryTest {

    @Test
    public void testLiterals() {
        QueryFactory.parse("price(1..2)");
        QueryFactory.parse("rating(1..2)");
        QueryFactory.parse("rating(1..2)&&price(1..4)");
        QueryFactory.parse("name(\"resturant\")");
        QueryFactory.parse("category(\"Cafe\")");

    }
}

//STRING:     ~[<>]+ ;
//TODO:fix spaces?