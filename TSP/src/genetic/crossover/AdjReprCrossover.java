package genetic.crossover;

import genetic.Chromosome;
import genetic.FitnessFunction;

public abstract class AdjReprCrossover extends Crossover{

	
	public AdjReprCrossover(FitnessFunction fitnessFct, 
			 Chromosome firstParent, 
			 Chromosome secondParent) {
		super(fitnessFct, firstParent, secondParent);
	}
	
		


}
