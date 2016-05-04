//shashi shekhar 


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Validation {

	public static boolean IsEmptyFile(File F1) {

		BufferedReader bfr = null;
		boolean status = true;

		try {

			bfr = new BufferedReader(new InputStreamReader(new FileInputStream(F1)));
			String line = null;
			
			int count=1;
			StringBuilder strb= new StringBuilder();
			
			while ((line = bfr.readLine()) != null) {
				line = line.trim();

				if (line.length() != 0 ) {
					
					if(count<50)
					{
					strb.append(line);
					
					}
					count++;
					
					status = false;

					//System.out.println(" line " + line);

				}

			}
			
			if((strb.toString().length()<Shingling.shingleSize))
			{
				status=true;;
				
				//System.out.println(" status validation false ");
				
			}
		

		} catch (Exception e) {
			System.out.println(" exception in  ..checking empty file   ");
			status = false;
		}

		finally {

			try {
				if (bfr != null)
					bfr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(" exception in validation of file ");
				// e.printStackTrace();
			}

		}

		return status;

	}

}
