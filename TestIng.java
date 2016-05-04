package Practice;

public class TestIng {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int  num=100;
		int [] a=new int [num];
		 a[0]=0;
		 a[1]=1;
		 a[2]=1;
		int  s=2;
		int sum =0;
		int sum1=0;
		
		for (int i=3; i<num;i++)	
		{	
			while(true)
			{	
		  int z=(int)Math.pow(2, s);
		  
		  if(i < z)
			{
		        sum=(int)Math.pow(2, s-1);
		        sum1=i-sum;
		        a[i]=a[sum]+ a[sum1];
		        break;
		       
		     }
			 else if (i==z)
			 { 
				 a[i]=1; 
				 break;
				 
			 }
			 
			 else
			 {
				 s++; 
			 }
			
			}
		  
		  System.out.println( " num "+ i+ "item "+ a[i]);
		 
		}
		

	}

}
