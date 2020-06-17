package genetic.mutation;

import genetic.Chromosome;
import genetic.FitnessFunction;
import tsp.Solution;
import util.SafeCopy;


/**
 * This class represents the swap mutation operator 
 * the cities at the given positions (they are chosen randomly).
 * 
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public class Swap extends Mutation {
	
	/**
	 * The first index.
	 */
	private int index1 = -1;
	
	/**
	 * The second index.
	 */
	private int index2 = -1;

	/**
	 * Constructor
	 * @param fitnessFct is the given fitness function.
	 * @param kid is the offspring which mutates.
	 * @param index1 is the first index.
	 * @param index2 is the second index.
	 */
	public Swap(FitnessFunction fitnessFct, Chromosome kid, int index1, int index2) {
		super(fitnessFct, kid);
		setIndex1(index1);
		setIndex2(index2);
		
	}
	/**
	 * Makes the swap mutation.
	 * @return the mutated offspring.
	 */
	public Chromosome doMutation() {
		
		int[] kidGenesCopy = new int[mutantGenes.length];	
		SafeCopy.copy(kidGenesCopy, mutantGenes);
		
		int tempValue1 = kidGenesCopy[index1];
		int tempValue2 = kidGenesCopy[index2];
		
		kidGenesCopy[index1] = tempValue2;
		kidGenesCopy[index2] = tempValue1;
		
		return new Chromosome(fitnessFct, new Solution(SIZE_OF_CHROMOSOME, kidGenesCopy));
		
	}
	
	/**
	 * Sets the first index at the given value.
	 * @param index1 is the value for the first index.
	 */
	private void setIndex1(int index1) {
		if (index1 < 0 || index1 >= SIZE_OF_CHROMOSOME) {
			throw new IllegalArgumentException("The given index is out of bounds!");
		}
		
		this.index1 = index1;
	}
	
	/**
	 * Sets the second index at the given value.
	 * @param index2 is the value for the second index.
	 */
	private void setIndex2(int index2) {
		if (index2 < 0 || index2 >= SIZE_OF_CHROMOSOME) {
			throw new IllegalArgumentException("The given index is out of bounds!");
		}
		
		this.index2 = index2;
	}
	

}
