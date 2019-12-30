package genetic.mutation;

import genetic.Chromosome;
import genetic.FitnessFunction;
import util.SaveCopy;

public abstract class Mutation {
	
	protected FitnessFunction fitnessFct;
	protected Chromosome mutant;
	protected int[] kidGenes;	
	protected final int arrLength;
	
	public Mutation(FitnessFunction fitnessFct, Chromosome kid) {
		//TODO:perhaps via setFct
		this.fitnessFct = fitnessFct;
		arrLength = kid.getGenesInPath().length;
		kidGenes = new int[arrLength];
		SaveCopy.copy(kidGenes, kid.getGenesInPath());				
	}
	
	public void start() {
		
		if (kidGenes.length == 0) {
			throw new IllegalStateException("The array of genes of the kid's chromosome is empty!");
		}

		mutant = doMutation(kidGenes);
		
	}
	
	protected abstract Chromosome doMutation(int[] kidGenes);

	public Chromosome getMutant() {
		return mutant;
	}	
}
