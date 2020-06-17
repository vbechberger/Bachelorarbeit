package genetic.mutation;

import java.util.ArrayList;

import genetic.Chromosome;
import genetic.FitnessFunction;

/**
 * Represents insertion mutation.
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public class Insertion extends IndicesBasedMutation{

	/**
	 * Constructor.
	 * @param fitnessFct is the fitness function.
	 * @param kid is the offspring which mutates.
	 * @param index1 is the start index of the interval
	 * @param index2 is the end index of the interval
	 */
	public Insertion(FitnessFunction fitnessFct, Chromosome kid, int index1, int index2) {
		super(fitnessFct, kid, index1, index2);
	}

	@Override
	protected void changeIntervalWithinIndices(ArrayList<Integer> genesInInterval) {
		
		/*Remove the first element and put it
		 * at the end of the list, the following cities automatically
		 *go one place to the beginning.
		 */		
		Integer firstElem = genesInInterval.remove(0);		
		genesInInterval.add(firstElem);		
	}

}
