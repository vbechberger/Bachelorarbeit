package genetic.mutation;

import java.util.ArrayList;

import genetic.Chromosome;
import genetic.FitnessFunction;

/**
 * = 2opt
 * @author valeriyabechberger
 *
 */
public class MutationInsertion extends IndicesBasedMutation{

	public MutationInsertion(FitnessFunction fitnessFct, Chromosome kid, int index1, int index2) {
		super(fitnessFct, kid, index1, index2);
	}

	@Override
	protected void changeIntervalWithinIndices(ArrayList<Integer> genesInInterval) {
		
		//remove the first element and put it
		// at the end of the list, the following cities automatically
		//go one place to the beginning		
		Integer firstElem = genesInInterval.remove(0);		
		genesInInterval.add(firstElem);		
	}

}
