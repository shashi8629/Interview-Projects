// shashi shekhar


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class DataCleaning {

	public static boolean removeWhiteSpace(File F1) {

		Boolean status = true;
		BufferedReader bfr = null;
		BufferedWriter bfwr = null;

		try {

			bfr = new BufferedReader(new InputStreamReader(new FileInputStream(F1)));
			String line = null;
			StringBuilder strb = new StringBuilder();
			while ((line = bfr.readLine()) != null) {
				line = line.trim();
				status = true;
				if (line.length() != 0) {
					String[] SplittedStrings = line.split("\\s+");

					StringBuilder SplittedStringAddition = new StringBuilder();

					for (int i = 0; i < SplittedStrings.length; i++) {

						SplittedStringAddition.append(SplittedStrings[i].trim());
						if (i != SplittedStrings.length - 1)
							SplittedStringAddition.append(" ");

					}

					strb.append(SplittedStringAddition.toString());
					strb.append(System.getProperty("line.separator"));

				}

			}

			if (F1.exists()) {
				F1.delete();
				F1.createNewFile();

			} else
				F1.createNewFile();

			bfr.close();
			bfwr = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(F1)));
			bfwr.write(strb.toString());
			bfwr.close();

		} catch (Exception e) {
			status = false;
			System.out.println(" exception in readimg file ..removing whitespaces  ");
		}

		finally {

			try {
				if (bfr != null)
					bfr.close();
			} catch (IOException e) {
				
				System.out.println("exception in Data cleaning class ");
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}

			try {
				if (bfwr != null)
					bfwr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}

		}

		return status;

	}

}
