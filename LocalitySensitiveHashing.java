

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.text.StyledEditorKit.BoldAction;

public class LocalitySensitiveHashing {

	static final int primeFactor = 137;

	static boolean first = true;

	static ArrayList<ArrayList<Integer>> calculatetheCurveValue(int[][] signaturematrix, int row, int band,
			int rowmatrix, int CollengthMatrix, ArrayList<File> listOfFiles) {

		ArrayList<ArrayList<Integer>> candiatepairlist = new ArrayList<ArrayList<Integer>>();

		for (int k = 0; k < band; k++) {
			int temp[][] = new int[row][CollengthMatrix];

			for (int i = k * row; i < row + (k * row); i++)
				for (int j = 0; j < CollengthMatrix; j++) {

					temp[i % row][j] = signaturematrix[i][j];

				}
			completeSimiliarity(temp, row, CollengthMatrix, candiatepairlist, listOfFiles);
		}

		return candiatepairlist;

	}

	private static void completeSimiliarity(int[][] temp, int rowmatrix, int collengthMatrix,
			ArrayList<ArrayList<Integer>> candiatepairlist, ArrayList<File> listOfFiles) {

		HashMap<Integer, ArrayList<Integer>> map1 = new HashMap<Integer, ArrayList<Integer>>();

		for (int j = 0; j < collengthMatrix; j++) {
			int[] temp2 = new int[rowmatrix];

			for (int i = 0; i < rowmatrix; i++) {

				temp2[i] = temp[i][j];

			}

			int key = HashingForSimiliarityBetweenColumnPair(temp2);

			if (map1.containsKey(key)) {

				ArrayList<Integer> al = map1.get(key);
				al.add(j);

			}

			else {

				ArrayList<Integer> al = new ArrayList<Integer>();
				al.add(j);
				map1.put(key, al);

			}
		}

		Set s = map1.keySet();
		Iterator it = s.iterator();
		while (it.hasNext()) {

			int keylist = (Integer) it.next();

			ArrayList<Integer> al = map1.get(keylist);

			if (al.size() > 1) {

				HashSet<Integer> temp2 = new HashSet<Integer>();
				temp2.addAll(al);

				ArrayList<Integer> temp3 = new ArrayList<Integer>();
				temp3.addAll(temp2);

				if (first) {
					candiatepairlist.add(temp3);
					first = false;
					System.out.println(" candidate pair  ");

					for (Integer num : temp3) {
						System.out.print(listOfFiles.get(num).getName() + "	");

					}

					System.out.println();

				} else {

					boolean status1 = isListExists(temp3, candiatepairlist);

					if (!status1) {

						candiatepairlist.add(temp3);
						System.out.println(" candidate pair  ");

						for (Integer num : temp3) {
							System.out.print(listOfFiles.get(num).getName() + "	");

						}

						System.out.println();

					}

				}

			}

		}

	}

	private static int HashingForSimiliarityBetweenColumnPair(int[] temp) {

		long temp1 = 1;
		for (int i = 0; i < temp.length; i++) {
			int a = temp[i];

			temp1 = temp1 + (long) Math.pow(10, i) * a;
		}

		return (int) (temp1 % primeFactor);

	}

	static boolean isListExists(ArrayList<Integer> al, ArrayList<ArrayList<Integer>> candiatepairlist) {
		boolean status = false;

		for (ArrayList<Integer> temp : candiatepairlist) {
			boolean status1 = true;

			if (al.size() > temp.size()) {

			} else if (al.size() == temp.size()) {

				for (int i = 0; i < al.size(); i++) {

					if (!temp.contains(al.get(i)))
						status1 = false;

				}

			} else {

				for (int i = 0; i < al.size(); i++) {

					if (!temp.contains(al.get(i)))
						status1 = false;

				}

			}

			if (status1)
				status = status1;
		}
		return status;
	}

}
