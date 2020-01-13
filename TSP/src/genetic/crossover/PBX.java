package genetic.crossover;

import java.util.HashSet;
import java.util.Set;

import genetic.Chromosome;
import genetic.FitnessFunction;
import genetic.Solution;

public class PBX extends CrossoverRandomIndices {

	public PBX(FitnessFunction fitnessFct, Chromosome firstParent, Chromosome secondParent, Set<Integer> indices) {
		super(fitnessFct, firstParent, secondParent, indices);
	}

	@Override
	protected Chromosome doCrossover() {
		
		int[] firstParent = parent1.getTourAsArr();
		int[] secondParent = parent2.getTourAsArr();
		
		int[] arrKid = new int[lengthOfChromosome];
		
		//make a set of the cities of the first parent, which take the 
		//positions according to the selected indices
		HashSet<Integer> selectedValues = new HashSet<Integer>();
		
		
		//copy the cities, which were found at the selected positions in the 
		//first parent, to the offspring at the same positions
		
		for(Integer index: indices) {
			arrKid[index] = firstParent[index];
			
			//remember the cities, which were found at the selected positions in the 
			//first parent	
			selectedValues.add(firstParent[index]);
		}
		
		
		//the other positions are filled with the remaining cities in 
		//the same order as in the second parent
		int nextIndexPar2 = 0;
		for (int i = 0; i < lengthOfChromosome; i++) {
			
			//jump over the indices in the kid which were already filled in with
			//the values from the first parent
			while (indices.contains(i)) {
				i++;
			}
			
			//jump over the values in the second parent, which were already 
			//copied from the first parent
			while (selectedValues.contains(secondParent[nextIndexPar2])) {
				nextIndexPar2++;
			}
			
			//fill in the kid array
			arrKid[i] = secondParent[nextIndexPar2];
			nextIndexPar2++;
		}
		
		return new Chromosome(fitnessFct, new Solution(lengthOfChromosome, arrKid));
	}

}
