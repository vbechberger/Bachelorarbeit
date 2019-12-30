package genetic.crossover;

import java.util.HashSet;
import java.util.Set;

import genetic.Chromosome;
import genetic.FitnessFunction;

public abstract class CrossoverRandomIndices extends CrossoverCycleSubset {
	
	protected Set<Integer> indices = new HashSet<Integer>();

	public CrossoverRandomIndices(FitnessFunction fitnessFct, Chromosome firstParent, Chromosome secondParent, Set<Integer> indices) {
		super(fitnessFct, firstParent, secondParent);
		setIndices(indices);
	}
	

	private void setIndices(Set<Integer> indices) {
		if (indices == null || indices.isEmpty()) {
			throw new IllegalStateException("The indices are not chosen!");
		}
		
		this.indices = indices;//TODO:Save copy? actually i do not change the set
	}

}
