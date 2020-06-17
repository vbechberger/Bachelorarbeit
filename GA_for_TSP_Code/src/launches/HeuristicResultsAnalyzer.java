package launches;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import genetic.Chromosome;
import genetic.FitnessFunction;
import genetic.Population;
import io.GAWriter;
import tsp.Solution;
import tsp.TSPInstance;

/**
 * Analyzes the results provided by the construction heuristics:
 * calculates the number of different round tours and the shortest tour of them
 * made by heuristics.
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public class HeuristicResultsAnalyzer {
	
	/**Number of used construction heuristics*/
	private static final int NUMBEROFHEURISTICS = 4;

	/**File for the results to be saved*/
	private static String fileToSaveResults;
	
	
	/**The name of the TSP instance*/
	private static String instanceName;
	
	/**Dimension of the TSP instance*/
	private static int dimension = -1;
	
	
	/**The best result which the heuristics
	 * could get within the given time limit
	 */
	private static double bestResult = 0.0;

	
	/**
	 * Main for launching the heuristics
	 * @param args two parameters:
	 * 
	 * args[0] is the path to the results of heuristics
	 * args[1] is the path to the TSP instance
	 * args[2] is the file where to save the results
	 */
	public static void main(String[] args) {
				
		
		if(args.length != 3) {
			printUsage();			
			System.exit(0);			
		}
		
		String heuristicResultsForInst = args[0];
		
		String pathInTSPLIB = args[1];
		
		TSPInstance tspInst = Parser.parseTSPInstance(pathInTSPLIB);
		FitnessFunction fitnessFct = tspInst.getDistanceTable();
		
		String[] splitted = heuristicResultsForInst.split("_"); 
		
		String lastString = splitted[splitted.length - 1];
		String[] temp = lastString.split("\\.");
			
		instanceName = temp[0];
		
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(instanceName);
		
		if(m.find()) {
			
			try {
				dimension = Integer.parseInt(m.group(0));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
				
		int numberOfCombinations = NUMBEROFHEURISTICS * dimension;
		
		int numberOfDiffResults = calcDiffResults(heuristicResultsForInst, fitnessFct);
		
		String result = "" + instanceName + "," + numberOfCombinations + "," + numberOfDiffResults + "," + bestResult;
		fileToSaveResults = args[2];
		GAWriter.writeResultsIntoFile(result, fileToSaveResults);
								
	}
	
	private static void printUsage() {
		System.err.println("Invalid number of arguments");
		System.err.println("Please give the following arguments:");
		System.err.println("[0]: path to the results of heuristics");
		System.err.println("[1]: the path to the TSP instance");
		System.err.println("[2]: the file where to save the results");		
	}

	/**
	 * Calculates the number of different round tours and the distance
	 * of the shortest tour..
	 * @param fileName is the file with the results of heuristics
	 * @param fitnessFct is the given fitness function which
	 * 					defines how the fitness value to be calculated.
	 * @return the number of different round tours.
	 */
	private static int calcDiffResults(String fileName, FitnessFunction fitnessFct) {
		
		Population pop = new Population();
		
		File fileWithResults = new File(fileName);
				
		if (!fileWithResults.canRead() || !fileWithResults.isFile()) {
			
			System.err.println("The file with heuristic results could not be read!");
			System.exit(0);
			
		} else {
			
			BufferedReader input = null;
						
			try {
				
				input = new BufferedReader(new FileReader(fileWithResults));
				String string = null;
								
				while((string = input.readLine()) != null) {
					
					//parse the string as a path and make a new Chromosome
					String[] splitedString = string.split("\\s+");
					
					Solution tour = Parser.getSolutionTour(splitedString, dimension);
					Chromosome next = new Chromosome(fitnessFct, tour);
					pop.addChromosome(next);
					
				}

			} catch (IOException e2) {
				e2.printStackTrace();
			
			} finally {
				if(input != null) {
					try {
						input.close();
					} catch (IOException e3) {
						e3.printStackTrace();
					}
				}
			}
			
		}
		Collections.sort(pop.getPop(), Collections.reverseOrder());
		
		bestResult = pop.getChromosomeAtIndex(0).getFitness();
		
		return pop.getCurrentSize();
		
	}

}
