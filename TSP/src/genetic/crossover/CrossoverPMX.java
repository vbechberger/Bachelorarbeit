package genetic.crossover;

import java.util.HashMap;
import java.util.HashSet;

import genetic.Chromosome;
import genetic.FitnessFunction;

public class CrossoverPMX extends CrossoverTwoCutPoints {

	public CrossoverPMX(FitnessFunction fitnessFct, 
						Chromosome firstParent, Chromosome secondParent, 
						int cutPoint1, int cutPoint2) {
		super(fitnessFct, firstParent, secondParent, cutPoint1, cutPoint2);
	}

	protected Chromosome doCrossover(int[] parent1, int[] parent2) {
		
		int[] arrKid = new int[arrLength];

		// randomly select two cut points on both parents
		// fill in two kids by exchanging the genetic information between
		// parents:
		for (int i = 0; i < cutPoint; i++) {
			arrKid[i] = parent2[i];
		}
		for (int i = cutPoint; i < cutPoint2 + 1; i++) {
			arrKid[i] = parent1[i];
		}
		for (int i = cutPoint2 + 1; i < arrLength; i++) {
			arrKid[i] = parent2[i];
		}

		// handle the duplicates in kids:

		// make a set with the mapped values between cuts
		HashSet<Integer> cut = new HashSet<Integer>();

		// determine mapping relationship between cuts <->
		HashMap<Integer, Integer> mapping = new HashMap<Integer, Integer>();

		for (int i = cutPoint; i < cutPoint2 + 1; i++) {
			mapping.put(parent1[i], parent2[i]);
			cut.add(arrKid[i]);
		}

		/**
		 * fill in the first kid from the rest of the second parent, 
		 * taking into account which values the first kid already has from 
		 * the first parent
		 */
		for (int i = 0; i < cutPoint; i++) {

			// if the value at the index i is in the set of the mapped values, i.e. 
			// it is already in the kid at some position between the cutting indices, 
			// then it is duplicated in the
			// kid and has to be replaced by a new value
			if (cut.contains(arrKid[i])) {
				//make sure the chosen mapped value is not duplicated as well
				while (cut.contains(arrKid[i])) {
					
					//take the mapped value
					arrKid[i] = mapping.get(arrKid[i]);
				}
			}
		}

		// make the same procedure for the part started from the second cut point
		// till the end of the array
		for (int i = cutPoint2 + 1; i < arrLength; i++) {

			if (cut.contains(arrKid[i])) {
				while (cut.contains(arrKid[i])) {
					arrKid[i] = mapping.get(arrKid[i]);
				}
			}

		}

		return new Chromosome(fitnessFct, arrKid);

	}

}
