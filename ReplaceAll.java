package DataMining;

import java.util.ArrayList;
import java.util.List;

public class ReplaceAll {
	
	/*￼public static int replaceAll(List list, String from, String to, boolean caseSensitive) Example:
		replaceAll(['foo', 'bar', 'foo'], 'Foo', 'test', false)
		should return ['test', 'bar', 'test']
				
*/
	
	public static void main(String[] args) 
	{	
	ArrayList<String> al = new ArrayList<String>();
	al.add("foo");
	al.add("bar");
	al.add("foo");
	//replaceAll( al, "Foo", "test", false);
	//System.out.println(list);
		
	}
	
	
	
	public  ArrayList replaceAll(ArrayList<String> list,String from, String to,boolean caseSensitive) 
	{
	
		
		for( int i=0;i<list.size();i++)	
		{
			if(list.get(i).toString().trim().equals(from.trim()))
		     	list.set(i,to.trim());
			
		}
		
		return list;
		
	}
	

}
