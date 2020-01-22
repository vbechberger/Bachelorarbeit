package genetic.mutation;

import java.util.ArrayList;

import genetic.Chromosome;
import genetic.FitnessFunction;

/**
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
 * @author valeriyabechberger
 *
 */
public class MutationDisplacement extends IndicesBasedMutation{
	
	private int middleIndex = -1;

	public MutationDisplacement(FitnessFunction fitnessFct, 
								Chromosome kid, 
								int startIndex, int middleIndex, int endIndex) {
		super(fitnessFct, kid, startIndex, endIndex);
		setMiddleIndex(middleIndex);
	}

	private void setMiddleIndex(int middleIndex) {
		checkIndexInBounds(middleIndex);
		checkNextIndexGreaterThanPrevios(firstIndex, middleIndex);
		checkNextIndexGreaterThanPrevios(middleIndex, lastIndex);
		this.middleIndex = middleIndex;		
	}

	@Override
	protected void changeIntervalWithinIndices(ArrayList<Integer> genesInInterval) {
		
		//remove first N indices from the interval list,
		//where N = middleIndex - firstIndex + 1
		for(int i = firstIndex; i <= middleIndex; i++) {
			Integer elem = genesInInterval.remove(0);
			
			//and put them in the same order at the end			
			genesInInterval.add(elem);
		}		
	}

}
