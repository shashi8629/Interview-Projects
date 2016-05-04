
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class SocketClient {

	 public static void main(String arg[]){
		 
		 
		 

		 
	 }
  

    public static  void hearbeat()
    {
    	
   
    	  String hostname =null;
    	   int port=0;
    	  Socket socketClient=null;
    	
        //Creating a SocketClient object
       String [] str= new String [4];
       str[0]="129.210.16.134";
       str[1]="129.210.16.150";
       str[2]="129.210.16.148";
     
        try {
        	
        	while(true)
        	{
        	
           Thread.sleep(5000);	
        	for(int i=0;i<3;i++)
        	{
        		
        		hostname=str[i];
        		port=9990;
        		socketClient = new Socket(hostname,port);
        		
        		String userInput;
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
                while ((userInput = stdIn.readLine()) != null) {
                    System.out.println(userInput);
                }
        	}
        	
        	}
        	//socketClient.close();
        

        } catch (UnknownHostException e) {
            System.err.println("Host unknown. Cannot establish connection");
        } catch (IOException e) {
            System.err.println("Cannot establish connection. Server may not be up."+e.getMessage());
        }catch (Exception e) {
			// TODO: handle exception
		}{
        	
        }
    }
}
