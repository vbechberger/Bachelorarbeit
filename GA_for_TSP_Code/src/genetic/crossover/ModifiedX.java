package genetic.crossover;

import java.util.HashSet;
import genetic.Chromosome;
import genetic.FitnessFunction;
import tsp.Solution;
import util.SafeCopy;

/**
 * Represents the modified crossover which uses one 
 * cut point and path representation.
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public class ModifiedX extends CutPointX {

	/**
	 * Constructor.
	 * Creates a modified crossover operator.
	 * 
	 * @param fitnessFct is the given fitness function.
	 * @param firstParent is the first parent chromosome.
	 * @param secondParent is the second parent chromosome.
	 * @param cutPoint is the first cut point.
	 */
	public ModifiedX(FitnessFunction fitnessFct, 
								Chromosome firstParent, 
								Chromosome secondParent, 
								int cutPoint) {
		super(fitnessFct, firstParent, secondParent, cutPoint);
	}

	/**
	 * Makes the modified crossover between two parent chromosomes.
	 * @return the offspring chromosome.
	 */
	protected Chromosome doCrossover() {
		
		int[] firstParent = parent1.getTourAsArr();
		int[] secondParent = parent2.getTourAsArr();
		
		int[] arrKid = new int[SIZE_OF_CHROMOSOME];
		
		/*If the cut point is the first or the last index
		 *then we just copy the corresponding parent.
		 */
		if (cutPoint == 0) {
			SafeCopy.copy(arrKid, secondParent);
		} else if (cutPoint == SIZE_OF_CHROMOSOME - 1) {
			SafeCopy.copy(arrKid, firstParent);
		} else {

			/*Make a set with cities from the first parent,
			 *which stand before the cut point
			 *so that we can check the duplicates.
			 */
			HashSet<Integer> cut = new HashSet<Integer>();

			/*Fill in the offspring with the cities from the first parent
			 *from the beginning till the cut point.
			 */
			for (int i = 0; i < cutPoint; i++) {
				arrKid[i] = firstParent[i];
				
				/* fill in the set */
				cut.add(arrKid[i]);
			}

			/*Define where we start to fill in the offspring
			 *with the cities from the second parent.
			 */
			int pos = cutPoint;

			for (int i = 0; i < SIZE_OF_CHROMOSOME; i++) {
				
				/*If the next city in the second parent has 
				 *not been taken from the first parent, i.e.
				 *this city did not stand in the 1st parent before the cut point.
				 */
				if (!cut.contains(secondParent[i])) {
					
					/*then it goes to the offspring*/
					arrKid[pos] = secondParent[i];
					pos++;
				}
				/*otherwise we ignore it and go to the next city in the 2nd parent*/
			}
		}

		return new Chromosome(fitnessFct, new Solution(SIZE_OF_CHROMOSOME, arrKid));
	}

}
