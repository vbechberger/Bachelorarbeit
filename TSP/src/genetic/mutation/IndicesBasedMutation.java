package genetic.mutation;

import java.util.ArrayList;

import genetic.Chromosome;
import genetic.FitnessFunction;
import genetic.Solution;
import util.SaveCopy;

public abstract class IndicesBasedMutation extends Mutation{

	//TODO: do it random-till the length of array	
	protected int firstIndex = -1;
	protected int lastIndex = -1;
	
	public IndicesBasedMutation(FitnessFunction fitnessFct, Chromosome kid, int index1, int index2) {
		super(fitnessFct, kid);
		setFirstIndex(index1);
		setLastIndex(index2);
	}
	
	protected Chromosome doMutation(int[] kidGenes) {
		
		int[] kidGenesCopy = new int[kidGenes.length];
		
		SaveCopy.copy(kidGenesCopy, kidGenes);		
		
		kidGenesCopy = fillInIntervalWithinIndices(kidGenesCopy);
				
		return new Chromosome(fitnessFct, new Solution(arrLength, kidGenesCopy));
	}

	/**
	 * 
	 * @param genesInList Corresponds to the interval within the first 
	 * 						and the last indices,
	 * 						where the corresponding changes happen
	 */
	protected abstract void changeIntervalWithinIndices(ArrayList<Integer> genesInInterval);

	private int[] fillInIntervalWithinIndices(int[] genes) {
		
		ArrayList<Integer> genesInList = new ArrayList<Integer>();
		
		for (int i = firstIndex; i < lastIndex + 1; i++) {
			genesInList.add(genes[i]);
		}
				
		changeIntervalWithinIndices(genesInList);
		
		for (int i = firstIndex; i < lastIndex + 1; i++) {
			//we put the values in the kid array starting from the position
			//index1, but in the temporary shuffled array from what we want to take the
			//values from the beginning
			genes[i] = genesInList.get(i - firstIndex);
		}
		
		return genes;
	}
	
	private void setFirstIndex(int index1) {
		checkIndexInBounds(index1);		
		this.firstIndex = index1;
	}
	
	private void setLastIndex(int index2) {
		checkIndexInBounds(index2);	
		checkNextIndexGreaterThanPrevios(firstIndex, index2);	
		this.lastIndex = index2;
	}
	
	
	protected void checkIndexInBounds(int index) {
		if (index < 0 || index >= arrLength) {
			throw new IndexOutOfBoundsException("The given index: " +
												index +"  is out of bounds!");
		}
	}
	
	protected void checkNextIndexGreaterThanPrevios(int prev, int next) {
		if (next <= prev) {
			throw new IllegalArgumentException("The next index: " 
					+ next + " must be greater than the previous index: "
					+ prev);
		}	
	}
}
