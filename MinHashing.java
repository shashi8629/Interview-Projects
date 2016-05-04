//shashi shekhar


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

public class MinHashing {

	
	static int colength = 0;
	static int rowlength = 0;
	static final int primeFactor = 102197;

	static String shinglesFileName = "";

	static final int ShinglesSelectionPerfile = 100;

	public static int[][] makeMatrixWithShinlesandDocuments(List<File> files) {

		int[][] matrix = null;

		BufferedWriter bfwr = null;
		BufferedReader bfr = null;

		try {
			
			System.out.println(" shingle size " + Shingling.Shinglesset.size() + "    files size " + files.size());

			matrix = new int[Shingling.Shinglesset.size()][files.size()];
			int m = 0, n = 0;

			for (String shingle : Shingling.Shinglesset) {
				n = 0;
				for (File file : files) {
					if (searchShinglesinDocument(file, shingle))
						matrix[m][n] = 1;
					else
						matrix[m][n] = 0;
					n++;
				}
				m++;

			}

			

			colength = files.size();
			rowlength = Shingling.Shinglesset.size();

		} catch (Exception e1) {

			System.out.println("  matrix ");
			e1.printStackTrace();
		} finally {
			try {
				if (bfr != null)
					bfr.close();
				if (bfwr != null)
					bfwr.close();

			} catch (Exception e1) {

			}

		}
		return matrix;
	}

	public static boolean searchShinglesinDocument(File file, String shingle) {
		boolean status = false;
		BufferedReader bfr = null;

		try {

			bfr = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = "";

			while ((line = bfr.readLine()) != null) {
				if (line.indexOf(shingle) != -1)

				{
					status = true;
					break;
				}

			}

			bfr.close();
		} catch (Exception e1) {

			System.out.println("  exception in search shingles in document ");
			e1.printStackTrace();
		}

		finally {
			try {
				if (bfr != null) {

					bfr.close();
				}

			} catch (Exception e1) {

				System.out.println(" fianlly block ");
			}

		}
		return status;
	}

	// static int increasingvalue=0;
	public static int hascodegenerator(String s) {

		int intLength = s.length() / 4;
		long sum = 0;
		for (int j = 0; j < intLength; j++) {
			char c[] = s.substring(j * 4, (j * 4) + 4).toCharArray();
			long mult = 1;
			for (int k = 0; k < c.length; k++) {
				sum += c[k] * mult;
				mult *= 256;
			}
		}

		char c[] = s.substring(intLength * 4).toCharArray();
		long mult = 1;
		for (int k = 0; k < c.length; k++) {
			sum += c[k] * mult;
			mult *= 256;
		}

		return (int) (Math.abs(sum) % primeFactor);

	}

	public static int[] hashCodeConverter(int rowLength, int coLength) {

		int[] hashCodeArray = null;
		
		try {
			

			hashCodeArray = new int[MinHashing.rowlength];
			int k = 0;
			for (String str1 : Shingling.Shinglesset) {
				hashCodeArray[k] = hascodegenerator(str1);
				k++;

			}

		} catch (Exception e1) {

			System.out.println(" Exception in code  converter to shingles");
			//e1.printStackTrace();

		}

		finally {

			try {/*
					 * if(bfr!=null) bfr.close(); if(bfwr!=null) bfwr.close();
					 */
			} catch (Exception e2) {

			}
		}



		return hashCodeArray;
	}

}
