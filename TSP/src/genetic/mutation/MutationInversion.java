package genetic.mutation;

import java.util.ArrayList;
import java.util.Collections;

import genetic.Chromosome;
import genetic.FitnessFunction;

public class MutationInversion extends IndicesBasedMutation {
	


	public MutationInversion(FitnessFunction fitnessFct, Chromosome kid, int index1, int index2) {
		super(fitnessFct, kid, index1, index2);
	}

	@Override
	protected void changeIntervalWithinIndices(ArrayList<Integer> genesInInterval) {
		Collections.reverse(genesInInterval);
	}

}
