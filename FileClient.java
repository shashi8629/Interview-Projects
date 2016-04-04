

import java.io.*; 
import java.rmi.*;

public class FileClient{

	 
	public static void invokeServerDownloadFile(String hostName,String fileName) {
	     
	
	      try {
	         String name = "//" + hostName + "/FileServer";
	         FileInterface fi = (FileInterface) Naming.lookup(name);
	         String status=  fi.downloadFile(fileName);
	         
	        String actualHostName=null;
	        Integer key=null;
	        
	         String replicaHostName=null;
	         
	         if(status!=null)
	         {
	        
	        	String [] str=  status.split(":");
	        	actualHostName=str[0];
	        	key=Integer.parseInt(str[1]);
	        	 replicaHostName=str[2];
	        	
	    
	         }
	         else
	         {
	        	 
	         }
	         
           
	         System.out.println(" FILE IS PERSENT AT  "+ actualHostName +" .KEY OF FILE   "+ key);
	         String name1 = "//" + actualHostName + "/FileServer1";
		     NodeServerInterface ni = (NodeServerInterface) Naming.lookup(name1);
		              // ni.downloadFile(key)
	         byte[] filedata = ni.downloadFile(key);
	        
	         File file = new File(fileName);
	         BufferedOutputStream output = new
	           BufferedOutputStream(new FileOutputStream(file.getName()));
	         output.write(filedata,0,filedata.length);
	         output.flush();
	         output.close();
	         
	         if(filedata.length>0)
	           System.out.println(" FILE IS DOWNLOADE ");
	         
	          if(filedata!=null && !actualHostName.trim().equals(replicaHostName.trim()))
	          {
	        	if(filedata.length==0) 
	        	{
	         System.out.println(" DUPLICATE   FILE IS PERSENT AT   "+ replicaHostName +" KEY OF FILE "+ key);
	         String name2 = "//" + replicaHostName + "/FileServer1";
		     NodeServerInterface ni1 = (NodeServerInterface) Naming.lookup(name2);
		              // ni.downloadFile(key)
	         byte[] filedata1 = ni1.downloadFile(key);
	         File file1 = new File(fileName);
	         BufferedOutputStream output1 = new
	           BufferedOutputStream(new FileOutputStream(file1.getName()));
	         output1.write(filedata1,0,filedata1.length);
	         output1.flush();
	         output1.close();
	         
	         
	         if(filedata.length>0)
	        	 System.out.println(" FILE IS DOWNLOADE ");
	         
	          }
	          }
	         
	      } catch(Exception e) {
	         System.err.println("FileServer exception: "+ e.getMessage());
	         e.printStackTrace();
	      }
	   }
	   
   
   
   public  static  void invokeSeverDeleteFile(String hostName,String fileName) {
	      try {
	         String name = "//" + hostName + "/FileServer";
	         FileInterface fi = (FileInterface) Naming.lookup(name);
	         String  status = fi.deleteFile(fileName);
	         
		        String actualHostName=null;
		       Integer key=null;
		       String replicaHostName=null;
		         
		         if(status!=null)
		         {
		        
		        	String [] str=  status.split(":");
		        	actualHostName=str[0];
		        	key=Integer.parseInt(str[1]);
		        	replicaHostName=str[2];
		       
		         }
		         else
		         {
		        	 
		         }
		         

		          // System.out.println(" Original File is  present at  "+ actualHostName +" Key Of Orginal File "+ key);
		           String name1 = "//" + actualHostName + "/FileServer1";
		           NodeServerInterface ni = (NodeServerInterface) Naming.lookup(name1);
		           boolean deleteFileStatus=  ni.deleteFile(key,false);
		           
		           
		           if(deleteFileStatus)
		           {
		        	  System.out.println(" FILE  IS  DELETED  . IT WAS  STORED IN SYSTEM  "+ actualHostName); 
		           }
		        	   
		           else
		           {
		        	   System.out.println("  FILE  IS NOT   DELETED"); 
		        	   
		           }
		           
		           
		         
		           if(!actualHostName.trim().equals(replicaHostName.trim()))
		           {
		           //System.out.println(" Duplicate  File is  present at "+ replicaHostName +" . Key of Duplicate File  "+ key);
		           String name2 = "//" + replicaHostName + "/FileServer1";
		           NodeServerInterface ni1 = (NodeServerInterface) Naming.lookup(name2);
		           boolean deleteFileStatus1=  ni1.deleteFile(key,true);
		           
		           
		           if(deleteFileStatus1)
		           {
		        	  System.out.println("DUPLICATE   FILE  IS  DELETED  . IT WAS  STORED IN SYSTEM "+ replicaHostName); 
		           }
		        	   
		           else
		           {
		        	   System.out.println("DUPLICATE   FILE  IS  NOT  DELETED"); 
		        	   
		           }
		            
		           }
		            
		        
	         /*
	         if(status1!=null)
	         System.out.println(" deleted");
	         else
	          System.out.println("  not deleted");
	          */
	        	 
	    
	      } catch(Exception e) {
	         System.err.println("FileServer exception: "+ e.getMessage());
	         e.printStackTrace();
	      }
	   }
   
   
   
   public  static  void invokeSeverSendingFile(String hostName,String fileName)
   
	// public  static  void main(String [] args )
   {

  // String  fileName="readme";
   
	      try {
	    	  
	    	       String name = "//" + hostName + "/FileServer";
		           FileInterface fi = (FileInterface) Naming.lookup(name);
		           String status= fi.receiveFile(fileName);
		           String actualHostName=null;
				   Integer key=null;
				   String replicaHostName=null;
		           if(status!=null)
			         {
			        	String [] str=  status.split(":");
			        	actualHostName=str[0];
			        	key=Integer.parseInt(str[1]);
			        	replicaHostName=str[2];
			         }
			         else
			         {
			        	
			        	 return;
			         }
			         
		            //System.out.println(" Original File is persent at  "+ actualHostName +" key of file is  "+ key);
		              String name1 = "//" + actualHostName + "/FileServer1";
			          NodeServerInterface ni = (NodeServerInterface) Naming.lookup(name1);
			           //System.out.println("filename"+ fileName);
			            File file = new File(fileName);
			            byte buffer[] = new byte[(int)file.length()];
			            BufferedInputStream input = new
			            BufferedInputStream(new FileInputStream(fileName));
			            input.read(buffer,0,buffer.length);
			            input.close();
			            
			             String filename= ni.receiveFile(buffer, fileName, key,false);
			           
				           if(filename!=null)
				           {
				        	  System.out.println(" FILE  "+ filename+" IS STORED IN SYSTEM "+ actualHostName); 
				           }
				        	   
				           else
				           {
				        	   System.out.println(" FILE  IS   NOT STORED  "); 
				        	   
				           }
			           
				           if(!actualHostName.trim().equals(replicaHostName.trim()))
				           {
			              // System.out.println("Replicated File is persent at "+ replicaHostName +" key of file is  "+ key);
			               String name2 = "//" + replicaHostName + "/FileServer1";
				           NodeServerInterface ni1 = (NodeServerInterface) Naming.lookup(name2);
				          
				            File file1 = new File(fileName);
				            byte buffer1[] = new byte[(int)file1.length()];
				            BufferedInputStream input1 = new
				            BufferedInputStream(new FileInputStream(fileName));
				            input1.read(buffer1,0,buffer1.length);
				            input1.close();
				             String filename1= ni1.receiveFile(buffer1, fileName, key,true);
			           
			 
				             if(filename1!=null)
					           {
					        	  System.out.println("DUPLICATE   FILE  "+ filename1+" IS STORED IN SYSTEM   "+ replicaHostName); 
					           }
					        	   
					           else
					           {
					        	   System.out.println("DUPLICATE   FILE  IS   NOT STORED "); 
					        	   
					           }
				           
				           }
			           
	      } catch(Exception e) {
	         System.err.println("FileServer exception: "+ e.getMessage());
	         e.printStackTrace();
	      }
	   } 
   
}