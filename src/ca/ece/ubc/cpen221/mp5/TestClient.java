package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class TestClient {
        
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    
    
    /**
     * Make a TestClient and connect it to a server running on
     * hostname at the specified port.
     * @throws IOException if can't connect
     */
    public TestClient(int port, String hostname) throws IOException {
        socket = new Socket(hostname, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
    }
    
    /**
     * Use a RestaurantDBServer to respond to main argument requests.
     * @param port
     * @param hostName
     * @param queries
     */
    public static void main(String[] args) {
        try {
            
            TestClient client = new TestClient(Integer.parseInt(args[0]), args[1]);
            
            for (int i = 2; i < args.length; i++) {
                client.sendRequest(args[i]);
                System.out.println("query sent: " + args[i]);
            }
            
            for (int i = 2; i < args.length; i++) {
                String reply = client.getReply();
                System.out.println("Query: " + args[i] + ",\n \t Result: " + reply);
            }
            
            client.close();
            
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
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
    
}
