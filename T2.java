package Practice;

import java.util.Scanner;

public class T2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		Scanner scan = new Scanner(System.in);
		 int t= scan.nextInt();
		 int  n= scan.nextInt();
		 int  e= scan.nextInt();
	
		 int a[] []  = new int [n] [n];
		
		 for(int i =0;i<e;i++)
		 {
			 
			 int l=	 scan.nextInt();
			 int s= scan.nextInt();
			 a[l-1][s-1]=1;
			 a[s-1][l-1]=1;
			 
		 }
		 
		
		 int  source= scan.nextInt();
		 
		 
		  int []  MatrixVector = new int [a.length];
		  int [] visited = new int [a.length];
		  
		  //int source=0;
		   
		  
    visited[source-1]=1;
	
    main(a,visited ,MatrixVector);
    
	//System.out.println("  ");
	
	  for(int i=0;i<MatrixVector.length;i++)
		  if(i!=source-1)
		  {
		if(MatrixVector [i]!=0)
           System.out.print(MatrixVector[i]*6+" ");
		else
			System.out.print(-1);
		  }
		

	
	System.out.println();


	}
	
	
	
	 static int  count=0;
	
	public  static void   main ( int[][] a  ,int [] visisted, int [] MatrixVector )
	{
		
		count++;
		
		boolean flag=false;
		 
			for(int i=0;i<visisted.length;i++)
			{
				
				if(visisted [i]==1)
				{
				for(int j=0;j<a.length;j++)
				{
					
				//	if(j!=i)
					if(visisted[j]==0  && a[i][j]==1)
					{
						visisted[j]=1;
						MatrixVector[j]=count;
						flag=true;
						
						
					}
	
					
				}
				}
			}
			
			
			
			if(flag)
			   main(a,visisted,MatrixVector);
		
	}
	
	
	

}
