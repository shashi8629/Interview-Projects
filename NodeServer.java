import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;

public class NodeServer {
	
	public static void main(String argv[]) {
		   
	      if(System.getSecurityManager() == null) {
	        System.setSecurityManager(new RMISecurityManager());
	       // System.setProperty("java.security.policy","/User/shashi/"+"projectPolicyFile");
	      }
	      
	     // String homeDir= System.getenv("home.dir");
	      //System.setProperty("java.security.policy",homeDir+"/"+"projectPolicyFile");
	    
	      try {
	    	  LocateRegistry.createRegistry(1099);
	         NodeServerInterface fi = new NodeServerImpl("FileServer1");
	         Naming.rebind("//127.0.0.1/FileServer1", fi);
	      } catch(Exception e) {
	         System.out.println("FileServer: "+e.getMessage());
	         e.printStackTrace();
	      }
	   }

}
