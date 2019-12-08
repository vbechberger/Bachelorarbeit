package genetic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import heuristics.CheapestInsertion;
import heuristics.DistanceInsertion;
import heuristics.DistanceInsertionType;
//import heuristics.DoubleNearestNeighbor;
//import heuristics.NearestNeighbor;

public class ConstrHeuristicLauncher {
	
	private static String pathWhereToSaveData;
	
	public static void main(String[] args) {
		
		
		String fileName = args[0];
		pathWhereToSaveData = args[1];

		File file = new File(fileName);
		TSPParser tsp;
		TSPInstance tspInst = null;
		
		
		if (!file.canRead() || !file.isFile()) {
			System.err.println("The file could not be read!");
			System.exit(0);
		}
		try {
			tsp = new TSPParser(file);
			tspInst = tsp.getTspInstance();
			double[][]distances = tspInst.getDistanceTable().getDistances();
			
			
			//int numberOfLaunches = tspInst.getDimension();
			int numberOfLaunches = 1;
			
			for (int startCity = 0; startCity < numberOfLaunches; startCity++) {				

				//writeTourIntoSameFile(new NearestNeighbor(distances, startCity).getTour());
				
				//writeTourIntoSameFile(new DoubleNearestNeighbor(distances, startCity).getTour());
				
				writeTourIntoSameFile(new CheapestInsertion(distances, startCity).getTour());
				
				writeTourIntoSameFile(new DistanceInsertion(DistanceInsertionType.NEAREST, 
															distances, 
															startCity).getTour());
				writeTourIntoSameFile(new DistanceInsertion(DistanceInsertionType.FARTHEST, 
															distances, 
															startCity).getTour());			
			}	
			
		} catch (IndexOutOfBoundsException eob) {
			System.out.println(tspInst.getName());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	}
	  
	private static void writeTourIntoSameFile(int[] tourAsArray) {
		
		Tour tour = new Tour(tourAsArray);
		String data = tour.toString();
		writeDataIntoSameFile(data, pathWhereToSaveData);
	}
		
	
	/**
	 * Writes the given data into the specified file,
	 * so that if the method will be called again with 
	 * a new data, it will be written in the same file at a
	 * new line.
	 * @param data The given data to be written at a new line 
	 * 			   of the specified file
	 * @param path The name of the file, where the data will be saved.
	 */
	private static void writeDataIntoSameFile(String data, String path) {
		
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
			//close resources 
			try {
			out.close(); 
			} catch (IOException e) {
				e.printStackTrace(); 
			}
		}		 
	}
	 
}
