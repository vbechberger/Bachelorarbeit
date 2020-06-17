package io;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Represents a help class to read a 
 * certain line in the given file.
 * 
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public class GAReader {
	
	/**
	 * Returns the line of the given file of the specified number
	 * @param lineNumber is the number of the line which is looked for
	 * @param input is the given file
	 * @return the line 
	 * @throws IOException if I/O error occurs.
	 */
	public static String getNline(int lineNumber, BufferedReader input) throws IOException {
		
		String string;
		int i = 1;
		while((string = input.readLine()) != null && i < lineNumber) {
			i++;
		}
		return string;
	}
}
