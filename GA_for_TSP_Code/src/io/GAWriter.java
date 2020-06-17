package io;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Represents a help class to write data to a file.
 * 
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public class GAWriter {
	
	/**
	 * Writes the given data into the specified file,
	 * so that if the method will be called again with 
	 * a new data, it will be written in the same file at a
	 * new line.
	 * @param data The given data to be written at a new line 
	 * 			   of the specified file
	 * @param path The name of the file, where the data will be saved.
	 */
	public static void writeResultsIntoFile(String data, String path) {
		
		BufferedWriter out = null;

		try { 
			out = new BufferedWriter( 
					new OutputStreamWriter( 
							new FileOutputStream(path, true))); /*append = true*/
			out.write(data); 
			out.newLine(); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		}finally{ 
			try {
			out.close(); 
			} catch (IOException e) {
				e.printStackTrace(); 
			}
		}		 
	}
	
	

}
