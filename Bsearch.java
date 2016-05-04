package T1;

import java.util.Arrays;

public class Bsearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	
		int a	[]={7,623,19,10,11,9,3,15};
		Arrays.sort(a);	
	int x=19;
	int low=0;
	int high=a.length-1;

bsearchFunction(a,low,high, 19);

		
		
	}

	public  static int  bsearchFunction(int [] a,int low, int high ,int key) {
		// TODO Auto-generated method stub
		int mid =0;
		
		

		while(low<=high)
		{
		mid=(low+high)/2;
			
		if(a[mid]==key)	
		{
			
		System.out.println(" found  position " + mid + " data"+ a[mid]);
		 return mid;
	
		}
			
		else if(a[mid]>key)
		 {
			
			high=mid-1;
			
			//System.out.println( " position less "+ high);
		 }
			
		 else
		 {

		 low =mid+1;
		 
		 //System.out.println( " position excess "+ low);
			 
		 }
		 
		 
			
		}
		
		
		
		return -1;
		
	}
	
	
	
	
	
	

}
