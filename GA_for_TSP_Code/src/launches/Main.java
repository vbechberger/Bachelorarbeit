package launches;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import genetic.Chromosome;
import genetic.FitnessFunction;
import genetic.Population;
import genetic.crossover.CrossoverType;
import genetic.mutation.MutationType;
import genetic.selection.SelectionType;
import io.GAWriter;
import tsp.DistanceTable;
import tsp.Solution;
import tsp.TSPInstance;

/**
 * Launches the genetic algorithm with the provided arguments.
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public class Main {
	
	 /*Optional command line arguments:*/
	@Option(name="-s",usage="set the size "
	 		+ "of the population at the given value: -s size")
	private static int fixedPopSize = -1;
	 
	@Option(name="-p",usage="set the number "
		 		+ "of participants in a selection: -p number")
	private static int numberOfParticipants = -1;
	 	 
	@Option(name="-t",usage="set the time limit: -t number")
	private static int timeLimit = -1;
	
	@Option(name="-it",usage="set the maximum number of generations: -it number")
	private static int maxGenerationsNumber = -1;
	 	 
	@Option(name="-h",usage="use the results of heuristics in the file: -h \"path to the file\"")
	private static String heuristicResults = null;
	 
	 
	 /*Obligatory command line arguments:*/
		/*args[0]*/
	private static String pathInTSPLIB;
		
		/*args[1]*/
	private static String fileToSaveResults;
	 
	 /*General parameters*/

		/*args[2]*/
	private static int seed;
		
		/*args[3]*/
	private static double popSizeFactor = 0;
		
		/*args[4]*/
	private static double mutationRate = -1;
	
	
	
	private static TSPInstance tspInst;
	
	private static int dimension = -1;
	
	private static FitnessFunction fitnessFct;
		
	private static Population pop;
	
	private static int targetSize = 0;
	
	
	/*GA operators*/
	private static MutationType mutationType;
	
	private static CrossoverType crossoverType;
	
	private static SelectionType selectionType;
	
	private static Random random;
	 
	// receives other command line parameters than options
	@Argument
	private List<String> arguments = new ArrayList<String>();

	public static void main(String[] args) {
		
		Main main = new Main();
		
		if(!main.parseArgs(args)) {
			System.exit(0);
		}
		
		GASimulator simulator = null;
		try {
			simulator = new GASimulator(random, 
					fitnessFct, 
					pop, 
					crossoverType, 
					mutationType, 
					selectionType, 
					mutationRate, 
					numberOfParticipants);
			simulator.makeNewGenerationsLoop(timeLimit);
			double winnerFitness = simulator.getBestChromosome(pop).getFitness();
			int numbOfMadeGenerations = simulator.getNumbOfMadeGenerations();

			String results = "" + seed;
			
			if(fixedPopSize > 0) {
				results = results + "," + fixedPopSize; 
			} else {
				results = results + "," + popSizeFactor; 
			}
			results = results + "," + mutationRate 
					+ "," + crossoverType 
					+ "," + mutationType
					+ "," + selectionType
					+ "," + winnerFitness
					+ "," + numbOfMadeGenerations;
			//write results to the file
			GAWriter.writeResultsIntoFile(results, fileToSaveResults);
			
		} catch (NullPointerException e) {
			System.err.println("Error with launching the genetic algorithm.");
			System.exit(0);

		}
		
		
	}
	/**
	 * Parses the all given command line arguments
	 * @param args are the given command line arguments
	 * @return true if the given arguments could be parsed successfully
	 * 			false, otherwise
	 */
	public boolean parseArgs(String[] args) {
		CmdLineParser parser = new CmdLineParser(this);
		
		try {
			
			parser.parseArgument(args);
			
			if(arguments.isEmpty()) {
				throw new NullPointerException("No arguments are given.");
			} else if (arguments.size() < 8) {
				throw new InputMismatchException("Not enough arguments are given.");
				
			}
		} catch (NullPointerException e) {	
			errorMessage(parser, e);            
            return false;
                        
		} catch (InputMismatchException e1) {	
			errorMessage(parser, e1);           
            return false;
			
			
		} catch (CmdLineException e2) {
			errorMessage(parser, e2);          
            return false;
		}
				
		
		if(fixedPopSize >= 0) {
			System.out.println("-s fixed size of population: "+ fixedPopSize);
		}
            		
		if(numberOfParticipants >= 0)
            System.out.println("-p number of participants in selection: "+ numberOfParticipants);
		
		if(timeLimit >= 0)
            System.out.println("-t time limit: "+ timeLimit);
		
		if(maxGenerationsNumber >= 0)
            System.out.println("-it maximum number of generations: "+ maxGenerationsNumber);
		
		if(heuristicResults != null)
            System.out.println("-h path to the heuristics: "+ heuristicResults);
		
		if (!parseNotOptionalArgs()) {
			return false;
		}
		
		return true;
	}

	/**
	 * Parses the obligatory command line arguments
	 * @return true if the obligatory arguments could be parsed successfully
	 * 			false, otherwise
	 */
	public boolean parseNotOptionalArgs() {
		// access non-option arguments
        
        /*Parse the next corresponding tsp instance from the list*/
		pathInTSPLIB = arguments.get(0);
		System.out.println("TSP Instance: " + pathInTSPLIB);
	
		tspInst = Parser.parseTSPInstance(pathInTSPLIB);
		fitnessFct = tspInst.getDistanceTable();
		dimension = tspInst.getDimension();
		
		fileToSaveResults = arguments.get(1);
		System.out.println("File to save results: " + fileToSaveResults);
		
		seed = Parser.parseSeed(arguments.get(2));		
		System.out.println("Seed: " + seed);
		
		random = new Random(seed);
		
		targetSize = Parser.setTargetSizeOfPop(dimension, fixedPopSize, arguments.get(3));
		try {
			/*get the target size of population*/
			popSizeFactor = Double.parseDouble(arguments.get(3));
		} catch (NumberFormatException e) {
			System.err.println("The size factor of "
					+ "the population could not be parsed!");
			return false;
		}
		
		
		pop = new Population(targetSize);
		
		/*If the path to heuristic results is given,
		 * we use them to fill in the population*/
		if(heuristicResults != null) {
			fillInPopWithHeuristicsResults(tspInst, heuristicResults);
			System.out.println("Size of population filled with the heuristics results:" + pop.getCurrentSize());
		}
		
		
		
		/*complete the population with random permutations*/
		pop.completePopulationWithTargetSize(fitnessFct, random);
		
		System.out.println("Total size of population:" + pop.getCurrentSize());
		
		mutationRate = Parser.setMutationRate(arguments.get(4));
		System.out.println("mutation rate: " + mutationRate);
		
		crossoverType = Parser.parseCrossoverType(arguments.get(5));		
		System.out.println("crossover type: " + crossoverType);
		
		mutationType = Parser.parseMutationType(arguments.get(6));		
		System.out.println("mutation type: " + mutationType);
		
		selectionType = Parser.parseSelectionType(arguments.get(7));		
		System.out.println("selection type: " + selectionType);
		
		return true;
		
	}
	
	/**
	 * This method takes the results of the construction heuristics
	 * which are saved in the corresponding file, takes the 
	 * distance table from the specified tsp instance and produces chromosomes
	 * which form the initial population.
	 * @param tspInst is the TSP instance which is to be solved with GA
	 * @param fileName is the name of the file, where the results 
	 * 				   of the heuristics are saved
	 */
	private static void fillInPopWithHeuristicsResults(TSPInstance tspInst, String fileName) {
		
		File fileWithResults = new File(fileName);
				
		if (!fileWithResults.canRead() || !fileWithResults.isFile()) {
			
			System.err.println("The file with heuristic results could not be read!");
			System.exit(0);
			
		} else {
			
			BufferedReader input = null;
			DistanceTable distTable = tspInst.getDistanceTable();
						
			try {
				
				input = new BufferedReader(new FileReader(fileWithResults));
				String string = null;
								
				while((string = input.readLine()) != null) {
					
					//parse the string as a path and make a new Chromosome
					String[] splitedString = string.split("\\s+");
					
					Solution tour = Parser.getSolutionTour(splitedString, dimension);
					Chromosome next = new Chromosome(distTable, tour);
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
		
	}


	/**
	 * Prints usage in case of invalid arguments.
	 * @param parser is the parser of the given command line arguments
	 * @param e the throwable object.
	 */
	public void errorMessage(CmdLineParser parser, Throwable e) {
		System.err.println(e.getMessage());
		System.err.println("java Main [options...] arguments: ");
		System.err.println("[0] path to the tsp instance file (.tsp)");		
		System.err.println("[1] file to save results (.txt)");
		System.err.println("[2] seed ");
		System.err.println("[3] population size factor (if the fixed size is given "
				+ "in options, it will be used instead) ");
		System.err.println("[4] mutation rate ");
		// print the list of available options
		parser.printUsage(System.err);
		System.err.println();
	}
	

}
