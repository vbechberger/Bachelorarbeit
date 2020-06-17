package genetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a population which consists of the chromosomes
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11 
 *
 */
public class Population {
	
	
	/**List of the chromosomes*/
	private List<Chromosome> population;

	/**Current size of the population*/
	private int currentSize;
	
	/**Desired size of the population*/
	private final int targetSize;
	
	/**
	 * Constructor.
	 * Creates a population of the given size.
	 * @param targetSize is the desired size of the population.
	 */
	public Population(int targetSize) {
		this.targetSize = targetSize;
		population = new ArrayList<Chromosome>();
		currentSize = 0;
	}
	
	/**
	 * Constructor
	 * Creates an empty population
	 */
	public Population() {
		this(0);
	}
	/**
	 * Fills in the population with the additional chromosomes
	 * (which have a random population of cities in their genes)
	 * until the target size is reached.
	 * @param fitnessFct is the fitness function which is necessary
	 * 						to create chromosomes.
	 * @param random is the Random object so that we can control the result via setting seeds
	 */
	public void completePopulationWithTargetSize(FitnessFunction fitnessFct, Random random) {
		
		while(currentSize < targetSize) {
			
			Chromosome next = new Chromosome(fitnessFct, random);
			population.add(next);
			currentSize++;			
		}	
		
		assert currentSize == targetSize: "Current size "
				+ "of population has to be equal to the target size.";
	}
	
	
	/**
	 * Adds the given chromosome to the population,
	 * 
	 * @param chromosome the given chromosome to be added to the population.
	 * @throws NullPointerException, if the null object was given as a parameter.
	 * @throws IllegalStateException, if the given chromosome could not be
	 * 									added to the population.
	 */
	public boolean addChromosome(Chromosome chromosome) {
		
		if(chromosome == null) {
			throw new NullPointerException("A null object cannot be added to the population.");			
		}
		
		/*If the equal chromosome already exists in the population
		 * do not add
		 */		
		if(!alreadyExists(chromosome)) {
			
			population.add(chromosome);
		
			currentSize++;
			
			assert currentSize == population.size(): "The size of population is"
				+ "inconsistent!";
			
			return true;
				
		} else {
			return false;
		}
		
	}
	
	/**
	 * Checks if the given chromosome is already in the population
	 * @param chromosome is the chromosome the presence of 
	 * 					 which has to be checked
	 * @return true, if the equal chromosome is already 
	 * 					in the population
	 * 		   false, otherwise.
	 */
	private boolean alreadyExists(Chromosome chromosome) {
		
		for(Chromosome chrom: population) {
			if(chrom.equals(chromosome)) {
				return true;
			}
		}
		return false;
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
			
		currentSize--;
		
		assert currentSize == population.size(): "The size of population is"
				+ "inconsistent!";
	}
	
	/**
	 * 
	 * @param index is the position in the population
	 * @return a chromosome which stands at the specified position in the population
	 */
	public Chromosome getChromosomeAtIndex(int index){
		if (index < 0 || index >= currentSize) {
			throw new IllegalArgumentException("The index is invalid: " + index);
		}
		if (population == null || population.size() == 0) {
			throw new NullPointerException("Population is empty.");
		}
		return population.get(index);
	}
	
	/**
	 * Checks if the specified chromosome is in the population.
	 * @param chromosome is the given chromosome
	 * @return true, if the population already contains the specified chromosome
	 * 		   false, otherwise
	 */
	public boolean contains(Chromosome chromosome) {
		
		for(Chromosome nextChr: population) {
			if(nextChr.equals(chromosome)) {
				return true;
			}
		}
		return false;
	}
	
	
	/* Getter- and setter methods */
	public int getCurrentSize() {
		return this.currentSize;
	}
	
	public int getTargetSize() {
		return this.targetSize;
	}
	
	public List<Chromosome> getPop() {
		return this.population;
	}
	
	
	/*Print*/
	/**
	 * Prints the fitness values of the chromosomes in the population
	 */
	public void print() {
		for(Chromosome chr: population) {
			System.out.print("" + chr.getFitness() + ", ");
		}
	}
}
