

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ClientThread extends Thread {
	


    private Socket clientSocket;
    private  String documnetRoot;
    
   

    public ClientThread(Socket client, String documnetRoot) {
        clientSocket = client;
        this.documnetRoot=documnetRoot;
       
    }

    public void run() {
    	
    
    	
    	
		
    	
    	 long startTime=System.currentTimeMillis();
    	 
    	//System.out.println(" in  run " + Thread.currentThread());
    	
    	 
    	Scanner in=null;
    	OutputStream out=null;
    	 PrintStream pw=null;
    	try
    	{
         in = new Scanner(clientSocket.getInputStream());
         out = clientSocket.getOutputStream();
         pw = new  PrintStream(out);
         boolean keepAlive = false;

        do {
            
        	String requestLine = in.nextLine();
            String line;
            String connection = "";
            keepAlive = false;

            //System.out.println(" within first while loop  " );
            System.out.println("  socket hascode "+clientSocket.hashCode() +"  Thread id   "+ Thread.currentThread());
            
            do {
                line = in.nextLine();
                 //System.out.println(" line "+ line );
                if(line ==null )
                	break;
                if (line.trim().length() == 0) {
                    break;
                }
                if (line.startsWith("Connection:") )
                		{
                    connection = line.substring(11).trim();
                   // System.out.println(" connection "+ connection );

                }
            }
            while (true);
            //System.out.println(" outside first while loop " );
            String [] tokens=requestLine.split("\\s+");
            int  inputlength = tokens.length;
            
            if (inputlength <3) {
                pw.println("HTTP/1.0 400 Bad Request");
                pw.println("Connection: close");
                pw.println("");
                pw.flush();
                continue;
            }

           // String httpMethod = line.substring(0, idx);
            String httpMethod =tokens[0];
            System.out.println(" httpMethod "+ httpMethod );
            //line = line.substring(idx+1);

            //idx = line.indexOf(" ");
            
            
            inputlength = tokens.length;
            
            if (inputlength < 3) {
                pw.println("HTTP/1.0 400 Bad Request");
                pw.println("Connection: close");
                pw.println("");
                pw.flush();
                continue;
            }

           // String httpVersion = line.substring(idx+1);
            String httpVersion=tokens[2];
            System.out.println("httpVersion "+httpVersion);
            
            if (!httpVersion.equals("HTTP/1.0") && !httpVersion.equals("HTTP/1.1")) {
                pw.println("HTTP/1.0 505 HTTP Version Not Supported");
                pw.println("Connection: close");
                pw.println("");
                pw.flush();
                continue;
            }

            if (connection != "") {
                keepAlive = connection.equalsIgnoreCase("keep-alive");
            }
            else if (httpVersion.equals("HTTP/1.1")) {
                keepAlive = true;
            }
            else {
                keepAlive = false;
            }

            //String filePath = line.substring(0, idx);
            
            String filePath = tokens[1];
            
            if(filePath.trim().equals("/"))
            {
            	filePath="index.html";
            }
            
            else  if (filePath.startsWith("/")) {
                filePath = filePath.substring(1);
            }
            
            filePath=documnetRoot+File.separator+filePath;
            
            System.out.println(" filepath "+filePath);
            // open the file and read it
            File f = new File(filePath);
            
            String mimeType="text/plain";

            if (!f.exists()) {
            	System.out.println(" file does not exist ");
                pw.println(httpVersion + " 404 Not Found");
                pw.println("Content-Length: 0");
                if (keepAlive) {
                    pw.println("Connection: keep-alive");
                } else {
                    pw.println("Connection: close");
                }
                pw.println("");
                pw.flush();
                continue;
            }
            else
            {
            	
            	 
     	        if (filePath.endsWith(".html") || filePath.endsWith(".htm"))
     	          mimeType="text/html";
     	        else if (filePath.endsWith(".jpg") || filePath.endsWith(".jpeg"))
     	          mimeType="image/jpeg";
     	        else if (filePath.endsWith(".gif"))
     	          mimeType="image/gif";
     	        else if (filePath.endsWith(".class"))
     	          mimeType="application/octet-stream";
     	        
            }

            if (!(httpMethod.equals("GET"))) {
            	System.out.println(" GET Method is not present  ");
            	
                pw.println(httpVersion +" 405 Method Not Allowed");
                pw.println("Allow: GET");
                if (keepAlive) {
                    pw.println("Connection: keep-alive");
                } else {
                    pw.println("Connection: close");
                }
                pw.println("");
                pw.flush();
                continue;
            }
            
           
	        

            pw.println(httpVersion + " 200 OK");
            pw.println("Content-Type:"+mimeType);
            pw.print("Content-Length: ");
            pw.println(f.length());
            if (keepAlive) {
                pw.println("Connection: keep-alive");
            } else {
                pw.println("Connection: close");
            }
            pw.println("");
            pw.flush();

            FileInputStream fis = new FileInputStream(f);
            PrintStream bw = pw;
            byte[] buffer = new byte[1024];
            int buflen;

            while ((buflen = fis.read(buffer)) > 0) {
                bw.write(buffer, 0, buflen);
                bw.flush();
            }
            
            long  diff =System.currentTimeMillis() - startTime;
            int  MaximMumAliveTimeTimeForThread=10000;
            
            if(diff>  MaximMumAliveTimeTimeForThread/Thread.activeCount())
            	keepAlive=false;
            
          
        	
            
            //System.out.println(" Keepalive  " +keepAlive);

        }
        while (keepAlive);
        
        System.out.println("  socket hascode "+clientSocket.hashCode() +"  Thread id   "+ Thread.currentThread());

        
        clientSocket.close();
    	}catch(Exception e1)
    	{
    		
    		System.out.println("  socket time out  ");
    		
    		//System.out.println( Calendar.getInstance().getTime().getTime()- d1.getTime() +"");
    		//e1.printStackTrace();
    		
    	}
    	
    	finally { // clean up
			try {
				if (in != null)
					in.close();
			} catch (Exception ignore) {
			}
			try {
				if (out != null)
					out.close();
			} catch (Exception ignore) {
			}
			try {
				
				clientSocket.close();
				
			} catch (IOException ignore) {
			}
			try
			{
				if(pw!=null)
					pw.close();
				
			}
			catch(Exception e1)
			{
				
			}
		}
    }
}