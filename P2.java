
public class P2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 String str="abcd";
		 reverse(str.toCharArray());
		 


	}

	
	static void reverse( char [] arr)
	 {
		 
		for(int i=0,z=arr.length-1;i<=(arr.length-1)/2;i++,z--)
		{
		 char c = arr[i];
		 arr[i]=arr[z];
		 arr[z]=c;
		}
		
		
		String str="";
		for(int i=0;i<arr.length;i++)
		{
			
			str=str+ arr[i];
			
		}
		
		
		System.out.println(str);
		
		
	
		 
	 }
	
	
}
