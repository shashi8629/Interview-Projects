package P1Final;

import java.math.BigDecimal;
import java.math.RoundingMode;

import DataMining.P1;

public class SolutionFraction {

	public static void main(Fraction[][] a, int t) {
		// TODO Auto-generated method stub
		// delete column vertices by providing value 9999
		// make it in recursion

		int n = t;
		int numberRemoveVertexes = 0;
		boolean[] vertexStatus = new boolean[n];
		for (int j = 0; j < n; j++) {
			vertexStatus[j] = false;
		}

		int oldRemainingVertex = -1;

		do {
			if (oldRemainingVertex != -1)
				oldRemainingVertex = numberRemoveVertexes;
			else
				oldRemainingVertex = 0;

			boolean[] status = new boolean[n];
			for (int j = 0; j < n; j++) {
				status[j] = true;
				for (int i = 0; i < n; i++) {
					if (a[i][j].compareTo(new Fraction("0")) != 0 && i != j
							&& a[i][j].compareTo(new Fraction("9999")) != 0 && !vertexStatus[j]) {
						status[j] = false;

					}

				}
				if (status[j]) {
					vertexStatus[j] = true;
					for (int i = 0; i < n; i++) {
						a[i][j] = new Fraction("9999");
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

			
			numberRemoveVertexes = 0;
			for (int i = 0; i < n; i++) {
				if (status[i]) {
					numberRemoveVertexes++;

					// System.out.println(" number of vertexes
					// "+numberRemoveVertexes );
					for (int j = 0; j < n; j++) {

						if (a[i][j].compareTo(new Fraction("0")) > 0 && a[i][j].compareTo(new Fraction("1")) < 0) {

							// System.out.println(" inside");

							int count = 0;
							for (int m = 0; m < n; m++) {

								if (a[m][j].compareTo(new Fraction("0")) > 0 && a[m][j].compareTo(new Fraction("1")) < 0
										&& m != i) {
									count++;
								}

							}
							Fraction adjustableFractionPart = null;
							if (count != 0) {
								adjustableFractionPart = a[i][j].divide(new Fraction("" + count));
								// System.out.println(" fraction part
								// "+adjustableFractionPart);

								for (int m = 0; m < n; m++) {

									if (a[m][j].compareTo(new Fraction("0")) > 0
											&& a[m][j].compareTo(new Fraction("1")) < 0 && m != i) {
										a[m][j] = a[m][j].add(adjustableFractionPart);
									}

								}
							}

						}
						a[i][j] = new Fraction("9999");

					}

				}

			}

			

		} while (oldRemainingVertex != numberRemoveVertexes);

		int deletedVertex = 0;
		for (int i = 0; i < n; i++) {
			if (vertexStatus[i]) {
				deletedVertex++;
				System.out.println("deletedVertex " + (i + 1));
			}

		}

		int remainingVertexes = n - deletedVertex;

		Fraction[][] b = new Fraction[remainingVertexes][remainingVertexes];
		for (int i = 0, k = 0; i < n; i++) {
			if (!vertexStatus[i]) {
				for (int j = 0, m = 0; j < n; j++) {
					if (!vertexStatus[j]) {
						b[k][m] = a[i][j];
						// System.out.print("----" +b[k][m]);
						m++;
					}

				}
				k++;
			}
			// System.out.println();
		}

		a = new Fraction[remainingVertexes][remainingVertexes];
		for (int i = 0; i < remainingVertexes; i++) {
			a[i] = new Fraction[remainingVertexes];
			for (int j = 0; j < remainingVertexes; j++) {

				a[i][j] = b[i][j];
				System.out.print("----" + a[i][j]);
			}
			System.out.println();
		}

		// second part
		// Vector matrix

		n = remainingVertexes;

		Fraction[] vector0 = new Fraction[n];
		for (int i = 0; i < n; i++) {
			vector0[i] = new Fraction("1").divide(new Fraction("" + n));

		}

		Fraction[][] vectorNNMatrix = new Fraction[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				vectorNNMatrix[i][j] = new Fraction("1").divide(new Fraction("" + n));

			}
		}
		/// matrix * multiplication // double positivePart = 0.125; //
		/// double negativePart = 1 - 0.125;
		Fraction[][] additionPart1Matrix = new Fraction[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				additionPart1Matrix[i][j] = a[i][j].multiply(new Fraction("875/1000"));
			}
		}
		Fraction[][] additionPart2Matrix = new Fraction[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				additionPart2Matrix[i][j] = vectorNNMatrix[i][j].multiply(new Fraction("125/1000"));
			}
		}

		boolean repeatStatus = true;
		Fraction[] vector1 = null;
		int iteration = 1;
		 BigDecimal[][] additionPart1MatrixDecimal = new BigDecimal[n][n];
		 BigDecimal[][] additionPart2MatrixDecimal = new BigDecimal[n][n];
		 BigDecimal vectors[] = new BigDecimal[vector0.length];
		 
		 BigDecimal[] vectors1 = null;
		 boolean decimalvalue=true;
		do {
			if(iteration<=5)
			{
			System.out.println(" iteration ------------" + iteration);
			vector1 = MatrixMultiplication(vector0, MatrixAddition(additionPart1Matrix, additionPart2Matrix));
			repeatStatus = measureDeviation(vector0, vector1);
			if (repeatStatus)
				 vector0 = vector1;
			// System.out.println("repeatStatus --" + repeatStatus);
			for (int i = 0; i < vector1.length; i++) {
				System.out.println(vector1[i]);
			}
    
			}
			else
			{
				
				System.out.println(" iteration ------------" + iteration);
				  if(decimalvalue)
				  {
				
					for (int i = 0; i < n; i++) {
						vectors[i] = P1.convertBigDecimal(""+vector1[i]);

					}
				 
				 for (int i = 0; i < n; i++) {
						for (int j = 0; j < n; j++) {
							additionPart1MatrixDecimal[i][j] =P1.convertBigDecimal(""+additionPart1Matrix[i][j]);
						}
					}
				 for (int i = 0; i < n; i++) {
						for (int j = 0; j < n; j++) {
							additionPart2MatrixDecimal[i][j] = P1.convertBigDecimal(""+additionPart2Matrix[i][j]);
						}
					}
				 decimalvalue=false;
			    }
				 vectors1 =SolutionBigDecimal.MatrixMultiplication(vectors, SolutionBigDecimal.MatrixAddition(additionPart1MatrixDecimal, additionPart2MatrixDecimal));
				 repeatStatus = SolutionBigDecimal.measureDeviation(vectors, vectors1);
				 if (repeatStatus)
					 vectors=vectors1;
				 
			
				 for (int i = 0; i < vectors1.length; i++) {
						System.out.println(vectors1[i].setScale(16, RoundingMode.HALF_UP));
					}
				
				
			}
			
					
			iteration++;	
			/*if(iteration==26 )
				break;*/
			
		} while (repeatStatus);

		/*for (int i = 0; i < vector1.length; i++) {
			System.out.println(vector1[i]);
		}
*/
	}

	
	
	private static boolean measureDeviation(Fraction[] vector0, Fraction[] vector1) {

		BigDecimal deviation = new BigDecimal("0.000000000001");
		int count = 0;
		for (int i = 0; i < vector1.length; i++) {

			// BigDecimal t1=
			// BigDecimal t2=

			if (!(P1.convertBigDecimal(vector0[i].toString()).subtract(P1.convertBigDecimal(vector1[i].toString()))
					.compareTo(deviation) > 0))
				count++;
		}
		if (count == vector1.length)
			return false;
		else
			return true;

	}

	private static Fraction[] MatrixMultiplication(Fraction[] vector, Fraction[][] matrixAddition) {
		// TODO Auto-generated method stub

		Fraction[][] matrixMultiplied = new Fraction[matrixAddition.length][1];

		// BigDecimal[][] matrixMultiplied= new BigDecimal
		// [matrixAddition.length] [1];

		for (int i = 0; i < matrixAddition.length; i++) {

			for (int j = 0; j < 1; j++) {
				matrixMultiplied[i][j] = new Fraction("0");

				for (int k = 0; k < matrixAddition.length; k++) { // aColumn

					matrixMultiplied[i][j] = matrixMultiplied[i][j].add(matrixAddition[i][k].multiply(vector[k]));
				}

			}

		}

		Fraction[] newVector = new Fraction[matrixAddition.length];
		for (int i = 0; i < newVector.length; i++) {
			newVector[i] = matrixMultiplied[i][0];
		}

		return newVector;
	}

	private static Fraction[][] MatrixAddition(Fraction[][] additionPart1Matrix, Fraction[][] additionPart2Matrix) {

		Fraction[][] matrixAdded = new Fraction[additionPart1Matrix.length][additionPart1Matrix.length];

		for (int i = 0; i < additionPart1Matrix.length; i++)

		{
			matrixAdded[i] = new Fraction[additionPart1Matrix.length];

			for (int j = 0; j < additionPart1Matrix.length; j++) {

				matrixAdded[i][j] = additionPart1Matrix[i][j].add(additionPart2Matrix[i][j]);

			}

		}

		// TODO Auto-generated method stub
		return matrixAdded;
	}

}
