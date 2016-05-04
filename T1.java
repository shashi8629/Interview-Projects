package T1;

import java.util.Arrays;
import java.util.Collections;

public class T1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
int  a	[]={7,6,23,19,10,11,9,3,15};
	//Output should be : 6 Pairs can be: 7,11 7,3 6,10 19,23 15,19 15,11


int k=-4;
findsolution(a,k);


	}

	private static void findsolution(int  [] a,int k) {
		
		Arrays.sort(a);
		
		for(int i=0;i<a.length;i++)
			
			System.out.print("  "+ a[i]);
		
		
		int count=0;
		int mid =a.length/2;
		
		for (int i=0;i<a.length ;i++)
		{
			int x= k+a[i];
			int  number = Bsearch.bsearchFunction(a,0, a.length-1, x);
		
			if(number!=-1)
			  System.out.println(" pair"+  a[i] +" and " +  a[number]);
			

		}
		

		// TODO Auto-generated method stub
		
	}

}
