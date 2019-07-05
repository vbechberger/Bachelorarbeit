package genetic.crossover;

import genetic.Chromosome;
import util.SaveCopy;

public abstract class Crossover {

	protected Chromosome kid1;
	protected Chromosome kid2;
	protected int[] parent1;
	protected int[] parent2;

	protected final int arrLength;

	public Crossover(Chromosome firstParent, Chromosome secondParent) {

		arrLength = firstParent.getGenes().length;
		parent1 = new int[arrLength];
		parent2 = new int[arrLength];
		SaveCopy.copy(parent1, firstParent.getGenes());
		SaveCopy.copy(parent2, secondParent.getGenes());
	}

	public abstract void start();

	/* Getter methods */
	public Chromosome getKid1() {
		if (kid1 == null) {
			throw new RuntimeException("Crossover was not started. There exists no kids!");
		}
		return kid1;
	}

	public Chromosome getKid2() {
		if (kid2 == null) {
			throw new RuntimeException("Crossover was not started. There exists no kids!");
		}
		return kid2;
	}

}
