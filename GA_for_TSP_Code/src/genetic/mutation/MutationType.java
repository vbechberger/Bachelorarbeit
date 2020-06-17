package genetic.mutation;

import java.util.Random;


import genetic.Chromosome;
import genetic.FitnessFunction;
/**
 * This class represents different types of mutation operators.
 * According to the specified type, it prepares the 
 * necessary parameters
 * and as a result returns the mutation operator which is ready to be used.
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public enum MutationType {
	UNDEFINED, 
	SWAP, 
	SCRAMBLE, 
	SHIFT, 
	INVERSION, 
	INSERTION, 
	DISPLACEMENT;
	

	/**Parameters for mutation*/
	private int cutPointStart = -1;
	private int cutPointMiddle = -1;
	private int cutPointEnd = -1;
	private int city = -1;
	private int steps = -1;
	
	/**
	 * Returns the mutation according to the type
	 * @param fitnessFct is the fitness function
	 * @param chromosome is the chromosome which will mutate
	 * @param random is the random
	 * @return a mutation instance of the corresponding type
	 */
	public Mutation getMutation(FitnessFunction fitnessFct, 
								Chromosome chromosome, 
								Random random) {
	
		setCutPoints(chromosome, random);
		
		/*Set mutation*/
		switch (this) {
		case SWAP: {
			
			return new Swap(fitnessFct, chromosome, cutPointStart, cutPointEnd);			
		}
		case SCRAMBLE: {	
			
			return new Scramble(fitnessFct, chromosome, cutPointStart, cutPointEnd);			
		}		
		case SHIFT: {
			
			return new Shift(fitnessFct, chromosome, city, steps);
		}
		case INVERSION: {

			return new Inversion(fitnessFct, chromosome, cutPointStart, cutPointEnd);			
		}		
		case INSERTION: {
			
			return new Insertion(fitnessFct, chromosome, cutPointStart, cutPointEnd);			
		}		
		case DISPLACEMENT: {
			
			return new Displacement(fitnessFct, chromosome, cutPointStart, cutPointMiddle, cutPointEnd);			
		}
		default:
			throw new IllegalArgumentException("The given mutation type does not exist: "
            		+ this.name());
		}
	}

	/**
	 * Sets parameters for the mutation dependent from the type.
	 * @param chromosome is the given chromosome which will mutate
	 * @param random
	 */
	private void setCutPoints(Chromosome chromosome, Random random) {
		int chromLength = chromosome.getSize();

		if(chromLength <= 0) {
			throw new IllegalStateException("CrossoverType: The length "
					+ "of chromosomes has to be positive!");
		}
		/*the first cut point can be maximum at the prelast position,
		 * so that the second cut point can have a position after that.
		 */
		int maxIndexValue = chromLength - 2;
		int minIndexValue = 0;

		
		/*Set cut points*/
		switch (this) {
		case SWAP: case SCRAMBLE: case INVERSION: case INSERTION: {
			
			/**Random cut points*/			
			cutPointStart = computeFirstIndex(random, maxIndexValue, minIndexValue);
			cutPointEnd = computeLastIndex(random, chromLength, cutPointStart);   
			
			break;
		} 
		
		case SHIFT: {
			
			city = random.nextInt(chromLength - 1 - 0 + 1);
			
			setNumberOfSteps(random, chromLength);
			
		}
		
		case DISPLACEMENT: {
			
			/*We have to redefine the maximum value of the first
			 * cut point: 
			 * the first cut point can be maximum at the position, which stands
			 * 2 steps to the left from the last position,
			 * so that the second and third cut points can have positions after that.
			 */	
			maxIndexValue = chromLength - 3;
			
			cutPointStart = computeFirstIndex(random, maxIndexValue, minIndexValue);
			cutPointMiddle = computeMiddleIndex(random, chromLength, cutPointStart); 
			cutPointEnd = computeLastIndex(random, chromLength, cutPointMiddle); 
			break;
			
		}
		default:
			throw new IllegalArgumentException("The given mutation type does not exist: "
            		+ this.name());
		}
	}

	/**
	 * Sets the number of steps.
	 * @param random is the Random object.
	 * @param chromLength is the length of teh chromosome.
	 */
	public void setNumberOfSteps(Random random, int chromLength) {
		
		if(chromLength < 3) {
			throw new IllegalArgumentException("MutationType: the given chromosome "
					+ "is too small to make mutation steps.");
		}
		
		/**at least 1 step and in order not to return at the same position, 
		 * therefore: 
		 * until steps < dimension - 1 
		 */
		steps = random.nextInt(chromLength - 2 - 1 + 1) + 1;

	}
	
	/**
	 * Computes the middle cut point
	 * @param random
	 * @param chromLength is the length of the chromosome which will mutate
	 * @param cutPointStart is the first cut point which was already defined
	 * @throws IllegalStateException, if there is no enough free positions 
	 * 								  to find a new index.
	 * @return an index for the middle cut point
	 */
	public int computeMiddleIndex(Random random, int chromLength, int cutPointStart) {
		
		/*We need to have at least two positions free for the middle and the last index*/
		int freePosRemained = chromLength - 1 - cutPointStart - 1;
		
		
		/*We check < 1 because by getting a random int,
		 * we will make a shift to the right (+1)*/
		if(freePosRemained < 1) {
			throw new IllegalStateException("MutationType: not enough "
					+ "free positions for calculating the middle index.");
		}
		/*The next index has to be greater to the first cut point + 1,
		 *therefore we add the index of the first cut point.
		 */
		int cutPoint2 = random.nextInt(freePosRemained) + cutPointStart + 1;
		return cutPoint2;
	}

	/**
	 * Calculates the last index taking into
	 * account the specified first cut point.
	 * @param random is the Random object.
	 * @param chromLength is the length of the chromosome.
	 * @param cutPoint1 is the given first cut point.
	 * @return the last index.
	 */
	public int computeLastIndex(Random random, int chromLength, int cutPoint1) {
		
		/*the indices have to be different, therefore, we subtract 1 at the end*/
		int freePosRemained = chromLength - 1 - cutPoint1 - 1;
		
		/*We check < 0 because by getting a random int,
		 * we will make a shift to the right (+1)*/
		if(freePosRemained < 0) {
			throw new IllegalStateException("MutationType: not enough "
					+ "free positions for calculating the last index.");
		}
		
		/*The next index has to be greater to the first cut point,
		 *therefore we add the index of the first cut point.
		 */
		int possibleIndexToStart = cutPoint1 + 1;
		
		int cutPoint2 = random.nextInt(freePosRemained + 1) + possibleIndexToStart;	
		
		
		return cutPoint2;
	}

	/**
	 * Defines the first cut point taking into 
	 * consideration the given bounds.
	 * @param random is the Random object.
	 * @param maxIndexValue is the maximal possible index.
	 * @param minIndexValue is the minimal possible index.
	 * @return the first cut point.
	 */
	public int computeFirstIndex(Random random, int maxIndexValue, int minIndexValue) {
		
		if(maxIndexValue < 0 || minIndexValue < 0) {
			throw new IllegalArgumentException("MutationType: "
					+ "maximum and minimum indices to compute "
					+ "the first cut point have to be not negative.");
			
		}
		int cutPoint1 = random.nextInt(maxIndexValue - minIndexValue + 1);
		return cutPoint1;
	}

}
