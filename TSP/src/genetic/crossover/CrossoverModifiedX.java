package genetic.crossover;

import java.util.HashSet;

import genetic.Chromosome;
import util.SaveCopy;

public class CrossoverModifiedX extends CrossoverCutPoint {

	public CrossoverModifiedX(Chromosome firstParent, Chromosome secondParent, int cutPoint) {
		super(firstParent, secondParent, cutPoint);
	}

	@Override
	protected Chromosome doCrossover(int[] parent1, int[] parent2) {
		
		int[] arrKid = new int[arrLength];
		
		//if the cut point is the first or the last index
		//then we just copy the corresponding parent
		if (cutPoint == 0) {
			SaveCopy.copy(arrKid, parent2);
		} else if (cutPoint == arrLength - 1) {
			SaveCopy.copy(arrKid, parent1);
		} else {

			//make a set with cities from the first parent,
			//which stand before the cut point
			//so that we can check the duplicates
			HashSet<Integer> cut = new HashSet<Integer>();

			//fill in the offspring with the cities from the first parent
			//from the beginning till the cut point
			for (int i = 0; i < cutPoint; i++) {
				arrKid[i] = parent1[i];
				// fill in the set
				cut.add(arrKid[i]);
			}

			//define where we start to fill in the offspring
			//with the cities from the second parent
			int pos = cutPoint;

			for (int i = 0; i < arrLength; i++) {
				//if the next city in the second parent has 
				//not been taken from the first parent, i.e.
				//this city did not stand in the 1st parent before the cut point
				if (!cut.contains(parent2[i])) {
					//then it goes to the offspring
					arrKid[pos] = parent2[i];
					pos++;
				}
				//otherwise we ignore it and go to the next city in the 2nd parent
			}
		}

		return new Chromosome(arrKid);
	}

}
