package genetic.crossover;

import genetic.Chromosome;
import genetic.FitnessFunction;
import tsp.Solution;
import util.SafeCopy;

/**
 * Represents a classical one-point 
 * crossover operator which works with the ordinary representation.
 * 
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public class OPX extends OrdReprX {
	
	/**
	 * The cut point
	 */
	private int cutPoint = -1;
	
	/**
	 * Constructor.
	 * Creates the OPX operator.
	 * 
	 * @param fitnessFct is the given fitness function.
	 * @param firstParent is the first parent chromosome.
	 * @param secondParent is the second parent chromosome.
	 * @param cutPoint is the first cut point.
	 */
	public OPX(FitnessFunction fitnessFct, 
			Chromosome firstParent, 
			Chromosome secondParent,
			int cutPoint) {
		super(fitnessFct, firstParent, secondParent);
		setCutPoint(cutPoint);
}

	/**
	 * Makes the OPX between two parent chromosomes.
	 * @return the offspring chromosome.
	 */
	protected Chromosome doCrossover() {
		
		int[] firstParent = transformPathToOrd(parent1.getTourAsArr());
		int[] secondParent = transformPathToOrd(parent2.getTourAsArr());
		
		int[] arrKid = new int[SIZE_OF_CHROMOSOME];
		
		/*If the cut point is the first or the last index
		 *then we just copy the corresponding parent.
		 */
		if (cutPoint == 0) {
			SafeCopy.copy(arrKid, secondParent);
		} else if (cutPoint == SIZE_OF_CHROMOSOME - 1) {
			SafeCopy.copy(arrKid, firstParent);
		} else {

			/*Fill in the offspring with the cities from the first parent
			 *from the beginning till the cut point.
			 */
			for (int i = 0; i < cutPoint; i++) {
				arrKid[i] = firstParent[i];
			}
			
			for (int i = cutPoint; i < SIZE_OF_CHROMOSOME; i++) {
				arrKid[i] = secondParent[i];
			}

		}
		
		return new Chromosome(fitnessFct, new Solution(SIZE_OF_CHROMOSOME, transformOrdToPath(arrKid)));
	}
	
	/**
	 * Sets the cut point at the given value.
	 * @param cutPoint is the given value for the cut point.
	 */
	private void setCutPoint(int cutPoint) {
		if (cutPoint < 0 || cutPoint >= SIZE_OF_CHROMOSOME) {
			throw new IllegalStateException("The cut point is not feasible!");
		}		
		this.cutPoint = cutPoint;
	}

}
