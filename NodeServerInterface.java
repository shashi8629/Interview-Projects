


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NodeServerInterface extends Remote {
	
  public byte[] downloadFile(int key) throws
  RemoteException;
  
  public  boolean   deleteFile(int key,boolean replicafile) throws
  RemoteException;
  
  
  public  String   receiveFile(byte [] fileData,String fileName, int key,boolean replicafile) throws
  RemoteException;
  
  
  public  void SendingFiles(int key1,int key2 ,String hostName,boolean replicafile) throws  RemoteException;
 
   
  public void updatereplication(int key1,int key2,boolean replicafile) throws  RemoteException;
  
  
 public String  displayKeys(String  hostname) throws  RemoteException;

public void SendingFiles(int i, int partionKeysBasedOnNewNumberofNodes, String string, boolean b, boolean c) throws  RemoteException;


public void updatefileStatus(int partionKeysBasedOnOldNumberofNodes, int partionKeysBasedOnNewNumberofNodes, boolean b) throws  RemoteException;
  
 
 
  
  
  
}