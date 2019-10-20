package genetic.mutation;

import genetic.Chromosome;
import genetic.FitnessFunction;


/**
 * This class represents the swap mutation operator 
 * which randomly selects two random positions and swaps 
 * the cities in these positions.
 * 
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 */
public class MutationSwap extends Mutation {
	
	private int index1 = -1;
	private int index2 = -1;

	public MutationSwap(FitnessFunction fitnessFct, Chromosome kid, int index1, int index2) {
		super(fitnessFct, kid);
		setIndex1(index1);
		setIndex2(index2);
		
	}
	
	public Chromosome doMutation(int[] kidGenes) {
		
		int tempValue1 = kidGenes[index1];
		int tempValue2 = kidGenes[index2];
		
		kidGenes[index1] = tempValue2;
		kidGenes[index2] = tempValue1;
		
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
