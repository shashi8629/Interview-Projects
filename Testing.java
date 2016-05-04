package T1;

import java.util.HashMap;

public class Testing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		HashMap<Integer, Integer> map= new HashMap<Integer,Integer>();
		map.put(1, 0);
		map.put(2, 0);
		map.put(3, 0);
		map.put(5, 0);
		map.put(7, 0);
		map.put(4, 1);
		map.put(0, 1);
		map.put(6, 1);
		map.put(9, 1);
		map.put(8, 2);
		int num=123;
		int temp =num;
		int count=0;
		
	while(temp!=0)
	{
		
	int remainder=temp%10;
	 count=count+ map.get(remainder);
	 temp=temp/10;
	  
	}
	System.out.println(count);	
	
		
	

	}

}
