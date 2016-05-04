package Practice;

public class T3 {
	
	public static void main(String[] args){
		Node root=null;
		String str="1001011";
		root=new Node();
		root.left=new Node();
		root.right= new Node();
		root.right.data='A';
		
		root.left.left=new Node();
		root.left.left.data='B';
		
		root.left.right=new Node();
		root.left.right.data='C';
		
		new T3().decode(str, root);
		
		System.out.println(finalstr);
		
	}
	 
	 void decode(String s ,Node root)
	    { 
		 
		 int  index=0;
	    for(int i=0;i<s.length();)
	        {
	          if(s.charAt(i)=='0')
	            {
	        	index=i;
	     
	        	//System.out.println(" start left index"+index);
	        	
	             index= find (root.left,s,index); 
	             
	             //System.out.println("left index"+index);
	            
	             i=index+1;
	            
	            }
	       
	          else
	            {
	        	index=i;
	            
	        	//System.out.println(" start Right index"+index);
	        	
	        	index= find (root.right,s,index);
	        	//System.out.println(" Right index"+index);
	            
	            i=index+1;
	            
	            
	            }  
	      }
	   
	   }

	 
	 static String finalstr="";
	 int  find ( Node root1 ,String s ,int index )
	  {
	    /* if(root1==null)
	     {
	    	 return index;
	     }
	     */
		 
	    if(root1.left == null && root1.right==null)
	    {	
	    	//System.out.println(" data "+root1.data);
	    	finalstr=finalstr+root1.data;
	    	return index;	
	    }
	    
	    index++;
	 
	    if(s.charAt(index)=='0')
	    {
	    return  find(root1.left,s,index);
	    }
	    else
	    {
	     return  find(root1.right, s,index );
	    }
	
	       		
	 }
	

}

	 
	   
	        
	     
