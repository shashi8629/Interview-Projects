package Practice;

import java.util.Scanner;

public class T1 {
	
	public static  void  main ( String args [])
	{
		
		int n =5;
		int m= 7 ;
		
		
		
		 
		for(int i=n;i>0 ;i--)
		{
			
		int x= (i *(i-1))/2;
		
		System.out.println(" value "+x);
		
		if(x<=m)
		{
			
			
		 int z= n-i;
		 
		 if(x+z<=m)
		 {
			
			System.out.println(i);
			break;
			
	
		 }
		 else
			 System.out.println(i-1);
		     break;
		
			
		}
		
		
		
		}
		
		
		System.out.println(" ");
		
	}

}
