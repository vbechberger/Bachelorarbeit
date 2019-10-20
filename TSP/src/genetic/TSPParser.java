package genetic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import util.Printer;

public class TSPParser {
	
	private String name;	
	
	private String comment;
	
	private InstanceType type;
	
	private int dimension = -1;
	
	private EdgeWeightType edgeWeightType;
	
	private int[][] coordinates;
	
	private TSPInstance tspInstance;
		

	public TSPParser(File file) throws IOException {
		loadFromFile(file);
		tspInstance = new TSPInstance(name, comment, type,
				dimension, coordinates, edgeWeightType);
		
	}
	
	public void loadFromFile(File file) throws IOException {
		BufferedReader in = null;
				
		try {
			in = new BufferedReader(new FileReader(file));
			String line = null;
			
			while ((line = in.readLine()) != null) {
				line = line.trim();
				
				if (line.equals("NODE_COORD_SECTION")) {
					
					coordinates = new int[dimension][2];
					
					int count = 0;
					while ((line = in.readLine()) != null && count < dimension) {
						line = line.trim();
						
						String[] splittedString = line.split("\\s+");
						
						int [] numbers = new int [splittedString.length];
						
					    for(int i = 0; i < splittedString.length; i++) {
					         numbers[i] = Integer.parseInt(splittedString[i].trim());
					    }
					    
					    coordinates[count][0] = numbers[1];
					    coordinates[count][1] = numbers[2];					    		
					    count++;
					}						
					//Printer.printArray2D(coordinates);
								
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
					
					//the imformation itself
					String content = tokens[1].trim();
					
					if (key.equals("NAME")) {
						name = content;
						Printer.printString("NAME: " + name);
						
					} else if (key.equals("COMMENT")) {
						if (comment == null) {
							comment = content;
						} else {
							comment = comment + "\n" + content;
						}						
						Printer.printString("COMMENT: " + comment);
						
					} else if (key.equals("TYPE")) {
						type = InstanceType.valueOf(content);						
						Printer.printString("TYPE: " + type);
						
					} else if (key.equals("DIMENSION")) {
						dimension = Integer.parseInt(content);
						Printer.printString("DIMENSION: " + dimension);
						
					} else if (key.equals("EDGE_WEIGHT_TYPE")) {
						edgeWeightType = EdgeWeightType.valueOf(content);
						Printer.printString("EDGE_WEIGHT_TYPE: " + edgeWeightType);
						
					} else {
						Printer.printString("Unknown key word: " + key);						
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

			}

	public TSPInstance getTspInstance() {
		return tspInstance;
	}
	

}
