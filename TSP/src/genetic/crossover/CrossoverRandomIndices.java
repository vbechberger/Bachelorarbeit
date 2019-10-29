package genetic.crossover;

import java.util.HashSet;
import java.util.Set;

import genetic.Chromosome;
import genetic.FitnessFunction;

public abstract class CrossoverRandomIndices extends CrossoverCycleSubset {
	
	protected Set<Integer> indices = new HashSet<Integer>();

	public CrossoverRandomIndices(FitnessFunction fitnessFct, Chromosome firstParent, Chromosome secondParent, Set<Integer> indices) {
		super(fitnessFct, firstParent, secondParent);
		this.indices = indices;//TODO:Save copy? actually i do not change the set

	}

	public void start() {

		if (indices == null || indices.isEmpty()) {
			throw new IllegalStateException("The indices are not chosen!");
		}

		kid1 = doCrossover(parent1, parent2);
		kid2 = doCrossover(parent2, parent1);

	}

	protected abstract Chromosome doCrossover(int[] parent1, int[] parent2);

}
