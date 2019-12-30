package genetic;

import java.util.Objects;
import java.util.ArrayList;
import java.util.Collections;

import util.Printer;
import util.SaveCopy;

/**
 * 
 * @author valeriyabechberger
 *
 */
public class Chromosome implements Comparable<Chromosome>{

	// parameters

	private final double fitness;
	private Solution genes;
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
	 * fitness function)
	 * Chromosome gets always the tour in path representation.
	 * 
	 * @param numbers
	 */
	public Chromosome(FitnessFunction fitnessFct, Solution tspSolution) {
		setFitnessFct(fitnessFct);
		setGenes(tspSolution);
		this.fitness = fitnessFct.calcFitness(tspSolution.getTour().tour);

	}


	public double getFitness() {
		return fitness;
	}
	
	public FitnessFunction getFitnessFct() {
		return fitnessFct;
	}
	
	public int getSize() {
		return this.genes.getDimension();
	}
	
	public Solution decodeIntoSolution() {
		return this.genes;
	}
	
	public int[] getGenesInPath() {
		return this.genes.getTour().getInPathAsArr();
	}
	
	
	
	
	private void setFitnessFct(FitnessFunction fitnessFct) {
		if(fitnessFct == null) {
			throw new NullPointerException("Fitness function is not defined!");	
		}
		this.fitnessFct = fitnessFct;
	}

	
	private void setGenes() {
		
		int[] genesInArray = new int[fitnessFct.getDimention()];
		
		ArrayList<Integer> tempTour = new ArrayList<Integer>();

		//random permutation of the cities
		//according to the dimension
		for (int i = 0; i < this.fitnessFct.getDimention(); i++) {
			tempTour.add(Integer.valueOf(i));			
		}

		Collections.shuffle(tempTour);
		SaveCopy.copy(genesInArray, tempTour);
		
		genes = new Solution(this.fitnessFct.getDimention(), genesInArray);
	}
	
	
	private void setGenes(Solution solutionTour) {
		
		if(solutionTour == null) {
			setGenes();
			return;
		}
		
		if (solutionTour.getDimension() != fitnessFct.getDimention()) {
			throw new IllegalStateException("Dimension size is inconsistent.");			
		}
		genes = solutionTour;
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
	
	public void print() {
		Printer.printArray(this.getGenesInPath());
	}

}
