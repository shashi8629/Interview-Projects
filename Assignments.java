package DataMining;


 class  Student 
 {
	 
 private	int  happinessFactor=0;

public int getHappinessFactor() {
	return happinessFactor;
}

public void setHappinessFactor(int happinessFactor) {
	this.happinessFactor = happinessFactor;
	
}


public void display()
{
	
	System.out.println(" happiness factor "+ happinessFactor);
}

public void   reading ()
{
	
	if( getHappinessFactor()+4>=10)
	{
	 
	 System.out.println(" reading"); 
	 setHappinessFactor(10);
		}
 else
 {
	
   System.out.println(" reading");
    setHappinessFactor(getHappinessFactor()+4);
 
 }
 
	 
}


public  void   sadMessage()
{
	if(getHappinessFactor()-1 >= -100)
	{
System.out.println("ouch");
setHappinessFactor(getHappinessFactor()-1);

 }
  
	 
}
 	 
 }
 
 
 class Grumpy extends Student
 {
	
	 
	 public void  reading ()
	 {
		 
		if( getHappinessFactor()+4>=10)
		{
		 
		 System.out.println(" reading");
		 
		 setHappinessFactor(10);
		 
		 System.out.println(" posting");
			}
	 else
	 {
		
       System.out.println(" reading");
	    setHappinessFactor(getHappinessFactor()+4);
		System.out.println(" posting");
		 
	 }
	 
	 
 }
	 
 }
 
 class  Sleepy  extends Student
 {
	 
 
	 public  void   sadMessage()
	 {
	 	
		if(getHappinessFactor()-2 >= -100)
		{
System.out.println("ouch");
setHappinessFactor(getHappinessFactor()-1);
System.out.println("ouch");
setHappinessFactor(getHappinessFactor()-1); 
	 }
	  
 
	 } 
 }
 
 
 
public class Assignments {
	
	
	
	
	public void flame( boolean  flameEmailStatus, Student student )
	{
		
		
		if(flameEmailStatus)
		{	
			student.sadMessage();
			student.reading();
			
		}
		/*
		else
		{
			student.reading();
			
		}
		*/
		
		student.display();
		
		
	}
	
	
	/*
	There are two types of Students: Sleepy, Grumpy. Students are mostly similar, but a little bit different. All students have a current happiness factor which is between in 10 and ­100 (initially 0). They all respond to the flame() message when they are flamed by e­mail. Here's what they do when flamed:
	1. Print "ouch" and decrement happiness by 1, unless it is already at ­100. Sleepy grad students are a little different— they go through the above process twice.
	2. Then they read their favorite newsgroup to relax (print "reading"). This causes the happiness factor to go up by 4, although it never goes above 10. In addition, the Grumpy grad student posts to the newsgroup after reading (print "posting").
	Write the code for Sleepy, Grumpy, and any support classes. You may omit constructors, and don't worry about the public/private/etc. keywords. Just write the code required by the flame() message, and use inheritance to minimize code repetition.
*/
	
	public static void main (String[] args) {
		// TODO Auto-generated method stub
		
		Student grumpy= new Grumpy();
		
		Student sleepy= new Sleepy();
		
		Boolean  flameEmail=true;
		
		Assignments  assign=new Assignments();
		
		assign.flame(flameEmail,grumpy);
		assign.flame(flameEmail,grumpy);
		assign.flame(flameEmail,grumpy);
		assign.flame(flameEmail,grumpy);
		assign.flame(flameEmail,grumpy);

		
		assign.flame(flameEmail,sleepy);
		assign.flame(flameEmail,sleepy);
		assign.flame(flameEmail,sleepy);
		assign.flame(flameEmail,sleepy);
		assign.flame(flameEmail,sleepy);
		assign.flame(flameEmail,sleepy);



		
		
		

	}

}
