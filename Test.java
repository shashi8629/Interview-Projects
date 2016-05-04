package HTTP1;

public class Test implements Runnable {
	


	

	   public static void main(String[] args) {

	   Thread t = Thread.currentThread();
	   
	   Thread t1 = new Thread(new Test());
	   t1.start();
	   
	   Thread t2 = new Thread(new Test());
	   t2.start();
	   
			   
	   t.setName("Admin Thread");
	   // set thread priority to 1
	   t.setPriority(1);
	     
	   // prints the current thread
	   System.out.println("Thread = " + t);
	    
	   int count = Thread.activeCount();
	   System.out.println("currently active threads = " + count);
	    
	   Thread th[] = new Thread[count];
	   // returns the number of threads put into the array 
	   Thread.enumerate(th);
	    
	   // prints active threads
	   for (int i = 0; i < count; i++) {
	   System.out.println(i + ": " + th[i]);
	   }
	   }

	@Override
	public void run() {
		
		while( true)
		{
			
		}
		// TODO Auto-generated method stub
		
	}
	


}
