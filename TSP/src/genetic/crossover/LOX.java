package genetic.crossover;

import java.util.HashSet;

import genetic.Chromosome;
import genetic.FitnessFunction;
import genetic.Solution;

public class LOX extends CrossoverTwoCutPoints {

	public LOX(FitnessFunction fitnessFct, 
						Chromosome firstParent, 
						Chromosome secondParent, 
						int cutPoint1, int cutPoint2) {
		super(fitnessFct, firstParent, secondParent, cutPoint1, cutPoint2);
	}

	protected Chromosome doCrossover() {
		
		int[] firstParent = parent1.getTourAsArr();
		int[] secondParent = parent2.getTourAsArr();
		
		//TODO: the same thing perhaps in othe operators
		//if the cut interval includes the whole chromosome,
		//return the first parent
		if ((cutPoint == 0) && (cutPoint2 == (lengthOfChromosome - 1))) {
			return new Chromosome(fitnessFct, new Solution(lengthOfChromosome, firstParent));
		}
		
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
		// the sequence in the second parent from the beginning of it

		int pos = 0;

		// define where we start to fill in the offspring
		if (cutPoint == 0) {
			pos = cutPoint2 + 1;
		}
		
		// go through the second parent from the very beginning of its chromosome
		for (int i = 0; i < lengthOfChromosome; i++) {
			
			//if the candidate city has not been used yet, 
			//then this city goes to the offspring 
			if (!cut.contains(secondParent[i])) {
				
				//if we are at the position in the cut, 
				//which is already filled in the offspring
				if (pos == cutPoint) {
					//if this part was at the end of the chromosome, then we are ready
					if (cutPoint2 == lengthOfChromosome - 1) {
						break;
					//if not, jump over this part	
					} else {
						pos = cutPoint2 + 1;
					}
				}
				
				arrKid[pos] = secondParent[i];				
				pos++;
			}
		}

		
		return new Chromosome(fitnessFct, new Solution(lengthOfChromosome, arrKid));
	}

}
