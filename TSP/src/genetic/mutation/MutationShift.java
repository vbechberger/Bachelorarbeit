package genetic.mutation;

import java.util.ArrayList;
import genetic.Chromosome;
import genetic.FitnessFunction;
import util.SaveCopy;

/**
 * This class represents the shift mutation operator.
 * It randomly selects an existing city number and then shifts it
 * to the left or right(in this implementation to the right)
 * n times where n is a random integer.
 * 
 * @author Valeria Bechberger(vsakharova@uos.de)
 *
 */
public class MutationShift extends Mutation {
	private int symbol = -1;
	private int stepsNumber = -1;

	public MutationShift(FitnessFunction fitnessFct, Chromosome kid, int symbol, int stepsNumber) {
		super(fitnessFct, kid);
		setSymbol(symbol);
		setStepsNumber(stepsNumber);		
	}

	protected Chromosome doMutation(int[] kidGenes) {
		
		
		//shift the randomly selected city at ramdomly selected number of positions
		
		//use arraylist as a temp list
		ArrayList<Integer> temp = new ArrayList<Integer>();
		SaveCopy.copy(temp, kidGenes);		
		int indexOfCity = temp.indexOf(symbol);		
		temp.remove((Integer)symbol);
		
		//calculate the new position of the element:
		int newPosition = (indexOfCity + stepsNumber) % arrLength; 
		
		temp.add(newPosition, symbol);
		
		//copy the result back to the kid array
		SaveCopy.copy(kidGenes, temp);
		
		return new Chromosome(fitnessFct, kidGenes);
	}
	
	
	
	
	private void setSymbol(int symbol) {
		if (symbol <= 0 || symbol > arrLength) {
			throw new IllegalArgumentException("The given symbol is not in the tour");
		}
		
		this.symbol = symbol;
	}
	
	private void setStepsNumber(int stepsNumber) {
		if (stepsNumber <= 0 || stepsNumber >= arrLength) {
			throw new IllegalArgumentException("The given number is not in the range");
		}
		
		this.stepsNumber = stepsNumber;
	}

}
