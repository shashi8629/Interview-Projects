package P1Final;
/* Fraction.java */

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

class Fraction {

	private long numerator;
	private long denominator;
	String fraction;

	public Fraction(long Fraction) {
		this(Fraction + "");

	}

	public Fraction(String Fraction) {
		this.fraction = Fraction;
		if (fraction.contains("/")) {
			String[] fract = fraction.split("/");
			numerator = Integer.parseInt(fract[0]);
			denominator = Integer.parseInt(fract[1]);
			long hcfValue = hcf(numerator, denominator);

			if (numerator % hcfValue == 0)
				numerator = numerator / hcfValue;

			// System.out.println(" num"+numerator);
			if (denominator % hcfValue == 0)
				denominator = denominator / hcfValue;
			// System.out.println(" denom "+denominator);

		}

		else {
			numerator = Integer.parseInt(Fraction);
			denominator = 1;
		}

	}

	public String toString() {
		long hcfValue = hcf(numerator, denominator);
		if (numerator % hcfValue == 0)
			numerator = numerator / hcfValue;

		// System.out.println(" num"+numerator);
		if (denominator % hcfValue == 0)
			denominator = denominator / hcfValue;

		// System.out.println(" denom "+denominator);
		return (numerator + "/" + denominator);
	}

	public long getNumerator() {
		return numerator;
	}

	public void setNumerator(long numerator) {
		this.numerator = numerator;
	}

	public long getDenominator() {
		return denominator;
	}

	public void setDenominator(long denominator) {
		this.denominator = denominator;
	}

	public static long hcf(long x, long y) {
		if (x != 0 && y != 0) {
			long k = -1;
			while (true) {
				k = x % y;
				if (k == 0)
					break;
				x = y;
				y = k;
			}
			return y;
		} else
			return 1;

	}

	static private long lcm(long x, long y) {
		/* Remove the following line. */
		if (x != 0 && y != 0) {
			long firstValue = x;
			long secondValue = y;
			return (firstValue * secondValue) / hcf(x, y);
		} else
			return 1;

	}

	public Fraction(long numerator, long denominator) {

		this.numerator = numerator;
		this.denominator = denominator;
	}

	public Fraction add(Fraction f2) {
		Fraction f1 = this;
		long finaDenominator = lcm(f1.getDenominator(), f2.getDenominator());
		// System.out.println(finaDenominator);
		long numerator1 = (finaDenominator / f1.denominator) * f1.getNumerator();
		// System.out.println(numerator1);
		long numerator2 = (finaDenominator / f2.denominator) * f2.getNumerator();
		// System.out.println(numerator2);
		long finalNumerator = numerator1 + numerator2;
		// System.out.println(finalNumerator);
		// System.out.println(finaDenominator);

		return new Fraction(finalNumerator, finaDenominator);

	}

	public Fraction subtract(Fraction f2) {
		Fraction f1 = this;
		long finaDenominator = lcm(f1.getDenominator(), f2.getDenominator());
		long numerator1 = (finaDenominator / f1.denominator) * f1.getNumerator();
		long numerator2 = (finaDenominator / f2.denominator) * f2.getNumerator();
		long finalNumerator = numerator1 - numerator2;
		return new Fraction(finalNumerator, finaDenominator);

	}

	public Fraction multiply(Fraction f2) {
		Fraction f1 = this;
		long finaDenominator = f1.getDenominator() * f2.getDenominator();
		long finalNumerator = f1.getNumerator() * f2.getNumerator();
		return new Fraction(finalNumerator, finaDenominator);

	}

	public Fraction divide(Fraction f2) {

		Fraction f1 = this;
		Fraction result = null;

		try {

			long finaDenominator = -1;
			long finalNumerator = -1;
			if (f2.denominator != 0) {
				finaDenominator = f1.getDenominator() * f2.getNumerator();
				finalNumerator = f1.getNumerator() * f2.getDenominator();
				result = new Fraction(finalNumerator, finaDenominator);

			} else {

				System.out.println(" fraction division exception");
				result = null;
			}

		} catch (Exception e1) {
			System.out.println(" fraction division exception");

		}
		return result;

	}

	/* private fields within a Fraction. */

	public static void main(String[] argv) {

		System.out.println(new Fraction("5/24").add(new Fraction("3/8")));

		System.out.println(hcf(24, 8));

	}

	public int compareTo(Fraction fraction2) {

		// TODO Auto-generated method stub
		Fraction result = this.subtract(fraction2);

		if (result.getNumerator() == 0)
			return 0;
		else if (result.getNumerator() > 0)
			return 1;
		else
			return -1;

	}

}
