package P1Final;

class temp
{
	
	 static int x=0;
	
	static int hode()
	{
		
		System.out.println("parent");

		return 0;
	}
}


public class Testing   extends temp {
	
	
	static int hode()
	{
		
		System.out.println("child");
		return 0;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		x++;
		x=20;
		x++;
		
		
	 
		temp t=	new Testing();
	 
	 System.out.println( x);
		t.hode();
		

	}
	
	
	

}
