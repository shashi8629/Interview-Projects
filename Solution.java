package T1;

import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

	       Scanner scan = new Scanner(System.in);
	       
	      int t=  scan.nextInt();
	      
	      String [] arr = new String [t];
	       
	      
	       
	       for(int k =0 ;k<t;k++)
	       {
	    		   
		   arr[k] =  scan.next();
		   
	    
	       }
	       
	       
	       for(int k =0 ;k<t;k++)
	       {
	    	
	       
	    	String    str=arr[k];
		  
		   if(checkpailindrome(str))
			   System.out.println(-1);
		   
		   else
		   {
		   
		   StringBuilder strb=  new StringBuilder(str);
		   int i=-1;
		   int j=-1;
		   
		   for(  i=0 ,j=str.length()-1 ; i<str.length()/2 ;i++,j--)
		   {
			    if( str.charAt(i)!= str.charAt(j))
			    	
			    {
			    	
			    //System.out.println( i+ " and "+ j);

			    	break;
			    	
			    }
			    	
			   
		   }
		   
		
	  StringBuilder strb1=  new StringBuilder(str);
		  strb.deleteCharAt(i);
		  
		 if(checkpailindrome(strb.toString()))
			 System.out.println(i);
           
		 else
			 
		 {
		 strb1.deleteCharAt(j); 
		  if(checkpailindrome(strb1.toString()));
			 System.out.println(j);
			 
		 }
		 

		   }
		  
		   }
		  
					  
		
	}

	private static  boolean checkpailindrome(String str) 
	{
	
		
		  		 
		boolean flag=true;
		   
		   for( int  i=0 ,j=str.length()-1 ; i<str.length()/2 ;i++,j--)
		   {
			    if( str.charAt(i)!= str.charAt(j))
			    
			    	
			     flag=false;
			    	
			    
			    	
		   }

		   return flag;
		
	}		  
		 
	}


