package com.hackton.sites;




public class CycleLinkedLists {
	
	 static ListNode root;

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			
			root= new ListNode(23);
			
			ListNode temp=root;
			
			temp.next=new ListNode(10);
		
			temp.next.next=new ListNode(11);
			
			temp.next.next.next=new ListNode(12);
			
			temp.next.next.next.next=new ListNode(12);
			
			temp.next.next.next.next.next=new ListNode(11);
			
			temp.next.next.next.next.next.next=new ListNode(10);
			
			temp.next.next.next.next.next.next.next=new ListNode(23);
			
		     //temp.next.next.next.next.next.next.next.next=new ListNode(23);
		     
			//ListNode temp1=	new Listproblem().swapPairs(root);
			
			new Listproblem().cycle(root);
			
			ListNode temp1=null;
			
		    while(temp1!=null)
			{
		    System.out.println("data"+temp1.data);
			temp1=temp1.next;
			}
		 
	
		}
	
		
		
}




