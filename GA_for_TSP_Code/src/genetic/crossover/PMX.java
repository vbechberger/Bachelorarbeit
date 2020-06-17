package genetic.crossover;

import java.util.HashMap;
import java.util.HashSet;

import genetic.Chromosome;
import genetic.FitnessFunction;
import tsp.Solution;

/**
 * Represents the partially-mapped crossover operator
 * which works with two cut points and the path representation.
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public class PMX extends TwoCutPointsX {

	/**
	 * Constructor
	 * @param fitnessFct is the given fitness function.
	 * @param firstParent is the first parent chromosome.
	 * @param secondParent is the second parent chromosome.
	 * @param cutPoint1 is the first cut point.
	 * @param cutPoint2 is the second cut point.
	 */
	public PMX(FitnessFunction fitnessFct, 
						Chromosome firstParent, Chromosome secondParent, 
						int cutPoint1, int cutPoint2) {
		super(fitnessFct, firstParent, secondParent, cutPoint1, cutPoint2);
	}

	/**
	 * Makes the PMX between two parent chromosomes.
	 * @return the offspring chromosome.
	 */
	protected Chromosome doCrossover() {
		
		int[] firstParent = parent1.getTourAsArr();
		int[] secondParent = parent2.getTourAsArr();
		
		int[] arrKid = new int[SIZE_OF_CHROMOSOME];

		/*Randomly select two cut points on both parents and
		 *fill in the kid by exchanging the genetic information between
		 *parents:
		 */
		for (int i = 0; i < cutPoint; i++) {
			arrKid[i] = secondParent[i];
		}
		for (int i = cutPoint; i < cutPoint2 + 1; i++) {
			arrKid[i] = firstParent[i];
		}
		for (int i = cutPoint2 + 1; i < SIZE_OF_CHROMOSOME; i++) {
			arrKid[i] = secondParent[i];
		}

		/*Handle the duplicates in kid:*/
		

		// make a set with the mapped values between cuts
		HashSet<Integer> cut = new HashSet<Integer>();

		// determine mapping relationship between cuts <->
		HashMap<Integer, Integer> mapping = new HashMap<Integer, Integer>();

		for (int i = cutPoint; i < cutPoint2 + 1; i++) {
			mapping.put(firstParent[i], secondParent[i]);
			cut.add(arrKid[i]);
		}

		/*
		 * fill in the kid from the rest of the second parent, 
		 * taking into account which values the kid already has from 
		 * the first parent
		 */
		for (int i = 0; i < cutPoint; i++) {

			/*If the value at the index i is in the set of the mapped values, i.e. 
			 *it is already in the kid at some position between the cutting indices, 
			 *then it is duplicated in the
			 *kid and has to be replaced by a new value.
			 */
			if (cut.contains(arrKid[i])) {
				//make sure the chosen mapped value is not duplicated as well
				while (cut.contains(arrKid[i])) {
					
					//take the mapped value
					arrKid[i] = mapping.get(arrKid[i]);
				}
			}
		}

		/*Make the same procedure for the part started from the second cut point
		 *till the end of the array.
		 */
		for (int i = cutPoint2 + 1; i < SIZE_OF_CHROMOSOME; i++) {

			if (cut.contains(arrKid[i])) {
				while (cut.contains(arrKid[i])) {
					arrKid[i] = mapping.get(arrKid[i]);
				}
			}

		}

		return new Chromosome(fitnessFct, new Solution(SIZE_OF_CHROMOSOME, arrKid));

	}

}
