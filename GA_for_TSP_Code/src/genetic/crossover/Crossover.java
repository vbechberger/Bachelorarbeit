package genetic.crossover;

import genetic.Chromosome;
import genetic.FitnessFunction;

/**
 * An abstract class which represents a crossover operator
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11 
 *
 */
public abstract class Crossover {
	
	/**Size of the chromosome which is always fixed*/
	protected final int SIZE_OF_CHROMOSOME;
	
	/**The fitness function which is used to calculate the fitness value*/
	protected FitnessFunction fitnessFct;
	
	/**The offspring which is created after the crossover operator is done*/
	protected Chromosome offspring;
	
	/**The first parent chromosome*/
	protected Chromosome parent1;
	
	/**The second parent chromosome*/
	protected Chromosome parent2;

	
	/**
	 * Constructor.
	 * @param fitnessFct is the fitness function to be used to calculate 
	 * 						the fitness value of the offspring which will be created							
	 * @param firstParent is the first parent chromosome
	 * @param secondParent is the second parent chromosome
	 */
	public Crossover(FitnessFunction fitnessFct, 
					 Chromosome firstParent, 
					 Chromosome secondParent) {
		
		this.fitnessFct = fitnessFct;		
		this.SIZE_OF_CHROMOSOME = firstParent.getSize();				
		setParents(firstParent, secondParent);

	}
	
	/**
	 * Sets the parents
	 * @param firstParent is the first parent chromosome
	 * @param secondParent is the second parent chromosome
	 */
	private void setParents(Chromosome firstParent, Chromosome secondParent) {
		parent1 = firstParent;
		parent2 = secondParent;
	}
	
	/**Each crossover operator has to implement this method*/
	protected abstract Chromosome doCrossover();

	/**
	 * 
	 * @return the offspring which was created
	 */
	public Chromosome getOffspring() {
		
		offspring = doCrossover();
		
		if (offspring == null) {
			throw new RuntimeException("Crossover was not successful. There exists no kid!");
		}
		return offspring;
	}	

}
