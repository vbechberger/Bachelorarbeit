package genetic.crossover;

import java.util.HashSet;

import java.util.Set;

import genetic.Chromosome;
import genetic.FitnessFunction;

/**
 * An abstarct class which represents the crosssover operators which uses
 * a set of random indices.
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public abstract class RandomIndicesX extends PathReprX {
	
	/**Set of indices*/
	protected Set<Integer> indices = new HashSet<Integer>();

	/**
	 * Constructor
	 * @param fitnessFct is the given fitness function.
	 * @param firstParent is the first parent chromosome.
	 * @param secondParent is the second parent chromosome.
	 * @param indices is a set of indices
	 */
	public RandomIndicesX(FitnessFunction fitnessFct, 
			              Chromosome firstParent, 
			              Chromosome secondParent, 
			              Set<Integer> indices) {
		
		super(fitnessFct, firstParent, secondParent);
		setIndices(indices);
	}
	
	/**
	 * Sets the indices.
	 * @param indices are the given indices.
	 */
	private void setIndices(Set<Integer> indices) {
		if (indices == null || indices.isEmpty()) {
			throw new IllegalStateException("The indices are not chosen!");
		}
		
		for(Integer next: indices) {
			if(next < 0 || next >= SIZE_OF_CHROMOSOME) {
				throw new IllegalArgumentException("RandomIndicesX: "
						+ "the given set of indices is not valid! "
						+ "At least one index " + next + " is out of bounds.");
			}
		}
		this.indices = indices;
	}

}
