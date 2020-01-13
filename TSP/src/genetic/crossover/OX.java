package genetic.crossover;

import java.util.HashSet;

import genetic.Chromosome;
import genetic.FitnessFunction;
import genetic.Solution;

public class OX extends CrossoverTwoCutPoints {

	public OX(FitnessFunction fitnessFct, 
						Chromosome firstParent, 
						Chromosome secondParent, 
						int cutPoint1, int cutPoint2) {
		super(fitnessFct, firstParent, secondParent, cutPoint1, cutPoint2);
	}

	protected Chromosome doCrossover() {
		
		int[] firstParent = parent1.getTourAsArr();
		int[] secondParent = parent2.getTourAsArr();
		
		int[] arrKid = new int[lengthOfChromosome];

		// make a set with values between cuts
		HashSet<Integer> cut = new HashSet<Integer>();

		// values between the cut points are copied to the offspring from the
		// first parent at the same positions
		for (int i = cutPoint; i < cutPoint2 + 1; i++) {
			arrKid[i] = firstParent[i];

			// save the values between cut points of the first parent
			cut.add(firstParent[i]);
		}

		// the remaining positions of the offspring are filled by considering
		// the sequence in the second parent, starting after the second
		// cut point and then continue from the beginning of the second parent
		int pos = -1;

		// define where we start to fill in the offspring
		if (cutPoint2 == lengthOfChromosome - 1) {
			pos = 0;
		} else {
			pos = cutPoint2 + 1;
		}
		// fill in the offspring
		for (int i = cutPoint2 + 1; i < lengthOfChromosome; i++) {
			if (!cut.contains(secondParent[i])) {
				arrKid[pos] = secondParent[i];
				// if the tail part of the offspring is filled, start from the
				// beginning
				if (pos == lengthOfChromosome - 1) {
					pos = 0;
				} else {
					pos++;
				}
			}
		}
		for (int i = 0; i < cutPoint2 + 1; i++) {
			if (!cut.contains(secondParent[i])) {
				arrKid[pos] = secondParent[i];

				// if the tail part of the offspring is filled, start from the
				// beginning
				if (pos == lengthOfChromosome - 1) {
					pos = 0;
				} else {
					pos++;
				}

			}
		}

		return new Chromosome(fitnessFct, new Solution(lengthOfChromosome, arrKid));
	}

}
