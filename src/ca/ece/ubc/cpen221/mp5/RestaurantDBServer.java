package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO: Implement a server that will instantiate a database, 
// process queries concurrently, etc.

/**
 * TODO: modify to suit this FibonacciServerMulti is a server that finds the
 * n^th Fibonacci number given n. It accepts requests of the form: Request ::=
 * Number "\n" Number ::= [0-9]+ and for each request, returns a reply of the
 * form: Reply ::= (Number | "err") "\n" where a Number is the request Fibonacci
 * number, or "err" is used to indicate a misformatted request.
 * FinbonacciServerMulti can handle multiple concurrent clients.
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
//            restaurant = 
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
        return " ";
    }

    /**
     * The server adds a new restaurant to the database if it does not exist
     * already.
     */
    public boolean addRestaurant() {
        return false;
    }

    /**
     * 
     * @param userDetails
     */
    public boolean addUser(String userDetails) {
        return false;
    }

    /**
     * 
     * @param reviewDetails
     */
    public boolean addReview(String reviewDetails) {
        return false;
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
                    callFunctionFromClientRequest(line);
                } catch (NumberFormatException e) {
                    // complain about ill-formatted request
                    System.err.println("reply: err");
                    out.println("err");
                }
                // important! flush our buffer so the reply is sent
                out.flush();
            }
        } finally {
            out.close();
            in.close();
        }
    }

    private boolean callFunctionFromClientRequest(String queryString) {

        ClientQuery query = ClientQuery.fromString(queryString);

        switch (query) {
        case RandomReview:
            randomReview(query.getQueryArgument());
            return true;

        case GetRestaurant:
            getRestaurant(query.getQueryArgument());
            return true;

        case AddRestaurant:
            addRestaurant();
            return true;

        case AddUser:
            addUser(query.getQueryArgument());
            return true;

        case AddReview:
            addReview(query.getQueryArgument());
            return true;
        }

        return false;
    }

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
