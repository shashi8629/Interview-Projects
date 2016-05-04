package T1;

import java.util.Arrays;

public class Quicksosrts {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		int a	[]={7,6,7,7,23,10,10,10,10,23,6,19,23,19,10,11,9,3,15};
		Arrays.sort(a);	
		
		for(int i=0;i<a.length;i++)
		{
			
			System.out.print(" "+ a[i]);
			
		}
		
		
		System.out.println();
		System.out.println();
		
		
		int prev=0;
		int next=0;
		
		int length=a.length;
		
		
	prev=a[0];
	int count=0;
	
		for(int i=1;i<length;i++)
		{
	
			next=a[i];
			
			System.out.println(" prev "+ prev +"next "+next);
			
			if(prev==next)
			{
				
				count++;
			   
			
			}
			else
			{
				
				System.out.println();
	
				if(count>0)
				{
					
				int pos=i;
				
				int k=pos;
				
				System.out.println(" count "+ count);
				
				int start=pos-count;
				
				 for(;k<length;k++,start++)
				 {
					 
					 a[start]=a[k]; 
				 }
				
				}
				
			 length=length-count;
			i=i-count;
			count=0;
			}
			
			prev= next;	
		}	
		
		if(count>0)
		{
			
			 length=length-count;
			
			
		}
		
		
		for(int i=0;i<length;i++)
		{
			
			System.out.println(" "+ a[i]);
		}

	}

}
