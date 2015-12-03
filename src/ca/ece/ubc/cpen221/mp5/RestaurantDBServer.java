package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ca.ece.ubc.cpen221.mp5.query.QueryFactory;

// TODO: Implement a server that will instantiate a database, 
// process queries concurrently, etc.

/**
 * A server that is capable of accepting queries from many clients and executing those queries concurrently. 
 * The clients communicate with the server by sending a query (a String) and they receive, in JSON format, 
 * a list of restaurants that satisfies the query.
 * 
 */
public class RestaurantDBServer {

    private ServerSocket serverSocket;

    private RestaurantDB db;
    // private ConcurrentLinkedQueue<String> queryQueue;

    // Rep invariant: serverSocket != null

    /**
     * Constructor, make a server that listens for connection on port.
     * 
     * @param port
     *            the port number of the server
     * @param restaurantDetails
     *            JSON Format of the restaurant details
     * @param userReviews
     *            JSON Format of the user reviews
     * @param userDetails
     *            JSON Format of the user details
     */
    public RestaurantDBServer(int port, String restaurantDetails, String userReviews, String userDetails)
            throws IOException {

        // queryQueue = new ConcurrentLinkedQueue<>();
        db = new RestaurantDB(restaurantDetails, userReviews, userDetails);
        serverSocket = new ServerSocket(port);
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
        
        for (Object object : db.getRestaurantList()) {
            
            Restaurant restaurant = (Restaurant) object;
            
            if (restaurant.getName() == restaurantName) {
                String restaurantID = restaurant.getBusinessID();
                
                for (Object rewiewObject : db.getReviewList()) {
                    Review review = (Review) rewiewObject;
                    
                    if (review.businessID == restaurantID) {
                        return String.valueOf(review.getStars());
                    }
                }
                
            }
        }
        
        
        return "not created random review yet";
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
            
            if (restaurant.getBusinessID() == businessID) {
                try {
                    return restaurant.representationInJSON();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }  
            }
        }
        
        return "cannot get resturant";
    }

    /**
     * The server adds a new restaurant to the database if it does not exist
     * already.
     * The server should add a new restaurant to the database with suitable checking 
     * (for example: does another restaurant with the same name exist at the same location
     * @param json restaurant information encoded in json string
     */
    public String addRestaurant(String json) {
        
        return db.addRestaurant(json);
    }

    /**
     * 
     * @param json user information encoded as json string
     */
    public String addUser(String json) {
       
        return db.addUser(json);
    }

    /**
     * 
     * @param json review information encoded as json string
     */
    public String addReview(String json) {
        
        return db.addReview(json);
    }

    /**
     * Start a FibonacciServer running on the default port.
     */
    public static void main(String[] args) {
        try {
            RestaurantDBServer server = new RestaurantDBServer(Integer.parseInt(args[0]), args[1], args[2], args[3]);
            server.serve();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                System.err.println("request: " + line);
                try {
                    out.println( callFunctionFromClientRequest(line) );
                } catch (NumberFormatException e) {
                    // complain about ill-formatted request
                    out.println("err");
                }
                
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
     * @param queryString query string (in format outlined in assignment) that specify the search query 
     * @return json string representation of query
     * @throws IOException if something goes wrong
     */
    public String query(String queryString) throws IOException {
        Set<Restaurant> matches = QueryFactory.parse(queryString).result(db);
        String output = "";
        for (Restaurant match : matches) {
            output += match.representationInJSON();
        }
        
        //TODO: check what happens with return character etc.
        
        return output;
    }
    
    
    /**
     * Call the appropriate query function based on the input string
     * @param queryString Input string, either specifying one of the specific query methods, or a query search
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
        }
        
        try {
            return query(queryString);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            return "query error - not reognized"; //TODO: make propper return message
        }
    }

    /**
     * Specifies the specific query formats and associated strings, 
     * enabling the appropriate methods to be called more easily
     */
    enum ClientQuery {

        RandomReview, GetRestaurant, AddRestaurant, AddUser, AddReview;

        private String queryArgument;

        public String getQueryArgument() {
            return queryArgument;
        }

        static ClientQuery fromString(String clientString) {

            for (ClientQuery query : ClientQuery.values()) {
                
                String queryString = query.toString();
                String substring = clientString.substring(0, queryString.length() - 1);

                if (queryString == substring) {

                    Pattern p = Pattern.compile("\"([^\"]*)\"");
                    Matcher m = p.matcher(queryString);

                    if (m.find()) {
                        query.queryArgument = m.group(1);
                    }
                    return query;
                }
            }

            return null;
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
            }

            return null;
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
