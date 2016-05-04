package Practice;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Node {
	int data ;
	Node left;
	Node right;
	
	public Node() {
		super();
	}

	public Node(int  data) {
		super();
		this.data = data;
	}	
	


}

public class BSTPGM {

	    static Node root;

		public static void main(String[] args) {

			// Scanner scan = new Scanner(System.in);

			try {
				BufferedReader bfr = new BufferedReader(
						new InputStreamReader(System.in));

				int n = Integer.parseInt(bfr.readLine().trim());

				root = new Node(1);
	            
				BSTPGM bs = new BSTPGM();

				String str = "";
				String last = "";

				// System.out.println();

				Queue<Node> q = new LinkedList<>();
				q.add(root);

				while ((str = bfr.readLine()) != null) {

					String[] string = str.split("\\s+");

					if (string.length == 2) {

						// System.out.println(string[0]+" " + string[1]);

						bs.bfs(q, Integer.parseInt(string[0]), Integer.parseInt(string[1]));

					} else {
						last = str;

						// System.out.println(" element "+ str);
						break;
					}

				}

				int[] a = new int[Integer.parseInt(last)];

				// System.out.println(a.length);

				for (int i = 0; i < a.length; i++) {

					String temp1 = bfr.readLine().trim();

					// System.out.println(temp1);

					a[i] = Integer.parseInt(temp1);

				}

				// System.out.println(" before all swap ");

				// bs.inorder(root);

				for (int i = 0; i < a.length; i++) {

					// bs.swap(root, a, a.length - i);
					bs.maxheight(root, 1, a [i]);
					bs.inorder(root);
					System.out.println();

				}

				// System.out.println(" after all swap ");

				// bs.inorder(root);

				bfr.close();

			} catch (Exception e1) {

				System.out.println("Exception 1");
				e1.printStackTrace();

			}

		}

		void inorder(Node temp) {
			if (temp.data == -1) {

				return;
			}

			inorder(temp.left);
			System.out.print(temp.data + " ");
			inorder(temp.right);
		}

		void bfs(Queue<Node> q, int data1, int data2) {

			boolean flag1 = true;
			boolean flag2 = true;

			while (flag1 || flag2) {
				Node temp1 = q.remove();
				if (temp1.left == null && temp1.data != -1) {
					// System.out.println(" data 1 "+data1);
					temp1.left = new Node(data1);
					q.add(temp1.left);
					flag1 = false;

				}
				if (temp1.right == null && temp1.data != -1) {
					// System.out.println(" data 2 "+data2);
					temp1.right = new Node(data2);
					q.add(temp1.right);
					flag2 = false;
				}

			}

		}

		void maxheight(Node temp, int count, int level) {
			if (temp == null)
				return;

		

				if (count == level) {

					Node t = temp.right;
					temp.right = temp.left;
					temp.left = t;

				}

			

			maxheight(temp.left, count + 1, level);

			maxheight(temp.right, count + 1, level);

		}

	
}
