package genetic;

/**
 * Represents an abstract fitness function to calculate the fitness value.
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11 
 *
 */
public abstract class FitnessFunction {
	
	/**
	 * Calculates the fitness value of the chromosome,
	 * according to the whole distance of the tour(=genes)
	 * The larger the whole distance is, the worse is the fitness value,
	 * and the other way around.
	 * @param genes is the given genes
	 * @return calculated fitness value
	 */
	public abstract double calcFitness(int[] genes);
	
	/**
	 * 
	 * @return length of the genes (= number of cities)
	 */
	public abstract int getDimention();
}
