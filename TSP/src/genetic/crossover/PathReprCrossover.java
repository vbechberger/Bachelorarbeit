package genetic.crossover;

import genetic.Chromosome;
import genetic.FitnessFunction;

public abstract class PathReprCrossover extends Crossover{

	public PathReprCrossover(FitnessFunction fitnessFct, 
					 Chromosome firstParent, 
					 Chromosome secondParent) {
		super(fitnessFct, firstParent, secondParent);
	}

}
