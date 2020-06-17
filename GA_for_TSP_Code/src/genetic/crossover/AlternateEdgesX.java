package genetic.crossover;

import java.util.Random;

import genetic.Chromosome;
import genetic.FitnessFunction;

/**
 * This type of crossover switches between two parent to find the next edge
 *for the offspring. If the next candidate edge introduces a sub cycle,
 *then get another random edge, which does not introduces a sub cycle.
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11 
 *
 */
public class AlternateEdgesX extends AdjReprX{
	
	/**Remembers which parent was used at last*/
	private boolean lastParentWas1 = false;
	
	public AlternateEdgesX(FitnessFunction fitnessFct, 
			 Chromosome firstParent, 
			 Chromosome secondParent,
			 int edgeIndex,
			 Random rand) {
		super(fitnessFct, firstParent, secondParent, edgeIndex, rand);
	}	

	/**
	 * Defines which edge to put in the offspring next:
	 *if the first parent was used already, then use the edge
	 *from the second parent. And vice versa, but if the chosen edge
	 *introduces a sub cycle, then choose another random edge that does not.
	 *	
	 *@param nextIndex The index for the edge, which will be added to the offspring
	 *@param firstParent first parent chromosome as array
	 *@param secondParent second parent chromosome as array
	 *
	 *@return int The number of this city which will build the edge
	 *			  in the offspring chromosome with the city with number 
	 *			  specified in the given nextIndex. In other words,
	 *			  it is a number which will stand in the offspring at the 
	 *			  index, specified by the given nextIndex.
	 */
	protected int findNextCityAndPutItInKid(int nextIndex, int[] firstParent, int[] secondParent) {
	
		int[] parent = new int[firstParent.length];
		
		//if the last parent used was not the first parent,		
		if(lastParentWas1 == false) {
			//then now it is its turn
			parent = firstParent;
			lastParentWas1 = true;
		} else {
			parent = secondParent;
			lastParentWas1 = false;
		}
		
		/*get next element from the parent*/
		elem = parent[nextIndex];
		
		/*if using this element, introduces a subcycle,
		 *get another random element, which does not introduces a cycle
		 */
		elem = chooseRandIfSubCycle(elem);
		
		/*put the element into the kid*/
		arrKid[nextIndex] = elem;		
		
		/*what was the element is a new index now*/
		nextIndex = elem;
		
		return nextIndex;
	}
}
