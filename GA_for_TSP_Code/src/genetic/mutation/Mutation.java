package genetic.mutation;

import genetic.Chromosome;
import genetic.FitnessFunction;
import util.SafeCopy;

/**
 * An abstract class which represents the mutation operators.
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public abstract class Mutation {
	
	/**
	 * The fitness function which is used to 
	 * calculate the fitness value of the produced mutant.
	 */
	protected FitnessFunction fitnessFct;
	
	/**
	 * The mutant which is the result of the applying
	 * a mutation operator to the offspring.
	 */
	protected Chromosome mutant;
	
	/**
	 * The genes of the mutant chromosome.
	 */
	protected int[] mutantGenes;
	
	/**
	 * The length of the chromosome.
	 */
	protected final int SIZE_OF_CHROMOSOME;
	
	/**
	 * Constructor.
	 * @param fitnessFct is the given fitness function.
	 * @param kid is the given offsprimg which mutates.
	 */
	public Mutation(FitnessFunction fitnessFct, Chromosome kid) {

		this.fitnessFct = fitnessFct;
		SIZE_OF_CHROMOSOME = kid.getGenesAsArray().length;
		mutantGenes = new int[SIZE_OF_CHROMOSOME];
		SafeCopy.copy(mutantGenes, kid.getGenesAsArray());				
	}
	
	protected abstract Chromosome doMutation();

	/**
	 * Return the mutant chromosome.
	 * @return the mutant chromosome.
	 */
	public Chromosome getMutant() {
		
		if (mutantGenes.length == 0) {
			throw new IllegalStateException("The array of genes of the kid's chromosome is empty!");
		}

		mutant = doMutation();
		
		if (mutant == null) {
			throw new RuntimeException("Mutation was not successful. There exists no mutant!");
		}
		
		return mutant;
	}	
}
