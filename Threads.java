

class A implements Runnable
{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}

class B implements Runnable
{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}



public class Threads {

	public static void main(String[] args) {
		// TODO Auto-generated method stu
		
	Thread T= new Thread(()-> System.out.println(" Running " + Thread.currentThread()));
	T.start();
	
	Thread T1= new Thread(()-> System.out.println(" Running  " + Thread.currentThread()));
	T1.start();

	}
	

}
