package DataMining;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;

class Valiadtor {

	public static boolean validationMatrix(BigDecimal[] colArray) throws WrongInputException {
		BigDecimal bigDecimal = new BigDecimal(0);
		for (int i = 0; i < colArray.length; i++) {
			// System.out.println(bigDecimal);
			bigDecimal = bigDecimal.add(colArray[i]);

		}

		if (BigDecimal.ONE.subtract(bigDecimal).compareTo(new BigDecimal("0.0000000000001")) > 0)
			throw new WrongInputException();

		return true;
	}
}

class WrongInputException extends Exception {
	private static final long serialVersionUID = 1L;

	public WrongInputException() {
	}

	public WrongInputException(String message) {
		super(message);
	}

}

public class P1 {

	public static BigDecimal convertBigDecimal(String fraction) {
		if (fraction.contains("/")) {
			String[] fract = fraction.split("/");
			return new BigDecimal(fract[0]).divide(new BigDecimal(fract[1]), 16, RoundingMode.HALF_UP);
		}

		else
			return new BigDecimal(fraction);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {


			BufferedReader in = new BufferedReader(new FileReader("/Users/shashi/Desktop/COEN281/t19.dat"));
			String line = null;
			BigDecimal[][] a = null;
			int t = 0;
			int lineno = 0;
			int totalArraySizes=0;
			int CounterIndex=0;
			try
			{
			while ((line = in.readLine()) != null) {

				if (IgnoreEmptyLine(line))
					continue;
				if (IgnoreLineStartsWithhash(line))
					continue;
				if (lineno == 0) {
					t = Integer.parseInt(line.trim().split("x")[0]);
					System.out.println(" vertices " + t);
					a = new BigDecimal[t][t];
					totalArraySizes=t*t;
					for(int i=0;i<t;i++)
					{
						a[i] = new BigDecimal[t];
					}
				
				} else {

					String[] arr = line.split("\\s+");
					for (int i = 0; i < arr.length; i++)
					{ 
						if(CounterIndex<totalArraySizes)
						{
						 int rowNo=CounterIndex/t;
						 int columnNo=CounterIndex%t;
						// System.out.println("   vertices value "+arr[i].trim()+" row num "+ rowNo+ " column"+ columnNo);
						 a[rowNo][columnNo] = convertBigDecimal(arr[i].trim());
						 CounterIndex++;
						}
					}
					
				}
				lineno++;
			}

			// add scanner code for reading input
			// validation of input

			BigDecimal[] temp = new BigDecimal[t];
			for (int j = 0; j < t; j++) {
				for (int i = 0; i < t; i++)
					temp[i] = a[i][j];

				try {
					Valiadtor.validationMatrix(temp);
				} catch (Exception e) {

					System.out.println("  Not valid input  ");
					System.exit(0);

				}
			}
			}
			
			catch(Exception e1)
			{
				
				System.out.println(" input error");
			}
			
			

			// flag the vertices of sink nodes and assign 9999 to jth sink node
			// to
			// 9999 and self edges
			// delete column vertices by providing value 9999
			// make it in recursion

			int n = t;
			int numberRemoveVertexes = 0;
			boolean [] vertexStatus= new boolean[n];
			for (int j = 0; j < n; j++) {
				vertexStatus[j]=false;
			}
			
			int oldRemainingVertex=-1;
			
			do {
				if(oldRemainingVertex!=-1)
					oldRemainingVertex=numberRemoveVertexes;
				else
					oldRemainingVertex=0;
					
					
				
					
				boolean[] status = new boolean[n];
				for (int j = 0; j < n; j++) {
					status[j] = true;
					  for (int i = 0; i < n; i++) {
						if (a[i][j].compareTo(BigDecimal.ZERO) != 0 && i != j && a[i][j].compareTo(new BigDecimal("9999")) != 0 && ! vertexStatus[j])
						{
							status[j] = false;
						    
						}
						

					}

					// System.out.println(" vertices status " + j + "--" +
					// status[j]);
					// delete column vertices by providing value 9999
					if (status[j])
					{
						vertexStatus[j]=true;
						for (int i = 0; i < n; i++) {
							a[i][j] = new BigDecimal("9999");
						}
					}
				}

				int l = 0;
				for (int i = 0; i < n; i++) {
					if (status[i])
						break;
					else
						l++;

				}

				if (l == n)
					break;

				// display
				/*
				 * for (int i = 0; i < n; i++) { for (int j = 0; j < n; j++) {
				 * System.out.print("----" + a[i][j]); } System.out.println();
				 * 
				 * }
				 */
				// delete the row vertices and adjust the value in other
				// vertices
				// row and column
				numberRemoveVertexes = 0;
				for (int i = 0; i < n; i++) {
					if (status[i]) {
						numberRemoveVertexes++;
						
						//System.out.println(" number of vertexes "+numberRemoveVertexes );
						for (int j = 0; j < n; j++) {

							if (a[i][j].compareTo(BigDecimal.ZERO) > 0 && a[i][j].compareTo(BigDecimal.ONE) < 0) {

								// System.out.println(" inside");

								int count = 0;
								for (int m = 0; m < n; m++) {

									if (a[m][j].compareTo(BigDecimal.ZERO) > 0 && a[m][j].compareTo(BigDecimal.ONE) < 0
											&& m != i) {
										count++;
									}

								}
								BigDecimal adjustableFractionPart = null;
								if (count != 0) {
									adjustableFractionPart = a[i][j].divide(new BigDecimal(count), 16,
											RoundingMode.HALF_UP);
									// System.out.println(" fraction part
									// "+adjustableFractionPart);

									for (int m = 0; m < n; m++) {

										if (a[m][j].compareTo(BigDecimal.ZERO) > 0
												&& a[m][j].compareTo(BigDecimal.ONE) < 0 && m != i) {
											a[m][j] = a[m][j].add(adjustableFractionPart);
										}

									}
								}

							}
							a[i][j] = new BigDecimal("9999");

						}

					}

				}

				// display
				
				 /*for (int i = 0; i < n; i++) 
				 { for (int j = 0; j < n; j++) {
				  System.out.print("----" + a[i][j]); 
				  } 
				  System.out.println();
				  
				  }
				 System.out.println("**********************:");
				 System.out.println();*/

				// copying old array into new array and remove vertexes

				

				// System.out.println(" remainingVertexes " +
				// remainingVertexes);

				

				// delete array a
				/*for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						a[i][j] = null;
					}

				}*/

				
			//	n = remainingVertexes;
				 
			} while (oldRemainingVertex!=numberRemoveVertexes );
			
			 int deletedVertex=0;
			for( int i=0;i<n ;i++)
			{
				if(vertexStatus[i])
				{
				deletedVertex++;
				System.out.println("deletedVertex " + (i+1));
				}

	
			}
			
	        int remainingVertexes=n-deletedVertex;
			
			BigDecimal[][] b = new BigDecimal[remainingVertexes][remainingVertexes];
			for (int i = 0, k = 0; i < n; i++) {
				if (!vertexStatus[i]) {
					for (int j = 0, m = 0; j < n; j++) {
						if (!vertexStatus[j]) {
							b[k][m] = a[i][j];
							 //System.out.print("----" +b[k][m]);
							m++;
						}

						
					}
					k++;
				}
				 //System.out.println();
			}
			
			a = new BigDecimal[remainingVertexes][remainingVertexes];
			for (int i = 0; i < remainingVertexes; i++) {
				a[i] = new BigDecimal[remainingVertexes];
				for (int j = 0; j < remainingVertexes; j++) {

					a[i][j] = b[i][j];
					System.out.print("----" + a[i][j]);
				}
				System.out.println();
			}

			// second part
			// Vector matrix
			 
			n=remainingVertexes;

			BigDecimal[] vector0 = new BigDecimal[n];
			for (int i = 0; i < n; i++) {
				vector0[i] = new BigDecimal("1").divide(new BigDecimal(n), 16, RoundingMode.HALF_UP);

			}

			BigDecimal[][] vectorNNMatrix = new BigDecimal[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					vectorNNMatrix[i][j] = new BigDecimal("1").divide(new BigDecimal(n), 16, RoundingMode.HALF_UP);

				}
			}
			/// matrix * multiplication // double positivePart = 0.125; //
			/// double negativePart = 1 - 0.125;
			BigDecimal[][] additionPart1Matrix = new BigDecimal[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					additionPart1Matrix[i][j] = a[i][j].multiply(new BigDecimal("0.875"));
				}
			}
			BigDecimal[][] additionPart2Matrix = new BigDecimal[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					additionPart2Matrix[i][j] = vectorNNMatrix[i][j].multiply(new BigDecimal("0.125"));
				}
			}

			boolean repeatStatus = true;
			BigDecimal[] vector1 = null;
			int iteration = 0;
			do {
				System.out.println(" iteration ------------" + iteration);
				vector1 = MatrixMultiplication(vector0, MatrixAddition(additionPart1Matrix, additionPart2Matrix));
				repeatStatus = measureDeviation(vector0, vector1);
				if (repeatStatus)
					vector0 = vector1;

				// System.out.println("repeatStatus --" + repeatStatus);

				for (int i = 0; i < vector1.length; i++) {
					System.out.println(vector1[i]);
				}

				iteration++;
				if (iteration == 3)
					break;

			} while (repeatStatus);

			for (int i = 0; i < vector1.length; i++) {
				System.out.println(vector1[i]);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static boolean IgnoreLineStartsWithhash(String line) {
		// TODO Auto-generated method stub

		if (line.trim().startsWith("#"))
			return true;

		return false;
	}

	private static boolean IgnoreEmptyLine(String line) {
		// TODO Auto-generated method stub
		if (line == null || line.length() == 0 || line.trim().length() == 0)
			return true;

		return false;

	}

	private static boolean measureDeviation(BigDecimal[] vector0, BigDecimal[] vector1) {
		// TODO Auto-generated method stub
		BigDecimal deviation = new BigDecimal("0.000000000001");

		int count = 0;

		for (int i = 0; i < vector1.length; i++) {

			if (!(vector0[i].subtract(vector1[i]).compareTo(deviation) > 0))
				count++;
		}
		if (count == vector1.length)
			return false;
		else
			return true;

	}

	private static BigDecimal[] MatrixMultiplication(BigDecimal[] vector, BigDecimal[][] matrixAddition) {
		// TODO Auto-generated method stub

		BigDecimal[][] matrixMultiplied = new BigDecimal[matrixAddition.length][1];

		// BigDecimal[][] matrixMultiplied= new BigDecimal
		// [matrixAddition.length] [1];

		for (int i = 0; i < matrixAddition.length; i++) {

			for (int j = 0; j < 1; j++) {
				matrixMultiplied[i][j] = new BigDecimal("0");

				for (int k = 0; k < matrixAddition.length; k++) { // aColumn

					matrixMultiplied[i][j] = matrixMultiplied[i][j].add(matrixAddition[i][k].multiply(vector[k]));
				}

			}

		}

		BigDecimal[] newVector = new BigDecimal[matrixAddition.length];
		for (int i = 0; i < newVector.length; i++) {
			newVector[i] = matrixMultiplied[i][0];
		}

		return newVector;
	}

	private static BigDecimal[][] MatrixAddition(BigDecimal[][] additionPart1Matrix,
			BigDecimal[][] additionPart2Matrix) {

		BigDecimal[][] matrixAdded = new BigDecimal[additionPart1Matrix.length][additionPart1Matrix.length];

		for (int i = 0; i < additionPart1Matrix.length; i++)

		{
			matrixAdded[i] = new BigDecimal[additionPart1Matrix.length];

			for (int j = 0; j < additionPart1Matrix.length; j++) {

				matrixAdded[i][j] = additionPart1Matrix[i][j].add(additionPart2Matrix[i][j]);

			}

		}

		// TODO Auto-generated method stub
		return matrixAdded;
	}

}
