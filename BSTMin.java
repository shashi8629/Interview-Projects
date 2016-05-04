package Practice;

import java.util.ArrayList;

public class BSTMin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node root = new Node(4);
	
		root.left = new Node(2);
		root.right = new Node(7);

		root.left.left = new Node(1);
		root.left.right = new Node(3);

		root.right.left = new Node(6);
		//root.right.left = new Node(8);
		
		int v1=7;
		int v2=6;
	
		lca(root,v1, v2);
		

	
	}
	
	
	
  static Node find(Node root,int v1)
  {
	  if(root==null)
		  return null;
	  
	  if(v1== root.data)
		  return root;
	  
	 Node temp= find(root.left,v1); 
	 if(temp!=null)
		 return temp;
	 
      temp=find(root.right,v1);
      if (temp!=null)
    	  return temp;
      else
          return null;
     
  
  }
	
	
	
	
	static Node lca(Node root,int v1,int v2)
	{
		
		Node temp=find(root,v1);
		Node temp1=find(root,v2);
	
		lcafind(root,temp,temp1);
		
	  return null;
		
	}
	
	
	 static void lcafind(Node root, Node a, Node b) {
		
		 if(root==null)
			 return ;
		 
		 
		Node t= lcafind(root.left,a);
		Node t1= lcafind(root.right,b);
		
		
		 
	}
	
	// one way 
	 //sort the linked lists
	 //sort the binary trees
	 
	 
	 
	 
	 
	
	

}
