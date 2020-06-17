package genetic.crossover;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import genetic.Chromosome;
import genetic.FitnessFunction;
import tsp.DistanceTable;

/**
 * This class represents different types of crossover operators.
 * According to the specified type, it prepares the 
 * necessary parameters
 * and as a result returns the crossover operator which is ready to be used.
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11 
 *
 */
public enum CrossoverType {
	
	UNDEFINED, 
	
	/*Ordinal representation crososver:*/
	ONE_POINT,
	
	/*Path representation crossovers:*/
	MODIFIED, 
	ORDER_BASED, 
	ORDER,
	LINEAR_ORDER,
	POSITION_BASED, 
	PARTIALLY_MAPPED, 
	CYCLE,
	
	/*Adjacency representation crossovers:*/
	ALTERNATE_EDGES,
	EDGE_REPRESENTATION,
	HEURISTIC;
	
	/**
	 * According to the desired type of crossover operator,
	 * it prepares the parameters and returns the crossover operator 
	 * which is ready to be used.
	 * 
	 * @param fitnessFct is the given fitness function.
	 * @param firstChromosome is the first parent chromosome.
	 * @param secChromosome is the second parent chromosome.
	 * @param random is the Random object.
	 * @return the crossover operator ready to be used.
	 */
	public Crossover getCrossover(FitnessFunction fitnessFct, 
								  Chromosome firstChromosome, 
								  Chromosome secChromosome,
								  Random random) {
		
		int chromLength = firstChromosome.getSize();
		
		if(chromLength <= 0) {
			throw new IllegalStateException("CrossoverType: The length "
					+ "of chromosomes has to be positive!");
		}
		
		int maxIndexValue = chromLength - 1;
		int minIndexValue = 0;
		
		
		Crossover crossover;
		
		switch (this) {
		
		case ONE_POINT: {

			/**Random cut point*/
			int cutPoint = getFirstIndex(random, maxIndexValue, minIndexValue);

			crossover = new OPX(fitnessFct, 
									  firstChromosome, 
									  secChromosome, 
									  cutPoint);
			break;
		}
		
		case MODIFIED: {

			/**Random cut point*/
			int cutPoint = getFirstIndex(random, maxIndexValue, minIndexValue);

			crossover = new ModifiedX(fitnessFct, 
									  firstChromosome, 
									  secChromosome, 
									  cutPoint);
			break;
		}
		case ORDER_BASED: {

			Set<Integer> indices = getSetOfIndices(random, chromLength, 
												   maxIndexValue, minIndexValue);

			crossover = new OBX(fitnessFct, firstChromosome, secChromosome, indices);
			break;
		}
		case ORDER: {

			int cutPoint1 = getFirstIndex(random, maxIndexValue, minIndexValue);
			
			int cutPoint2 = getSecondIndex(random, chromLength, cutPoint1);  
	    	
			crossover = new OX(fitnessFct, 
							   firstChromosome, 
							   secChromosome, 
							   cutPoint1, 
							   cutPoint2);
			break;
		}	
		case LINEAR_ORDER: {

			int cutPoint1 = getFirstIndex(random, maxIndexValue, minIndexValue);
			
			int cutPoint2 = getSecondIndex(random, chromLength, cutPoint1);
	    	
			crossover = new LOX(fitnessFct, 
							    firstChromosome, 
							    secChromosome, 
							    cutPoint1, 
							    cutPoint2);
			break;
		}	
		case POSITION_BASED: { 

			Set<Integer> indices = getSetOfIndices(random, chromLength, 
												   maxIndexValue, minIndexValue);

			crossover = new PBX(fitnessFct, firstChromosome, secChromosome, indices);
			break;
		}	
		case PARTIALLY_MAPPED: { 

			int cutPoint1 = getFirstIndex(random, maxIndexValue, minIndexValue);
			
			int cutPoint2 = getSecondIndex(random, chromLength, cutPoint1);
	    	
			crossover = new PMX(fitnessFct, 
								firstChromosome, 
								secChromosome, 
								cutPoint1, 
								cutPoint2);
			break;
		}	
		case CYCLE: { 

			crossover = new CycleX(fitnessFct, firstChromosome, secChromosome);
			break;
		}
		
		case ALTERNATE_EDGES: { 

			int indexOfFirstEdge = getFirstIndex(random, maxIndexValue, minIndexValue);
			crossover = new AlternateEdgesX(fitnessFct, 
											firstChromosome, 
											secChromosome, 
											indexOfFirstEdge, 
											random);
			break;
		}
		
		case EDGE_REPRESENTATION: { 

			crossover = new ERX(fitnessFct, 
								firstChromosome, 
								secChromosome,
					 			random);
			break;
		}
		
		case HEURISTIC: { 

			int indexOfFirstEdge = getFirstIndex(random, maxIndexValue, minIndexValue);
			
			if(fitnessFct instanceof DistanceTable) {
				crossover = new HX(fitnessFct, 
								   firstChromosome, 
								   secChromosome, 
								   (DistanceTable)fitnessFct,
								   indexOfFirstEdge, 
								   random);
			} else {
				throw new IllegalArgumentException("No distance table given!");
			}
						
			break;
		}
		
		
		default:
			throw new IllegalArgumentException("The given crossover type does not exist: "
					+ this.name());
		}

		
		return crossover;
		
	}

	/**
	 * Returns the second random index taking into consideration 
	 * the first cut point which was already chosen.
	 * @param random is the Random object.
	 * @param chromLength is the length of the chromosome.
	 * @param cutPoint1 is the already specified first cut point.
	 * @return the valid index where the second cut point will be made.
	 */
	private int getSecondIndex(Random random, int chromLength, int cutPoint1) {
		
		int freePosRemained = chromLength - 1 - cutPoint1;
		
		/*The second cut point has to be greater or equal to the first cut point,
		 *therefore we add the index of the first cut point.
		 */
		int cutPoint2 = random.nextInt(freePosRemained + 1) + cutPoint1;
		return cutPoint2;
	}

	/**
	 * Returns a random index for the first cut point 
	 * taking into consideration the specified bounds.
	 * @param random is the Random object.
	 * @param maxIndexValue is the maximal index which can be used.
	 * @param minIndexValue is the minimal index which can be used.
	 * @return a random index for the first cut point.
	 */
	private int getFirstIndex(Random random, int maxIndexValue, int minIndexValue) {
		int cutPoint1 = random.nextInt(maxIndexValue - minIndexValue + 1);
		return cutPoint1;
	}

	/**
	 * Makes a set of random indices
	 * taking into account the given bounderies for the possible indices.
	 * 
	 * @param random is the Random object.
	 * @param chromLength is the length of the chromosome.
	 * @param maxIndexValue is the maximal index which can be used.
	 * @param minIndexValue is the minimal index which can be used.
	 * @return a set of random indices.
	 */
	private Set<Integer> getSetOfIndices(Random random, int chromLength, int maxIndexValue, int minIndexValue) {
		Set<Integer> indices = new HashSet<Integer>();

		/**define a random number for the size of the set of indices for the cities which will be 
		 * chosen. It is in the range between 1 and the number of cities in the tour
		 * (=chromosome length) - 1,
		 * because if no cities or all the cities will be chosen, the 
		 * offspring chromosome will be just like the first parent chromosome*/

		int minValue = 1;
		int maxValueExcl = chromLength - 1;
		
		/*we get a random integer in the interval [0, maxValueExcl) and shift this interval at 1*/
		int setSize = random.nextInt(maxValueExcl - minValue) + minValue;
		
		for (int i = 0; i < setSize; i++) {
			
			/*we get an index in in the interval [0, maxIndexValue]*/
			int nextIndex = random.nextInt(maxIndexValue - minIndexValue + 1);

			indices.add(nextIndex);
		}
		return indices;
	}
}
