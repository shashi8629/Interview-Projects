
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
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
import java.util.Vector;

public class FileImpl extends UnicastRemoteObject
  implements FileInterface {

   private String name;
   private static  Vector <String> hostNameList;
   private static  Hashtable<String, Integer> heartBeatTable;
   
   private  static final int  NumberOfKeys=102197;
  
   public FileImpl(String s) throws RemoteException{
      super();
      name = s;
      hostNameList= new Vector<String>();
      heartBeatTable= new Hashtable<String,Integer>();
      
   }
  
   public String downloadFile(String fileName){
	      try {
	           int hashKey=  HashinAlgorith.hascodegenerator(fileName);
	           int NumberOfNodes= hostNameList.size();
	           int partionKeys=  NumberOfKeys/NumberOfNodes;
	           boolean keystatus=false;
	           int nodeNumber=-1;
	           
	           if(NumberOfNodes>1)
	               for(int i=1;i<=NumberOfNodes;i++)
	               {
	              	 if(hashKey<partionKeys*i)
	              	 {
	              		
	              		 nodeNumber=(i-1);
	              		 keystatus=true;
	              		 break;
	              	 } 
	               }
	              
	              else
	              {
	              	 nodeNumber=0;
	              	 keystatus=true;
	              	
	              }
	           
	           
	            if(keystatus)
	            {
	            	//if(hostNameList.size()==1)		
	            	 //return hostNameList.get(nodeNumber)+":"+hashKey+":"+hostNameList.get(nodeNumber);
	            	 //else
	            	 return hostNameList.get(nodeNumber)+":"+hashKey+":"+hostNameList.get((nodeNumber+1)%hostNameList.size());
	         
	            }
	            else
	            {
	           	 return null;
	            }
	       
	    	  
         } catch(Exception e){
            System.out.println("FileImpl: "+e.getMessage());
            e.printStackTrace();
            return(null);
         }
      }
   
@Override
public String  deleteFile(String fileName) throws RemoteException {
	
	try {
	
        int hashKey=  HashinAlgorith.hascodegenerator(fileName);
        int NumberOfNodes= hostNameList.size();
        int partionKeys=  NumberOfKeys/NumberOfNodes;
        boolean keystatus=false;
        int nodeNumber=-1;
        
        if(NumberOfNodes>1)
            for(int i=1;i<=NumberOfNodes;i++)
            {
           	 if(hashKey<partionKeys*i)
           	 {
           		
           		 nodeNumber=(i-1);
           		 keystatus=true;
           		 break;
           	 }
           	
            }
           
           else
           {
           	 nodeNumber=0;
           	 keystatus=true;
           	 
           	
           }
     
      
         if(keystatus)
         {
        	 
        	 //if(hostNameList.size()==1)		
            	 //return hostNameList.get(nodeNumber)+":"+hashKey+":"+hostNameList.get(nodeNumber);
            	// else
            	 return hostNameList.get(nodeNumber)+":"+hashKey+":"+hostNameList.get((nodeNumber+1)%hostNameList.size());
         
         }
         else
         {
        	 return null;
         }
    

     } catch(Exception e){
        System.out.println("Filedele: "+e.getMessage());
        return null;
      
     }
	
}

@Override
public String  receiveFile( String fileName) throws RemoteException {
	// TODO Auto-generated method stub
	
      String homeDir= System.getenv("HOME");
     File file = new File(fileName);
     try
     {
     
        int hashKey=  HashinAlgorith.hascodegenerator(fileName);
        int NumberOfNodes= hostNameList.size();
        int partionKeys=  NumberOfKeys/NumberOfNodes;
        boolean keystatus=false;
        int nodeNumber=-1;
        
        if(NumberOfNodes>1)
            for(int i=1;i<=NumberOfNodes;i++)
            {
           	 if(hashKey<partionKeys*i)
           	 {
           		
           		 nodeNumber=(i-1);
           		 keystatus=true;
           		 break;
           	 }
            }
           else
           {
           	 nodeNumber=0;
           	 keystatus=true;
      	
           }
     
      
         if(keystatus)
         {
        	
        	 //if(hostNameList.size()==1)		
        	// return hostNameList.get(nodeNumber)+":"+hashKey+":"+hostNameList.get(nodeNumber);
        	 //else
        	 return hostNameList.get(nodeNumber)+":"+hashKey+":"+hostNameList.get((nodeNumber+1)%hostNameList.size());
         }
         else
         {
        	 return null;
         }
      
 
  } catch(Exception e){
     System.out.println("FileImpl Receive Sever : "+e.getMessage());
     e.printStackTrace();
     return(null);
  }
	
	//return  fileName;
}


@Override
 public String  addServer(String  hostname) throws RemoteException {
	
	// TODO Auto-generated method stub
	if(hostNameList.size()==0)
	{
		
	System.out.println("  in  add server ");
	
		hostNameList.add(hostname);
		
		 new Thread(new Runnable(){
            @Override
           public void run(){
               try{
               while(true){
                   //System.out.println(" thread is running");
                  heartbeat();
               }
               
               }catch(Exception e){
                  
               }
           }
       }).start();
		 
		 
       new Thread(new Runnable(){
           @Override
          public void run(){
           	
              try{
              while(true){
            	  
            	  Thread.sleep(5000);
                // System.out.println("  deleting thread is running");
             Set<Entry<String, Integer>> s=	  heartBeatTable.entrySet();
             Iterator<Entry<String, Integer>> it  = s.iterator();
             
             while(it.hasNext())
             {
            	  Map.Entry<String, Integer> temp1= it.next();
            	  if(temp1.getValue()==0)
            	  {
            		  
            		System.out.println(" delete server name "+ temp1.getKey());
                     deleteServer(temp1.getKey());
            		 it.remove();
            		  //removing from heartbeattable
            		  //key hostname
            	  }
             }
          	   // deleteServer
                 
              }
              
              }catch(Exception e){
                 
              }
          }
      }).start();;
       
      
	}
	else if(hostNameList.size()==1)
	{
		
		 try
		    {
		
		int  oldNodeNumbers=hostNameList.size();
		hostNameList.add(hostname);
		int newNodeNumbers=hostNameList.size();
		
		System.out.println("oldNodeNumbers "+oldNodeNumbers);
        System.out.println("newNodeNumbers "+newNodeNumbers);
        
		int partionKeysBasedOnOldNumberofNodes=  NumberOfKeys/oldNodeNumbers;
	    int partionKeysBasedOnNewNumberofNodes=  NumberOfKeys/newNodeNumbers;
	    
	     System.out.println(" replication  source  "+  hostNameList.get(0)+ " destination   "+ hostNameList.get((0)%hostNameList.size()));
	     System.out.println(" for replication transferring files from key "+ partionKeysBasedOnNewNumberofNodes  + " to  key "+ partionKeysBasedOnOldNumberofNodes);
	     String name4 = "//" +hostNameList.get(0)+ "/FileServer1";
	     NodeServerInterface ni2 = (NodeServerInterface) Naming.lookup(name4);
	     ni2.updatereplication(partionKeysBasedOnNewNumberofNodes,partionKeysBasedOnOldNumberofNodes ,true);
	     
	    
	         System.out.println("   transferring files  original from key "+partionKeysBasedOnNewNumberofNodes  + " to  key "+ partionKeysBasedOnOldNumberofNodes);
        	 System.out.println(" source  "+  hostNameList.get(0)+ " destination   "+ hostNameList.get(1));
	         String name1 = "//" +hostNameList.get(0)+ "/FileServer1";
	         
	         
		     NodeServerInterface ni = (NodeServerInterface) Naming.lookup(name1);
		     ni.SendingFiles(partionKeysBasedOnNewNumberofNodes,partionKeysBasedOnOldNumberofNodes ,hostNameList.get(1),false);
	         
		    
		    
	       //replication 
		     System.out.println(" replication  source  "+  hostNameList.get(0)+ " destination   "+ hostNameList.get((0+1)%hostNameList.size()));
		     System.out.println(" for replication transferring files from key "+ 0  + " to  key "+ partionKeysBasedOnNewNumberofNodes  );
		     String name2 = "//" +hostNameList.get(0)+ "/FileServer1";
		     NodeServerInterface ni1 = (NodeServerInterface) Naming.lookup(name2);
		     ni1.SendingFiles(0,partionKeysBasedOnNewNumberofNodes ,hostNameList.get((0+1) %hostNameList.size() ),false,true);
		     
		     
		 
		     
	    }catch(Exception e1)
		    {
	    	
	    	System.out.println(" exception ");
	    	
		    }
	         
		
	}


	else
	{
		// add the server
		try
		{
		int  oldNodeNumbers=hostNameList.size();
		hostNameList.add(hostname);
		int newNodeNumbers=hostNameList.size();
		System.out.println("oldNodeNumbers "+oldNodeNumbers);
        System.out.println("newNodeNumbers "+newNodeNumbers);	
		int partionKeysBasedOnOldNumberofNodes=  NumberOfKeys/oldNodeNumbers;
	    int partionKeysBasedOnNewNumberofNodes=  NumberOfKeys/newNodeNumbers;
	    
	        boolean keystatus=false;
	        int nodeNumber=-1;
	        int x1=-1;
	        int x2=-1;
	         for(int i=1;i<=oldNodeNumbers;i++)
	         {
	        	 
	        	 x1= partionKeysBasedOnOldNumberofNodes*i;
	        	 x2= partionKeysBasedOnNewNumberofNodes*i;
	        	 System.out.println(" rang "+  x2 + "   to  "+ x1);
			     System.out.println("   transferring files  original from key "+ x2  + " to  key "+ x1);
	        	 System.out.println(" source  "+  hostNameList.get(i-1)+ " destination   "+ hostNameList.get(i));
		         String name1 = "//" +hostNameList.get(i-1)+ "/FileServer1";
			     NodeServerInterface ni = (NodeServerInterface) Naming.lookup(name1);
			     ni.SendingFiles(x2,x1 ,hostNameList.get(i),false);
			     
	        	
			     //replication 
			     
			    // if(!hostNameList.get(i).trim().equals(hostNameList.get((i+1)%hostNameList.size()).trim()))
			     //{
			    	
			     if(i==oldNodeNumbers)
			     {
			    	
			    	 System.out.println(" replication  source  "+  hostNameList.get(i)+ " destination   "+ hostNameList.get((i+1)%hostNameList.size()));
				     System.out.println(" for replication transferring files from key "+ (NumberOfKeys-partionKeysBasedOnOldNumberofNodes)  + " to  key "+ (NumberOfKeys-partionKeysBasedOnNewNumberofNodes));
			      
			      String name2 = "//" +hostNameList.get(0)+ "/FileServer1";
				  NodeServerInterface ni1 = (NodeServerInterface) Naming.lookup(name2);
				  ni1.SendingFiles( NumberOfKeys-partionKeysBasedOnOldNumberofNodes, NumberOfKeys-partionKeysBasedOnNewNumberofNodes,hostNameList.get((i)),true); 	 
			    	 
			     }
			     else
			     {
			    	 
			    	 System.out.println(" replication  source  "+  hostNameList.get(i)+ " destination   "+ hostNameList.get((i+1)%hostNameList.size()));
				     System.out.println(" for replication transferring files from key "+ x2  + " to  key "+ x1);
				     
			     String name2 = "//" +hostNameList.get(i % newNodeNumbers)+ "/FileServer1";
			     NodeServerInterface ni1 = (NodeServerInterface) Naming.lookup(name2);
			     ni1.SendingFiles(x2,x1 ,hostNameList.get((i+1 % newNodeNumbers)),true);
			     
			     }
			     
			    // //
			    
	         }
	       
		}
	         catch(Exception e)
	         {
	        	 
	        	 System.out.println("Exception in server node  add server ");
	        	 e.printStackTrace();
	         }
	       
	       //convert keys to filename  ..compulsory 
	      //node number  starts from 0
		 // manage the files
		// client code  for tranferring the files 
		//handle replicafiles 
		 
		
	}
	
		
	
	return hostname+":"+hostNameList.size();
}





@Override
 public String  deleteServer(String hostname) throws RemoteException {
	// TODO Auto-generated method stub
	
	///delete server
	
	if(hostNameList.size()==0)
	{
		
	}
	else if(hostNameList.size()==1)
	{	
		
		// make array empty 
	hostNameList.remove(hostname);
	}

	else if(hostNameList.size()==2)
	{	
      int  oldNodeNumbers=hostNameList.size();
	   int index=-1;
	   for(int i=0;i<hostNameList.size();i++)
	   {
		   if (hostname.equals(hostNameList.get(i)))
		   {
			  index=i; 
		   }
	   }
	   
	   System.out.println( " deleting  server index "+ index );
	   hostNameList.remove(hostname);
	   int newNodeNumbers=hostNameList.size();
	   
	   System.out.println("oldNodeNumbers "+oldNodeNumbers);
       System.out.println("newNodeNumbers "+newNodeNumbers);
     
	   int partionKeysBasedOnOldNumberofNodes=  NumberOfKeys/oldNodeNumbers;
	   int partionKeysBasedOnNewNumberofNodes=  NumberOfKeys/newNodeNumbers;
	   
	   try
	   {
		  
		   if(index==0)
		   {
	System.out.println(" replication  to orginal convert source  "+  hostNameList.get(0)+ " destination   "+ hostNameList.get((0)%hostNameList.size()));
    System.out.println(" for replication transferring files from key "+ 0  + " to  key "+ partionKeysBasedOnOldNumberofNodes );
    String name4 = "//" +hostNameList.get(0)+ "/FileServer1";
    NodeServerInterface ni2 = (NodeServerInterface) Naming.lookup(name4);
    ni2.updatefileStatus(0,partionKeysBasedOnOldNumberofNodes,false);
    
   
		   }
		   else
		   {
			   
			   System.out.println(" replication  source  "+  hostNameList.get(0)+ " destination   "+ hostNameList.get((0)%hostNameList.size()));
			    System.out.println(" for replication transferring files from key "+ partionKeysBasedOnOldNumberofNodes  + " to  key "+ partionKeysBasedOnNewNumberofNodes );
			    String name4 = "//" +hostNameList.get(0)+ "/FileServer1";
			    NodeServerInterface ni2 = (NodeServerInterface) Naming.lookup(name4);
			    ni2.updatefileStatus(partionKeysBasedOnOldNumberofNodes,partionKeysBasedOnNewNumberofNodes,false);
			   
		   }
    // with removal 
	   }
	   catch(Exception e1)
	   {
		  e1.printStackTrace();
	   }
	}
	
	else
	{	
	try
		{
		  Integer index=null;
		   int  oldNodeNumbers=hostNameList.size(); 
		   for(int i=0;i<hostNameList.size();i++)
		   {
			   if(hostname.equals(hostNameList.get(i)))
			   {
				   index=i;
			   }
		   }
		  
		   System.out.println( " deleting  server index "+ index );
		  // remove from list hostname
		   hostNameList.remove(hostname);
		   int previousnode=index-1 ;
		   if(previousnode<0)
		   {
			   previousnode=(previousnode+hostNameList.size()) % hostNameList.size();
			   
		   }
		   int nextNode=index % hostNameList.size();
		   int newNodeNumbers=hostNameList.size();
		   System.out.println("oldNodeNumbers "+oldNodeNumbers);
	       System.out.println("newNodeNumbers "+newNodeNumbers);
	        
		   int partionKeysBasedOnOldNumberofNodes=  NumberOfKeys/oldNodeNumbers;
		   int partionKeysBasedOnNewNumberofNodes=  NumberOfKeys/newNodeNumbers;
		   
		   
		   if(index>1)
		   {
			   
		    //update duplicate copy 
			   

			   //transfer the  files from index to index -1 ( after deleting )
			     System.out.println("  converting original  file to duplicate  file   source  "+  hostNameList.get(previousnode)+ " destination   "+ hostNameList.get((previousnode)));
			     System.out.println(" for  updating original to replication  files from key "+ partionKeysBasedOnOldNumberofNodes*index + " to  key "+ (partionKeysBasedOnOldNumberofNodes*(index+1)));
			     String name5 = "//" +hostNameList.get(previousnode)+ "/FileServer1";
			     NodeServerInterface ni5 = (NodeServerInterface) Naming.lookup(name5);
			     // convert replicated file to orginal file 
			     ni5.updatereplication(partionKeysBasedOnOldNumberofNodes*(index-1),partionKeysBasedOnOldNumberofNodes*(index),true);
			   
		   //transfer the  files from index to index -1 ( after deleting )
		     System.out.println("  convertiong relpication file to orginal  source  "+  hostNameList.get(nextNode)+ " destination   "+ hostNameList.get((previousnode)));
		     System.out.println(" for replication transferring files from key "+ partionKeysBasedOnOldNumberofNodes*index + " to  key "+ (partionKeysBasedOnOldNumberofNodes*(index+1)));
		     String name1 = "//" +hostNameList.get(nextNode)+ "/FileServer1";
		     NodeServerInterface ni1 = (NodeServerInterface) Naming.lookup(name1);
		    // convert replicated file to orginal file 
		    ni1.SendingFiles(partionKeysBasedOnOldNumberofNodes*index,(partionKeysBasedOnOldNumberofNodes*(index+1)) % NumberOfKeys,hostNameList.get((previousnode)),true,false);
		 
		  
		    // transferring files in forward way  orginal file to original files only 
		   
		    int   forwardcounter=index-1;
		    if(forwardcounter <0)
		    {
		    	forwardcounter=(forwardcounter+newNodeNumbers) % newNodeNumbers;
		    }
		     int fcounter=index;
		     
		     while( forwardcounter<newNodeNumbers-1 )
		     {
		    	 

		     String name3 = "//" +hostNameList.get(forwardcounter)+ "/FileServer1";
		    	
			  NodeServerInterface ni3 = (NodeServerInterface) Naming.lookup(name3);
			     
		      int  xfactor1=(partionKeysBasedOnNewNumberofNodes*fcounter) % NumberOfKeys;
		       
		      int  xfactor3=(partionKeysBasedOnOldNumberofNodes*(fcounter+1)) % NumberOfKeys;
		      
		      System.out.println("    source  "+  hostNameList.get(forwardcounter)+ " destination   "+ hostNameList.get((forwardcounter+1)));
			  System.out.println(" for  orginal file  transferring files from key "+ xfactor1  + " to  key "+ xfactor3  );
			     
		      ni3.SendingFiles(xfactor1,xfactor3,hostNameList.get((forwardcounter+1) % newNodeNumbers),false);
		      fcounter++;
		      forwardcounter++;
		      
		     }
		     
		     
		     // // transferring files in backward way  for original files
		     int   backwardcounter=index-1;
		     
		     if(backwardcounter <0)
			    {
		    	 backwardcounter=(backwardcounter+newNodeNumbers) % newNodeNumbers;
			    }
		     
		     int bcounter =index;
		     while( backwardcounter>0 )
		     {
		    	 
		    
		    	 String name3 = "//" +hostNameList.get(backwardcounter)+ "/FileServer1";
			     NodeServerInterface ni3 = (NodeServerInterface) Naming.lookup(name3);
			 
		        int counting=bcounter-1;
		        
		        if(counting<0)
		        {
		        	counting=(counting+newNodeNumbers) % newNodeNumbers;
		        	
		        }
		       
		      int  xfactor1=(partionKeysBasedOnOldNumberofNodes*(counting)) % NumberOfKeys;
		      
			  int  xfactor3=(partionKeysBasedOnNewNumberofNodes*counting) % NumberOfKeys;
			  
			  int sourcenode=backwardcounter;
			  int  destinationode=backwardcounter-1;
					  
			  if(destinationode<0)
				  destinationode=(destinationode+newNodeNumbers )% newNodeNumbers ;
		    
		 	 System.out.println("   orginal  file transferring  source  "+  hostNameList.get(sourcenode)+ " destination   "+ hostNameList.get(destinationode));
		     System.out.println(" for  orginal files  transferring files from key "+xfactor1 + " to  key "+ xfactor3  );
		  
		      ni3.SendingFiles(xfactor1,xfactor3,hostNameList.get(destinationode),false);
		      bcounter--;
		      backwardcounter--;
		    
		     }
		     
		      
		    // // transferring files in  forward way  for duplicate  files
		      int   Dforwardcounter=index-1;
			    if(Dforwardcounter <0)
			    {
			    	Dforwardcounter=(Dforwardcounter+newNodeNumbers) % newNodeNumbers;
			    }
			     int Dfcounter=index-1;
			   
			     while( Dforwardcounter<newNodeNumbers )
			     {
			      String name6 = "//" +hostNameList.get(Dforwardcounter)+ "/FileServer1";
			    	
				  NodeServerInterface ni6 = (NodeServerInterface) Naming.lookup(name6);
				     
			      int  Dxfactor1=(partionKeysBasedOnNewNumberofNodes*Dfcounter) % NumberOfKeys;
			       
			      int  Dxfactor3=(partionKeysBasedOnOldNumberofNodes*(Dfcounter+1)) % NumberOfKeys;
			      
			      System.out.println("    source  "+  hostNameList.get(Dforwardcounter)+ " destination   "+ hostNameList.get((Dforwardcounter+1) % newNodeNumbers));
				  System.out.println(" for  orginal file  transferring files from key "+ Dxfactor1  + " to  key "+ Dxfactor3  );
				     
			      ni6.SendingFiles(Dxfactor1,Dxfactor3,hostNameList.get((Dforwardcounter+1) % newNodeNumbers),true);
			      Dfcounter++;
			      Dforwardcounter++;
			      
			     }
			     
		 // // transferring files in backward way  for duplicate  files
		     int   Dbackwardcounter=index-1;
		     if(Dbackwardcounter <0)
			    {
		    	 Dbackwardcounter=(Dbackwardcounter+newNodeNumbers) % newNodeNumbers;
			    }
		     
		     
		     int Dbcounter =index-1;
		     
		     while( Dbackwardcounter>1 )
		     {
		    	 String name7 = "//" +hostNameList.get(Dbackwardcounter)+ "/FileServer1";
			     NodeServerInterface ni7 = (NodeServerInterface) Naming.lookup(name7);
			   
		        int Dcounting=Dbcounter-1;
		        
		        if(Dcounting<0)
		        {
		        	Dcounting=(Dcounting+newNodeNumbers) % newNodeNumbers;
		        	
		        }
		       
		      int  Dxfactor1=(partionKeysBasedOnOldNumberofNodes*(Dcounting)) % NumberOfKeys;
		      
			  int Dxfactor3=(partionKeysBasedOnNewNumberofNodes*Dcounting) % NumberOfKeys;
			  
			  int Dsourcenode=Dbackwardcounter;
			  int  Ddestinationode=Dbackwardcounter-1;
					  
			  if(Ddestinationode<0)
				  Ddestinationode=(Ddestinationode+newNodeNumbers )% newNodeNumbers ;
		    
		 	 System.out.println("   orginal  file transferring  source  "+  hostNameList.get(Dsourcenode)+ " destination   "+ hostNameList.get(Ddestinationode));
		     System.out.println(" for  orginal files  transferring files from key "+Dxfactor1 + " to  key "+ Dxfactor3  );
		  
		      ni7.SendingFiles(Dxfactor1,Dxfactor3,hostNameList.get(Ddestinationode),true);
		      Dbcounter--;
		      Dbackwardcounter--;
		      
		     }
		     
		   
		   }
		   else if ( index ==0)
		   {
			   //last node to orginal to  last node  duplicate 
			   // delete on 0 th location 
			 
			// no  change in status 
			   //transfer the  files from index to index -1 ( after deleting )
			     System.out.println("  convertiong relpication file to orginal  in same machine  source  "+  hostNameList.get(0)+ " destination   "+ hostNameList.get((0)));
			     System.out.println(" for making original  transferring files from key "+ partionKeysBasedOnOldNumberofNodes*index + " to  key "+ (partionKeysBasedOnOldNumberofNodes*(index+1)));
			     String name1 = "//" +hostNameList.get(0)+ "/FileServer1";
			     NodeServerInterface ni1 = (NodeServerInterface) Naming.lookup(name1);
			    // convert replicated file to orginal file 
			     ni1.updatereplication(partionKeysBasedOnOldNumberofNodes*(index), (partionKeysBasedOnOldNumberofNodes*(index+1)) % NumberOfKeys, false);
			     
			     
			   
			     System.out.println("  convertiong orginal file of last location to duplicate file at 0th location     in same machine  source  "+  hostNameList.get(hostNameList.size()-1)+ " destination   "+ hostNameList.get((hostNameList.size()-1)));
			     System.out.println(" for replication transferring files from key "+ partionKeysBasedOnOldNumberofNodes*(oldNodeNumbers-1) + " to  key "+ (partionKeysBasedOnOldNumberofNodes*(oldNodeNumbers)));
			     String name2 = "//" +hostNameList.get(hostNameList.size()-1)+ "/FileServer1";
			     NodeServerInterface ni2 = (NodeServerInterface) Naming.lookup(name2);
			    // convert replicated file to orginal file 
			     ni2.SendingFiles(partionKeysBasedOnOldNumberofNodes*(oldNodeNumbers-1), (partionKeysBasedOnOldNumberofNodes*(oldNodeNumbers)) % NumberOfKeys,hostNameList.get(0), false,true);
			     
			  
			     System.out.println("  transferring dulpicate file to  next location   to duplicate file    source  "+  hostNameList.get(0)+ " destination   "+ hostNameList.get((1)));
			     System.out.println(" for replication transferring files from key "+ partionKeysBasedOnOldNumberofNodes*(0) + " to  key "+ (partionKeysBasedOnOldNumberofNodes*(1)));
			     String name3 = "//" +hostNameList.get(0)+ "/FileServer1";
			     NodeServerInterface ni3 = (NodeServerInterface) Naming.lookup(name3);
			    // convert replicated file to orginal file 
			     ni3.SendingFiles(partionKeysBasedOnOldNumberofNodes*0, (partionKeysBasedOnOldNumberofNodes*(1)) % NumberOfKeys,hostNameList.get(1),true);
			     

		        int   forwardcounter=0;
			     int fcounter=index+1;
			     while( forwardcounter<newNodeNumbers-1 )
			     {
			    
			      String name4 = "//" +hostNameList.get(forwardcounter)+ "/FileServer1";
				  NodeServerInterface ni4 = (NodeServerInterface) Naming.lookup(name4);
			      int  xfactor1=(partionKeysBasedOnNewNumberofNodes*fcounter) % NumberOfKeys;
			      int  xfactor3=(partionKeysBasedOnOldNumberofNodes*(fcounter+1)) % NumberOfKeys; 
			      System.out.println("    source  "+  hostNameList.get(forwardcounter)+ " destination   "+ hostNameList.get((forwardcounter+1)));
				  System.out.println(" for  orginal file  transferring files from key "+ xfactor1  + " to  key "+ xfactor3  );     
			      ni4.SendingFiles(xfactor1,xfactor3,hostNameList.get((forwardcounter+1) % newNodeNumbers),false);
			      fcounter++;
			      forwardcounter++;
			     }
			     
			     
			    
			     int   Dforwardcounter=1;
			     int Dfcounter=1;
			     while( Dforwardcounter<newNodeNumbers )
			     {
			    
			      String name4 = "//" +hostNameList.get(Dforwardcounter)+ "/FileServer1";
				  NodeServerInterface ni4 = (NodeServerInterface) Naming.lookup(name4);
			      int  xfactor1=(partionKeysBasedOnNewNumberofNodes*Dfcounter) % NumberOfKeys;
			      int  xfactor3=(partionKeysBasedOnOldNumberofNodes*(Dfcounter+1)) % NumberOfKeys; 
			      System.out.println("    source  "+  hostNameList.get(Dforwardcounter %  newNodeNumbers)+ " destination   "+ hostNameList.get((Dforwardcounter+1) % newNodeNumbers));
				  System.out.println(" for  orginal  file  transferring files from key "+ xfactor1  + " to  key "+ xfactor3  );     
			      ni4.SendingFiles(xfactor1,xfactor3,hostNameList.get((Dforwardcounter+1) % newNodeNumbers),true);
			      Dfcounter++;
			      Dforwardcounter++;
			     }
			    
			   
		   }
		   else{
			   
			   //
			 
			   // no  change in status  // for duplicate 
			   //transfer the  files from index to index -1 ( after deleting )
			     System.out.println("  transferring  relpication file to replication  in same machine  source  "+  hostNameList.get(1)+ " destination   "+ hostNameList.get((0)));
			     System.out.println(" for replication transferring files from key "+ partionKeysBasedOnOldNumberofNodes*index + " to  key "+ (partionKeysBasedOnOldNumberofNodes*(index+1)));
			     String name1 = "//" +hostNameList.get(1)+ "/FileServer1";
			     NodeServerInterface ni1 = (NodeServerInterface) Naming.lookup(name1);
			    // convert replicated file to orginal file 
			     ni1.updatereplication(partionKeysBasedOnOldNumberofNodes*(index), (partionKeysBasedOnOldNumberofNodes*(index+1)) % NumberOfKeys, false);
			     
			    
			     System.out.println("   converting orginal  file to  next location   to duplicate file    source  "+  hostNameList.get(0)+ " destination   "+ hostNameList.get((1)));
			     System.out.println(" for replication transferring files from key "+ partionKeysBasedOnOldNumberofNodes*(0) + " to  key "+ (partionKeysBasedOnOldNumberofNodes*(1)));
			     String name8 = "//" +hostNameList.get(0)+ "/FileServer1";
			     NodeServerInterface ni8 = (NodeServerInterface) Naming.lookup(name8);
			    // convert replicated file to orginal file 
			     ni8.SendingFiles(partionKeysBasedOnOldNumberofNodes*0, (partionKeysBasedOnOldNumberofNodes*(1)) % NumberOfKeys,hostNameList.get(1),  false,true );
			       
			     

				     int   forwardcounter=index;
				     int fcounter=index+1;
				     while( forwardcounter<newNodeNumbers-1 )
				     {
				      String name3 = "//" +hostNameList.get(forwardcounter)+ "/FileServer1";	
					  NodeServerInterface ni3 = (NodeServerInterface) Naming.lookup(name3);  
				      int  xfactor1=(partionKeysBasedOnNewNumberofNodes*fcounter) % NumberOfKeys;
				      int  xfactor3=(partionKeysBasedOnOldNumberofNodes*(fcounter+1)) % NumberOfKeys;
				      System.out.println("    source  "+  hostNameList.get(forwardcounter)+ " destination   "+ hostNameList.get((forwardcounter+1)));
					  System.out.println(" for  orginal file  transferring files from key "+ xfactor1  + " to  key "+ xfactor3  );  
				      ni3.SendingFiles(xfactor1,xfactor3,hostNameList.get((forwardcounter+1) % newNodeNumbers),false);
				      fcounter++;
				      forwardcounter++;
				     }
				     
				     
				     int   backwardcounter=index;
				     int bcounter =index+1;
				     
				     while( backwardcounter>0 )
				     {
				    	 String name3 = "//" +hostNameList.get(backwardcounter)+ "/FileServer1";
					     NodeServerInterface ni3 = (NodeServerInterface) Naming.lookup(name3);
					     
				        int counting=bcounter-1;
				        if(counting<0)
				        {
				        	counting=(counting+newNodeNumbers) % newNodeNumbers;
				        }
				      int  xfactor1=(partionKeysBasedOnOldNumberofNodes*counting) % NumberOfKeys;
					  int  xfactor3=(partionKeysBasedOnNewNumberofNodes*counting) % NumberOfKeys;
					  int sourcenode=backwardcounter;
					  int  destinationode=backwardcounter-1;
							  
					  if(destinationode<0)
						  destinationode=(destinationode+newNodeNumbers )% newNodeNumbers ;
				    
				 	 System.out.println("   orginal  file transferring  source  "+  hostNameList.get(sourcenode)+ " destination   "+ hostNameList.get(destinationode));
				     System.out.println(" for  orginal files  transferring files from key "+xfactor1 + " to  key "+ xfactor3  );
				  
				      ni3.SendingFiles(xfactor1,xfactor3,hostNameList.get(destinationode),false);
				      bcounter--;
				      backwardcounter--;
				    
				     }
				     
				     
		
				     
				     // // transferring files in  forward way  for duplicate  files
				         int   Dforwardcounter=index;
					     int Dfcounter=index;
					     while( Dforwardcounter<newNodeNumbers )
					     {
					      String name6 = "//" +hostNameList.get(Dforwardcounter)+ "/FileServer1";
					    	
						  NodeServerInterface ni6 = (NodeServerInterface) Naming.lookup(name6); 
					      int  Dxfactor1=(partionKeysBasedOnNewNumberofNodes*Dfcounter) % NumberOfKeys;
					       
					      int  Dxfactor3=(partionKeysBasedOnOldNumberofNodes*(Dfcounter+1)) % NumberOfKeys;
					      
					      System.out.println("    source  "+  hostNameList.get(Dforwardcounter)+ " destination   "+ hostNameList.get((Dforwardcounter+1) % newNodeNumbers));
						  System.out.println(" for  orginal file  transferring files from key "+ Dxfactor1  + " to  key "+ Dxfactor3  );
						     
					      ni6.SendingFiles(Dxfactor1,Dxfactor3,hostNameList.get((Dforwardcounter+1) % newNodeNumbers),true);
					      Dfcounter++;
					      Dforwardcounter++;
					      
					     }
					     

	
		   } 
		    //-------
		 
		 }
         catch(Exception e1)
         {
        	
        	 System.out.println("Exception in server node  add server ");
        	 e1.printStackTrace();
         }
 
        	 
         }
           
          //node number  starts from 0
		 // manage the files
		// client code  for tranferring the files 
		// manage the files
		// delete the nodes
		
 
	return hostname+":"+hostNameList.size();
}

@Override
public Hashtable<String,Integer> heartBeatChecking() throws RemoteException {
	// TODO Auto-generated method st
	 heartbeat();
	return heartBeatTable;
}

private void  heartbeat() throws RemoteException  {
 //Creating a SocketClient object
 try {
	String hostname =null;
	int port=0;
	 Socket socketClient=null;
	 Thread.sleep(5000);
	 
	 
	 //System.out.println(" heart beat is running ");
	 
 	for(int i=0;i<hostNameList.size();i++)
 	{
 		hostname=hostNameList.get(i);
 		
 		port=9990;
 
 		 try
 		 {
 		socketClient = new Socket(hostname,port);
 		 String userInput;
         BufferedReader stdIn = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
         while ((userInput = stdIn.readLine()) != null) {
        	
        	 try
        	 {
        	 int statusbit=Integer.parseInt(userInput.trim());
        	// System.out.println( " statusbit " + statusbit);
             heartBeatTable.put(hostNameList.get(i),statusbit);
        	 }
        	 catch(Exception e)
        	 {
        		 System.out.println("exception ");
             heartBeatTable.put(hostNameList.get(i),0);
            
        	 }
         }
         
 		 }
         catch(Exception e1 )
         {
        	
        	 System.out.println(" socket exception ");
             heartBeatTable.put(hostNameList.get(i),0);
         }
 	}
 	socketClient.close();
 
 } catch (UnknownHostException e) {
     System.err.println("Host unknown. Cannot establish connection");
 } catch (IOException e) {
     System.err.println("Cannot establish connection. Server may not be up."+e.getMessage());
 }catch (Exception e) {
	 
	// TODO: handle exception
}
	// TODO Auto-generated method stub
	
}




}
