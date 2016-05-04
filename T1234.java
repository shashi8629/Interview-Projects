import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class T1234 {

    public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//System.out.println(" ");
	
		StringBuilder strb= new StringBuilder();
	
		String str1="/*";
		
		String str2="*/";
		
		String str3="//";
				
		try
		{
		
	BufferedReader bfr = new BufferedReader(new InputStreamReader(new FileInputStream(new File("/Users/shashi/T4"))));

	  String str=null;
	
	  boolean flag=false;
	  
		 while((str=bfr.readLine())!=null)
				 {
			 
	//System.out.println(str);
			 
			str= str.trim();
		
		if(str.trim().length()!=0)
		
		{
		
		 if (str.contains(str1)&& !flag)
		  {
			 flag=true;
			 
			if(str.contains(str2))
			{
				strb.append(str.substring(str.indexOf(str1),str.indexOf(str2)+2));
				
				strb.append("\n");
				flag=false;
				
			}
			else
			{
				strb.append(str.substring(str.indexOf(str1),str.length()));
				strb.append("\n");
			
			}
			
			
		}
		
		else if (str.contains(str2) && flag)
		{
			
			flag=false;
			
			if(str.contains(str1))
			{
				strb.append(str.substring(0,str.indexOf(str2)));
                strb.append(str.substring(str.indexOf(str1)));
				strb.append("\n");
				flag=true;
                
			}
			else
			{
				strb.append(str.substring(0,str.indexOf(str2)+2));
				strb.append("\n");
				
				
				//strb.append(str.substring(str.indexOf(str2),str.length()));
			  }
			
		}
		 
		else if (flag)
		{
			
			strb.append(str);
			strb.append("\n");
		
		}
		 
		
		else
		{
			
			
		}
		 
		 if(str.contains("//") && !flag)
			{
			
				strb.append(str.substring(str.indexOf("//")));
				strb.append("\n");
			}
	 }
         }
            
           strb.delete( strb.lastIndexOf("\n") , strb.length());
		 
		 System.out.println(strb.toString());
		 
		}
		catch(Exception e1 )
		{
			
		}
    }
}