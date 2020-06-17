package genetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import tsp.Solution;
import tsp.representation.PathTour;
import util.Printer;
import util.SafeCopy;

/**
 * Represents a chromosome which encodes a TSP tour.
 * Chromosome gets always the tour in path representation.
 * 
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11 
 *
 */
public class Chromosome implements Comparable<Chromosome>{

	/**Fitness value of the chromosome*/
	private double fitness = -1;;
	
	/**The genes of the chromosomes which represent a feasible solution to the TSP*/
	private Solution genes = null;
	
	/**Fitness function to calculate the fitness value*/
	private FitnessFunction fitnessFct = null;
	
	/**
	 * Constructor.
	 * It makes a new chromosome object, according to the
	 * given dimension (which is defined by the given
	 * fitness function). There will be produced a random
	 * permutation of the genes as a default variant,
	 * and the fitness value of
	 * chromosomes will be calculated (defined by the given
	 * fitness function).
	 * @param fitnessFct a fitness function which defines the way
	 * 						how the fitness is to be calculated
	 * @param random is the instance of the type Random
	 */
	public Chromosome(FitnessFunction fitnessFct, Random random) {
		setFitnessFct(fitnessFct);
		setGenes(random);
		this.fitness = fitnessFct.calcFitness(genes.getTour().getTourAsArr());
	}


	/** 
	 * Constructor.
	 * It makes a chromosome object,
	 * where the genes correspond the given feasible TSP solution (tour)
	 * The fitness value of
	 * chromosomes will be also calculated (defined by the given
	 * fitness function)
	 * 
	 * @param fitnessFctis the given fitness function which 
	 * 						is necessary to calculate the fitness
	 * 					    value of the new chromosome.
	 * 
	 * @param tspSolution is a feasible TSP solution (which encodes a feasible tour)
	 */
	public Chromosome(FitnessFunction fitnessFct, Solution tspSolution) {
		setFitnessFct(fitnessFct);
		setGenes(tspSolution);
		this.fitness = fitnessFct.calcFitness(tspSolution.getTour().getTourAsArr());

	}
		
	/**
	 * Compares two chromosomes according to their fitness values.
	 * The chromosome with the greater fitness value 
	 * is fitter than the other one with the smaller fitness value.
	 * 
	 * @param o the other chromosome,  
	 * 			which we compare with the actual chromosome
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
        
        return this.genes.equals(chromosome.genes);
    }
	
	@Override
    public int hashCode() {
        return genes.hashCode();
    }
	
	/*Setter methods*/
	
	/**
	 * Sets the fitness function to the given one.
	 * @param fitnessFct is the given fitness function.
	 */
	private void setFitnessFct(FitnessFunction fitnessFct) {
		if(fitnessFct == null) {
			throw new NullPointerException("Fitness function is not defined!");	
		}
		this.fitnessFct = fitnessFct;
	}

	/**
	 * Creates a random permutation of cities for the genes.
	 * @param random is the Random object
	 */
	private void setGenes(Random random) {
		
		int[] genesInArray = new int[fitnessFct.getDimention()];
		
		ArrayList<Integer> tempTour = new ArrayList<Integer>();

		/*random permutation of the cities
		 *according to the dimension
		 **/
		for (int i = 0; i < this.fitnessFct.getDimention(); i++) {
			tempTour.add(Integer.valueOf(i));			
		}

		Collections.shuffle(tempTour, random);
		SafeCopy.copy(genesInArray, tempTour);
		
		genes = new Solution(this.fitnessFct.getDimention(), genesInArray);
	}
	
	/**
	 * Creates the genes according to the given solution tour.
	 * @param solutionTour is a feasible TSP solution tour.
	 */
	private void setGenes(Solution solutionTour) {
		
		if(solutionTour == null) {
			throw new IllegalStateException("The solution tour is empty.");
		}
		
		if (solutionTour.getDimension() != fitnessFct.getDimention()) {
			throw new IllegalStateException("Dimension size is inconsistent.");			
		}
		genes = solutionTour;
	}
	
	/*Getter methods*/
	
	public double getFitness() {
		return fitness;
	}
	
	public FitnessFunction getFitnessFct() {
		return fitnessFct;
	}
	
	/**
	 * @return  the length of the genes.
	 */
	public int getSize() {
		return this.genes.getDimension();
	}
	
	/**
	 * Decodes the genes onto a TSP solution.
	 * @return a TSP solution
	 */
	public Solution decodeIntoSolution() {
		return this.genes;
	}
	
	/**
	 * @return the genes in the form of the array in the path representation
	 */
	public int[] getGenesAsArray() {
		return this.genes.getTour().getInPathAsArr();
	}
	
	/**
	 * @return the genes in the form of the PathTour
	 */
	public PathTour getGenesAsPathTour() {
		return this.genes.getTour();
	}
	
	
	/*Print*/
	
	/**
	 * Prints the chromosome
	 */
	public void print() {
		try {
			Printer.printArray(this.getGenesAsArray());
		} catch (NullPointerException e) {
			e.printStackTrace();
		}		
	}

}
