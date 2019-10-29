package genetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Population {
	
	private List<Chromosome> population = new ArrayList<Chromosome>();

	// general parameters

	// size
	final int MIN_SIZE = 5;
	int size;

	/**
	 * Creates a new population according to the given
	 * size.
	 * The created population is filled with the 
	 * corresponding amount of chromosomes,
	 * which are created according to the given
	 * fitness function.
	 * The population is sorted based on the fitness value after that:
	 * the chromosomes with the larger fitness value come first.
	 * 
	 * @param size
	 * @param fitnessFct
	 */
	public Population(int size, FitnessFunction fitnessFct) {
		setSize(size);
		init(fitnessFct);		
	}

	/**
	 * Initializes the population, creating the generation #0
	 * The population is sorted based on the fitness value, where
	 * the chromosomes with the larger fitness value come first.
	 * 
	 *  @param fitnessFct the given fitness function,
	 *  					which is needed to create chromosomes.
	 */
	private void init(FitnessFunction fitnessFct) {
		
		int step = 0;
		while (step < this.size) {
			Chromosome chromosome = new Chromosome(fitnessFct);
			population.add(chromosome);
		}	
		
		/**Sort the population based on the fitness value of chromosomes 
		 * with the fittest ones at the beginning*/
		Collections.sort(population, Collections.reverseOrder());
	}
	
	
	/* Getter- and setter methods */
	public int getSize() {
		return this.size;
	}

	private void setSize(int size) {
		if (size < MIN_SIZE) {
			throw new IllegalArgumentException(
					"The size of the population " + "has to be greater than " + MIN_SIZE + " !");
		}
		this.size = size;
	}
	
	/**
	 * Adds the given chromosome to the population,
	 * which is always sorted after that.
	 * 
	 * @param chromosome the given chromosome to be added to the population.
	 * @throws NullPointerException, if the null object was given as a parameter.
	 * @throws IllegalStateException, if the given chromosome could not be
	 * 									added to the population.
	 */
	public void addChromosome(Chromosome chromosome) {
		
		if(chromosome == null) {
			throw new NullPointerException("A null object cannot be added to the population.");			
		}
		
		if (population.add(chromosome) == false) {
			throw new IllegalStateException("The chromosome with the fitness value: " 
											+ chromosome.getFitness() 
											+ " could not be added to "
											+ "the population");			
		}
		
		/**Sort the population based on the fitness value of chromosomes 
		 * with the fittest ones at the beginning*/
		Collections.sort(population, Collections.reverseOrder());
				
	}
	
	/**
	 * Removes the given chromosome from the population,
	 * which is always sorted after that.
	 * 
	 * @param chromosome the given chromosome to be removed from the population.
	 * @throws NullPointerException, if the null object was given as a parameter.
	 * @throws IllegalStateException, if the given chromosome could not be
	 * 									removed from the population.
	 */
	public void removeChromosome(Chromosome chromosome) {
		
		if(chromosome == null) {
			throw new NullPointerException("A null object cannot "
											+ "be removed from the population.");			
		}
		if (population.remove(chromosome) == false) {
			throw new IllegalStateException("The chromosome with the fitness value: " 
											+ chromosome.getFitness() 
											+ " could not be removed from "
											+ "the population");			
		}
				
		/**Sort the population based on the fitness value of chromosomes 
		 * with the fittest ones at the beginning*/
		Collections.sort(population, Collections.reverseOrder());
	}
	
	public Chromosome getBestFitChromosome() {
		if (population.size() <= 1) {
			throw new IllegalStateException("The population is too small!");
		}
		return population.get(0);
	}
	
	public Chromosome getSecondFitChromosome() {
		if (population.size() <= 1) {
			throw new IllegalStateException("The population is too small!");
		}
		return population.get(1);
	}

}
