

import java.rmi.Naming;
import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) 
	{
		
		
		boolean status=true ;
		
		do 
		{
			Scanner scan= new Scanner(System.in);
			System.out.println(" 1   ADD  A  PEER SERVER");
			System.out.println(" 2   TO UPLOAD A FILE  IN PEER TO  PEER  MODEL  ");
			System.out.println(" 3   DOWNLOAD A FILE  ");
			System.out.println(" 4   DELETE  FILE  ");
			System.out.println(" 5   DELETE A SERVER ");
			System.out.println(" 6   FILE  DETAILS OF SYSTEM  ");
			int  choice = scan.nextInt();
			String CentralizedServer="129.210.16.133";
			 
			 switch (choice)
			 {
			 case 1:
				 System.out.println("ENTER THE HOSTNAME  ");
				 String   hostname=scan.next();
			     ServerFunctionality.addServer(CentralizedServer,hostname);
			break;
			
			case 2:
			 System.out.println(" ENTER THE FILENAME ");
			 String fileName=scan.next();
			    FileClient.invokeSeverSendingFile(CentralizedServer, fileName);
			    
		    break;
			case 3:
				 System.out.println(" ENTER THE FILENAME ");
				fileName=scan.next();
				//CentralizedServer="localhost";
				FileClient.invokeServerDownloadFile(CentralizedServer, fileName);
				//RMI
				//Registry Setting
				//rmic compiler 
				// Set the shell script
		    break;	 
			case 4:
				 System.out.println(" ENTER THE FILENAME ");
				fileName=scan.next();
				//CentralizedServer="localhost";
				FileClient.invokeSeverDeleteFile(CentralizedServer, fileName);
			break;
			case 5:
				 System.out.println("ENTER THE HOSTNAME  ");
				   hostname=scan.next();
				  System.out.println(ServerFunctionality.deletServer(CentralizedServer,hostname));
				
				
			case 6:
				 System.out.println("ENTER THE HOSTNAME  ");
				   hostname=scan.next();
			
				   try
				   {
					   
				   String name1 = "//" +hostname+ "/FileServer1";
			       NodeServerInterface ni = (NodeServerInterface) Naming.lookup(name1);
			       String Status= ni.displayKeys(hostname);
			     
			       System.out.println(Status);
			       
				   }
				   catch (Exception e1)
				   {
					   
				   }
				
			 break;
			 default: 
			 status=false;
			 break;
			 
			 }
					
}
		
	while ( status);
	}
	
	

}
