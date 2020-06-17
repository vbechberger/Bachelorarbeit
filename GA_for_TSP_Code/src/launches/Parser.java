package launches;

import java.io.File;
import java.io.IOException;

import genetic.crossover.CrossoverType;
import genetic.mutation.MutationType;
import genetic.selection.SelectionType;
import tsp.Solution;
import tsp.TSPInstance;
import tsp.TSPParser;
/**
 * Help class which parses different arguments into the needed format.
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public class Parser {

	
	/**
	 * Creates a TSP Instance from the data in the given path
	 * 
	 * @param pathInTSPLIB is the path to the data in the TSPLIB
	 * @return TSPInstance
	 */
	static TSPInstance parseTSPInstance(String pathInTSPLIB) {
		
		File file = new File(pathInTSPLIB);
		TSPParser tsp;
		TSPInstance tspInst = null;
		
		
		if (!file.canRead() || !file.isFile()) {
			System.err.println("The file could not be read!");
			System.exit(0);
		}
		try {
			tsp = new TSPParser(file);
			tspInst = tsp.getTspInstance();
			
		} catch (IndexOutOfBoundsException eob) {
			eob.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return tspInst;
	}
	
	/**
	 * Parses the selection type.
	 * @param selectionType is the given type of selection.
	 * @return the selection type.
	 */
	static SelectionType parseSelectionType(String selectionType) {
		/*get the crossover type*/
		SelectionType type = null;
		
		try {
			type = SelectionType.valueOf(selectionType);
		} catch (IllegalArgumentException e) {
			System.err.println("The type of selection could not be recognized!");
			System.exit(0);
		}
		
		return type;		
	}
	
	/**
	 * Parses the given crossover type.
	 * @param crossoverType is the given crossover type.
	 * @return crossover type.
	 */
	static CrossoverType parseCrossoverType(String crossoverType) {
		/*get the crossover type*/
		CrossoverType type = null;
		
		try {
			type = CrossoverType.valueOf(crossoverType);
		} catch (IllegalArgumentException e) {
			System.err.println("The type of crossover could not be recognized!");
			System.exit(0);
		}
		
		return type;		
	}
	
	/**
	 * Parses the given mutation type.
	 * @param mutationType is the given mutation type.
	 * @return mutation type.
	 */
	static MutationType parseMutationType(String mutationType) {
		/*get the crossover type*/
		MutationType type = null;
		
		try {
			type = MutationType.valueOf(mutationType);
		} catch (IllegalArgumentException e) {
			System.err.println("The type of mutation could not be recognized!");
			System.exit(0);
		}
		
		return type;		
	}
	
	/**
	 * Parses the given seed.
	 * @param seedInStr the given seed as string.
	 * @return the seed.
	 */
	static int parseSeed(String seedInStr) {
		int seed = -1;
		try {
			/*get seed*/
			String thirdArg = seedInStr;
			if(thirdArg.equals("EOF")) {
				/*write results to the file*/
				System.exit(0);
			} else {
				seed = Integer.parseInt(thirdArg);
			}			
		} catch (NumberFormatException e) {
			System.err.println("The seed could not be parsed!");
			System.exit(0);
		}		
		return seed;
	}
	
	/**
	 * Parses the given data about the population size
	 * @param dimension is the dimension of the TSP instance
	 * @param fixedPopSize is the population size given as a fixed value.
	 * @param popSizeFactorInStr is the factor for the population size.
	 * @return the resulted population size.
	 */
	static int setTargetSizeOfPop(int dimension, int fixedPopSize, String popSizeFactorInStr) {
		
		int targetSize = -1;
		if(fixedPopSize >= 0) {
			System.out.println("-s size: "+ fixedPopSize);
			
			targetSize = fixedPopSize;
		} else {
			double popSizeFactor = 0;
			try {
				/*get the target size of population*/
				popSizeFactor = Double.parseDouble(popSizeFactorInStr);
			} catch (NumberFormatException e) {
				System.err.println("The size factor of "
						+ "the population could not be parsed!");
				System.exit(0);
			}
			targetSize = (int) (popSizeFactor * dimension);
		}
		
		return targetSize;
	}
	
	/**
	 * Parses the mutation rate.
	 * @param mutationRateInStr is the given population rate.
	 * @return mutation rate.
	 */
	static double setMutationRate(String mutationRateInStr) {
		double mutationRate = 0;
		try {
			/*get the mutation rate*/
			mutationRate = Double.parseDouble(mutationRateInStr);
		} catch (NumberFormatException e) {
			System.err.println("The mutation rate could not be parsed!");
			System.exit(0);
		}
		
		return mutationRate;
	}
	
	/**
	 * Encodes the given tour in form of the splitted string into the solution tour.
	 * @param splitedString is the tour in form of a splitted string.
	 * @return solution tour.
	 */
	static Solution getSolutionTour(String[] splitedString, int dimension) {
	
		if(splitedString.length < dimension) {
			throw new IllegalArgumentException("The given string is not valid!");
		}
		
		int[] tour = new int[dimension];
		
		for(int i = 0; i < splitedString.length; i++) {
									
			try {
				int nextCity = Integer.parseInt(splitedString[i]);
				tour[i] = nextCity;
				
			} catch (NumberFormatException e1) {
				System.err.print("Could not parse into int: " + splitedString[i]);
				e1.printStackTrace();
			}			
		}
		return new Solution(dimension, tour);
	}
	
}
