package genetic.crossover;

import java.util.ArrayList;
import java.util.HashMap;

import genetic.Chromosome;
import genetic.FitnessFunction;
import genetic.Solution;
import util.SaveCopy;

/**
 * This class represents the cycle crossover(Oliver et al., 1987), which focuses 
 * on subsets of cities that occupy the same subset of positions in both parents.
 * 
 * @author vbechberger
 *
 */
public class CycleX extends CrossoverCycleSubset {
	

	public CycleX(FitnessFunction fitnessFct, 
							Chromosome firstParent, 
							Chromosome secondParent) {
		super(fitnessFct, firstParent, secondParent);
	}

	protected Chromosome doCrossover() {
		
		int[] arrKid = new int[lengthOfChromosome];
		
		int[] firstParent = parent1.getTourAsArr();
		int[] secondParent = parent2.getTourAsArr();
		
				
		HashMap<Integer, Integer> cycle = findCycle(firstParent, secondParent);
		
		//if there is no cycle, i.e. if two parents are equal,
		//return the first parent
		if(cycle == null) {
			SaveCopy.copy(arrKid, firstParent);
			return new Chromosome(fitnessFct, new Solution(lengthOfChromosome, arrKid));
		}
		
		
		//fill in the kid with the values of the cycle from the first parent,
		//using the same positions
		for (Integer index: cycle.keySet()) {
			arrKid[index] = cycle.get(index);
		}
		
		//fill in the remaining positions of the kid with the values from the 
		//second parent, preserving the positions as well
		
		for(int i = 0; i < lengthOfChromosome; i++) {
			if(!cycle.containsValue(secondParent[i])) {
				arrKid[i] = secondParent[i];
			} 
		}
		
		return new Chromosome(fitnessFct, new Solution(lengthOfChromosome, arrKid));
	}
	
	private HashMap<Integer, Integer> findCycle(int[] parent1, int[] parent2) {
		
		ArrayList<Integer> parent1List = new ArrayList<Integer>();
		
		//the hash map with the elements which have the same indices in both parents
		//Map: index -> city number
		HashMap<Integer, Integer> elements = new HashMap<Integer, Integer>();
		
		SaveCopy.copy(parent1List, parent1);
		
		//find the start element: start with the first element of the first parent
		//But if the first elements of the parents are equal,
		//start with the next element, where the parents differ
		int startIndex = 0;
		while(parent1[startIndex] == parent2[startIndex]) {
			startIndex++;
			//if both parent are equal
			if (startIndex == lengthOfChromosome) {
				//then there is no subcycle, return null
				return null;
			}
		}
		
		
		//find a cycle till we find this element in the second parent
		int firstElemPar1 = parent1[startIndex];
		elements.put(startIndex, firstElemPar1);
		
		int elem = parent2[startIndex];
		while(elem != firstElemPar1) {			
			int index = parent1List.indexOf(elem);
			elements.put(index, elem);
			
			elem = parent2[index];			
		}
		
		return elements;
	}
	
}
