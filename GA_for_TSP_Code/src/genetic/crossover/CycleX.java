package genetic.crossover;

import java.util.ArrayList;
import java.util.HashMap;

import genetic.Chromosome;
import genetic.FitnessFunction;
import tsp.Solution;
import util.SafeCopy;

/**
 * This class represents the cycle crossover (developed by Oliver et al., 1987), 
 * which focuses on subsets of cities 
 * that occupy the same subset of positions in both parents.
 * 
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11 
 *
 */
public class CycleX extends PathReprX {
	

	/**
	 * Constructor.
	 * Creates a cycle crossover.
	 * @param fitnessFct is the given fitness function.
	 * @param firstParent is the first parent chromosome.
	 * @param secondParent is the second parent chromosome.
	 */
	public CycleX(FitnessFunction fitnessFct, 
							Chromosome firstParent, 
							Chromosome secondParent) {
		super(fitnessFct, firstParent, secondParent);
	}

	/**
	 * Makes a cycle crossover between two parent chromosomes.
	 * 
	 * @return the offspring chromosome.
	 */
	protected Chromosome doCrossover() {
		
		int[] arrKid = new int[SIZE_OF_CHROMOSOME];
		
		int[] firstParent = parent1.getTourAsArr();
		int[] secondParent = parent2.getTourAsArr();
		
				
		HashMap<Integer, Integer> cycle = findCycle(firstParent, secondParent);
		
		/*If there is no cycle, i.e. if two parents are equal,
		 *return the first parent.
		 */
		if(cycle == null) {
			SafeCopy.copy(arrKid, firstParent);
			return new Chromosome(fitnessFct, new Solution(SIZE_OF_CHROMOSOME, arrKid));
		}
		
		
		/*Fill in the kid with the values of the cycle from the first parent,
		 *using the same positions.
		 **/
		for (Integer index: cycle.keySet()) {
			arrKid[index] = cycle.get(index);
		}
		
		/*fill in the remaining positions of the kid with the values from the 
		 *second parent, preserving the positions as well.
		 */
		
		for(int i = 0; i < SIZE_OF_CHROMOSOME; i++) {
			if(!cycle.containsValue(secondParent[i])) {
				arrKid[i] = secondParent[i];
			} 
		}
		
		return new Chromosome(fitnessFct, new Solution(SIZE_OF_CHROMOSOME, arrKid));
	}
	
	private HashMap<Integer, Integer> findCycle(int[] parent1, int[] parent2) {
		
		ArrayList<Integer> parent1List = new ArrayList<Integer>();
		
		/*Create a hash map with the elements which have the same indices in both parents
		 *Map: index -> city number
		 */
		HashMap<Integer, Integer> elements = new HashMap<Integer, Integer>();
		
		SafeCopy.copy(parent1List, parent1);
		
		/*Find the start element: start with the first element of the first parent.
		 *If the first elements of the parents are equal,
		 *start with the next element, where the parents differ.
		 */
		int startIndex = 0;
		while(parent1[startIndex] == parent2[startIndex]) {
			startIndex++;
			//if both parent are equal
			if (startIndex == SIZE_OF_CHROMOSOME) {
				//then there is no subcycle, return null
				return null;
			}
		}
				
		/*Find a cycle till we find this element in the second parent*/
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
