package OS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class Assignment1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scan = new Scanner(System.in);
		System.out.println(" Enter the file name ");
		String str = scan.nextLine();
		System.out.println(" Enter the Number of thread ");
		int numberOfThread = scan.nextInt();
		String[] files = str.split("\\s+");
		int fileCount = 0;
		try {
			while (fileCount < files.length) {
				String fileName = files[fileCount];
				fileName = fileName;

				BufferedReader bfr = new BufferedReader(new FileReader(new File(fileName)));
				int lineCount = 0;
				while (bfr.readLine() != null) {
					lineCount++;
				}
				bfr.close();
				
				System.out.println(" line count "+ lineCount);

				int[] startValueForThread = new int[numberOfThread];
				int[] endvalueForThread = new int[numberOfThread];
				
				
				int partion = lineCount / numberOfThread;
				
				System.out.println(" partion value "+partion);
				
				Thread[] t = new Thread[numberOfThread];
				for (int i = 0; i < numberOfThread; i++) {
					startValueForThread[i] = partion * i + 1;
					endvalueForThread[i] = partion * (i + 1);
					
					 if(i==numberOfThread-1)
						 endvalueForThread[i]=lineCount;

					t[i] = new Thread(new WordCount(fileName, startValueForThread[i], endvalueForThread[i]));
					t[i].start();

				}

				for (int i = 0; i < numberOfThread; i++) {

					t[i].join();
				}

				Set<Entry<String, Integer>> s = WordCount.WordCountMap.entrySet();
				Iterator<Entry<String, Integer>> itentry = s.iterator();
				while (itentry.hasNext()) {
					Map.Entry<String, Integer> temp = itentry.next();
					System.out.println(" WORD  " + temp.getKey() + "  COUNT  " + temp.getValue());
				}
				
				fileCount++;

			}
			scan.close();

		} catch (Exception e1) {

			System.out.println("Exception in main ");
			e1.printStackTrace();
		}

	}

}
