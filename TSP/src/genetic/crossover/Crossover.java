package genetic.crossover;

import genetic.Chromosome;
import genetic.FitnessFunction;

public abstract class Crossover {
	
	protected final int lengthOfChromosome;
	
	protected FitnessFunction fitnessFct;
	protected Chromosome kid;
	
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
	
	protected abstract Chromosome doCrossover();

	/* Getter methods */
	public Chromosome getKid() {
		
		kid = doCrossover();
		
		if (kid == null) {
			throw new RuntimeException("Crossover was not successful. There exists no kid!");
		}
		return kid;
	}

}
