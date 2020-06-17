package launches;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import heuristics.DistanceInsertion;
import heuristics.DistanceInsertionType;
import heuristics.DoubleNearestNeighbor;
import heuristics.NearestNeighbor;
import io.GAWriter;
import tsp.Solution;
import tsp.TSPInstance;
import tsp.TSPParser;

/**
 * Launches four construction heuristics within 10 minutes
 * and write all the produced tours in one file for a given TSP instance
 * 
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public class ConstrHeuristicLauncherTimer {
	
	/**
	 * The path to the file where to save the results.
	 */
	private static String pathWhereToSaveData;
	
	/**
	 * The number of the cities.
	 */
	private static int dimension = -1;
	
	/**
	 * Time limit as a stop criterion
	 */
	private static final int TEN_MINUTES = 10 * 60 * 1000;
	
	/**
	 * the RAndom object
	 */
	private static Random random = new Random();
	
	
	
	public static void main(String[] args) {
			
		if(args.length != 2) {
			printUsage();			
			System.exit(0);			
		}
		
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
			dimension = tspInst.getDimension();
			
			long startTimestamp = System.currentTimeMillis();
			
			LocalDateTime dateTime = LocalDateTime.now(); 
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			System.out.println(dateTime.format(formatter));
			
			long newCurrentTimeStamp = System.currentTimeMillis();
			
			/*Until the time limit is ran out,
			 *continue to launch heuristics
			 */	
			
			int maxCity = dimension - 1;
			int minCity = 0;
			
			while(newCurrentTimeStamp < startTimestamp + TEN_MINUTES) {
				
				int startCity = random.nextInt(maxCity - minCity + 1);
				
				//Launch heuristics
				writeSolutionIntoSameFile(new NearestNeighbor(distances, startCity).getTour());
				
				writeSolutionIntoSameFile(new DoubleNearestNeighbor(distances, startCity).getTour());
				
				writeSolutionIntoSameFile(new DistanceInsertion(DistanceInsertionType.NEAREST, 
															distances, 
															startCity).getTour());
				
				writeSolutionIntoSameFile(new DistanceInsertion(DistanceInsertionType.FARTHEST, 
															distances, 
															startCity).getTour());	
				
				newCurrentTimeStamp = System.currentTimeMillis();
				
			}	
			System.out.println("Ten minutes ran out.");
			LocalDateTime dateTimeEnd = LocalDateTime.now(); 
			System.out.println(dateTimeEnd.format(formatter));			
			
		} catch (IndexOutOfBoundsException eob) {
			System.out.println(tspInst.getName());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	
	}
	  
	/**
	 * Writes the solution tour into the same file.
	 * @param tourAsArray is the given tour.
	 */
	private static void writeSolutionIntoSameFile(int[] tourAsArray) {
		
		Solution solution = new Solution(dimension, tourAsArray);
		String tour = solution.toString();
		GAWriter.writeResultsIntoFile(tour, pathWhereToSaveData);
	}
	
	
	/**
	 * Prints usage.
	 */
	private static void printUsage() {
		System.err.println("Invalid number of arguments");
		System.err.println("Please give the following arguments:");
		System.err.println("[0]: path to the TSP instance file");
		System.err.println("[1]: path to file where to save the data");
	}
	 
}
