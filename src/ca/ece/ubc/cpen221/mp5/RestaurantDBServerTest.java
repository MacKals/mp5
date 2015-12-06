package ca.ece.ubc.cpen221.mp5;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.RestaurantDBServer.ClientQuery;

public class RestaurantDBServerTest {

    RestaurantDBServer server;

    // @Before
    public void launchServer() {
        try {
            server = new RestaurantDBServer(4949, "restaurants.json", "reviews.json", "users.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // @Test
    public void testRandomReview() {
        System.out.println(server.randomReview("La Val's Pizza"));

    }

    // @Test
    public void testGetRestaurant() {

        System.out.println(server.getRestaurant("4D7IdtyRjH8qxcsHaz1-GA"));

    }

    // @Test
    public void testAddRestaurant() {

        // System.out.println(server.addRestaurant("La Val's Pizza"));

    }

    // @Test
    public void testAddUser() {

    }

    // @Test
    public void testAddReview() {

    }

    // @Test
    public void testSearchQuery() {
        System.out.println(server.query("price(1..2)"));
        System.out.println(server.query("name(\"Italian\")"));
        System.out.println(server.query("in(\"test\")"));

        // "in("Telegraph Ave") && (category("Chinese") || category("Italian"))
        // && price(1..2)

    }

    @Test
    public void testClientQueryParser() {
        List<String> queries = new ArrayList<>();
        List<ClientQuery> enumAnswer = new ArrayList<>();
        List<String> argumentAnswer = new ArrayList<>();

        queries.add("randomReview(\"Restaurant Name\")");
        enumAnswer.add(ClientQuery.RandomReview);
        argumentAnswer.add("Restaurant Name");

        queries.add("getRestaurant(\"businessId\")");
        enumAnswer.add(ClientQuery.GetRestaurant);
        argumentAnswer.add("businessId");

        queries.add("addRestaurant(\"Restaurant Details in JSON format\")");
        enumAnswer.add(ClientQuery.AddRestaurant);
        argumentAnswer.add("Restaurant Details in JSON format");

        queries.add("addUser(\"User details in JSON format\")");
        enumAnswer.add(ClientQuery.AddUser);
        argumentAnswer.add("User details in JSON format");

        queries.add("addReview(\"Review details in JSON format\")");
        enumAnswer.add(ClientQuery.AddReview);
        argumentAnswer.add("Review details in JSON format");

        queries.add("price(1..3)");
        enumAnswer.add(ClientQuery.SearchQuery);
        argumentAnswer.add("price(1..3)");

        for (int i = 0; i < queries.size(); i++) {
            ClientQuery clientQuery = ClientQuery.fromString(queries.get(i));
            assertEquals(clientQuery, enumAnswer.get(i));
            assertEquals(clientQuery.getQueryArgument(), argumentAnswer.get(i));
        }

    }
}
