package HTTP1;


import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

class WebService  { 
	 //static final int PORT = 1040;  // just for demo
	 Handler handler = new Handler();
	 
	
	 public void run(int port,String documnetRoot ) { 
	  try { 
	   ServerSocket socket = new ServerSocket(port);
	   
	   System.out.println("Waiting For the Client .......");
	   
	   for (;;) {
		   
	    final Socket connection = socket.accept();
	  
	   
	  System.out.println(" connected socket "+ connection.hashCode());
	  connection.setSoTimeout(300000);
	   ArrayList< Socket> socketlist= new ArrayList<Socket>();
	   socketlist.add(connection);
	  
	   
	   
	    
	    new Thread(new Runnable() {
	     public void run() {
	    	 //System.out.println(" hello world"+ Thread .currentThread());
	      handler.process(connection,documnetRoot);
	     }}).start();
	    
	  }
	   
	   
	  }
	  catch(Exception e) { 
		  System.out.println(" Error in server side ");
		  
	  } // die
	 }

	 public static void main(String[ ] args) {
	  //new Thread(new WebService()).start();
		 
         System.out.println(" Main Thread id "+ Thread.currentThread());
		 int  port = 0;
			String documnetRoot = "";
			 if(args.length !=4)
			 {
				System.out.println(" provide alll the  cmd arguments ");
				 System.exit(1); 
				 System.out.println(" exit  ");
			 }
			 
			for (int i = 0; i < args.length; i++) {
				if (args[i].trim().equals("-document_root"))
					documnetRoot = args[i + 1].trim();
				else if (args[i].trim().equals("-port"))
					port = Integer.parseInt(args[i + 1].trim());
				else {

				}
					

			}
			
			//System.out.println(" port no "+ port);	
			//System.out.println(" documnetRoot "+ documnetRoot);
		 
		 new WebService().run(port,documnetRoot);
		 
		 
	 }

	}