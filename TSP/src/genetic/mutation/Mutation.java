package genetic.mutation;

import genetic.Chromosome;
import util.SaveCopy;

public abstract class Mutation {
	
	protected Chromosome mutant;
	protected int[] kidGenes;	
	protected final int arrLength;
	
	public Mutation(Chromosome kid) {
		arrLength = kid.getGenes().length;
		kidGenes = new int[arrLength];
		SaveCopy.copy(kidGenes, kid.getGenes());				
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
