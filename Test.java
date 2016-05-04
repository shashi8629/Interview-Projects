import java.util.Stack;

import javax.swing.tree.VariableHeightLayoutCache;

public class Test {
	
	public static void main(String [] args )
	{
		int x=5;
		
	
		

	
	
	//public static  int  toBinary(int n) {
	
	
	 char [] a ={'{','}','[',']'};
	
	 
	 String str="{([})}";
	 String str2="{}[][]{}";
	 
	 
	 
	System.out.println(varify(str2.toCharArray()));
		
		
	}
	
	/*

public static  int  toBinary(int n) {
  
    String binary = ""; 
    while (n > 0) {
        int rem = n % 2;
        if(rem==0)
        	rem=1;
        else
        	rem=0;
        	
        
        binary = rem + binary;
 
        n = n / 2;
    }
    //return  Integer.parseInt(binary, 2);
    
    return 0;
   
}

*/
	
	
	
	
static boolean varify(char[] values )
{
	
Stack<Character>s= new Stack<Character>();


if(values.length>0)
s.push(values[0]);
else
	return false;

for(int i=1 ;i<values.length ;i++)
{
char a=values[i];
switch(a)
{

case '{':
s.push(a);

break;
case '}':
	if(s.peek()=='{')
	{
		s.pop();	
	
	}

break;

case '[':
s.push(a);
break;

case ']':
	

	if('['==s.peek())
	{
		s.pop();
		
	}
	
	
	
break;

default:
	break;
}


}


//System.out.println(" stack size"+s.size());

if(s.size()==0)
	return true;
else
	return false;



}





}
