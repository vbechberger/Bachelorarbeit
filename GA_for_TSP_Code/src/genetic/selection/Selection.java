package genetic.selection;

import java.util.Random;

import genetic.Chromosome;
import genetic.Population;

/**
 * An abstract class which represent selection operators.
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public abstract class Selection {
	
	/**
	 * Population.
	 */
	protected Population pop;
	/**
	 * Random object to control the results via setting seeds.
	 */
	protected Random rand;
	
	/**
	 * Constructor.
	 * @param rand is the random object.
	 * @param pop is the population.
	 */
	public Selection(Random rand, Population pop) {
		this.rand = rand;
		setPopulation(pop);
		
	}
	
	/**
	 * 
	 * @return the winner chromosome.
	 */
	public abstract Chromosome getWinner();
	
	/**
	 * Sets the population.
	 * @param pop is the given population.
	 */
	public void setPopulation(Population pop) {
		this.pop = pop;
	}

}
