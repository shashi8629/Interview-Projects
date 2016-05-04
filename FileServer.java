

import java.io.*;
import java.rmi.*;

public class FileServer {
   public static void main(String argv[]) {
	    String homeDir= System.getenv("home.dir");
	    

	      
      if(System.getSecurityManager() == null) {
        System.setSecurityManager(new RMISecurityManager());
       // System.setProperty("java.security.policy","/User/shashi/"+"projectPolicyFile");
      }
      
  

      
     // String homeDir= System.getenv("home.dir");
      //System.setProperty("java.security.policy",homeDir+"/"+"projectPolicyFile");
      
      
      
      try {
         FileInterface fi = new FileImpl("FileServer");
         
         Naming.rebind("//127.0.0.1/FileServer", fi);
      } catch(Exception e) {
         System.out.println("FileServer: "+e.getMessage());
         e.printStackTrace();
      }
   }
}
