package Practice;

public class FibNoacci {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	    long Fib[] = new long [50];
	    
	    // dynamic programming 
	    
		Fib[0]=0;
		Fib[1]=1;
		
		for(int i=2;i<Fib.length;i++)
		{
			Fib[i]=Fib[i-1]+Fib[i-2];
			
		}
	
		for(int i=0;i<Fib.length;i++)
		{
			System.out.println( Fib[i]);
		}
		
				
	}

}
