package genetic.crossover;
import java.util.Random;

import genetic.AdjTour;
import genetic.Chromosome;
import genetic.FitnessFunction;

public abstract class AdjReprCrossover extends Crossover{

	protected AdjTour parent1;
	protected AdjTour parent2;
	
	protected int indexOfFirstEdge = -1;

	protected Random rand = new Random();
	
	public AdjReprCrossover(FitnessFunction fitnessFct, 
			 Chromosome firstParent, 
			 Chromosome secondParent) {
		super(fitnessFct, firstParent, secondParent);
		parent1 = firstParent.getGenesAsPathTour().transformIntoAdj();
		parent2 = secondParent.getGenesAsPathTour().transformIntoAdj();
	}
	
	protected void setFirstCity(int indexOfFirstEdge) {
		if(indexOfFirstEdge < 0 || indexOfFirstEdge >= lengthOfChromosome) {
			throw new IllegalArgumentException("the given city: " +
					indexOfFirstEdge + " does not exist in the tour");
		}
		this.indexOfFirstEdge = indexOfFirstEdge;	
	}
	
	/**
	 * Sets seed for random for making tests.
	 * @param seed
	 */
	public void setRandom(int seed) {
		rand = new Random(seed);
	}	


}
