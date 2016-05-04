//shashi shekhar


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedHashSet;

public class Shingling {

	public static final int shingleSize = 5;

	public static final int primeNumber = 97;

	public static int hashingAlgorithm(String str) {
		return str.hashCode() % 97;
	}

	static LinkedHashSet<String> Shinglesset = new LinkedHashSet<String>();;

	public static File[] createShingles(File F1) {

		BufferedReader bfr = null;
		BufferedWriter bfwr = null;
		File fileWithShingles = null;
		
		File[] fileArryay = new File[2];

		try {

			fileWithShingles = new File(F1.getAbsolutePath() + "Shingles");
			StringBuilder strb = new StringBuilder();
			bfr = new BufferedReader(new InputStreamReader(new FileInputStream(F1)));
			String line = null;
			String reminingPartOfString = "";

			while ((line = bfr.readLine()) != null) {

				int start = 0;
				int end = shingleSize;
				String temp = reminingPartOfString + line;
				int lineLength = temp.length();

				String shingles = "";

				if (end <= lineLength)
					shingles = temp.substring(start, end);
				else
					shingles = temp.substring(start, lineLength);

				while (shingles.length() == shingleSize && end <= lineLength) {
					Shinglesset.add(shingles);
					start = start + 1;
					end = start + shingleSize;

					if (end <= lineLength)
						shingles = temp.substring(start, end);
					else
						shingles = temp.substring(start, lineLength);
				}

				if (shingles.length() > 0 && shingles.length() <= shingleSize) {
					// reminingPartOfString = shingles;
					Shinglesset.add(shingles);
				}
			}

			bfr.close();
			for (String str : Shinglesset) {
				strb.append(str);
				strb.append(System.getProperty("line.separator"));


			}


		} catch (Exception e1) {

			//e1.printStackTrace();
			System.out.println(" Exception in shingles ");

		}

		return fileArryay;

	}

}
