package OS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Hashtable;

public class WordCount  implements Runnable{
	
	
	private String fileName;
	static Hashtable<String, Integer>WordCountMap;

	public WordCount(String fileName, int startValueForThread, int endValueForThread) {
		super();
		this.fileName = fileName;
		this.startValueForThread = startValueForThread;
		this.endValueForThread = endValueForThread;
		WordCountMap=new Hashtable<>();
	}


	private int startValueForThread;
	private int endValueForThread;
	
	public  void wordcount()
	{
		
	BufferedReader bfr=null;
try
{
    bfr = new BufferedReader(new FileReader(new File(fileName)));
	String str="";
	int count=1;
	while((str=bfr.readLine())!=null)
	{
		
	if(count>=startValueForThread && count<=endValueForThread )
	{
	
		
		 String [] words= str.split("\\s+");
		 
		 for(int i=0;i<words.length;i++)
		 {
			synchronized (WordCountMap) {
				
				if(WordCountMap.containsKey(words[i]))
				   {
					  int tmpWordCount=  WordCountMap.get(words [i]);
					  tmpWordCount++;
					  WordCountMap.put(words[i],tmpWordCount);
				   }
				   else
				   {
					   WordCountMap.put(words[i],1);   
					   
				   }
				
			}
		   

		 }
 
	}
		
	 count++;
	}
	
	
}
catch(Exception e1 )
{
	
}
finally
{
	try
	{
	if(bfr!=null)
		bfr.close();
	}
	catch(Exception e1 )
	{
		
	}
}
	}
	


	@Override
	public void run() {	
		//System.out.println("Thread id "+Thread.currentThread());
		//System.out.println(" fileName"+ fileName +" startValueForThread  "+startValueForThread+ " endValueForThread  "+endValueForThread);
		wordcount();
		
	}
}
