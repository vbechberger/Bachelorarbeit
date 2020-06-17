package launches;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import genetic.Chromosome;
import genetic.FitnessFunction;
import genetic.Population;
import genetic.crossover.Crossover;
import genetic.crossover.CrossoverType;
import genetic.mutation.Mutation;
import genetic.mutation.MutationType;
import genetic.selection.Selection;
import genetic.selection.SelectionType;
import genetic.selection.TournamentSelection;

/**
 * Launches the genetic algorithm.
 * 
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public class GASimulator {
	
	private Random random;
	
	/**
	 * The fitness function
	 */
	private FitnessFunction fitnessFct;
	
	/**
	 * The population
	 */
	private Population pop;
	
	/**
	 * The type of crossover operator
	 */
	private CrossoverType crossoverType;
	
	/**
	 * The type of mutation type
	 */
	private MutationType mutationType;
	
	/**
	 * The type of selection type
	 */
	private SelectionType selectionType;
	
	/**
	 * Mutation rate
	 */
	private double mutationRate = 0;
	
	/**
	 * The counter for the number of made generations
	 */
	private int numbOfMadeGenerations = 0;
	
	/**
	 * The number of participants in the tournament selection
	 */
	private int numberOfParticipantsInSelection = -1;
	
	
	/**
	 * Constructor.
	 * 
	 * @param random
	 * @param fitnessFct is the given fitness function.
	 * @param pop is the given population.
	 * @param crossoverType is the type of crossover.
	 * @param mutationType is the mutation type.
	 * @param selectionType is the type of selection.
	 * @param mutationRate is the mutation rate.
	 * @param participants is the number of participants in the tournament selection.
	 */
	public GASimulator(Random random,
			FitnessFunction fitnessFct,
			Population pop, 
			CrossoverType crossoverType,
			MutationType mutationType,
			SelectionType selectionType,
			double mutationRate,
			int participants) {
		
		this.random = random;
		this.fitnessFct = fitnessFct;
		this.pop = pop;
		this.crossoverType = crossoverType;
		this.mutationType = mutationType;
		this.selectionType = selectionType;
		this.mutationRate = mutationRate;
		this.numberOfParticipantsInSelection = participants;
		
	}
		
	/**
	 * Updates the population with a new generation.
	 */
	private void makeNewGeneration() {
		
		Selection selection = selectionType.getSelection(random, pop);
		
		setParticipantsNumberAtFixedNumberIfProvided(selection);

		Chromosome mom = selection.getWinner();
		/*parent chromosomes have to be different*/
		Chromosome dad;
		do {
			dad = selection.getWinner();			
		} while(dad.equals(mom));
		
		/*Define the parent with the worst fitness value*/
		Chromosome worstParent = (mom.getFitness() < dad.getFitness()) ?  mom :  dad;
		
		/* Make a crossover between the chosen chromosomes*/
		Crossover crossover = crossoverType.getCrossover(fitnessFct, mom, dad, random); 
		Chromosome offspring = crossover.getOffspring();
	
		
		/* Make a mutation to the created offspring*/
		int rangeMin = 0;
		int rangeMax = 100;

		double randomValue = rangeMin + (rangeMax - rangeMin) * random.nextDouble();
		
		Mutation mutation = null;
		if (randomValue <= mutationRate * 100) {
			
			mutation = mutationType.getMutation(fitnessFct, offspring, random);
		}
		/* Put the kid (offspring if it does not mutate, or mutant) into the population*/

		if (mutation == null) {

			if(pop.addChromosome(offspring)) {
				/*After adding a chromosome, 
				 * the worst parent is deleted from the population
				 */
				pop.removeChromosome(worstParent);
			}

		} else {
			if (pop.addChromosome(mutation.getMutant())) {
				/*After adding a chromosome, 
				 * the worst parent is deleted from the population
				 */
				pop.removeChromosome(worstParent);
			}
		}


		/*Increment the number of produced generations*/
		numbOfMadeGenerations++;

	}

	/**
	 * Set the number of participants in the tournament selection if provided.
	 * @param selection is the selection type used.
	 */
	void setParticipantsNumberAtFixedNumberIfProvided(Selection selection) {
		if(numberOfParticipantsInSelection >= 0) {
			try {
				((TournamentSelection)selection).makeParticipantsNumberFixed(numberOfParticipantsInSelection);
			} catch (IllegalArgumentException e) {
				System.err.println("The number of participants"
						+ "in selection could not be set!");
				System.exit(0);
			}
		}
	}	
	
	
	/**
	 * Makes the genetic algorithm loop until the time ran out.
	 * @param timeLimit is the given time limit as a stop criterion.
	 */
	void makeNewGenerationsLoop(int timeLimit) {
		long startTimestamp = System.currentTimeMillis();
		
		LocalDateTime dateTime = LocalDateTime.now(); 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		System.out.println(dateTime.format(formatter));
		
		long newCurrentTimeStamp = System.currentTimeMillis();
		
		/*until the stop criterium is reached:
		 * (10 min)
		 * continue to produce offsprings(and mutants)
		 * and put them into the population
		 * */		
		while(newCurrentTimeStamp < startTimestamp + timeLimit) {
			
			//Make new generation of the current population 
			makeNewGeneration();
			
			newCurrentTimeStamp = System.currentTimeMillis();
			
		}	
		LocalDateTime dateTime2 = LocalDateTime.now(); 
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		System.out.println(dateTime2.format(formatter2));
				
	}
	
	/**
	 * Looks for the fittest chromosome in the population.
	 * 
	 * @param pop is the given population.
	 * @return the fittest chromosome in the population.
	 */
	Chromosome getBestChromosome(Population pop) {
		
		if (pop == null) {
			throw new NullPointerException("Population is empty.");
		}
		if (pop.getCurrentSize() == 0) {
			throw new NullPointerException("Population is empty.");
		}
		Chromosome best = pop.getChromosomeAtIndex(0);
		double bestFitness = best.getFitness();
		for(Chromosome next: pop.getPop()) {
			if(next.getFitness() > bestFitness) {
				bestFitness = next.getFitness();
				best = next;
			}
		}
		return best;
	}

	/**
	 * 
	 * @return the number of made generations.
	 */
	public int getNumbOfMadeGenerations() {
		return numbOfMadeGenerations;
	}
	
	
}
