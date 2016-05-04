package Energy;

import java.util.Arrays;
import java.util.Scanner;

public class Algo1 {

	
	
	public static void main(String[] args) {

		 Scanner scan = new Scanner(System.in);
		int choice = 0;
		
		do {

			System.out.println("ENTER THE OPTION  ");
			System.out.println("1  FOR  NAIVE ALGORITHM  ");
			System.out.println("2  FOR  ALTERNATE  ALGORITHM ");
			System.out.println("3  FOR  OPTIMIZED ALGORITHM  ");
			System.out.println("4  FOR THREADED ALGORITHM ");
			System.out.println("5  FOR THREADED  BALANCED ALGORITHM ");
			System.out.println("6  FOR THREADED  UNBALANCED ALGORITHM ");
			choice = scan.nextInt();
			switch (choice) {
			case 1:
				System.out.println(" ENTER THE NUMBER OF RUN");
				fun1(scan.nextInt());
				break;
			case 2:
				System.out.println(" ENTER THE NUMBER OF RUN");
				fun2(scan.nextInt());
				break;
			case 3:
				System.out.println(" ENTER THE NUMBER OF RUN");
				fun3(scan.nextInt());
				break;
			case 4:
				System.out.println(" ENTER THE NUMBER OF RUN");
				int run=scan.nextInt();
				System.out.println(" ENTER THE NUMBER OF THREADS");
				int thread=scan.nextInt();
				multiThreadedSorting(run,thread);
				break;
			case 5:
				System.out.println(" ENTER THE NUMBER OF RUN");
				int run1=scan.nextInt();
				System.out.println(" ENTER THE NUMBER OF THREADS");
				int thread1=scan.nextInt();
				balanceMultiThreadedSorting(run1,thread1);
				break;
			case 6:
				System.out.println(" ENTER THE NUMBER OF RUN");
				int run2=scan.nextInt();
				System.out.println(" ENTER THE NUMBER OF THREADS");
				int thread2=scan.nextInt();
				unbalancedMultiThreadedSorting(run2,thread2);
				break;
			default:
				break;
			}

		} while (choice <= 6 && choice >= 1);

		System.out.println("EXIT");

	}

	static void fun1(int run) {

		int LENGTH = 1000; // initial length of array to sort
		int RUNS = run; // how many times to grow by 2?

		for (int i = 1; i <= RUNS; i++) {
			int[] a = ParallelMergeSort.createRandomArray(LENGTH);
			// run the algorithm and time how long it takes
			long startTime1 = System.currentTimeMillis();
			Selectionsort(a);
			long endTime1 = System.currentTimeMillis();

			if (!ParallelMergeSort.isSorted(a)) {
				throw new RuntimeException("not sorted afterward: " + Arrays.toString(a));
			}
			System.out.printf("%10d elements  =>  %6d ms \n", LENGTH, endTime1 - startTime1);
			LENGTH = LENGTH * 2;

			// double size of array for next time
		}

	}

	static void fun2(int run) {

		int LENGTH = 1000; // initial length of array to sort
		int RUNS = run; // how many times to grow by 2?

		for (int i = 1; i <= RUNS; i++) {
			int[] a = ParallelMergeSort.createRandomArray(LENGTH);
			// run the algorithm and time how long it takes
			long startTime1 = System.currentTimeMillis();
			insertionSort(a);
			long endTime1 = System.currentTimeMillis();

			if (!ParallelMergeSort.isSorted(a)) {
				throw new RuntimeException("not sorted afterward: " + Arrays.toString(a));
			}
			System.out.printf("%10d elements  =>  %6d ms \n", LENGTH, endTime1 - startTime1);
			LENGTH = LENGTH * 2;

			// double size of array for next time
		}

	}

	static void fun3(int run) {

		int LENGTH = 1000; // initial length of array to sort
		int RUNS = run; // how many times to grow by 2?

		for (int i = 1; i <= RUNS; i++) {
			int[] a = ParallelMergeSort.createRandomArray(LENGTH);
			// run the algorithm and time how long it takes
			long startTime1 = System.currentTimeMillis();
			heapSort(a);
			long endTime1 = System.currentTimeMillis();

			if (!ParallelMergeSort.isSorted(a)) {
				throw new RuntimeException("not sorted afterward: " + Arrays.toString(a));
			}
			System.out.printf("%10d elements  =>  %6d ms \n", LENGTH, endTime1 - startTime1);
			LENGTH = LENGTH * 2;

			// double size of array for next time
		}

	}

	public static void Selectionsort(int[] a) {

		for (int i = 0; i < a.length; i++) {
			int min = i;
			for (int j = i; j < a.length; j++) {
				if (a[min] > a[j]) {
					min = j;
				}
			}
			swap(a, min, i);
		}
	}

	public static void swap(int[] a, int pos1, int pos2) {
		int temp = a[pos1];
		a[pos1] = a[pos2];
		a[pos2] = temp;

	}

	public static void insertionSort(int[] a) {
		for (int i = 1; i < a.length; i++) {

			int temp = a[i];
			int j = i - 1;

			while (j >= 0 && a[j] > temp) {
				a[j + 1] = a[j];
				j--;
			}
			a[j + 1] = temp;
		}
	}

	public static void heapSort(int[] a) {
		/*
		 * int i,t; heapify(a,a.length);
		 * 
		 * for (i=a.length-1;i>0;i--) { t = a[0]; a[0] = a[i]; a[i] = t;
		 * adjust(a,i);
		 * 
		 * }
		 */

		for (int i = 1; i < a.length; i++)
			createHeap(a, i, a.length);

		for (int i = a.length - 1; i >= 0; i--) {
			swap(a, i, 0);
			maxheap(a, 0, i - 1);

		}

	}

	public static void createHeap(int[] a, int pos, int length) {

		while (pos > 0) {

			int root = 0;
			if (pos % 2 == 0)
				root = pos / 2 - 1;
			else
				root = pos / 2;

			if (a[root] < a[pos]) {
				swap(a, root, pos);
				pos = root;
			}

			else
				break;

		}

	}

	public static void adjustHeap(int[] a, int start, int length) {
		while (start <= length) {
			int left = 2 * start + 1;
			int right = 2 * start + 2;
			int pos = -1;
			if (left <= length) {
				if (right <= length) {
					if (a[left] >= a[right])
						pos = left;
					else
						pos = right;
				} else
					pos = left;
				if (a[pos] > a[start]) {
					swap(a, pos, start);
					start = pos;
				} else
					break;

			} else
				break;
		}

	}

	public static void maxheap(int[] a, int i, int n) {
		int left = 2 * i;
		int right = 2 * i + 1;
		int largest = -1;
		if (left <= n && a[left] > a[i]) {
			largest = left;
		} else {
			largest = i;
		}

		if (right <= n && a[right] > a[largest]) {
			largest = right;
		}
		if (largest != i) {
			swap(a, i, largest);
			maxheap(a, largest, n);
		}
	}

	static void heapify(int a[], int n) {
		int k, i, j, item;
		for (k = 1; k < n; k++) {
			item = a[k];
			i = k;
			j = (i - 1) / 2;
			while ((i > 0) && (item > a[j])) {
				a[i] = a[j];
				i = j;
				j = (i - 1) / 2;
			}
			a[i] = item;
		}
	}

	static void adjust(int a[], int n) {
		int i, j, item;
		j = 0;
		item = a[j];
		i = 2 * j + 1;
		while (i <= n - 1) {
			if (i + 1 <= n - 1)
				if (a[i] < a[i + 1])
					i++;
			if (item < a[i]) {
				a[j] = a[i];
				j = i;
				i = 2 * j + 1;
			} else
				break;
		}
		a[j] = item;
	}

	public static void multiThreadedSorting(int run,int thread) {

		try {
			 String [] args= new String[2];
			 args[0]=""+run;
			 args[1]=""+thread;
			ParallelMergeSort.main(args);
		} catch (Exception e1) {

		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void balanceMultiThreadedSorting(int run,int thread) {

		try {
			
			 String [] args= new String[2];
			 args[0]=""+run;
			 args[1]=""+thread;
			ParallelMergeSort.main(args);
			
		} catch (Exception e1) {

		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void unbalancedMultiThreadedSorting(int run ,int thread) {

		try {
			
	 String [] args= new String[2];
	 
	 args[0]=""+run;
	 args[1]=""+thread;
			 
			ParallelMergeSort.main(args);
			
		} catch (Exception e1) {

		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
