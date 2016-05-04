package com.hackton.sites;

public class QuickSorts {

	
	public static void main(String[] args) {
	
		//int [] a = {5,3,4,8,2,3,1,6};
		
		int [] a = new int [10];
		for(int i=0;i<a.length;i++)
		{
		a[i]= (int)(Math.random()*100);
		System.out.println( a[i]);	
		}
	
		
		QuickSorts q=new QuickSorts();
		q.Quicksort(a,0,a.length-1);
		
	    System.out.println(" -----after sort------");
		
		for(int i=0;i<a.length;i++)
		{
		System.out.println( a[i]);
		}
		
		//low=0
		//high=end;
		//
		
	}
	 void Quicksort(int [] a,int low,int high)
	{
		if(low<high)
		{
		int partion=partionionang(a,low,high);
		Quicksort(a,low,partion-1);
		Quicksort(a,partion+1,high);
		}
		 
	}
	 
	int partionionang(int a[] , int low , int high)
		{
		//System.out.println(" insisde");
		int left=low+1;
		int right=high;
		int pivot=a[low];
		while(true)
		{
		while(left<=high)
		{
			if(a[left]<pivot)
			{
			 left++;	
			//System.out.println(" left"+left);
			}
				
		
			else
				break;
		}
		
		while(right>=low)
		{
			if(a[right]>pivot)
			{
				right--;	
				//System.out.println("right"+right);
			}
			else
				break;
		}
		
	
		if(left>right)
			break;
		
		if(left<=right)
		{
		int temp=a[left];
		a[left]=a[right];
		a[right]=temp;
		right--;
		left++;
		}
		
		}
		
		a[low]=a[left-1];
		a[left-1]=pivot;

	return left-1;
		}
		
}
