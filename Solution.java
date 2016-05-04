import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Solution {

	public static void main(String[] arg) {
		// TODO Auto-generated method stub
		
	   int a[]={ 8,2,3,4,5,9};
		
	   HashMap m= new HashMap();
	   
	   for(int i=0;i<a.length;i++)
	        m.put(i,a[i]);
		
	   
	   Map<Integer, Integer> sortedMap = sortByComparator(m);
	   
		
		//LinkedHashMap m2= new LinkedHashMap<>(m1);
		//Set  s = m2.entrySet();
		//Iterator it = s.iterator();

	   LinkedHashMap<Integer, Integer> m2= new LinkedHashMap<>(sortedMap);
	 
	   LinkedHashSet<Integer> s2= new LinkedHashSet<>(m2.keySet());
	   
        ArrayList<Integer> al =  new ArrayList<Integer>(s2);
		
	    ListIterator<Integer> it1= al.listIterator();
	
        Iterator<Integer>  it2= al.iterator();
	
   
        
        while(it1.hasNext())
        {
        	it1.next();
        	
        }
	
	while(it2.hasNext())
	{
		
		int temp1=(Integer)it1.previous();
		
		 int temp2=(Integer)it2.next();
		 
		 
		//System.out.println(temp1+"    "+ temp2);
		
		if(temp1-temp2>=0)
		{
			
			
			if((Integer)m2.get(temp1)>(Integer) m2.get(temp2))
			{
				
				System.out.println( temp1 +"   " +temp2);
				break;
				
			}
			
		}
		
		
		
	}
	
	
		
		
	}
	
	private static Map<Integer, Integer> sortByComparator(Map<Integer, Integer> unsortMap) {

		// Convert Map to List
		List<Map.Entry<Integer, Integer>> list = 
			new LinkedList<Map.Entry<Integer, Integer>>(unsortMap.entrySet());

		// Sort list with comparator, to compare the Map values
		Collections.sort(list, new Comparator<Map.Entry<Integer,Integer>>(){
		
			public int compare(Map.Entry<Integer, Integer> o1,Map.Entry<Integer, Integer> o2) 
			
			{
				return (o1.getValue()).compareTo(o2.getValue());
			}
			
			
		});
		
		
		

		// Convert sorted map back to a Map
		
		
		
		Map<Integer, Integer> sortedMap = new LinkedHashMap<Integer, Integer>();
		
		
		for (Iterator<Map.Entry<Integer, Integer>> it = list.iterator(); it.hasNext();) {
			Map.Entry<Integer, Integer> entry = it.next();
			
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

}
