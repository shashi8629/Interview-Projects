
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Hashtable;

public interface FileInterface extends Remote {
	
  public String downloadFile(String fileName) throws
  RemoteException;
  
  public  String  deleteFile(String fileName) throws
  RemoteException;
  
  
  public  String   receiveFile(String fileName) throws
  RemoteException;
  
  
   public String addServer(String hostName)throws
   RemoteException;
  
   
   public String  deleteServer(String hostName) throws
   RemoteException;
  
   public  Hashtable<String ,Integer>  heartBeatChecking( )  throws
   RemoteException;
  
  
}