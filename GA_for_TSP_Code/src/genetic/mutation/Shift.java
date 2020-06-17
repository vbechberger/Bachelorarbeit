package genetic.mutation;

import java.util.ArrayList;
import genetic.Chromosome;
import genetic.FitnessFunction;
import tsp.Solution;
import util.SafeCopy;

/**
 * This class represents the shift mutation operator.
 * It randomly selects an existing city number and then shifts it
 * to the left or right(in this implementation to the right)
 * n times where n is a random integer.
 * 
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public class Shift extends Mutation {
	
	/**
	 * The city which is to be shifted
	 */
	private int symbol = -1;
	
	/**
	 * The number of steps at which the city has to be shifted.
	 */
	private int stepsNumber = -1;

	/**
	 * Constructor.
	 * @param fitnessFct is the given fitness function.
	 * @param kid is the offspring which mutates.
	 * @param symbol is the city which is to be shifted
	 * @param stepsNumber is the number of steps at which the given city has to be shifted.
	 */
	public Shift(FitnessFunction fitnessFct, Chromosome kid, int symbol, int stepsNumber) {
		super(fitnessFct, kid);
		setSymbol(symbol);
		setStepsNumber(stepsNumber);		
	}

	/**
	 * Makes the scramble mutation.
	 * @return the mutated offspring.
	 */
	protected Chromosome doMutation() {
		
		int[] kidGenesCopy = new int[mutantGenes.length];		
		SafeCopy.copy(kidGenesCopy, mutantGenes);
		
		/*Shift the randomly selected city at the selected number of positions*/
		
		ArrayList<Integer> temp = new ArrayList<Integer>();
		SafeCopy.copy(temp, kidGenesCopy);		
		int indexOfCity = temp.indexOf(symbol);		
		temp.remove((Integer)symbol);
		
		//calculate the new position of the element:
		int newPosition = (indexOfCity + stepsNumber) % SIZE_OF_CHROMOSOME; 
		
		temp.add(newPosition, symbol);
		
		//copy the result back to the kid array
		SafeCopy.copy(kidGenesCopy, temp);
		
		return new Chromosome(fitnessFct, new Solution(SIZE_OF_CHROMOSOME, kidGenesCopy));
	}
	
	
	
	/**
	 * Checks the validity of the given symbol
	 * and sets it
	 * @param symbol
	 * @throws IllegalArgumentException if the
	 * 							given symbol smaller than 0 and greater
	 * 							or equal to the chromosome's size
	 */
	private void setSymbol(int symbol) {
		if (symbol < 0 || symbol >= SIZE_OF_CHROMOSOME) {
			throw new IllegalArgumentException("The given symbol is not in the tour");
		}
		
		this.symbol = symbol;
	}
	
	/**
	 * Checks the validity of the given number of steps.
	 * and sets it
	 * @param stepsNumber is the given number of steps.
	 * @throws IllegalArgumentException if the
	 * 							given number of steps smaller than 0 and greater
	 * 							or equal to the chromosome's size
	 */
	private void setStepsNumber(int stepsNumber) {
		if (stepsNumber <= 0 || stepsNumber >= SIZE_OF_CHROMOSOME) {
			throw new IllegalArgumentException("The given number is not in the range");
		}
		
		this.stepsNumber = stepsNumber;
	}

}
