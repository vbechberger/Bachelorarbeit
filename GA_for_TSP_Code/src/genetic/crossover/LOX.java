package genetic.crossover;

import java.util.HashSet;

import genetic.Chromosome;
import genetic.FitnessFunction;
import tsp.Solution;

/**
 * Represents the linear order crossover operator
 * which uses two cut points and path representation.
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public class LOX extends TwoCutPointsX {

	
	/**
	 * Constructor.
	 * Creates a LOX operator.
	 * 
	 * @param fitnessFct is the given fitness function.
	 * @param firstParent is the first parent chromosome.
	 * @param secondParent is the second parent chromosome.
	 * @param cutPoint1 is the first cut point.
	 * @param cutPoint2 is the second cut point.
	 */
	public LOX(FitnessFunction fitnessFct, 
						Chromosome firstParent, 
						Chromosome secondParent, 
						int cutPoint1, int cutPoint2) {
		super(fitnessFct, firstParent, secondParent, cutPoint1, cutPoint2);
	}

	/**
	 * Makes the linear order crossover between two parent chromosomes.
	 * @return the offspring chromosome.
	 */
	protected Chromosome doCrossover() {
		
		int[] firstParent = parent1.getTourAsArr();
		int[] secondParent = parent2.getTourAsArr();
		
		/*If the cut interval includes the whole chromosome,
		 *return the first parent.
		 */
		if ((cutPoint == 0) && (cutPoint2 == (SIZE_OF_CHROMOSOME - 1))) {
			return new Chromosome(fitnessFct, new Solution(SIZE_OF_CHROMOSOME, firstParent));
		}
		
		int[] arrKid = new int[SIZE_OF_CHROMOSOME];

		/*Make a set with values between cuts.*/
		HashSet<Integer> cut = new HashSet<Integer>();

		/*The values between the cut points are copied to the offspring from the
		 *first parent at the same positions.
		 */
		for (int i = cutPoint; i < cutPoint2 + 1; i++) {
			arrKid[i] = firstParent[i];
			/*save the values between cut points of the first parent*/
			cut.add(firstParent[i]);
		}

		/*The remaining positions of the offspring are filled by considering
		 *the sequence in the second parent from the beginning of it.
		 */

		int pos = 0;

		/*Define where we start to fill in the offspring.*/
		if (cutPoint == 0) {
			pos = cutPoint2 + 1;
		}
		
		/*Go through the second parent from the very beginning of its chromosome.*/
		for (int i = 0; i < SIZE_OF_CHROMOSOME; i++) {
			
			/*If the candidate city has not been used yet, 
			 *then this city goes to the offspring.
			 */
			if (!cut.contains(secondParent[i])) {
				
				/*If we are at the position in the cut, 
				 *which is already filled in the offspring.
				 */
				if (pos == cutPoint) {
					/*If this part was at the end of the chromosome, then we are ready*/
					if (cutPoint2 == SIZE_OF_CHROMOSOME - 1) {
						break;
					/*If not, jump over this part.*/	
					} else {
						pos = cutPoint2 + 1;
					}
				}
				
				arrKid[pos] = secondParent[i];				
				pos++;
			}
		}
		
		return new Chromosome(fitnessFct, new Solution(SIZE_OF_CHROMOSOME, arrKid));
	}

}
