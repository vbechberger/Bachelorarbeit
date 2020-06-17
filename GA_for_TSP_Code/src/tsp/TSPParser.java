package tsp;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Represents a parser for the TSP instance from the TSPLIB.
 * This class uses the approach to fill the distance table 
 * with the edge weights (distances) offered by
 * https://github.com/dhadka/TSPLIB4J/blob/master/src/org/moeaframework/problem/tsplib/EdgeWeightMatrix.java
 * 
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public class TSPParser {
		
	/**
	 * TSP Instance
	 */
	private TSPInstance tspInstance; 
		
	/**
	 * Constructor
	 * 
	 * @param file is the given file with the TSP instance.
	 * @throws IOException if I/O error occurs.
	 */
	public TSPParser(File file) throws IOException {
		loadFromFile(file);		
	}
	
	/**
	 * Loads the data from the given file and creates
	 * a TSP instance.
	 * 
	 * @param file is the given file.
	 * @throws IOException if I/O error occurs.
	 */
	private void loadFromFile(File file) throws IOException {
		BufferedReader in = null;
		
		String name = null;	
		String comment = null;
		int dimension = -1;
		EdgeWeightType edgeWeightType = null;
		EdgeWeightFormat edgeWeightFormat = null;
		double[][] data = null;
		
		try {
			in = new BufferedReader(new FileReader(file));
			String line = null;
			
			while ((line = in.readLine()) != null) {
				line = line.trim();
				
				if (line.equals("NODE_COORD_SECTION")) {
					
					data = new double[dimension][2];
					
					int count = 0;
					while ((line = in.readLine()) != null && count < dimension) {
						line = line.trim();
						
						String[] splittedString = line.split("\\s+");
						
						double [] numbers = new double[splittedString.length];
						
					    for(int i = 0; i < splittedString.length; i++) {
					         numbers[i] = Double.parseDouble(splittedString[i].trim());
					    }
					    
					    data[count][0] = numbers[1];
					    data[count][1] = numbers[2];					    		
					    count++;
					}						
					
				} else if (line.equals("EDGE_WEIGHT_SECTION")) {
					
					data = parseEdgeWeightSection(in, edgeWeightFormat, dimension);
										
				} else if (line.equals("DISPLAY_DATA_SECTION")) {
					/*We do not need to parse the coordinates,
					 *since the weights are explicitly given in the edge weight section.
					 */
					
					//jump over this section
					for(int i = 0; i < dimension; i++) {
						line = in.readLine();						
					}
				} else if (line.equals("EOF")) {
					break;
				} else if (line.isEmpty()) {
					//do nothing
				} else {
					
					//split the line into two strings, one before the :, the second - after it
					String[] tokens = line.split(":");
					
					//use trim() function to remove the spaces
					
					//the first word in the line defines what information is given in the line
					String key = tokens[0].trim();
					
					//the information itself
					String content = tokens[1].trim();
					
					if (key.equals("NAME")) {
						name = content;
						
					} else if (key.equals("COMMENT")) {
						if (comment == null) {
							comment = content;
						} else {
							comment = comment + "\n" + content;
						}						
						
					} else if (key.equals("TYPE")) {
						
						//do nothing: we have only the tsp instances here
						
					} else if (key.equals("DIMENSION")) {
						dimension = Integer.parseInt(content);
						
					} else if (key.equals("EDGE_WEIGHT_TYPE")) {
						edgeWeightType = EdgeWeightType.valueOf(content);
						
					} else if (key.equals("DISPLAY_DATA_TYPE")) {
						/*Do nothing as this type is given 
						 *inconsistently and does not influence 
						 *the way of parsing the data.
						 */						 
						
					} else if (key.equals("EDGE_WEIGHT_FORMAT")) {
						edgeWeightFormat = EdgeWeightFormat.valueOf(content);
												
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
		
		/*Make a TSP Instance with the parsed data*/
		tspInstance = new TSPInstance(name, comment,
				dimension, data, edgeWeightType);
	}
	
	
	/**
	 * Parses the section with edge weights (distances).
	 * 
	 * @param in is the given file.
	 * @param edgeWeightFormat is the format of the edge weights.
	 * @param dimension is the number of cities.
	 * @return the distances in 2D array.
	 * @throws IOException if I/O error occurs.
	 */
	private double[][] parseEdgeWeightSection(BufferedReader in, 
											  EdgeWeightFormat edgeWeightFormat, 
											  int dimension) throws IOException {
		
		double [][] weights = new double[dimension][dimension];
		
		Queue<Double> entries = new LinkedList<Double>();
		
		switch(edgeWeightFormat) {
		case UPPER_ROW: 
			for (int i = 0; i < dimension - 1; i++) {
				for (int j = i + 1; j < dimension; j++) {
					if (entries.isEmpty()) {
						readNextLine(in, entries);
					}
					weights[i][j] = entries.poll();
					weights[j][i] = weights[i][j];
				}
			}
			break;
		case UPPER_DIAG_ROW:
			for (int i = 0; i < dimension; i++) {
				for (int j = i; j < dimension; j++) {
					if (entries.isEmpty()) {
						readNextLine(in, entries);
					}
					weights[i][j] = entries.poll();
					weights[j][i] = weights[i][j];
				}
			}
			break;
		case FULL_MATRIX:
			for (int i = 0; i < dimension; i++) {
				for (int j = 0; j < dimension; j++) {
					if (entries.isEmpty()) {
						readNextLine(in, entries);
					}
					weights[i][j] = entries.poll();					
				}
			}
			break;
		case LOWER_DIAG_ROW:
			for (int i = 0; i < dimension; i++) {
				for (int j = 0; j < i + 1; j++) {
					if (entries.isEmpty()) {
						readNextLine(in, entries);
					}
					weights[i][j] = entries.poll();
					weights[j][i] = weights[i][j];
				}
			}
			break;
		case FUNCTION: throw new IllegalArgumentException("The "
				+ "edge weight format: " + EdgeWeightFormat.FUNCTION.toString() 
				+ " has no Edge Weight Section");
		default:
			throw new IllegalArgumentException("Unsupported matrix type");			
		}
		
		if (!entries.isEmpty()) {
			throw new IOException("edge weight matrix is longer than expected");
		}
		
		return weights;
	}
	

	/**
	 * Reads the next line of the file, splits it and puts in the queue.
	 * https://github.com/dhadka/TSPLIB4J/blob/master/src/org/moeaframework/problem/tsplib/EdgeWeightMatrix.java
	 * 
	 * @param reader is the given file.
	 * @param entries is the queue where the splitted line is put.
	 * @throws IOException if I/O error occurs.
	 */
	private void readNextLine(BufferedReader reader, Queue<Double> entries)
			throws IOException {
		
		String line = null;
		
		do {
			line = reader.readLine();
			
			if (line == null) {
				throw new EOFException("the end of file is reached");
			}
		} while ((line = line.trim()).isEmpty());
		
		String[] tokens = line.split("\\s+");
		
		for (int i = 0; i < tokens.length; i++) {
			entries.offer(Double.parseDouble(tokens[i]));
		}
	}
	
	/**
	 * 
	 * @return the TSP instance.
	 */
	public TSPInstance getTspInstance() {
		return tspInstance;
	}
}
