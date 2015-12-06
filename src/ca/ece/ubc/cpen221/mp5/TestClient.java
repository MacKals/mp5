package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TestClient {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    
    
    /**
     * Make a TestClient and connect it to a server running on
     * hostname at the specified port.
     * @throws IOException if can't connect
     */
    public TestClient(String hostname, int port) throws IOException {
        socket = new Socket(hostname, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
    }
    
    /**
     * Send a request to the server. Requires this is "open".
     * @param query to find Function(x)
     * @throws IOException if network or server failure
     */
    public void sendRequest(String query) throws IOException {
        out.print(query + "\n");
        out.flush(); // important! make sure query actually gets sent
    }
    

    /**
     * Get a reply from the next request that was submitted.
     * Requires this is "open".
     * @return the requested query number
     * @throws IOException if network or server failure
     */
    public String getReply() throws IOException {
        String reply = in.readLine();
        if (reply == null) {
            throw new IOException("connection terminated unexpectedly");
        }
        return reply;
    }
    

    /**
     * Closes the client's connection to the server.
     * This client is now "closed". Requires this is "open".
     * @throws IOException if close fails
     */
    public void close() throws IOException {
        in.close();
        out.close();
        socket.close();
    }
    
    private static final int PORT = 4949;
    private static final int N = 1;
    
    /**
     * Use a FibonacciServer to find the first N query numbers.
     */
    public static void main(String[] args) {
        try {
            TestClient client = new TestClient("localhost", PORT);

            List<String> queries = new ArrayList<>();
            
            
            //add to list
            queries.add("randomReview(\"La Val's Pizza\")");
//            queries.add("getRestaurant(\"4D7IdtyRjH8qxcsHaz1-GA\")");
//            queries.add("addRestaurant(\"\")");
//            queries.add("in(\"Telegraph Ave\") && (category(\"Chinese\") || category(\"Italian\")) && price(1..2)");
    

            
            // send the requests to find the first N query numbers
            for (String query : queries) {
                client.sendRequest(query);
                System.out.println("query: " + query + "= ?");
            }
            
            // collect the replies
            for (String query : queries) {
                String reply = client.getReply();
                System.out.println("query(" + query + ") = " + reply);
            }
            
            client.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    
}
