import java.rmi.Naming;

public class ServerFunctionality {
	
	public static String   addServer(String hostName, String hostname2)
	 {
		
		String status=null;
		
		      try {
		         String name = "//" + hostName + "/FileServer";
		         FileInterface fi = (FileInterface) Naming.lookup(name);
		          status = fi.addServer(hostname2);
		         if(status!=null)
		         System.out.println(" SERVER  "+ status.split(":")[1]+" IS ADDED  .DETAILS  "+ status.split(":")[0]);
		         else
		          System.out.println( " null ");
		        	 
		    
		      } catch(Exception e) {
		         System.err.println(" add node Server exception: "+ e.getMessage());
		         e.printStackTrace();
		      }
		   
		      
		      return status;
		 
	 }

	 
	public static String   deletServer(String hostName,String hostname2)
	 {
		 
		String status=null;
	
		      try {
		    	  
		    	  String name = "//" + hostName + "/FileServer";
			         FileInterface fi = (FileInterface) Naming.lookup(name);
			          status = fi.deleteServer(hostname2);
			         if(status!=null)
			          System.out.println(" SERVER IS  "+ status.split(":")[1]+"  REMOVED .DETAILS  "+ status.split(":")[0]);
			         else
			          System.out.println( " null ");
		        	 
		    
		      } catch(Exception e) {
		         System.err.println("FileServer exception: "+ e.getMessage());
		         e.printStackTrace();
		      }
		   
		      return status;
	 }
}
