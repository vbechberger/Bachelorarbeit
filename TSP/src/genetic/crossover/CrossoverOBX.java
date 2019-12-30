package genetic.crossover;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Set;

import genetic.Chromosome;
import genetic.FitnessFunction;
import genetic.Solution;
import util.SaveCopy;

/**
 * This class represents the order-based crossover.
 * This operator randomly selects a subset of cities in 
 * the first parent chromosome and puts them into the 
 * offspring in the exactly the same order, in which these 
 * cities occur in the first parent, but at the positions, 
 * which these cities occupy in the second parent. 
 * Each remaining position in the offspring is filled with 
 * a city which occupies this position in the second parent.
 * 
 * Note that, for convenience of implementation, 
 * this class gets in the constructor 
 * not the subset of cities directly, but the subset of positions,
 * which these cities occupy in the first parent.
 * 
 * @author valeriyabechberger
 *
 */
public class CrossoverOBX extends CrossoverRandomIndices {

	public CrossoverOBX(FitnessFunction fitnessFct, Chromosome firstParent, Chromosome secondParent, Set<Integer> indices) {
		super(fitnessFct, firstParent, secondParent, indices);
	}

	protected Chromosome doCrossover(Chromosome par1, Chromosome par2) {
		
		int[] parent1 = par1.getGenesInPath();
		int[] parent2 = par2.getGenesInPath();
		
		int[] arrKid = new int[lengthOfChromosome];

		SaveCopy.copy(arrKid, parent2);

		// find the elements which stand at the given positions in the first
		// parent,
		// preserving the order
		int[] selected = new int[indices.size()];

		int count = 0;
		for(Integer index: indices) {
			selected[count] = parent1[index];
			count++;
		}
		// find at which indices these elements stand in the second parent
		ArrayList<Integer> parent2List = new ArrayList<Integer>();

		SaveCopy.copy(parent2List, parent2);

		ArrayList<Integer> indicesInParent2 = new ArrayList<Integer>();
		for (int i = 0; i < selected.length; i++) {
			indicesInParent2.add(parent2List.indexOf(selected[i]));
		}
		// sort the indices
		Collections.sort(indicesInParent2);

		// put the chosen elements from the first parent into the second parent,
		// at the defined indices but with preserved order
		for (int i = 0; i < selected.length; i++) {
			arrKid[indicesInParent2.get(i)] = selected[i];
		}

		return new Chromosome(fitnessFct, new Solution(lengthOfChromosome, arrKid));

	}

}
