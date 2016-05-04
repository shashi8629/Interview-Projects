import java.util.Scanner;

public class DPhilProblem {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter the number of philospher");
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		Solution s = new Solution(n);

		Thread[] philospher = new Thread[n];
		for (int i = 0; i < philospher.length; i++) {
			philospher[i] = new Thread(s, "" + i);
			philospher[i].start();
		}

	}

}
