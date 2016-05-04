package P1Final;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SolutionBigDecimal {

	public static void main(BigDecimal[][] a, int t) {
		
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
					if (a[i][j].compareTo(BigDecimal.ZERO) != 0 && i != j
							&& a[i][j].compareTo(new BigDecimal("9999")) != 0 && !vertexStatus[j]) {
						status[j] = false;

					}

				}

				// System.out.println(" vertices status " + j + "--" +
				// status[j]);
				// delete column vertices by providing value 9999
				if (status[j]) {
					vertexStatus[j] = true;
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
			numberRemoveVertexes = 0;
			for (int i = 0; i < n; i++) {
				if (status[i]) {
					numberRemoveVertexes++;

					// System.out.println(" number of vertexes
					// "+numberRemoveVertexes );
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

									if (a[m][j].compareTo(BigDecimal.ZERO) > 0 && a[m][j].compareTo(BigDecimal.ONE) < 0
											&& m != i) {
										a[m][j] = a[m][j].add(adjustableFractionPart);
									}

								}
							}

						}
						a[i][j] = new BigDecimal("9999");

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

		BigDecimal[][] b = new BigDecimal[remainingVertexes][remainingVertexes];
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

		n = remainingVertexes;

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
		int iteration = 1;
		do {
			System.out.println(" iteration ------------" + iteration);
			vector1 = MatrixMultiplication(vector0, MatrixAddition(additionPart1Matrix, additionPart2Matrix));
			repeatStatus = measureDeviation(vector0, vector1);
			if (repeatStatus)
				vector0 = vector1;

			// System.out.println("repeatStatus --" + repeatStatus);

			for (int i = 0; i < vector1.length; i++) {
				System.out.println(vector1[i].setScale(16, RoundingMode.HALF_UP));
			}

			iteration++;
			//if (iteration == 5)
			//	break;

		} while (repeatStatus);

		/*for (int i = 0; i < vector1.length; i++) {
			System.out.println(vector1[i]);
		}*/

	}

	public  static boolean measureDeviation(BigDecimal[] vector0, BigDecimal[] vector1) {
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

	public  static BigDecimal[] MatrixMultiplication(BigDecimal[] vector, BigDecimal[][] matrixAddition) {
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

	public static BigDecimal[][] MatrixAddition(BigDecimal[][] additionPart1Matrix,
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
