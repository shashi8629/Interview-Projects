package HTTP1;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.StringTokenizer;

class Handler {
	
	public static  boolean IsfilePermission(String directoryPath, String fileName) {
		return new File(directoryPath + fileName).exists()
				? new File(directoryPath + fileName).canRead() : false;

	}

	void process(Socket s,String documnetRoot) {
		
		//System.out.println(" in  real code ");
		BufferedReader in = null;
		PrintStream out = null;
		//System.out.println("  doc root "+documnetRoot);
		BufferedReader bfr=null;
		
		
		try {
			//in = new DataInputStream(s.getInputStream());
		    in = new BufferedReader(new InputStreamReader(s.getInputStream())); 
			out = new PrintStream(s.getOutputStream());
			//System.out.println(" input file name");
			
			  String s1=in.readLine();
		      System.out.println("fist line"+s1);  
			
			  String filename="";
		      
		      String []  st= s1.split("\\s+");
		      

		        // Parse the filename from the GET command
		       
		        	if(st[0]!=null&&st[1]!=null)
		        	{
		        	
		         if(st[0].trim().equalsIgnoreCase("GET") )
		          {
		          filename=st[1].trim();
		          //System.out.println(" ss 1");
		          }
		         }
		        	
		        
		        if (filename.endsWith("/"))
		        {
		          filename+="/index.html";
		          //System.out.println(" ss"+filename);
		        }

		       /*( else 
		        {
		        // Remove leading / from filename
		        while (filename.indexOf("/")==0)
		          filename=filename.substring(1);
		            
		        }
		       */
		        
		        String line;
	            line = "";
	            while ((line = in.readLine()) != null && (line.length() != 0)) {
	                System.out.println(line);
	               /* if (line.indexOf("Content-Length:") > -1) {
	                    postDataI = new Integer(line
	                            .substring(
	                                    line.indexOf("Content-Length:") + 16,
	                                    line.length())).intValue();
	                }
	                */
	            }
		       
		   // System.out.println(" filename  "+ filename);
            System.out.println(" Thread id "+ Thread.currentThread());
			//System.out.println(in.readUTF());
			//String outmsg = "";

			System.out.println(" complete file path "+documnetRoot+File.separator +filename);

			File file= new File(documnetRoot+File.separator +filename);
		    if(file.exists())
		    {
		    	
		    	System.out.println(" file exists");
		    	
		    	if(IsfilePermission(documnetRoot+File.separator , filename))
		    	{
			/*bfr = new BufferedReader(
					new InputStreamReader (new FileInputStream(file.getAbsolutePath())));
			
			String str = "";
			StringBuilder strb = new StringBuilder();
			while ((str = bfr.readLine()) != null) {
				strb.append(str);
				strb.append(System.getProperty("line.seprator"));

			}
			
			bfr.close();
			*/
	        String mimeType="text/plain";
	        if (filename.endsWith(".html") || filename.endsWith(".htm"))
	          mimeType="text/html";
	        else if (filename.endsWith(".jpg") || filename.endsWith(".jpeg"))
	          mimeType="image/jpeg";
	        else if (filename.endsWith(".gif"))
	          mimeType="image/gif";
	        else if (filename.endsWith(".class"))
	          mimeType="application/octet-stream";
	        
	        long Content_Length=file.length();
	      
	        out.print("HTTP/1.0 200 OK\r\n"+
	                "Content-type: "+mimeType+"\r\n"+"Content-Length:"+Content_Length+"bytes"+"\r\n"+"Date:"+new Date()+"\r\n\r\n");
	        
	        //out.print(strb.toString());
	        BufferedInputStream f= new BufferedInputStream(new FileInputStream(file.getAbsolutePath()));
	        
	              // Send file contents to client, then close the connection
	           byte[] a=new byte[4096];
	              int n;
	              while ((n=f.read(a))>0)
	                out.write(a,0,n);
	              
	              
	              
	              
	             // out.close();
	       // httpResponse="HTTP/1.0 200 OK\r\n"+
	         // "Content-type: "+mimeType+"\r\n\r\n";
	        		//"Content-Length:"+Content_Length+"bytes"+"\r\n"+"Date"+new Date()+"\r\n\r\n";
			//outmsg = httpResponse+strb.toString();
			//System.out.println(outmsg+"\n");
			//out.println(outmsg+"\n");
			//out.flush();
			
		    }
		    
		    else
		    {
		    	
		    	System.out.println(" file don't have permission   exists");
		    	 out.println("HTTP/1.0 403 Forbidden \r\n"+
		    	          "Content-type: text/html\r\n\r\n"+
		    	          "<html><head></head><body>"+filename+" is not accessbale </body></html>\n");
		    	      
		    }
		    }
		    else
		    {
		    	
		    	System.out.println(" file does not  exists");

		    	 out.println("HTTP/1.0 404 Not Found\r\n"+
		    	          "Content-type: text/html\r\n\r\n"+
		    	          "<html><head></head><body>"+filename+" not found</body></html>\n");
		    	       
		    	
		    }
			
			
			// int result = -request;
			// return negation to client
			// out.writeInt(result);
		} catch (Exception ex) {
			
			out.println("HTTP/1.0 400 Bad Request\r\n"+
	    	          "Content-type: text/html\r\n\r\n"+
	    	          "<html><head></head><body> Bad Http request </body></html>\n");
	    	       
			
			System.out.println(" exception in server thread ");
		} // fall through

		finally { // clean up
			try {
				if (in != null)
					in.close();
			} catch (IOException ignore) {
			}
			try {
				if (out != null)
					out.close();
			} catch (Exception ignore) {
			}
			try {
				s.close();
			} catch (IOException ignore) {
			}
			try
			{
				if(bfr!=null)
					bfr.close();
				
			}
			catch(Exception e1)
			{
				
			}
		}
	}
}
