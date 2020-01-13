package genetic.crossover;

import genetic.Chromosome;
import genetic.FitnessFunction;
import genetic.PathTour;

public abstract class PathReprCrossover extends Crossover{
	
	protected PathTour parent1;
	protected PathTour parent2;

	public PathReprCrossover(FitnessFunction fitnessFct, 
					 Chromosome firstParent, 
					 Chromosome secondParent) {
		super(fitnessFct, firstParent, secondParent);
		parent1 = firstParent.getGenesAsPathTour();
		parent2 = secondParent.getGenesAsPathTour();
	}

}
