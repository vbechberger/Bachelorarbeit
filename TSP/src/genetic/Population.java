package genetic;

import java.util.ArrayList;
import java.util.List;

public class Population {
	
	List<Chromosome> population = new ArrayList<Chromosome>();

	// general parameters

	// size
	final int MIN_SIZE = 5;
	int size;

	public Population(int size) {
		setSize(size);
	}

	/**
	 * Initializes the population, creating the generation #0
	 */
	public void init() {
		
		Chromosome one = new Chromosome(new int[]{1,2,3,4,5,6,7,8});
		population.add(one);
		
		Chromosome two = new Chromosome(new int[]{8,7,6,5,4,3,2,1});
		population.add(two);
		
		Chromosome three = new Chromosome(new int[]{5,4,3,2,1,8,7,6});
		population.add(three);
		
		Chromosome four = new Chromosome(new int[]{5,6,7,8,1,2,3,4});
		population.add(four);
		
		
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
