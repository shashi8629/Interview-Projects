


import java.io.*;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;

public class FileServer {
   public static void main(String argv[]) {
	    
	  
      if(System.getSecurityManager() == null) {
        System.setSecurityManager(new RMISecurityManager());
       // System.setProperty("java.security.policy","/User/shashi/"+"projectPolicyFile");
      }
      
     // String homeDir= System.getenv("home.dir");
      //System.setProperty("java.security.policy",homeDir+"/"+"projectPolicyFile");
     
      try {
    	  LocateRegistry.createRegistry(1099);
         FileInterface fi = new FileImpl("FileServer");
         Naming.rebind("//127.0.0.1/FileServer", fi);
      } catch(Exception e) {
         System.out.println("FileServer: "+e.getMessage());
         e.printStackTrace();
      }
   }
}
