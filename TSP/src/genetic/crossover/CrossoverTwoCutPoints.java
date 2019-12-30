package genetic.crossover;

import genetic.Chromosome;
import genetic.FitnessFunction;

public abstract class CrossoverTwoCutPoints extends CrossoverCutPoint {

	protected int cutPoint2 = -1;

	public CrossoverTwoCutPoints(FitnessFunction fitnessFct, 
								 Chromosome firstParent, 
								 Chromosome secondParent, 
								 int cutPoint, 
								 int cutPoint2) {
		
		super(fitnessFct, firstParent, secondParent, cutPoint);
		setSecondCutPoint(cutPoint2);
	}

	private void setSecondCutPoint(int cutPoint2) {
		if (cutPoint2 < 0 || cutPoint >= lengthOfChromosome) {
			throw new IllegalStateException("The second cut point is not feasible!");
		}
		this.cutPoint2 = cutPoint2;
	}

}
