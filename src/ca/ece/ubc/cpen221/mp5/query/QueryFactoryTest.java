package ca.ece.ubc.cpen221.mp5.query;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class QueryFactoryTest {

    @Test
    public void testLiterals() {

        List<String> results = new ArrayList<>();
        List<String> answers = new ArrayList<>();

        results.add(QueryFactory.parse("price(2..4)").stringRepresentation());
        answers.add("price: 2...4");
        
        results.add(QueryFactory.parse("(rating(1..2) || name(\"res\")) && price(3..5)").stringRepresentation());
        answers.add("((rating: 1...2 || name: res) && price: 3...5)");

        results.add(QueryFactory.parse("rating(1..2) && price(1..4)").stringRepresentation());
        answers.add("(rating: 1...2 && price: 1...4)");
        
        results.add(QueryFactory.parse("in(\"Telegraph Ave\") && (category(\"Chinese\") || category(\"Italian\")) && price(2..2)").stringRepresentation());
        answers.add("(in: Telegraph Ave && ((cathegory: Chinese || cathegory: Italian) && price: 2...2))");
       
        for (int i = 0; i < results.size(); i++) {
            System.out.println(results.get(i));
            assertEquals(results.get(i), answers.get(i));
        }
    }
}