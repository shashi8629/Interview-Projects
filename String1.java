import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class String1 {

	public static void main(java.lang.String[] args) {
		// TODO Auto-generated method stub
		
		StringBuilder strb= new StringBuilder();
		
		try
		{
		
	BufferedReader bfr = new BufferedReader(new InputStreamReader(new FileInputStream(new File("/Users/shashi/T7"))));
      
	String str=null;
	boolean flag=false;
	Scanner scan=new Scanner(System.in);
	
	int n=49;
     //n = scan.nextInt();
	
	int count=-1;
	

		 while(count<n)
				 {
			 
			// str=scan.nextLine();
			 
			 str=bfr.readLine();
			 
			 count++;
			 //System.out.println( str);
			 
			 str=str.trim();
			 
			 if(str.length()!=0)
			 {
				 
				 String str1=str; 
				 
				 while(str1.contains("<a href"))
				 {
					 
					  int y= str1.indexOf("\"",str1.indexOf("href"));
					  
					  //System.out.println(y);
					 
					   int z= str1.indexOf("\"",y+1);
					   
					   //System.out.println( z);
					   
					   String str2=str1.substring(y+1,z);
					   
					   String str3=str1.substring(z+1);
					   strb.append(str2+",");
					   
					   if(str3.contains(">")&& str3.contains("</a>"))
					   {
					   int m=str1.indexOf(">",z+1);
					   //System.out.println(m);
					   int l=str1.indexOf("</a>",m+1);
					   //System.out.println(l);
					   strb.append(str1.substring(m+1,l));
					   strb.append("\n");
					   str1=  str1.substring(z+1);
					   
					   
					   }
					   
					   else
					   {
						   strb.append("\n");
						   str1=  str1.substring(z+1);
						   
					   }
					   

					  
					  
					// System.out.println(str1);
					      
				 }
			
			 }
			
	
		}
		 
		 System.out.println(strb.toString());
		 
		}
		
		catch (Exception e1)
		{
			e1.printStackTrace();
			
		}

	}

}
