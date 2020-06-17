package genetic;

/**
 * Represents a dummy distance function where all the distances are 0.
 * It is used mainly for the tests.
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public class DummyFitnessFct extends FitnessFunction{
	
	private int dimension = -1;
	
	/**
	 * Constructor
	 * @param dimension is the given dimension
	 */
	public DummyFitnessFct(int dimension) {
		setDimension(dimension);
	}
	
	/**
	 * Sets the fitness value at 0.
	 * @return fitness value set at 0.
	 */
	public double calcFitness(int[] genes) {
		return 0;
	}
	
	/**
	 * @return the dimension.
	 */
	public int getDimention() {
		return this.dimension;
	}
	
	/**
	 * Sets the dimension.
	 * @param dimension is the given dimension.
	 */
	private void setDimension(int dimension) {
		if (dimension <= 0) {
			throw new IllegalArgumentException("The dimension has to be positive!");			
		}		
		this.dimension = dimension;
	}

}
