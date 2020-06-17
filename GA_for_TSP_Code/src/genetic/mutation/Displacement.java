package genetic.mutation;

import java.util.ArrayList;

import genetic.Chromosome;
import genetic.FitnessFunction;

/**
 * Represents the displacement mutation.
 * This mutation operator takes three indices, 
 * which define three cut points. 
 * The cities which occupy the positions between 
 * the first cut point(inclusively) and 
 * the second cut point(inclusively) are shifted 
 * at the position after the third cut point.  
 * It means that the cities between 
 * the second cut point(exclusively) and 
 * the third cut point(inclusively) go 
 * automatically to the beginning, 
 * where the first cut point was.
 *  
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public class Displacement extends IndicesBasedMutation{
	
	private int middleIndex = -1;

	/**
	 * Constructor
	 * @param fitnessFct is the fitness function.
	 * @param kid is the offspring which mutates.
	 * @param startIndex is the start index of the interval.
	 * @param middleIndex is the middle index.
	 * @param endIndex is the end index of the interval.
	 */
	public Displacement(FitnessFunction fitnessFct, 
								Chromosome kid, 
								int startIndex, int middleIndex, int endIndex) {
		super(fitnessFct, kid, startIndex, endIndex);
		setMiddleIndex(middleIndex);
	}

	/**
	 * Sets the middle index.
	 * @param middleIndex is the given value for the middle index.
	 */
	private void setMiddleIndex(int middleIndex) {
		checkIndexInBounds(middleIndex);
		checkNextIndexGreaterThanPrevios(firstIndex, middleIndex);
		checkNextIndexGreaterThanPrevios(middleIndex, lastIndex);
		this.middleIndex = middleIndex;		
	}

	@Override
	protected void changeIntervalWithinIndices(ArrayList<Integer> genesInInterval) {
		
		/*Remove first N indices from the interval list,
		 *where N = middleIndex - firstIndex + 1.
		 */
		for(int i = firstIndex; i <= middleIndex; i++) {
			Integer elem = genesInInterval.remove(0);
			
			//and put them in the same order at the end			
			genesInInterval.add(elem);
		}		
	}

}
