package genetic.crossover;

import genetic.Chromosome;
import genetic.FitnessFunction;

public abstract class Crossover {
	
	protected final int lengthOfChromosome;
	
	protected FitnessFunction fitnessFct;
	protected Chromosome kid1;
	protected Chromosome kid2;
	
	protected Chromosome parent1;
	protected Chromosome parent2;

	public Crossover(FitnessFunction fitnessFct, 
					 Chromosome firstParent, 
					 Chromosome secondParent) {
		
		this.fitnessFct = fitnessFct;		
		this.lengthOfChromosome = firstParent.getSize();				
		setParents(firstParent, secondParent);

	}
	
	private void setParents(Chromosome firstParent, Chromosome secondParent) {
		parent1 = firstParent;
		parent2 = secondParent;
	}
	
	protected abstract Chromosome doCrossover(Chromosome parent1, Chromosome parent2);

	/* Getter methods */
	public Chromosome getKid1() {
		
		kid1 = doCrossover(parent1, parent2);
		
		if (kid1 == null) {
			throw new RuntimeException("Crossover was not successful. There exists no kid!");
		}
		return kid1;
	}

	public Chromosome getKid2() {
		
		kid2 = doCrossover(parent2, parent1);
		
		if (kid2 == null) {
			throw new RuntimeException("Crossover was not successful. There exists no kid!");
		}
	
		return kid2;
	}

}
