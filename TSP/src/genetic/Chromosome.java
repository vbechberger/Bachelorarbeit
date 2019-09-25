package genetic;

import util.Printer;
import util.SaveCopy;

public class Chromosome implements Comparable<Chromosome>{

	// parameters

	private final double fitness;
	private int[] genes;

	public Chromosome(Instance instance) {
		genes = new int[instance.getTour().size()];
		SaveCopy.copy(genes, instance.getTour());
		if (instance.type == TSPType.EUCLIDEAN) {
			this.fitness = calculateFitnessEuclidean(instance.getDistances());
		} else {
			Printer.printString("other types of TSP are not implemented");
			this.fitness = -1;
		}
	

	}

	/** Is used only for tests!!!!!
	 * TODO: ELIMINATE THIS CONSTRUCTOR
	 * 
	 * @param numbers
	 */
	public Chromosome(int[] numbers) {
		genes = new int[numbers.length];
		SaveCopy.copy(genes, numbers);
		
		this.fitness = -1;
		
		//TODO:to introduce other types of TSP
		//calculateFitnessEuclidean();

	}

	private double calculateFitnessEuclidean(double[][] distances) {
		System.out.println("calculate Fitness is to implement!");
		
		double sum = 0;
		
		for (int i = 0; i < genes.length - 1; i++) {
			int start = genes[i];
			int end = genes[i + 1];
			sum = sum + distances[start][end];
		}
		
		int start = genes[genes.length - 1];
		int end = genes[0];
		sum = sum + distances[start][end];	
		
		if (sum <= 0) {
			throw new IllegalStateException("The distance of "
					+ "tour is smaller or equal than 0!");
		}
		
		return sum;
	}
	


	public double getFitness() {
		return fitness;
	}

	public int[] getGenes() {
		return genes;
	}

	
	
	/**
	 * Compares two chromosomes according to their fitness value.
	 * The chromosome with the greater fitness value 
	 * is greater than the other one with the smaller fitness value.
	 * 
	 * @param o the other chromosome, with 
	 * 			which we compare the actual chromosome
	 * 
	 * @param 1 if the actual chromosome has a greater fitness value than the other chromosome
	 * 		 -1 if it has a smaller fitness value
	 * 		  0 if both chromosomes have the equal fitness value	
	 */
	@Override
	public int compareTo(Chromosome o) {
		
		if (this.getFitness() > o.getFitness()) {
			return 1;			
		} else if (this.getFitness() < o.getFitness()) {
			return -1;
		} else {
			return 0;
		}		
	}

}
