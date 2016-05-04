package P1Final;

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

	public static boolean validationMatrixFraction(Fraction[] colArray) throws WrongInputException {
		Fraction fraction = new Fraction("" + 0);

		for (int i = 0; i < colArray.length; i++) {
			// System.out.println(bigDecimal);
			fraction = fraction.add(colArray[i]);

			

		}

	
		if (fraction.compareTo(new Fraction("1")) != 0)
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

			//BufferedReader in = new BufferedReader(new FileReader("/Users/shashi/Desktop/COEN281/t12.dat"));
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String line = null;
			BigDecimal[][] a = null;
			Fraction[][] f = null;
			String[][] string = null;

			int t = 0;
			int lineno = 0;
			int totalArraySizes = 0;
			int CounterIndex = 0;
			boolean decimaltype = true;
			try {
				while ((line = in.readLine()) != null) {

					if (IgnoreEmptyLine(line))
						continue;
					if (IgnoreLineStartsWithhash(line))
						continue;
					if (lineno == 0) {
						t = Integer.parseInt(line.trim().split("x")[0]);
						System.out.println(" vertices " + t);
						a = new BigDecimal[t][t];
						f = new Fraction[t][t];
						string = new String[t][t];
						totalArraySizes = t * t;
						for (int i = 0; i < t; i++) {
							a[i] = new BigDecimal[t];
							f[i] = new Fraction[t];
							string[i] = new String[t];
						}

					} else {

						String[] arr = line.split("\\s+");
						for (int i = 0; i < arr.length; i++) {
							if (CounterIndex < totalArraySizes) {
								int rowNo = CounterIndex / t;
								int columnNo = CounterIndex % t;
								//System.out.println("   vertices value " + arr[i].trim() + " row num " + rowNo	+ " column" + columnNo);

								if (!arr[i].trim().contains("/")) {
									string[rowNo][columnNo] = arr[i].trim();
								} else {
									decimaltype = false;
									string[rowNo][columnNo] = arr[i].trim();

								}

								CounterIndex++;
							}
						}

					}
					lineno++;
				}

				// add scanner code for reading input
				// validation of input

				System.out.println(" decimal type " + decimaltype);
				if (decimaltype) {

					for (int i = 0; i < t; i++)
						for (int j = 0; j < t; j++)
							a[i][j] = convertBigDecimal(string[i][j].trim());

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
				} else {
					for (int i = 0; i < t; i++)
						for (int j = 0; j < t; j++)
							f[i][j] = new Fraction(string[i][j].trim());

					Fraction[] temp = new Fraction[t];
					for (int j = 0; j < t; j++) {
						for (int i = 0; i < t; i++)
							temp[i] = f[i][j];

						try {

							Valiadtor.validationMatrixFraction(temp);

						} catch (Exception e) {

							System.out.println("  Not valid  fraction input  ");
							System.exit(0);

						}
					}

				}

			}

			catch (Exception e1) {

				System.out.println(" input error");
				e1.printStackTrace();
				System.exit(0);
			}

			// flag the vertices of sink nodes and assign 9999 to jth sink node
			// to
			// 9999 and self edges

			if (decimaltype)
				SolutionBigDecimal.main(a, t);
			else
				SolutionFraction.main(f, t);

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

}
