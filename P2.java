
//shashi shekhar

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class P2 {

	static final int band = 25;
	static final int row = 4;
	static final int numberOfHashingFunctions = 100;

	public static void main(String[] args) {

		BufferedReader bfr = null;

		try {

			ArrayList<File> listOfFiles1 = new ArrayList<File>();
			try {

			//bfr = new BufferedReader(
						//new InputStreamReader(new FileInputStream("/Users/shashi/Downloads/P2_Test_Files/filelist")));
				
			

				 bfr = new BufferedReader(new InputStreamReader(System.in));

				String fileName = null;

				//fileName="/Users/shashi/Downloads/P2_Test_Files"+fileName;

				String[] name = null;

				while ((fileName = bfr.readLine()) != null) {

					name = checkmultiplefilesinline(fileName);

					if (name == null) {

						if (fileName.trim().length() == 0)
							continue;
						if (fileName.trim().startsWith("#"))
							continue;

						// fileName = "/home/mwang2/test/coen281/" +
						// fileName.trim();

						//fileName = "/Users/shashi/Downloads/P2_Test_Files/" + fileName.trim();
						
						// validation of file
						
						fileName = "/home/mwang2/test/coen281/" + fileName.trim();
						

						fileName = fileName.trim();

						System.out.println("filename" + fileName);
						File f1 = new File(fileName);

						boolean isSymbolicLink = Files.isSymbolicLink(f1.toPath());
						if (isSymbolicLink) {
							System.out.println(" symbolink link   file  " + f1.getName());
							System.out.println(" provide proper input ");
							System.exit(0);
						}

						if (f1.exists()) {
							boolean validationsttaus = Validation.IsEmptyFile(f1);

							System.out.println(" empty file status " + validationsttaus);
							if (!validationsttaus) {

								System.out.println(" Data Cleaning status " + DataCleaning.removeWhiteSpace(f1));
								boolean validationsttaus1 = Validation.IsEmptyFile(f1);
								if (!validationsttaus1) {
									listOfFiles1.add(f1);
								} else {
									System.out.println(fileName + "  file is  empty or less than shingle size   ");
									System.out.println(" provide proper input  ");
									System.exit(0);

								}

							} else {

								System.out.println(fileName + "  file is  empty or less than shingle size   ");
								System.out.println(" provide proper input ");
								System.exit(0);

							}

						} else

						{
							System.out.println(fileName + " file does not exits ");
							System.exit(0);

						}

					} else {
						for (int i = 0; i < name.length; i++) {

							fileName = name[i];

							// fileName = "/home/mwang2/test/coen281/" +
							// fileName.trim();

							//fileName = "/Users/shashi/Downloads/P2_Test_Files/" + fileName.trim();
							fileName = "/home/mwang2/test/coen281/" + fileName.trim();

							fileName = fileName.trim();

							System.out.println("filename" + fileName);
							File f1 = new File(fileName);

							boolean isSymbolicLink = Files.isSymbolicLink(f1.toPath());
							if (isSymbolicLink) {
								System.out.println(" symbolink link   file  " + f1.getName());
								System.out.println(" provide proper input ");
								System.exit(0);
							}

							// validation of file
							
							if (f1.exists()) {
								boolean validationsttaus = Validation.IsEmptyFile(f1);

								System.out.println(" empty file status " + validationsttaus);
								if (!validationsttaus) {

									System.out.println(" Data Cleaning status " + DataCleaning.removeWhiteSpace(f1));
									boolean validationsttaus1 = Validation.IsEmptyFile(f1);
									if (!validationsttaus1) {
										listOfFiles1.add(f1);
									} else {
										System.out.println(fileName + "  file is  empty or less than shingle size   ");
										System.out.println(" provide proper input  ");
										System.exit(0);

									}

								} else {

									System.out.println(fileName + "  file is  empty or less than shingle size   ");
									System.out.println(" provide proper input ");
									System.exit(0);

								}

							} else

							{
								System.out.println(fileName + " file does not exits ");
								System.exit(0);

							}

						}

					}

				}

			} catch (Exception e1) {
				System.out.println(" No proper input ");
				if (bfr != null)
					bfr.close();

				System.exit(0);

			}

			HashSet<File> hash = new HashSet<File>();
			hash.addAll(listOfFiles1);

			ArrayList<File> listOfFiles = new ArrayList<File>();
			listOfFiles.addAll(hash);

			System.out.println(" no of files  " + listOfFiles.size());

			for (File file : listOfFiles) {
				System.out.println(" file name " + file.getName());
				Shingling.createShingles(file);
			}

			// created matrix for similiar documents according to shingles
			
			int[][] matrix = MinHashing.makeMatrixWithShinlesandDocuments(listOfFiles);

			//minhasing 
			
			int[] firstOriginalHashList = MinHashing.hashCodeConverter(MinHashing.rowlength, MinHashing.colength);
			int[][] hashingFunctionOutCome = new int[firstOriginalHashList.length][numberOfHashingFunctions];
			//prime number generation 
			int[] primeNumberArray = generatePrimeNumber(4000, 8000, numberOfHashingFunctions);
			int[] primefactor = generatePrimeNumber(5, 1000, numberOfHashingFunctions);
			System.out.println(" " + primeNumberArray.length);

			
			// hashing function generation 
			for (int j = 0; j < numberOfHashingFunctions; j++) {
				for (int i = 0; i < MinHashing.rowlength; i++) {

					int hashfunctionoutput = (primefactor[j] * (firstOriginalHashList[i] + 2));

					hashfunctionoutput = hashfunctionoutput % primeNumberArray[j];

					hashingFunctionOutCome[i][j] = hashfunctionoutput;
				}

			}

			// created signature matrix 
			int[][] signaturematrix = new int[numberOfHashingFunctions][MinHashing.colength];

			for (int i = 0; i < numberOfHashingFunctions; i++) {

				for (int j = 0; j < MinHashing.colength; j++) {

					signaturematrix[i][j] = 9999999;
				}
			}

			for (int i = 0; i < MinHashing.rowlength; i++) {

				for (int j = 0; j < MinHashing.colength; j++) {
					if (matrix[i][j] == 1) {
						// use j

						for (int p = 0; p < numberOfHashingFunctions; p++) {

							signaturematrix[p][j] = Math.min(signaturematrix[p][j], hashingFunctionOutCome[i][p]);

						}

					}

				}
			}

			System.out.println("*************** signature matrix *****************");

			for (int i = 0; i < numberOfHashingFunctions; i++) {

				for (int k = 0; k < MinHashing.colength; k++) {

					System.out.print(signaturematrix[i][k] + "		");

				}
				System.out.println();

			}

		

			//finding  candidatepairlist
			ArrayList<ArrayList<Integer>> candiatepairlist = LocalitySensitiveHashing.calculatetheCurveValue(
					signaturematrix, row, band, numberOfHashingFunctions, MinHashing.colength, listOfFiles);

			//calculated threshold value
			double threshold = Math.pow(1 / (double) band, 1 / (double) row);
			System.out.println("threshold value" + threshold);

			ArrayList<ArrayList<Integer>> candiatepairs = new ArrayList<ArrayList<Integer>>();
			HashMap<Integer, HashSet<Integer>> map = new HashMap<Integer, HashSet<Integer>>();

			ArrayList<HashSet<Integer>> setarray = new ArrayList<HashSet<Integer>>();

			boolean jaccobsimiliarity = false;

			for (int p = 0; p < candiatepairlist.size(); p++) {
				boolean flag = true;

				for (int k = 0; k < candiatepairlist.get(p).size(); k++) {

					for (int s = k + 1; s < candiatepairlist.get(p).size(); s++) {
						if (k != s) {
							if (jaccobsimiliarity(candiatepairlist.get(p).get(k), candiatepairlist.get(p).get(s),
									threshold, signaturematrix, numberOfHashingFunctions,listOfFiles)) {
								jaccobsimiliarity = false;
								grouping(setarray, candiatepairlist.get(p).get(k), candiatepairlist.get(p).get(s));

							}
						}

					}

				}

			}

			// display groups 
			int[] visited = new int[listOfFiles.size()];

			int counter = 1;
			if ( jaccobsimiliarity) {

				for (int i = 0; i < listOfFiles.size(); i++) {
					System.out.println(" group " + counter + " { " + listOfFiles.get(i).getName() + "}");
					counter++;
					visited[i] = 1;

				}

			}

			int count = 1;
			for (HashSet<Integer> s : setarray) {
				Iterator it1 = s.iterator();
				String str = "";
				while (it1.hasNext()) {

					Integer t2 = (Integer) it1.next();
					visited[t2] = 1;
					str = str + "{" + listOfFiles.get(t2).getName() + "}";

				}

				System.out.println(" group " + count + "  " + str);

				count++;

			}

			for (int i = 0; i < visited.length; i++) {
				if (visited[i] == 0) {
					visited[i] = 1;
					System.out.println(" group " + count + " { " + listOfFiles.get(i).getName() + "}");
					count++;
				}
			}

			bfr.close();

		} catch (Exception e1) {

			System.out.println(" Exception in main ");
			//e1.printStackTrace();
		}

		finally {
			try {

				if (bfr != null)
					bfr.close();

			} catch (Exception e1) {

			}

		}
	}

	private static String[] checkmultiplefilesinline(String fileName) {

		String[] str = null;
		if (fileName.trim().contains(" ")) {
			str = fileName.split("\\s+");

		}

		return str;

	}

	
	// grouping   documents 
	
	static boolean grouping(ArrayList<HashSet<Integer>> x, int col1, int col2)

	{
		boolean status = false;

		boolean status1 = false;
		boolean status2 = false;

		for (HashSet<Integer> al : x) {
			for (Integer al1 : al) {
				if (al1 == col1) {
					status1 = true;
				}

			}

		}

		for (HashSet<Integer> al : x) {
			for (Integer al1 : al) {
				if (al1 == col2) {
					status2 = true;
				}

			}

		}

		if (status1 && status2) {

		} else if (status1 && !status2) {

			for (HashSet<Integer> al : x) {
				boolean tempflag = false;
				for (Integer al1 : al) {
					if (al1 == col1) {

						tempflag = true;
					}

				}

				al.add(col2);

			}

		} else if (!status1 && status2) {

			for (HashSet<Integer> al : x) {
				boolean tempflag = false;

				for (Integer al1 : al) {
					if (al1 == col2) {

						tempflag = true;
					}

				}

				if (tempflag)
					al.add(col1);

			}

		} else {

			HashSet<Integer> t = new HashSet<Integer>();
			t.add(col1);
			t.add(col2);
			x.add(t);

		}

		return status;
	}
	
	

	//finding jaccob similiarity
	
	private static boolean jaccobsimiliarity(int k, int s, double threshold, int[][] signaturematrix, int rowlength, ArrayList<File> listOfFiles) {

		int numberOfCount = 0;

		for (int i = 0; i < rowlength; i++) {

			if (signaturematrix[i][k] == signaturematrix[i][s]) {
				numberOfCount++;
			}

		}

		double temp = (double) numberOfCount / (2 * numberOfHashingFunctions - numberOfCount);

		System.out.println("  similarity  value " + temp + " column " +listOfFiles.get(k).getName() + " column" + listOfFiles.get(s).getName());

		return temp >= threshold;

	}

	

	//finding genrate prime number 
	
	static int[] generatePrimeNumber(int firstnum, int lastnum, int count) {

		int[] a = new int[count];
		int k = 0;
		for (int i = firstnum; i < lastnum; i++) {

			boolean isPrime = true;
			for (int j = 2; j < i / 2; j++) {

				if (i % j == 0) {
					isPrime = false;
					break;
				}
			}

			if (isPrime) {
				if (k < count) {
					a[k] = i;
					k++;

				}

			}
		}

		return a;
	}
}
