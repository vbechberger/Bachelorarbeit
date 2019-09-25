package genetic.crossover;

import java.util.ArrayList;

import java.util.Collections;

import genetic.Chromosome;
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

	public CrossoverOBX(Chromosome firstParent, Chromosome secondParent, ArrayList<Integer> indices) {
		super(firstParent, secondParent, indices);
	}

	protected Chromosome doCrossover(int[] parent1, int[] parent2) {
		
		int[] arrKid = new int[arrLength];

		SaveCopy.copy(arrKid, parent2);

		// find the elements which stand at the given positions in the first
		// parent,
		// preserving the order
		int[] selected = new int[indices.size()];

		for (int i = 0; i < selected.length; i++) {
			selected[i] = parent1[indices.get(i)];
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

		return new Chromosome(arrKid);

	}

}
