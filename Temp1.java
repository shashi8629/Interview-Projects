package HTTP1;

import java.net.*;
import java.io.*;

public class Temp1 extends Thread
{
   private ServerSocket serverSocket;
   
   public Temp1(int port) throws IOException
   {
      serverSocket = new ServerSocket(port);
      serverSocket.setSoTimeout(30000);
   }

   public void run()
   {
	   
      
    	  
    	  
         try
         {
        	 System.out.println("Waiting for client on port " +
        		      serverSocket.getLocalPort() + "...");
        		    Socket server = null;
        		   server = serverSocket.accept();
        	 
            
            String str="";
            while(!str.equals("end"))
            {
            
            server.setSoTimeout(10000);
            System.out.println(server.hashCode());
            
            System.out.println("Just connected to "
                  + server.getRemoteSocketAddress());
            
            BufferedReader  in = new BufferedReader(new InputStreamReader(server.getInputStream())); 
            str=in.readLine();
         
            System.out.println(str);
            DataOutputStream out =
                 new DataOutputStream(server.getOutputStream());
            out.writeUTF("Thank you for connecting to "
              + server.getLocalSocketAddress() + "\nGoodbye!");
            
            }
             
            
            server.close();
         }catch(SocketTimeoutException s)
         {
            System.out.println("Socket timed out!");
           // break;
         }catch(IOException e)
         {
            e.printStackTrace();
           // break;
         }
     }

   public static void main(String [] args)
   {
      int port = Integer.parseInt("9090");
      try
      {
         Thread t = new Temp1(port);
         t.start();
      }catch(IOException e)
      {
         e.printStackTrace();
      }
   }
}