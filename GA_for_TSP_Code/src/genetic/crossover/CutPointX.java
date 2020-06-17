package genetic.crossover;

import genetic.Chromosome;
import genetic.FitnessFunction;

/**
 * This class represents the crossover operators which use a cut point.
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11 
 *
 */
public abstract class CutPointX extends PathReprX {
	
	/**The index where the cut point is made*/
	protected int cutPoint = -1;

	/**
	 * Constructor.
	 * Creates a crossover operator which uses a cut point.
	 * 
	 * @param fitnessFct is the given fitness function.
	 * @param firstParent is the first parent chromosome.
	 * @param secondParent is the second parent chromosome.
	 * @param cutPoint is the index where the cut point will be made.
	 */
	public CutPointX(FitnessFunction fitnessFct, 
							Chromosome firstParent, 
							Chromosome secondParent, 
							int cutPoint) {
		super(fitnessFct, firstParent, secondParent);
		setCutPoint(cutPoint);
	}

	
	/**
	 * Sets the current cut point at the specified value.
	 * @param cutPoint is the specified cut point.
	 */
	private void setCutPoint(int cutPoint) {
		if (cutPoint < 0 || cutPoint >= SIZE_OF_CHROMOSOME) {
			throw new IllegalStateException("The cut point is not feasible!");
		}		
		this.cutPoint = cutPoint;
	}
}
