
public class HashingFunc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		String str="sagsh";
		
		int sum=0;
		
		for(int i=0;i<str.length();i++)
		{
		 sum =sum +  str.codePointAt(i)* Math.pow(10,i);
		 
			
		}
		
		

		
	}

}
