package genetic.crossover;

import java.util.HashSet;
import genetic.Chromosome;
import genetic.FitnessFunction;
import genetic.Solution;
import util.SaveCopy;

public class ModifiedX extends CrossoverCutPoint {

	public ModifiedX(FitnessFunction fitnessFct, 
								Chromosome firstParent, 
								Chromosome secondParent, 
								int cutPoint) {
		super(fitnessFct, firstParent, secondParent, cutPoint);
	}

	@Override
	protected Chromosome doCrossover() {
		
		int[] firstParent = parent1.getTourAsArr();
		int[] secondParent = parent2.getTourAsArr();
		
		int[] arrKid = new int[lengthOfChromosome];
		
		//if the cut point is the first or the last index
		//then we just copy the corresponding parent
		if (cutPoint == 0) {
			SaveCopy.copy(arrKid, secondParent);
		} else if (cutPoint == lengthOfChromosome - 1) {
			SaveCopy.copy(arrKid, firstParent);
		} else {

			//make a set with cities from the first parent,
			//which stand before the cut point
			//so that we can check the duplicates
			HashSet<Integer> cut = new HashSet<Integer>();

			//fill in the offspring with the cities from the first parent
			//from the beginning till the cut point
			for (int i = 0; i < cutPoint; i++) {
				arrKid[i] = firstParent[i];
				// fill in the set
				cut.add(arrKid[i]);
			}

			//define where we start to fill in the offspring
			//with the cities from the second parent
			int pos = cutPoint;

			for (int i = 0; i < lengthOfChromosome; i++) {
				//if the next city in the second parent has 
				//not been taken from the first parent, i.e.
				//this city did not stand in the 1st parent before the cut point
				if (!cut.contains(secondParent[i])) {
					//then it goes to the offspring
					arrKid[pos] = secondParent[i];
					pos++;
				}
				//otherwise we ignore it and go to the next city in the 2nd parent
			}
		}

		return new Chromosome(fitnessFct, new Solution(lengthOfChromosome, arrKid));
	}

}
