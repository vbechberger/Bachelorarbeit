package genetic.crossover;

import java.util.HashSet;
import java.util.Set;

import genetic.Chromosome;
import genetic.FitnessFunction;
import tsp.Solution;

/**
 * Represents the position-based crossover operator
 * which works with a set if indices and path representation.
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public class PBX extends RandomIndicesX {

	/**
	 * Constructor
	 * @param fitnessFct is the given fitness function.
	 * @param firstParent is the first parent chromosome.
	 * @param secondParent is the second parent chromosome.
	 * @param indices is the given set of indices.
	 */
	public PBX(FitnessFunction fitnessFct, Chromosome firstParent, Chromosome secondParent, Set<Integer> indices) {
		super(fitnessFct, firstParent, secondParent, indices);
	}


	/**
	 * Makes the PBX crossover between two parent chromosomes.
	 * @return the offspring chromosome.
	 */
	protected Chromosome doCrossover() {
		
		int[] firstParent = parent1.getTourAsArr();
		int[] secondParent = parent2.getTourAsArr();
		
		int[] arrKid = new int[SIZE_OF_CHROMOSOME];
		
		/*Make a set of the cities of the first parent, which take the 
		 *positions according to the selected indices.
		 */
		HashSet<Integer> selectedValues = new HashSet<Integer>();
		
		
		/*Copy the cities, which were found at the selected positions in the 
		 *first parent, to the offspring at the same positions.
		 */
		
		for(Integer index: indices) {
		
			arrKid[index] = firstParent[index];
			
			/*Remember the cities, which were found at the selected positions in the 
			 *first parent.
			 */	
			selectedValues.add(firstParent[index]);
		}
		
		
		/*The other positions are filled with the remaining cities in 
		 *the same order as in the second parent.
		 */
		int nextIndexPar2 = 0;
		for (int i = 0; i < SIZE_OF_CHROMOSOME; i++) {
			
			/*Jump over the indices in the kid which were already filled in with
			 *the values from the first parent.
			 */
			while (indices.contains(i)) {
				/*Check the case if i equals the last index*/
				if(i < SIZE_OF_CHROMOSOME - 1) {
					i++;
					/*if yes we are ready*/
				} else {
					return new Chromosome(fitnessFct, new Solution(SIZE_OF_CHROMOSOME, arrKid));
				}
				
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
		
		return new Chromosome(fitnessFct, new Solution(SIZE_OF_CHROMOSOME, arrKid));
	}

}
