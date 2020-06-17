package genetic.mutation;

import java.util.ArrayList;

import genetic.Chromosome;
import genetic.FitnessFunction;
import tsp.Solution;
import util.SafeCopy;

/**
 * An abstract class which represents mutation operators
 * which use an interval within which the changes happen.
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public abstract class IndicesBasedMutation extends Mutation{

	protected int firstIndex = -1;
	protected int lastIndex = -1;
	
	/**
	 * Constructor
	 * @param fitnessFct is the fitness function.
	 * @param kid is the offspring which mutates.
	 * @param index1 is the start index of the interval
	 * @param index2 is the end index of the interval
	 */
	public IndicesBasedMutation(FitnessFunction fitnessFct, Chromosome kid, int index1, int index2) {
		super(fitnessFct, kid);
		setFirstIndex(index1);
		setLastIndex(index2);
	}
	
	/**
	 * Makes mutation.
	 * @return the offspring chromosome.
	 */
	protected Chromosome doMutation() {
		
		int[] kidGenesCopy = new int[mutantGenes.length];		
		SafeCopy.copy(kidGenesCopy, mutantGenes);		
		
		kidGenesCopy = fillInIntervalWithinIndices(kidGenesCopy);
				
		return new Chromosome(fitnessFct, new Solution(SIZE_OF_CHROMOSOME, kidGenesCopy));
	}

	/**
	 * Makes changes only within the given interval.
	 * @param genesInList Corresponds to the interval within the first 
	 * 						and the last indices,
	 * 						where the corresponding changes happen
	 */
	protected abstract void changeIntervalWithinIndices(ArrayList<Integer> genesInInterval);

	/**
	 * Makes changes in the interval in the genes.
	 * @param genes are the genes of the offspring.
	 * @return the changed genes.
	 */
	private int[] fillInIntervalWithinIndices(int[] genes) {
		
		ArrayList<Integer> genesInList = new ArrayList<Integer>();
		
		for (int i = firstIndex; i < lastIndex + 1; i++) {
			genesInList.add(genes[i]);
		}
				
		changeIntervalWithinIndices(genesInList);
		
		for (int i = firstIndex; i < lastIndex + 1; i++) {
			/*We put the values in the kid array starting from the position
			 *index1, but in the temporary shuffled array from what we want to take the
			*values from the beginning.
			**/
			genes[i] = genesInList.get(i - firstIndex);
		}
		
		return genes;
	}
	
	/**
	 * Sets the first index.
	 * @param index1 is the value for the first index.
	 */
	private void setFirstIndex(int index1) {
		checkIndexInBounds(index1);		
		this.firstIndex = index1;
	}
	
	/**
	 * Sets the last index.
	 * @param index2 is the value for the last index.
	 */
	private void setLastIndex(int index2) {
		checkIndexInBounds(index2);	
		checkNextIndexGreaterThanPrevios(firstIndex, index2);	
		this.lastIndex = index2;
	}
	
	/**
	 * Checks the validity of the given index.
	 * @param index is the given index.
	 */
	protected void checkIndexInBounds(int index) {
		if (index < 0 || index >= SIZE_OF_CHROMOSOME) {
			throw new IndexOutOfBoundsException("The given index: " +
												index +"  is out of bounds!");
		}
	}
	/**
	 * Check if the next index is greater than the previous one.
	 * @param prev is the previous index.
	 * @param next is the next index.
	 */
	protected void checkNextIndexGreaterThanPrevios(int prev, int next) {
		if (next <= prev) {
			throw new IllegalArgumentException("The next index: " 
					+ next + " must be greater than the previous index: "
					+ prev);
		}	
	}
}
