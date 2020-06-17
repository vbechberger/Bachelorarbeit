package genetic.crossover;

import genetic.Chromosome;
import genetic.FitnessFunction;

/**
 * An abstract class which represent the crossover operators which
 * work with two cut points.
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public abstract class TwoCutPointsX extends CutPointX {

	/**
	 * The second cut point
	 */
	protected int cutPoint2 = -1;

	/**
	 * Constructor.
	 * @param fitnessFct is the given fitness function.
	 * @param firstParent is the first parent chromosome.
	 * @param secondParent is the second parent chromosome.
	 * @param cutPoint1 is the first cut point.
	 * @param cutPoint2 is the second cut point.
	 */
	public TwoCutPointsX(FitnessFunction fitnessFct, 
								 Chromosome firstParent, 
								 Chromosome secondParent, 
								 int cutPoint, 
								 int cutPoint2) {
		
		super(fitnessFct, firstParent, secondParent, cutPoint);
		setCutPoint2(cutPoint2);
	}

	/**
	 * Sets the second cut point.
	 * @param cutPoint2 is the given index for the second cut point.
	 */
	private void setCutPoint2(int cutPoint2) {
		if (cutPoint2 < 0 || cutPoint >= SIZE_OF_CHROMOSOME) {
			throw new IllegalArgumentException("The second cut point is out of bounds!");
		}
		if (cutPoint2 < cutPoint) {
			throw new IllegalArgumentException("The second cut point can not be smaller than the first cut point!");
		}
		this.cutPoint2 = cutPoint2;
	}

}
