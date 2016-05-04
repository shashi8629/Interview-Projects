package Practice;

import java.util.Arrays;

public class P1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		String str = "abcde";
		
        char[] str1= str.toCharArray();
		  
		  Arrays.sort(str1);
		  
		  int prev ;
		  int next ; 
		 
		Boolean flag = true;
		 
		 prev=str1[0];
		 
		  for(int i=1;i<str.length();i++)
		  {
			  
			  next= str1[i];
			  
			  if(prev==next)
			  {
				  flag=false;
				   
			  }
			  
			  prev=next;
	  
			 
		  }
		  
		  
		  if(flag)
		  {
			
			  System.out.println(" unique");
		  }
		  
		  else
		  {
			  System.out.println(" not  unique");
			  
		  }
		  
		  
		  
		
		

	}

}
