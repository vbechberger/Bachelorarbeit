package genetic;

import java.util.Objects;
import java.util.ArrayList;
import java.util.Collections;

import util.SaveCopy;

public class Chromosome implements Comparable<Chromosome>{

	// parameters

	private final double fitness;
	private int[] genes;
	private FitnessFunction fitnessFct = null;
	
	/**
	 * Makes a new chromosome object, according to the
	 * given dimension (which is defined by the given
	 * fitness function). There will be produced a random
	 * permutation of the genes as a default variant,
	 * and the fitness value of
	 * chromosomes will be calculated (defined by the given
	 * fitness function).
	 * @param fitnessFct a fitness function which defines the way
	 * 						how the fitness is to be calculated
	 */
	public Chromosome(FitnessFunction fitnessFct) {
		this(fitnessFct, null);
	}


	/** 
	 * Makes a chromosome object,
	 * where the genes are defined by the given sequence if integers
	 * The fitness value of
	 * chromosomes will be also calculated (defined by the given
	 * fitness function).
	 * 
	 * @param numbers
	 */
	public Chromosome(FitnessFunction fitnessFct, int[] tour) {
		setFitnessFct(fitnessFct);
		setGenes(tour);
		this.fitness = fitnessFct.calcFitness(genes);

	}


	public double getFitness() {
		return fitness;
	}

	public int[] getGenes() {
		return genes;
	}
	
	public FitnessFunction getFitnessFct() {
		return fitnessFct;
	}
	
	private void setFitnessFct(FitnessFunction fitnessFct) {
		if(fitnessFct == null) {
			throw new NullPointerException("Fitness function is not defined!");	
		}
		this.fitnessFct = fitnessFct;
	}

	
	private void setGenes() {
		genes = new int[fitnessFct.getDimention()];
		
		ArrayList<Integer> tempTour = new ArrayList<Integer>();

		//random permutation of the cities
		//according to the dimension
		for (int i = 0; i < this.fitnessFct.getDimention(); i++) {
			tempTour.add(Integer.valueOf(i));			
		}

		Collections.shuffle(tempTour);
		SaveCopy.copy(genes, tempTour);
	}
	
	
	private void setGenes(int[] tour) {
		
		if(tour == null) {
			setGenes();
			return;
		}
		
		if (tour.length != fitnessFct.getDimention()) {
			throw new IllegalStateException("Dimension size is inconsistent.");			
		}
		genes = new int[tour.length];
		SaveCopy.copy(genes, tour);
	}
	
	
	/**
	 * Compares two chromosomes according to their fitness value.
	 * The chromosome with the greater fitness value 
	 * is fitter than the other one with the smaller fitness value.
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
	
	@Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Chromosome chromosome = (Chromosome) obj;
        
        return chromosome.fitness == this.fitness;
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(fitness);
    }

}
