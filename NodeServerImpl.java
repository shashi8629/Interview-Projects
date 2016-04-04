import java.io.*;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class NodeServerImpl extends UnicastRemoteObject
  implements NodeServerInterface {
   private String name;
   private static  Hashtable <Integer,String> fileMap;
   private static  Hashtable <Integer,String>replicaFileMap;
   
   
   
   public NodeServerImpl(String s) throws RemoteException{
      super();
      name = s;
      fileMap= new Hashtable<Integer,String>(); 
      replicaFileMap= new Hashtable<Integer,String>(); 
      
   }
  
   
   
   public byte[] downloadFile(int key){
	      try {

	        String homeDir= System.getenv("HOME");
	        File file=null;
	        
	        if(fileMap.get(key)!=null)
             file = new File(fileMap.get(key));
	        
	        if(replicaFileMap.get(key)!=null)
	             file = new File(replicaFileMap.get(key));
	        
	 
            byte buffer[] = new byte[(int)file.length()];
            BufferedInputStream input = new
            BufferedInputStream(new FileInputStream(file.getAbsolutePath()));
            input.read(buffer,0,buffer.length);
            input.close();
            return(buffer);
         } catch(Exception e){
            System.out.println("FileImpl: "+e.getMessage());
            e.printStackTrace();
            return(null);
         }
      }
   
   
   
@Override
public boolean deleteFile(int key,boolean replicafile) throws RemoteException {
	
	// we are storing the files where our server runs 
	try {
		String homeDir= System.getenv("HOME");
		
        
	File	file=null;
        //fileMap.remove(key);
        
        if(replicafile)
        {
             file = new File(replicaFileMap.get(key));
        	 replicaFileMap.remove(key);
        }
        else
        {
              file = new File(fileMap.get(key));
        	  fileMap.remove(key);
        
        }
        
        if(file.exists())
        {
        	file.delete();
        	return true;
        }
        else
        	return true;
         

     } catch(Exception e){
        System.out.println("Filedele: "+e.getMessage());
      
     }
	return false;
}

@Override
public String  receiveFile(byte [] filedata, String fileName,int key,boolean replicafile) throws RemoteException {
	// TODO Auto-generated method stub
    if(replicafile)
    	replicaFileMap.put(key, fileName);
    else
	   fileMap.put(key,fileName);
	 
	 
	 String homeDir= System.getenv("HOME");
     File file = new File(fileName);
     try
     {
    	 
    	if(!file.exists())
    	{
    		file.createNewFile();
    	}
    	else
    	{
    		file.delete();
    		file.createNewFile();
    	}
    	
       BufferedOutputStream output = new
       BufferedOutputStream(new FileOutputStream(file.getAbsolutePath()));
       output.write(filedata,0,filedata.length);
       output.flush();
       output.close();
       
      
 
  } catch(Exception e){
     System.out.println("FileImpl Receive Sever : "+e.getMessage());
     e.printStackTrace();
     return(null);
  }
	
	return  fileName;
}








@Override
synchronized public void SendingFiles(int key1,int key2,String hostName,boolean replicafile ) throws RemoteException {
	     
	//int [] key = new int [x1-x2];
	// for(int j=x1,p=0;j<x2;j++,p++)
	// {
		 //convert keys to filename  ..compulsory 
		// transfer  files  which is represented by keys to server  (i-1)  from  (i) 
		// key [p]=j; 
	 //}
	
	 
   // System.out.println("actualHostName "+ hostName +" key "+ key);
	
	try
	{
	int count=0;
	
	
	if(!replicafile)
	{
	for(int i=key1;i<key2;i++)
	{
		if(fileMap.get(i)!=null)
		{
        String name1 = "//" + hostName + "/FileServer1";
        NodeServerInterface ni = (NodeServerInterface) Naming.lookup(name1);
        File file = new File(fileMap.get(i));
        byte buffer[] = new byte[(int)file.length()];
        BufferedInputStream input = new
        BufferedInputStream(new FileInputStream(fileMap.get(i)));
        input.read(buffer,0,buffer.length);
        input.close(); 
        String filename= ni.receiveFile(buffer, fileMap.get(i),i,false);
        System.out.println(" filename "+filename);
        count++;
        fileMap.remove(i);
		} 
		//delete is required 
		
		}
       if(count>0)
       {
    	  System.out.println("  number of file"+ count +" stored  in system"+ hostName); 
       }
    	   
       else
       {
    	   System.out.println("   no file is transferred"); 
    	   
       }
       
	} 
	
	else
	{
		
		for(int i=key1;i<key2;i++)
		{
			
			
			if(replicaFileMap.get(i)!=null)
			{
	        String name1 = "//" + hostName + "/FileServer1";
	        NodeServerInterface ni = (NodeServerInterface) Naming.lookup(name1);
	        File file = new File(replicaFileMap.get(i));
	        byte buffer[] = new byte[(int)file.length()];
	        BufferedInputStream input = new
	        BufferedInputStream(new FileInputStream(replicaFileMap.get(i)));
	        input.read(buffer,0,buffer.length);
	        input.close(); 
	        String filename= ni.receiveFile(buffer, replicaFileMap.get(i),i,true);
	        System.out.println(" filename "+filename);
	        count++;
	        replicaFileMap.remove(i);
			} 
			//delete is required 
			}
	       if(count>0)
	       {
	    	  System.out.println("  replica number of file"+ count +" stored  in system"+ hostName); 
	       }
	    	   
	       else
	       {
	    	   System.out.println("   no replica  file is transferred"); 
	    	   
	       }
		
	}
      
	}
	catch(Exception e1)
	{
		
		System.out.println(" exception in sending files ");
		e1.printStackTrace();
	}
	
	// TODO Auto-generated method stub
	
}


@Override
 public void updatereplication(int key1, int key2, boolean replicafile)  throws RemoteException{
	// TODO Auto-generated method stub
	
	
	if(replicafile)
	{
	System.out.println("   update status ");

		for(int i=key1;i<key2;i++)
		{
			//System.out.println(" filemap "+ fileMap.get(i));
			if(fileMap.get(i)!=null)
			{
				replicaFileMap.put(i,fileMap.get(i));
				
				//fileMap.remove(i);
				
				System.out.println("   update status "+  i);
		}
		
	}
	}
	else
	{
		
		for(int i=key1;i<key2;i++)
		{
			//System.out.println(" filemap "+ fileMap.get(i));
			if(replicaFileMap.get(i)!=null)
			{
				fileMap.put(i,replicaFileMap.get(i));
				
				//fileMap.remove(i);
				
				System.out.println("   update status "+  i);
		}
		
	}
		
	}
		
		
}
	




@Override
public String displayKeys(String  hostname) throws RemoteException {
	// TODO Auto-generated method stub
	
	System.out.println(" List of  orginal  keys  in  " +  hostname);
	
   Set<Entry<Integer, String>>  s= fileMap.entrySet();
   
  Iterator<Entry<Integer, String>> it = s.iterator();
  
  String finalString="";
   while(it.hasNext())
   {
	   
	  Map.Entry<Integer, String> temp=it.next();
	  
	  finalString=finalString+"  KEY OF FILE   " + temp.getKey() + "  ORIGINAL FILE NAME "  + temp.getValue()+"\n";
	  
	   System.out.println("  key " + temp.getKey() + "  ORIGINAL FILE NAME   "  + temp.getValue() );
   }
    
  System.out.println(" List of replicated keys  in  "+ hostname);
 
  Set<Entry<Integer, String>>  s1= replicaFileMap.entrySet();
  
  Iterator<Entry<Integer, String>> it1 = s1.iterator();
  
  while(it1.hasNext())
  {  
	  Map.Entry<Integer, String> temp=it1.next();
	  
	  finalString=finalString+"  KEY OF FILE      " + temp.getKey() + "  DUPLICATE FILE NAME   "  + temp.getValue()+"\n";
	  
	  System.out.println("  key " + temp.getKey() + " file  name  "  + temp.getValue() );
  }
   
  
  return finalString;
  
	
}



@Override
public void SendingFiles(int key1, int key2, String hostName, boolean replicafilem, boolean original)
		throws RemoteException {
	

	// TODO Auto-generated method stub
	try
	{
	int count=0;
	// convert orginal file to replica file 
	if(original)
	{
	for(int i=key1;i<key2;i++)
	{

		if(fileMap.get(i)!=null)
		{
        String name1 = "//" + hostName + "/FileServer1";
        NodeServerInterface ni = (NodeServerInterface) Naming.lookup(name1);
        File file = new File(fileMap.get(i));
        byte buffer[] = new byte[(int)file.length()];
        BufferedInputStream input = new
        BufferedInputStream(new FileInputStream(fileMap.get(i)));
        input.read(buffer,0,buffer.length);
        input.close(); 
        String filename= ni.receiveFile(buffer, fileMap.get(i),i,true);
        // here true for replica file 
        System.out.println(" filename "+filename);
        count++;
        //fileMap.remove(i);
		} 
		//delete is required 
		}
       if(count>0)
       {
    	  System.out.println("  number of file"+ count +" stored  in system"+ hostName); 
       }
	
  }
	
	else
	{
		
		// convert replica  file to  original file 
		
		for(int i=key1;i<key2;i++)
		{

			if(replicaFileMap.get(i)!=null)
			{
	        String name1 = "//" + hostName + "/FileServer1";
	        NodeServerInterface ni = (NodeServerInterface) Naming.lookup(name1);
	        File file = new File(replicaFileMap.get(i));
	        byte buffer[] = new byte[(int)file.length()];
	        BufferedInputStream input = new
	        BufferedInputStream(new FileInputStream(replicaFileMap.get(i)));
	        input.read(buffer,0,buffer.length);
	        input.close(); 
	        String filename= ni.receiveFile(buffer, replicaFileMap.get(i),i,false);
	        System.out.println(" filename "+filename);
	        count++;
	             //fileMap.remove(i);
			} 
			//delete is required 
			
			}
	       if(count>0)
	       {
	    	  System.out.println("  number of file"+ count +" stored  in system"+ hostName); 
	       }
		
	  }
	
	
	}catch(Exception e1 )
	{
		
		System.out.println(" exception ");
	}


}



@Override
public void updatefileStatus(int key1, int key2,boolean replicafile)  throws RemoteException {
	
	
	if(replicafile)
	{
	System.out.println("   update status ");

		for(int i=key1;i<key2;i++)
		{
			//System.out.println(" filemap "+ fileMap.get(i));
			if(fileMap.get(i)!=null)
			{
				replicaFileMap.put(i,fileMap.get(i));
				
				fileMap.remove(i);
				
				System.out.println("   update status "+  i);
		}
		
	}
	}
	else
	{
		
		for(int i=key1;i<key2;i++)
		{
			//System.out.println(" filemap "+ fileMap.get(i));
			if(replicaFileMap.get(i)!=null)
			{
				fileMap.put(i,replicaFileMap.get(i));
				
				replicaFileMap.remove(i);
				
				System.out.println("   update status "+  i);
		}
		
	}
		
	}
		
	
	// TODO Auto-generated method stub
	
}




}




