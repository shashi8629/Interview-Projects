package T1;

import java.util.Arrays;

public class DuplicateRemoveinplace {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int a	[]={7,6,7,7,23,10,10,10,10,23,6,19,23,19,10,11,9,3,15};
		//Arrays.sort(a);	
		int length=a.length-1;
		
for(int i=0;i<length;i++)
{
	
for (int j=i+1;j<length;j++)
{
	if(a[i]==a[j])
	{
		int x=j;
		
		for(int k=x;k<length;k++)
		{
			a[k]=a[k+1];
			
		}
		
		length--;
		j--;
	}
	
	
}
	 	
}



for(int i=0;i<length;i++)
{
	
	System.out.println(" "+ a[i]);
}


}
	
}