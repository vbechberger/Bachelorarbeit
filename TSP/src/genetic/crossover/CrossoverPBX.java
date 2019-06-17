package genetic.crossover;

import java.util.ArrayList;
import java.util.HashSet;

import genetic.Chromosome;

public class CrossoverPBX extends CrossoverRandomIndices {

	public CrossoverPBX(Chromosome firstParent, Chromosome secondParent, ArrayList<Integer> indices) {
		super(firstParent, secondParent, indices);
	}

	@Override
	protected Chromosome doCrossover(int[] parent1, int[] parent2) {
		
		int[] arrKid = new int[arrLength];
		
		//set of the selected indices
		HashSet<Integer> selectedIndices = new HashSet<Integer>();
		//set of the cities of the first parent, which take the 
		//positions according to the selected indices
		HashSet<Integer> selectedValues = new HashSet<Integer>();
		
		
		//copy the cities, which were found at the selected positions in the 
		//first parent, to the offspring at the same positions
		
		for(Integer index: indices) {
			arrKid[index] = parent1[index];
			
			//remember the selected indices
			selectedIndices.add(index);
			
			//remember the cities, which were found at the selected positions in the 
			//first parent	
			selectedValues.add(parent1[index]);
		}
		
		
		//the other positions are filled with the remaining cities in 
		//the same order as in the second parent
		int nextIndexPar2 = 0;
		for (int i = 0; i < arrLength; i++) {
			
			//jump over the indices in the kid which were already filled in with
			//the values from the first parent
			while (selectedIndices.contains(i)) {
				i++;
			}
			
			//jump over the values in the second parent, which were already 
			//copied from the first parent
			while (selectedValues.contains(parent2[nextIndexPar2])) {
				nextIndexPar2++;
			}
			
			//fill in the kid array
			arrKid[i] = parent2[nextIndexPar2];
			nextIndexPar2++;
		}
		
		return new Chromosome(arrKid);
	}

}
