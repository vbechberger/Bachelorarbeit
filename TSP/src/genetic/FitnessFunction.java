package genetic;

public abstract class FitnessFunction {
	
	/**
	 * Calculates the fitness value of the chromosome,
	 * according to the whole distance of the tour(=genes)
	 * The larger the whole distance is, the worse is the fitness value,
	 * and the other way around.
	 * @param distance
	 * @return double the Fitness value
	 */
	public abstract double calcFitness(int[] genes);
	
	public abstract int getDimention();
}
