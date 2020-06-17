package genetic.crossover;

import java.util.HashSet;

import genetic.Chromosome;
import genetic.FitnessFunction;
import tsp.Solution;

/**
 * Represent the order crossover operator which
 * uses two cut points and work on the path representation.
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public class OX extends TwoCutPointsX {

	/**
	 * Constructor.
	 * @param fitnessFct is the given fitness function.
	 * @param firstParent is the first parent chromosome.
	 * @param secondParent is the second parent chromosome.
	 * @param cutPoint1 is the first cut point.
	 * @param cutPoint2 is the second cut point.
	 */
	public OX(FitnessFunction fitnessFct, 
						Chromosome firstParent, 
						Chromosome secondParent, 
						int cutPoint1, int cutPoint2) {
		super(fitnessFct, firstParent, secondParent, cutPoint1, cutPoint2);
	}

	/**
	 * Makes the order crossover between two parent chromosomes.
	 * @return the offspring chromosome.
	 */
	protected Chromosome doCrossover() {
		
		int[] firstParent = parent1.getTourAsArr();
		int[] secondParent = parent2.getTourAsArr();
		
		int[] arrKid = new int[SIZE_OF_CHROMOSOME];

		// make a set with values between cuts
		HashSet<Integer> cut = new HashSet<Integer>();

		/*Values between the cut points are copied to the offspring from the
		 *first parent at the same positions.
		 */
		for (int i = cutPoint; i < cutPoint2 + 1; i++) {
			arrKid[i] = firstParent[i];

			// save the values between cut points of the first parent
			cut.add(firstParent[i]);
		}

		/* The remaining positions of the offspring are filled by considering
		 * the sequence in the second parent, starting after the second
		 * cut point and then continue from the beginning of the second parent.
		 */
		int pos = -1;

		// define where we start to fill in the offspring
		if (cutPoint2 == SIZE_OF_CHROMOSOME - 1) {
			pos = 0;
		} else {
			pos = cutPoint2 + 1;
		}
		// fill in the offspring
		for (int i = cutPoint2 + 1; i < SIZE_OF_CHROMOSOME; i++) {
			if (!cut.contains(secondParent[i])) {
				arrKid[pos] = secondParent[i];
				/* If the tail part of the offspring is filled, 
				 *start from the beginning.
				 */
				if (pos == SIZE_OF_CHROMOSOME - 1) {
					pos = 0;
				} else {
					pos++;
				}
			}
		}
		for (int i = 0; i < cutPoint2 + 1; i++) {
			if (!cut.contains(secondParent[i])) {
				arrKid[pos] = secondParent[i];

				/*If the tail part of the offspring is filled, 
				 *start from the beginning.
				 */
				if (pos == SIZE_OF_CHROMOSOME - 1) {
					pos = 0;
				} else {
					pos++;
				}
			}
		}

		return new Chromosome(fitnessFct, new Solution(SIZE_OF_CHROMOSOME, arrKid));
	}

}
