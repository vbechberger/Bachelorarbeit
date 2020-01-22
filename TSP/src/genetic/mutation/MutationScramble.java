package genetic.mutation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import genetic.Chromosome;
import genetic.FitnessFunction;

public class MutationScramble extends IndicesBasedMutation {
	

	public MutationScramble(FitnessFunction fitnessFct, Chromosome kid, int index1, int index2) {
		super(fitnessFct, kid, index1, index2);
	}
	
	@Override
	protected void changeIntervalWithinIndices(ArrayList<Integer> genesInInterval) {
		Collections.shuffle(genesInInterval, new Random(42));
	}

}
