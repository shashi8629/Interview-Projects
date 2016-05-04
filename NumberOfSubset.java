package Practice;

public class NumberOfSubset {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
  char [] a= { 'a','b','c','d'};

   for (int i=0;i<(int)Math.pow(2,a.length);i++)
   {
	    
	   String binaryNum = Integer.toBinaryString(i);
	   
	    int x= a.length-binaryNum.length();
	    
	    while(x>0)
	    {
	    	binaryNum="0"+binaryNum;
	    	x--;		
	    }
	    
	    System.out.println(binaryNum);
	   
	   int position=0;
	   int ch=0;
	   String str="{";
	   
	   if(i==0)
	   {
		 
		   
	   }
	   else
	   {
	   while((ch =binaryNum.indexOf("1",position))!=-1)
	   {
		   str=str+a[ch]+",";
		   position=ch+1;
		   
	    }
	   }
	   
	  str=str+"}"; 
	  
	  System.out.println(str);
	   
	   
	   
   }


	}

}
