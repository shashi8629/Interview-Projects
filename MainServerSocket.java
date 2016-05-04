package HTTP1;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
public class MainServerSocket {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
    	
    	
    	int port = 0;
		String documnetRoot = "";
		if (args.length != 4) {
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

       
        int serverPort = port;
        
        Socket clientSockets = null;

        try {
        	ServerSocket listenSocket = new ServerSocket(serverPort);
            
            while (true) {
            	 System.out.println(" in  server  "+Thread.currentThread() );
            	 clientSockets = listenSocket.accept();
                new ClientThread(clientSockets,documnetRoot).start();;
           }

        } catch (Exception e) {
            System.out.println("Exception in  server ");
        }
    }
}

