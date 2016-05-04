package Practice;


import java.util.Scanner;

public class Solution2 {

   public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		Scanner scan = new Scanner(System.in);
		 int t= scan.nextInt();
       
       for(int p=0;p<t;p++)
           {
           
           int  n= scan.nextInt();
		   int  e= scan.nextInt();
           
		 int a[] []  = new int [n] [n];
		 
		 int []  [] weights= new int [n][n];
    
		 for(int i =0;i<e;i++)
		 {
			 
     		 int l=	 scan.nextInt();
			 int s= scan.nextInt();
			 a[l-1][s-1]=1;
			 a[s-1][l-1]=1;
			 weights[l-1][s-1]=scan.nextInt();
			 weights[s-1][l-1]= weights[l-1][s-1];
			 
			 
		 }
		
		 
		 
		 int  source= scan.nextInt();
		  int []  MatrixVector = new int [a.length];
		  int [] visited = new int [a.length];  
		  //int source=0;  
    visited[source-1]=1;
    int count=1;
    main(a,visited ,MatrixVector,  weights);
	//System.out.println("  ");
	  for(int i=0;i<MatrixVector.length;i++)
		  if(i!=source-1)
		  {
		if(MatrixVector [i]!=0)
           System.out.print(MatrixVector[i]*6+" ");
		else
			System.out.print(-1 +" ");
		  }
	System.out.println();  
       }
	}
	 
	 
	 
	public  static void   main ( int[][] a  ,int [] visisted, int [] MatrixVector,int [] []  weights )
	{
	
		boolean flag=false;
		
		
		int temp [] = new int [visisted.length];
	
			for(int i=0;i<visisted.length;i++)
			{
				if(visisted [i]==1)
				{
				for(int j=0;j<a.length;j++)
				{
				  if(j!=i)
					if(visisted[j]==0  && a[i][j]==1)
					{
						temp[j]=1;
						MatrixVector[j]= weights [i] [j];
						flag=true;
					}
				  }
				}
			}
			
			
			
			
for (int i=0;i<visisted.length;i++)
	if(visisted[i]==0)
	 visisted[i] =temp[i];

			if(flag)
			{
				
			}
		
	}
	
}

