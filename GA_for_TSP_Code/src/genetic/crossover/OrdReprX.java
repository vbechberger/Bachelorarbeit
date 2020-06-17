package genetic.crossover;

import java.util.ArrayList;
import java.util.List;

import genetic.Chromosome;
import genetic.FitnessFunction;
import tsp.representation.PathTour;

/**
 * An abstract class which represents crossover operators which work with
 * the ordinary representation.
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public abstract class OrdReprX extends Crossover{
	
	/**First parent in the path representation*/
	protected PathTour parent1;
	/**Second parent in the path representation*/
	protected PathTour parent2;
	
	/**First parent in the ordinal representation*/
	protected int[] ordinalPar1;
	
	/**Second parent in the ordinal representation*/
	protected int[] ordinalPar2;
	
	/**Reference tour*/
	private List<Integer> ref;

	/**
	 * Constructor.
	 * @param fitnessFct is the given fitness function.
	 * @param firstParent is the first parent chromosome.
	 * @param secondParent is the second parent chromosome.
	 */
	public OrdReprX(FitnessFunction fitnessFct, 
					 Chromosome firstParent, 
					 Chromosome secondParent) {
		super(fitnessFct, firstParent, secondParent);
		parent1 = firstParent.getGenesAsPathTour();
		parent2 = secondParent.getGenesAsPathTour();
	}
	
	/**
	 * Initializes the reference tour.
	 */
	private void initRef() {
		ref = new ArrayList<Integer>();
		
		for(int i = 0; i < SIZE_OF_CHROMOSOME; i++) {
			ref.add(i, i);
		}		
	}

	/**
	 * Transforms the given tour in the path representation in form of array 
	 * into the ordinal representation in form of array.
	 * @param path the given tour in the path representation in form of array. 
	 * @return its ordinal representation in form of array.
	 */
	public int[] transformPathToOrd(int[] path) {
		
		int[] ordinal = new int[SIZE_OF_CHROMOSOME];
		
		assert ordinal.length == path.length : "Inconsistent length of tours.";
		
		initRef();
		for(int i = 0; i < path.length; i++) {
			
			Integer nextElem = path[i];
			
			int posInRef = ref.indexOf(nextElem);
			
			ref.remove(nextElem);
			ordinal[i] = posInRef;
		}
				
		return ordinal;		
	}
	
	/**
	 * Transforms the given tour in the ordinal representation in form of array 
	 * into the path representation in form of array.
	 * @param ordinal the given tour in the ordinal representation in form of array.
	 * @return its path representation in form of array.
	 */
	public int[] transformOrdToPath(int[] ordinal) {
		
		int[] path = new int[SIZE_OF_CHROMOSOME];
		
		assert ordinal.length == path.length : "Inconsistent length of tours.";
		
		initRef();
		for(int i = 0; i < ordinal.length; i++) {
			
			Integer elem = ref.get(ordinal[i]);
			
			path[i] = elem;
			ref.remove(elem);
		}
				
		return path;		
	}	

}
