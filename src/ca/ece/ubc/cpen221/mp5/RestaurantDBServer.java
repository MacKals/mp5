package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;

import ca.ece.ubc.cpen221.mp5.query.QueryFactory;

// process queries concurrently, etc.

/**
 * A server that is capable of accepting queries from many clients and executing
 * those queries concurrently. The clients communicate with the server by
 * sending a query (a String) and they receive, in JSON format, a list of
 * restaurants that satisfies the query.
 * 
 */
public class RestaurantDBServer {

    private ServerSocket serverSocket;

    private RestaurantDB db;

    // Rep invariant: serverSocket != null

    /**
     * Constructor that makes a server that listens for connection on port.
     * 
     * @param port
     *            the port number of the server, requires 0 <= port <= 65535
     * @param restaurantDetails
     *            JSON Format of the restaurant details
     * @param userReviews
     *            JSON Format of the user reviews
     * @param userDetails
     *            JSON Format of the user details
     */
    public RestaurantDBServer(int port, String restaurantDetails, String userReviews, String userDetails)
            throws IOException {
                
        db = new RestaurantDB(restaurantDetails, userReviews, userDetails);
        serverSocket = new ServerSocket(port);
    }

    /**
     * Start a RestaruantServer running on specified port from specified files.
     */
    public static void main(String[] args) {
        
        for (int i = 0; i < args.length; i++) {
            System.out.println("input arg " + i + " = " + args[i] );
        }
        
        try {
            RestaurantDBServer server = new RestaurantDBServer(Integer.parseInt(args[0]), args[1], args[2], args[3]);
            server.serve();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * To this request, the server should respond by providing a random review
     * (in JSON format) for the restaurant that matches the provided name. If
     * more than one restaurant matches the name then any restaurant that
     * satisfies the match can be selected.
     * 
     * @param restaurantName
     */
    public String randomReview(String restaurantName) {

        for (Restaurant restaurant : db.getRestaurantList()) {
            if (restaurant.getName().equals(restaurantName)) {
                String restaurantID = restaurant.getBusinessID();

                for (Review review : db.getReviewList()) {
                    if (review.businessID.equals(restaurantID)) {
                        try {
                            return review.representationInJSON();
                        } catch (IOException e) {
                            return ReturnMessages.malformedExpressionError;
                        }
                    }
                }
            }
        }

        return ReturnMessages.notFoundError;
    }

    /**
     * the server responds with the restaurant details in JSON format for the
     * restaurant that has the provided business identifier.
     * 
     * @param businessID
     */
    public String getRestaurant(String businessID) {

        for (Object object : db.getRestaurantList()) {
            Restaurant restaurant = (Restaurant) object;

            if (restaurant.getBusinessID().equals(businessID)) {
                try {
                    return restaurant.representationInJSON();
                } catch (IOException e) {
                    return ReturnMessages.malformedExpressionError;
                }
            }
        }

        return ReturnMessages.notFoundError;
    }

    /**
     * The server adds a new restaurant to the database if it does not exist
     * already. The server should add a new restaurant to the database with
     * suitable checking (for example: does another restaurant with the same
     * name exist at the same location
     * 
     * @param json restaurant information encoded in json string
     * @return operation completion status
     */
    public String addRestaurant(String json) {
        return db.addRestaurant(json);
    }

    /**
     * 
     * @param json user information encoded as json string
     * @return operation completion status
     * 
     */
    public String addUser(String json) {
        return db.addUser(json);
    }

    /**
     * 
     * @param json review information encoded as json string
     * @return operation completion status
     */
    public String addReview(String json) {
        return db.addReview(json);
    }

    /**
     * Handle one client connection. Returns when client disconnects.
     * 
     * @param socket
     *            socket where client is connected
     * @throws IOException
     *             if connection encounters an error
     */
    private void handle(Socket socket) throws IOException {
        System.err.println("client connected");

        // get the socket's input stream, and wrap converters around it
        // that convert it from a byte stream to a character stream,
        // and that buffer it so that we can read a line at a time
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // similarly, wrap character=>bytestream converter around the
        // socket output stream, and wrap a PrintWriter around that so
        // that we have more convenient ways to write Java primitive
        // types to it.
        PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

        try {
            // each request is a single line containing a number
            for (String line = in.readLine(); line != null; line = in.readLine()) {

                String result = callFunctionFromClientRequest(line);
                
                System.err.println("Request: " + line + "\n\tResult: " + result);
                
                out.println(result);

                // flushing buffer so the reply is sent
                out.flush();
            }
        } finally {
            out.close();
            in.close();
        }
    }

    /**
     * Takes a query string and launches the Antlr query parser
     * 
     * @param queryString
     *            query string (in format outlined in assignment) that specify
     *            the search query
     * @return json string representation of query
     */
    public String query(String queryString) {

        try {
            
            if (queryString.isEmpty()) return ReturnMessages.emptyQueryError;
            
            Set<Restaurant> matches = QueryFactory.parse(queryString).result(db);
            
            String output = "";
            for (Restaurant match : matches) {
                output += match.representationInJSON();
            }
            
            if (output.isEmpty()) return ReturnMessages.notFoundError;
            
            return output;

        } catch (IOException e) {
            return ReturnMessages.malformedExpressionError;
        }
    }

    /**
     * Call the appropriate query function based on the input string
     * 
     * @param queryString
     *            Input string, either specifying one of the specific query
     *            methods, or a query search
     * @return result of query in Json format
     */
    private String callFunctionFromClientRequest(String queryString) {

        ClientQuery query = ClientQuery.fromString(queryString);

        switch (query) {
        case RandomReview:
            return randomReview(query.getQueryArgument());

        case GetRestaurant:
            return getRestaurant(query.getQueryArgument());

        case AddRestaurant:
            return addRestaurant(query.getQueryArgument());

        case AddUser:
            return addUser(query.getQueryArgument());

        case AddReview:
            return addReview(query.getQueryArgument());
        
        default :
            return query(query.getQueryArgument());
        }
    }

    /**
     * Specifies the specific query formats and associated strings, enabling the
     * appropriate methods to be called more easily
     */
    public enum ClientQuery {

        RandomReview, GetRestaurant, AddRestaurant, AddUser, AddReview, SearchQuery;

        private String queryArgument;

        public String getQueryArgument() {
            return queryArgument;
        }

        static ClientQuery fromString(String clientString) {

            for (ClientQuery query : ClientQuery.values()) {

                String queryString = query.toString();
                
                //protection for searchQueries
                if (clientString.length() < queryString.length()) break; 
                String substring = clientString.substring(0, queryString.length());
                
                if (queryString.equals(substring)) {

                    int firstQuoteIndex = clientString.indexOf("\"") + 1;
                    int secondQuoteIndex = clientString.indexOf("\"", firstQuoteIndex);
                    
                    query.queryArgument = clientString.substring(firstQuoteIndex, secondQuoteIndex);
                    
                    return query;
                }
            }

            //no matches in pre-specified strings, we conclude we are handling a search query
            ClientQuery query = ClientQuery.SearchQuery;
            query.queryArgument = clientString;
            return query;
        }

        @Override
        public String toString() {
            switch (this) {

            case RandomReview:
                return "randomReview";

            case GetRestaurant:
                return "getRestaurant";

            case AddRestaurant:
                return "addRestaurant";

            case AddUser:
                return "addUser";

            case AddReview:
                return "addReview";
            
            default :
                return "searchQuery";
            }
        }
    }

    /**
     * Run the server, listening for connections and handling them.
     * 
     * @throws IOException
     *             if the main server socket is broken
     */
    public void serve() throws IOException {
        while (true) {

            // block until a client connects
            final Socket socket = serverSocket.accept();

            // create a new thread to handle that client
            Thread handler = new Thread(new Runnable() {
                public void run() {
                    try {
                        try {
                            handle(socket);
                        } finally {
                            socket.close();
                        }
                    } catch (IOException ioe) {
                        // this exception wouldn't terminate serve(),
                        // since we're now on a different thread, but
                        // we still need to handle it
                        ioe.printStackTrace();
                    }
                }
            });

            // start the thread
            handler.start();
        }
    }
}
