package genetic.crossover;

import genetic.Chromosome;
import genetic.FitnessFunction;

public abstract class CrossoverCutPoint extends PathReprCrossover {
	protected int cutPoint = -1;

	public CrossoverCutPoint(FitnessFunction fitnessFct, 
							Chromosome firstParent, 
							Chromosome secondParent, 
							int cutPoint) {
		super(fitnessFct, firstParent, secondParent);
		setCutPoint(cutPoint);
	}

	
	private void setCutPoint(int cutPoint) {
		if (cutPoint < 0 || cutPoint >= lengthOfChromosome) {
			throw new IllegalStateException("The cut point is not feasible!");
		}		
		this.cutPoint = cutPoint;
	}

}
