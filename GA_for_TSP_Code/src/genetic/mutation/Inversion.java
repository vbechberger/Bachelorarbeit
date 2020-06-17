package genetic.mutation;

import java.util.ArrayList;
import java.util.Collections;

import genetic.Chromosome;
import genetic.FitnessFunction;
/**
 * Represents the inversion mutation:
 * the genes within the interval are reversed.
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public class Inversion extends IndicesBasedMutation {
	

	/**
	 * Constructor
	 * @param fitnessFct is the fitness function.
	 * @param kid is the offspring which mutates.
	 * @param index1 is the start index of the interval
	 * @param index2 is the end index of the interval
	 */
	public Inversion(FitnessFunction fitnessFct, Chromosome kid, int index1, int index2) {
		super(fitnessFct, kid, index1, index2);
	}

	@Override
	protected void changeIntervalWithinIndices(ArrayList<Integer> genesInInterval) {
		Collections.reverse(genesInInterval);
	}

}
