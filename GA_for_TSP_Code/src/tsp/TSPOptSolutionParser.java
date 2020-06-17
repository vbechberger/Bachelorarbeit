package tsp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Represents a parser which reads the optimal solution from the TSPLIB.
 * 
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public class TSPOptSolutionParser {
		
	/**
	 * The TSP instance for which the optimal solution 
	 * is provided
	 */
	private TSPInstance tspInstance;
	
	/**
	 * The known optimal solution
	 */
	private Solution solution;
		
	/**
	 * Constructor.
	 * 
	 * @param file is the file with optimal solution for the given TSP instance.
	 * @param tspInstance  is the given TSP instance.
	 * @throws IOException if I/O error occurs.
	 */
	public TSPOptSolutionParser(File file, TSPInstance tspInstance) throws IOException {
		this.tspInstance = tspInstance;
		solution = getSolutionPathFromFile(file);		
	}
	
	/**
	 * Extracts the optimal solution from the given file.
	 * 
	 * @param file is the given file with the optimal solution.
	 * @return the optimal solution from the given file.
	 * @throws IOException if I/O error occurs.
	 */
	private Solution getSolutionPathFromFile(File file) throws IOException {
		
		String fullName = file.getName();
		String[] splitted = fullName.split("\\.");
		
		BufferedReader in = null;
		
		String name = splitted[0];
		String nameWithExt = name + ".tsp";
		
		if(!name.equals(tspInstance.getName()) && !nameWithExt.equals(tspInstance.getName()) ) {
			throw new IllegalArgumentException("The instance: "
					+ tspInstance.getName()
					+ " and the solution name: " + name + " does not correspond to each other!");
		}
		
		int dimension = tspInstance.getDimension();
		int[] tour = null;
		
		try {
			in = new BufferedReader(new FileReader(file));
			String line = null;
			
			while ((line = in.readLine()) != null) {
				line = line.trim();
				
				if (line.equals("TOUR_SECTION")) {			
					
					tour = new int[dimension];
					
					/*for the reason of different formatting 
					 * in different instances
					 * first write the whole tour in one string
					 */
					String tourString = "";
					while (!(line = in.readLine()).contentEquals("-1")) {
						tourString = tourString + " " + line;
					}	

					tourString = tourString.trim();
					String[] splittedLine = tourString.split("\\s+");
					
					/*parse the tour as string in the int array*/
					for(int i = 0; i < dimension; i++) {
						try {
							/*We start with city 0 therefore -1*/
							tour[i] = Integer.parseInt(splittedLine[i].trim()) - 1;
						} catch (NumberFormatException e) {
							e.printStackTrace();
						}
					}	
					
				} else if (line.equals("-1") || line.equals("EOF")) {
					break;
				} else if (line.isEmpty()) {
					//do nothing
				} else {
					
					//split the line into two strings, one before the :, the second - after it
					String[] tokens = line.split(":");
					
					//the first word in the line defines what information is given in the line
					String key = tokens[0].trim();
					
					//the information itself
					String content = tokens[1].trim();
						
					if (key.equals("DIMENSION")) {
						if(dimension != Integer.parseInt(content)) {
							throw new IllegalStateException("Dimensions"
									+ "of the instance and "
									+ "its solution are different");
						}

					} else {
						//do nothing			
					}
				}
			}
			
		 } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (in != null) 
	                try {
	                    in.close();
	                } catch (IOException e) {
	                }
	            
	        }		
		/**Make a TSP Instance with the parsed data*/
		return new Solution(dimension, tour);
		
	}

	/**
	 * 
	 * @return the optimal solution.
	 */
	public Solution getSolution() {
		return solution;
	}

}
