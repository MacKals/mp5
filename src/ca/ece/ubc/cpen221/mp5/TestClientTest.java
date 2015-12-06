package ca.ece.ubc.cpen221.mp5;

//import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestClientTest {

    private static final int PORT = 4949;
    private static final String HOST_NAME = "localhost";

    @Test
    public void test() {

        try {
            
            TestClient client = new TestClient(PORT, HOST_NAME);

            List<String> queries = new ArrayList<>();

            // add to list
            queries.add("randomReview(\"La Val's Pizza\")");
            queries.add("in(\"Telegraph Ave\") && (category(\"Chinese\") || category(\"Italian\")) && price(1..2)");
            queries.add("getRestaurant(\"4D7IdtyRjH8qxcsHaz1-GA\")");
            queries.add("addRestaurant(\"\")");
            queries.add("in(\"Telegraph Ave\") && (category(\"Chinese\") || category(\"Italian\")) && price(1..2)");

            // send the requests to find the first N query numbers
            for (String query : queries) {
                client.sendRequest(query);
//                System.out.println("Query sent: " + query);
            }

            // collect the replies
            for (String query : queries) {
                String reply = client.getReply();
                System.out.println("Query: " + query + ", \n \t Result: " + reply);
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
