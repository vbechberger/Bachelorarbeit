package genetic.crossover;

import genetic.Chromosome;
import genetic.FitnessFunction;

public abstract class CrossoverCycleSubset extends Crossover {

	public CrossoverCycleSubset(FitnessFunction fitnessFct, 
								Chromosome firstParent, 
								Chromosome secondParent) {
		super(fitnessFct, firstParent, secondParent);
	}

	
	public void start() {
		
		kid1 = doCrossover(parent1, parent2);
		kid2 = doCrossover(parent2, parent1);

	}
	
	protected abstract Chromosome doCrossover(int[] parent1, int[] parent2);

}
