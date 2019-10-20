package genetic.mutation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import genetic.Chromosome;
import genetic.FitnessFunction;

public class MutationScramble extends Mutation {
	
	//TODO: do it random-till the length of array	
	private int index1 = -1;
	private int index2 = -1;
	

	public MutationScramble(FitnessFunction fitnessFct, Chromosome kid, int index1, int index2) {
		super(fitnessFct, kid);
		setIndex1(index1);
		setIndex2(index2);
	}

	protected Chromosome doMutation(int[] kidGenes) {
		
		
		ArrayList<Integer> shuffled = new ArrayList<Integer>();
		
		for (int i = index1; i < index2 + 1; i++) {
			shuffled.add(kidGenes[i]);
		}
		Collections.shuffle(shuffled, new Random(42));
		
		for (int i = index1; i < index2 + 1; i++) {
			//we put the values in the kid array starting from the position
			//index1
			kidGenes[i] = shuffled.get(i - index1);
		}
				
		return new Chromosome(fitnessFct, kidGenes);
	}
	
	
	private void setIndex1(int index1) {
		if (index1 < 0 || index1 >= arrLength) {
			throw new IllegalArgumentException("The given index is out of bounds!");
		}
		
		this.index1 = index1;
	}
	
	private void setIndex2(int index2) {
		if (index2 < 0 || index2 >= arrLength) {
			throw new IllegalArgumentException("The given index is out of bounds!");
		}
		
		this.index2 = index2;
	}
	
	


}
