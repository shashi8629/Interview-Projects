import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class T134 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		
		
		
	try
	{

	    
		Scanner s = new Scanner(System.in);
	    StringBuilder sb = new StringBuilder();
	    
	    
	    String str="";
	    
	    
	    while (s.hasNext() &&s.next()!="" ) {
	        String l = s.nextLine();
	        sb.append(l);
	        
	      }
		     
		    

		
		  
		      String pattern = "(?://.*)|(/\\*(?:.|[\\n\\r])*?\\*/)";

		      // Create a Pattern object
		      Pattern r = Pattern.compile(pattern);

		      // Now create matcher object.
		      Matcher m = r.matcher(sb.toString());
		      int i = 0;
		      
		      while(m.find()){
		      
		         System.out.print(m.group(i++) );
		      
		      }
		   
	}
	catch(Exception e )
	{
		

	}

}
	
}
