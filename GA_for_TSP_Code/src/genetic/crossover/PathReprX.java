package genetic.crossover;

import genetic.Chromosome;
import genetic.FitnessFunction;
import tsp.representation.PathTour;

/**
 * An abstract class which represents the crossover operators
 * which work with the path representation.
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public abstract class PathReprX extends Crossover{
	
	/**First parent in the path representation*/
	protected PathTour parent1;
	/**Second parent in the path representation*/
	protected PathTour parent2;

	/**
	 * Constructor.
	 * @param fitnessFct is the given fitness function.
	 * @param firstParent is the first parent chromosome.
	 * @param secondParent is the second parent chromosome.
	 */
	public PathReprX(FitnessFunction fitnessFct, 
					 Chromosome firstParent, 
					 Chromosome secondParent) {
		super(fitnessFct, firstParent, secondParent);
		parent1 = firstParent.getGenesAsPathTour();
		parent2 = secondParent.getGenesAsPathTour();
	}

}
