package genetic;

import java.util.ArrayList;
import java.util.List;

public class Population {
	
	List<Chromosome> population = new ArrayList<Chromosome>();

	// general parameters

	// size
	final int MIN_SIZE = 5;
	int size;

	public Population(int size, FitnessFunction fitnessFct) {
		setSize(size);
		init(fitnessFct);
	}

	/**
	 * Initializes the population, creating the generation #0
	 */
	public void init(FitnessFunction fitnessFct) {
		
		int step = 0;
		while (step < this.size) {
			Chromosome chromosome = new Chromosome(fitnessFct);
			population.add(chromosome);
		}		
	}
	
	
	/* Getter- and setter methods */
	public int getSize() {
		return this.size;
	}

	void setSize(int size) {
		if (size < MIN_SIZE) {
			throw new IllegalArgumentException(
					"The size of the population " + "has to be greater than " + MIN_SIZE + " !");
		}
		this.size = size;
	}

}
