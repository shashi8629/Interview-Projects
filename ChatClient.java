
import java.net.*;
import java.io.*;

public class ChatClient
{  private Socket socket              = null;
   private BufferedReader  console   = null;
   private BufferedWriter streamOut = null;
   
   public ChatClient(String serverName, int serverPort)
   {  System.out.println("Establishing connection. Please wait ...");
      try
      {  socket = new Socket(serverName, serverPort);
         System.out.println("Connected: " + socket);
         start();
      }
      catch(UnknownHostException uhe)
      {  System.out.println("Host unknown: " + uhe.getMessage());
      }
      catch(IOException ioe)
      {  System.out.println("Unexpected exception: " + ioe.getMessage());
      }
      String line = "";
      while (!line.equals(".bye"))
      {  try
     
         {
    	  Thread.sleep(1000);
    	 // Runtime.getRuntime().exec("python  ");
    	  
    	  line = console.readLine();
            streamOut.write(line);
            streamOut.flush();
         }
         catch(Exception ioe)
         {  System.out.println("Sending error: " + ioe.getMessage());
         }
      }
   }
   
 
   
   
   public void start() throws IOException
   {  console   = new BufferedReader(new InputStreamReader(System.in));
      streamOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
   }
   
   
   public void stop()
   {  try
      {  if (console   != null)  console.close();
         if (streamOut != null)  streamOut.close();
         if (socket    != null)  socket.close();
      }
      catch(IOException ioe)
      {  System.out.println("Error closing ...");
      }
   }
   
   
   public static void main(String args[])
   { 
	   ChatClient client = null;
     // if (args.length != 2)
         //System.out.println("Usage: java ChatClient host port");
      //else
         client = new ChatClient("127.0.0.1",8888);
   }
}

