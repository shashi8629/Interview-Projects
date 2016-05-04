

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.IntBuffer;

/**
 * 
 */
/**
* A simple socket server
* @author faheem
*
*/
public class SocketServer {
    
    private ServerSocket serverSocket;
    private int port;
    
    public SocketServer(int port) {
        this.port = port;
    }
    
    public void start() throws Exception {
        System.out.println("Starting the socket server at port:" + port);
        serverSocket = new ServerSocket(port);
        
        //Listen for clients. Block till one connects
        
        while(true)
        {
        	
        System.out.println("Waiting for clients...");
        Socket client = serverSocket.accept();
        
        //A client has connected to this server. Send welcome message
        sendWelcomeMessage(client);
        }
        
        
    }
    
    private void sendWelcomeMessage(Socket client) throws Exception {
    
    	 Process p = Runtime.getRuntime().exec("python  /Users/shashi/Documents/workspace_pyck/Assign1/src/t1.py");
    	 p.waitFor();
    	 BufferedReader bfr =  new BufferedReader (new InputStreamReader(p.getInputStream()));
    	 String str="";
    	 StringBuilder strb = new StringBuilder(); 
    	 while((str=bfr.readLine())!=null)
    	 {
    		 strb.append(str); 
    	 }
    	 
  
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        writer.write(strb.toString());
        writer.flush();
        writer.close();
    }
    
    /**
    * Creates a SocketServer object and starts the server.
    *
    * @param args
    */
    public static void main(String[] args) {
        // Setting a default port number.
        int portNumber = 9990;
        
        try {
            // initializing the Socket Server
            SocketServer socketServer = new SocketServer(portNumber);
            socketServer.start();
            
            } catch (Exception e) {
            e.printStackTrace();
        }
    }
}