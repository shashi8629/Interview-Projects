package com.hackton.sites;


class ListNode
{

	public ListNode(int data) {
		super();
		this.data = data;
	}
	
	int data;
	ListNode next;
	 
}

public class Listproblem {

	 static ListNode root;

	public static void main(String[] args) {
		
		root= new ListNode(1);
		
		ListNode temp=root;
		
		temp.next=new ListNode(2);
	
		temp.next.next=new ListNode(3);
		
		temp.next.next.next=new ListNode(4);
		
		temp.next.next.next.next=new ListNode(5);
		
		temp.next.next.next.next.next=new ListNode(6);
		
		temp.next.next.next.next.next.next=new ListNode(7);
		
		temp.next.next.next.next.next.next.next=new ListNode(8);
		
		ListNode temp1=new Listproblem().reverseBetween(root,1,2);
		
	    while(temp1!=null)
		{
	    System.out.println("data"+temp1.data);
		temp1=temp1.next;
		}
	 

	
	}

	
	
	
public ListNode swapPairs(ListNode head) {

	if(head==null || head.next==null)
    	return head;
	
	ListNode temp=head;
	ListNode temp1=null;
	ListNode temp2=null;
	//Node temp3=null;
	int count=1;
	ListNode tempprev=null;
	while(temp!=null)
	{
		if(count%2==1)
		{
			temp1=temp;
			temp=temp.next;
		}
		else
		{
			temp2=temp;
			//temp3=temp.link;
			temp=temp.next;
			
			 if(count==2)
			 {
				 head=temp2;
			 }
			 
			
			 if(tempprev!=null)
			 tempprev.next=temp2;
			 
			temp2.next=temp1;
			temp1.next=temp;
			tempprev=temp1;	
		}
		//System.out.println(" count"+count);
		 count++;
	}
	
	
if(count%2==0)
{
	tempprev.next=temp1;	
}

	return head;
        
    }





public boolean isPalindrome(ListNode head )
{

	ListNode temp= head;
	ListNode temp1= null;
	ListNode temp2= null;
	
	int count=0;
	while(temp!=null)
	{ 
		temp=temp.next;
		//System.out.println("count "+count);
		count++;
	}
	
	System.out.println(" counting "+count);
	int count1=0;
	temp= head;
	
	System.out.println(" count " +((count-1)/2-1));
	
	while(count1<=(count-1)/2)
	{ 
		temp=temp.next;
		//System.out.println("count "+count);
		count1++;
	}
	 
	temp1=temp;
	temp2=reversealinkedlist(head,(count-1)/2);
	
	
	if((count-1)%2==0)
	{	
    temp2=temp2.next;
	}
	
	
	
	System.out.println(" check   temp    1 "+ temp1.data);
	System.out.println(" check    temp   2 "+ temp2.data);
	
	while(temp2!=null)
	{
		if(temp2.data!=temp1.data)
		{
			System.out.println(" not pailindrome");
		  break;	
		}
		
		temp2=temp2.next;
		temp1=temp1.next;
	}
		
	return false;	
}




public ListNode reversealinkedlist(ListNode head,int count)
{
	
	ListNode temp1=head;
	ListNode prevnode=null;
	ListNode currnode=head;
	int count1=0;
	
	while(temp1!=null && count1<=count)
	{
		// nextnode=temp1.next;
		
		//System.out.println(" yses");
		
		 currnode=temp1;
		 temp1=temp1.next;
		 currnode.next=prevnode;
		 prevnode=currnode;
		 count1++;
		 
	}
	head=currnode;
	ListNode temp=head;;
	while(temp!=null)
	{ 
		
		System.out.println("data"+ temp.data);
		temp=temp.next;
	}
	return head;
	
}





 void recurusion(ListNode head)
 {
	 recurusion(head.next); 
	 
 }
 
 
 
 
	 public ListNode reverseBetween(ListNode head, int m, int n) {
	        
	    if(head==null ||head.next==null)
	            return head;
	       
	    ListNode temp=head;
	    if(n-m>0)
	    {
	      
	        int count=1;
	        ListNode prev1=null;
	        ListNode next1=null;
	        ListNode prev=null;
	        ListNode curr=null;
	       
	       if(m==1)
	       {
	           
	    	   
	    	 System.out.println(" linked ;lisr");
	        prev1=null;
	        next1=temp;
	        prev=null;
	        curr=temp;
	        int x=n-m;
	        int count1=0;
	        temp=curr;
	        
	        
	        
	        while(count1<=x )
	        {
	         temp=temp.next;
	         curr.next=prev;
	         prev=curr;
	         curr=temp;
	         count1++;
	        }
	        
	        head=curr;
	     }
	     else
	     {
	       while(count<m-1)
	       {
	           temp=temp.next;
	           count++;
	       }
	       
	        prev1=temp;
	        next1=temp.next;
	    
	        prev=temp;
	        curr=temp.next;
	        
	        int x=n-m;
	        int count1=1;
	        temp=curr;
	        
	        while(count1<=x)
	        {
	         temp=temp.next;
	         curr.next=prev;
	         prev=curr;
	         curr=temp;
	         count1++;
	        }
	        
	         prev1.next=curr;
	         next1.next=temp;
	         
	     }
	       
	        
	        
	         
	    } 
	    
	    return  head;
	        
	    }
	 

	 
	 
	 

public boolean cycle(ListNode root2) {
	
	// TODO Auto-generated method stub
	
	
	ListNode temp= root2;
	ListNode temp1= root2;
	
			
			while(temp1!=null)
			{
				if(temp1==temp)
					return true;
				
				temp1=temp1.next.next;
				temp=temp.next;
				
			}
	
		
			return false;
}




}
