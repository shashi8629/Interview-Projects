import java.net.*;
import java.io.*;
public class ChatServer1 implements Runnable
	{  
	  private ServerSocket     server = null;
	   private Thread           thread = null;
	   private ChatServerThread1 client = null;

	   
	   
	   public ChatServer1(int port)
	   {  try
	      {  System.out.println("Binding to port " + port + ", please wait  ...");
	         server = new ServerSocket(port);  
	         System.out.println("Server started: " + server);
	         start();
	      }
	      catch(IOException ioe)
	      { 
	    	  System.out.println(ioe); 
	      }
	   }
	   
	   
	   public void run()
	   {  
		   
		   while (thread != null)
	      {  try
	         {  System.out.println("Waiting for a client ..."); 
	            addThread(server.accept());
	         }
	         catch(IOException ie)
	         {  System.out.println("Acceptance Error: " + ie); }
	      }
	   }
	   
	   
	   
	   public void addThread(Socket socket)
	   {  System.out.println("Client accepted: " + socket);
	      client = new ChatServerThread1(this, socket);
	      try
	      {  client.open();
	         client.start();
	      }
	      catch(IOException ioe)
	      {  System.out.println("Error opening thread: " + ioe); }
	   }
	   public void start()                   { /* no change */ 
		   
		   if (thread == null)
		      {  thread = new Thread(this); 
		         thread.start();
		      }
		   
		   
	   }
	   public void stop()                    { /* no change */ 
		   
		   if (thread != null)
		      { // thread.stop(); 
		         thread = null;
		      }
	   }
	   
	   public static void main(String args[]){ /* no change */ 
			ChatServer1 server = null;
		    //  if (args.length != 1)
		      //   System.out.println("Usage: java ChatServer port");
		     // else
		         server = new ChatServer1(8888);
	   }
	}


	
 class ChatServerThread1 extends Thread
	{  private Socket          socket   = null;
	   private ChatServer1      server   = null;
	   private int             ID       = -1;
	   private DataInputStream streamIn =  null;

	   public ChatServerThread1(ChatServer1 _server, Socket _socket)
	   {  server = _server;  socket = _socket;  ID = socket.getPort();
	   }
	   
	   public void run()
	   {  System.out.println("Server Thread " + ID + " running.");
	      while (true)
	      {  try
	         {  System.out.println(streamIn.readUTF());
	         }
	         catch(IOException ioe) {  }
	      }
	   }
	   public void open() throws IOException
	   {  streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
	   }
	   public void close() throws IOException
	   {  if (socket != null)    socket.close();
	      if (streamIn != null)  streamIn.close();
	   }

	

}
