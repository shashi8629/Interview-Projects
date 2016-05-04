package Practice;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class BST1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Node root = new Node(4);
		root.left = new Node(2);
		root.right = new Node(7);

		root.left.left = new Node(1);
		root.left.right = new Node(3);

		root.right.left = new Node(6);
		// root.right.right=new Node(' ');

		Node t = new BST1().lca(root, 2, 3);

		System.out.println(t.data);

	}

	Node lca(Node root, int v1, int v2) {

		Stack<Node> st = new Stack<>();
		lca1(root, v1, v2, st);
		
		  System.out.println(" stack size"+stack.size()); 
		  System.out.println( " stack1 size"+stack1.size());
		 

		if (stack.size() >= stack1.size()) {

			for (int i = stack1.size() - 1; i >= 0; i--) {

				if (stack.get(i) == stack1.get(i)) {

					return stack.get(i);
				}

			}
		} else {

			for (int i = stack.size() - 1; i >= 0; i--) {

				if (stack.get(i) == stack1.get(i)) {

					return stack.get(i);
				}

			}

		}
		return null;

	}

	static Stack<Node> stack = new Stack<>();
	static Stack<Node> stack1 = new Stack<>();

	void lca1(Node root, int v1, int v2, Stack<Node> st) {
		if (root == null) {
			return;
		}

		st.push(root);
		
		if (root.data == v1) {
			stack.addAll(st);
		}
		if (root.data == v2) {
			stack1.addAll(st);
		}

		lca1(root.left, v1, v2, st);
		lca1(root.right, v1, v2, st);
		st.pop();

	}

}
