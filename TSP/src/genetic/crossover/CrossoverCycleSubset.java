package genetic.crossover;

import genetic.Chromosome;
import genetic.FitnessFunction;

public abstract class CrossoverCycleSubset extends PathReprCrossover {

	public CrossoverCycleSubset(FitnessFunction fitnessFct, 
								Chromosome firstParent, 
								Chromosome secondParent) {
		super(fitnessFct, firstParent, secondParent);
	}

}
