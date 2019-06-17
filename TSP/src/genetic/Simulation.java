package genetic;

import java.util.ArrayList;

import genetic.crossover.Crossover;
import genetic.crossover.CrossoverCycleX;
import genetic.crossover.CrossoverModifiedX;
import genetic.crossover.CrossoverOBX;
import genetic.crossover.CrossoverOX;
import genetic.crossover.CrossoverPBX;
import genetic.crossover.CrossoverPMX;
import genetic.crossover.CrossoverType;
import genetic.mutation.Mutation;
import genetic.mutation.MutationSwap;
import genetic.mutation.MutationType;
import util.SaveCopy;

public class Simulation {
	private Crossover crossover;
	private CrossoverType type;
	private Mutation mutation;
	private MutationType mutationType;
	private Chromosome kid1;
	private Chromosome kid2;
	private Chromosome parent1;
	private Chromosome parent2;
	private Chromosome mutant;
	private Chromosome kid;
	private ArrayList<Integer> indices = new ArrayList<Integer>();
	private int startIndex = -1;
	private int endIndex = -1;
	private int index1 = -1;
	private int index2 = -1;

	
	public Simulation(CrossoverType type, Chromosome parent1, Chromosome parent2, 
						ArrayList<Integer> indices,
						int startIndex, int endIndex,
						MutationType mutationType, Chromosome kid,
						int index1, int index2) {
		this.parent1 = parent1;
		this.parent2 = parent2;
		this.type = type;
		SaveCopy.copy(this.indices, indices);
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		
		this.kid = kid;
		this.mutationType = mutationType;
		this.index1 = index1;
		this.index2 = index2;

	}
	
	public Simulation(CrossoverType type, Chromosome parent1, Chromosome parent2, 
						ArrayList<Integer> indices,
						int startIndex, int endIndex) {
		this(type, parent1, parent2, indices, startIndex, endIndex, 
				MutationType.UNDEFINED, null,  -1, -1);

	}

	public Simulation(CrossoverType type, Chromosome parent1, Chromosome parent2, ArrayList<Integer> indices) {
		this(type, parent1, parent2, indices, -1, -1, 
				MutationType.UNDEFINED, null,  -1, -1);
	}

	public Simulation(CrossoverType type, Chromosome parent1, Chromosome parent2, int startIndex, int endIndex) {
		this(type, parent1, parent2, null, startIndex, endIndex, 
				MutationType.UNDEFINED, null,  -1, -1);
	}

	public Simulation(CrossoverType type, Chromosome parent1, Chromosome parent2, int startIndex) {
		this(type, parent1, parent2, null, startIndex, -1, 
				MutationType.UNDEFINED, null,  -1, -1);
	}
	public Simulation(CrossoverType type, Chromosome parent1, Chromosome parent2) {
		this(type, parent1, parent2, null, -1, -1, 
				MutationType.UNDEFINED, null,  -1, -1);
	}
	
	public Simulation(MutationType mutationType, Chromosome kid, int index1, int index2) {
		this(CrossoverType.UNDEFINED, null, null, null, -1, -1,
				mutationType, kid, index1, index2);

	}
	
	

	public void start() {

		if (type == CrossoverType.UNDEFINED && mutationType != MutationType.UNDEFINED) {
			startMutation();
		} else if (type != CrossoverType.UNDEFINED && mutationType == MutationType.UNDEFINED) {
			startCrossover();
		} else {
			//TODO:other variants??
		}
	}
	
	private void startCrossover() {
			
		if (type == CrossoverType.CYCLE) {
			crossover = new CrossoverCycleX(parent1, parent2);
		} else if (type == CrossoverType.MODIFIED) {
			crossover = new CrossoverModifiedX(parent1, parent2, startIndex);
		} else if (type == CrossoverType.ORDER) {
			crossover = new CrossoverOX(parent1, parent2, startIndex, endIndex);
		} else if (type == CrossoverType.ORDERBASED) {
			crossover = new CrossoverOBX(parent1, parent2, indices);
		} else if (type == CrossoverType.POSITIONBASED) {
			crossover = new CrossoverPBX(parent1, parent2, indices);
		} else if (type == CrossoverType.PARTIALLYMAPPED) {
			crossover = new CrossoverPMX(parent1, parent2, startIndex, endIndex);
		} else if (type == CrossoverType.UNDEFINED){
			// do nothing
		}
		// make children's chromosomes
		crossover.start();
		if (crossover.getKid1() == null || crossover.getKid2() == null) {
			throw new IllegalStateException("No kids were made");
		}
		kid1 = crossover.getKid1();
		kid2 = crossover.getKid2();
	}

	
	private void startMutation() {
		if (mutationType == MutationType.SWAP) {
			mutation = new MutationSwap(kid, index1, index2);
		} else {
			// do nothing
		}
		mutation.start();
		if (mutation.getMutant() == null) {
			throw new IllegalStateException("No mutant was made");
		}
		mutant = mutation.getMutant();		
	}
	
	

	public Chromosome getKid1() {
		return kid1;
	}


	public Chromosome getKid2() {
		return kid2;
	}

	public Chromosome getMutant() {
		return mutant;
	}

}
