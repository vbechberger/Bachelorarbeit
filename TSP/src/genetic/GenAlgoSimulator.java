package genetic;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;

import genetic.crossover.Crossover;
import genetic.crossover.CrossoverCycleX;
import genetic.crossover.CrossoverModifiedX;
import genetic.crossover.CrossoverOBX;
import genetic.crossover.CrossoverOX;
import genetic.crossover.CrossoverPBX;
import genetic.crossover.CrossoverPMX;
import genetic.crossover.CrossoverType;
import genetic.mutation.Mutation;
import genetic.mutation.MutationScramble;
import genetic.mutation.MutationShift;
import genetic.mutation.MutationSwap;
import genetic.mutation.MutationType;

public class GenAlgoSimulator {
	
	private final int chromLength;
	private Random random = new Random(42);
	private final int maxIndexValue;
	private final int minIndexValue = 0;
	
	//general parameters:
	private int popStartSize = -1;
	private final int popMaxSize;
	
	private final int maxGenerationsNumber;
	
	/** The probability that the offspring mutates, 
	 * has a value between 0 and 1*/
	private double mutationRate = -1;
	
	
	
	private CrossoverType crossoverType;
	private MutationType mutationType;
	//private Selection selection;
	private FitnessFunction fitnessFct;
	private Representation representation;
	private Crossover crossover;
	private Mutation mutation;
	
	

	public GenAlgoSimulator(int popStartSize,
							int popMaxSize,
							
							int maxGenerationsNumber,
							int chromLength,
							CrossoverType crossoverType,
							MutationType mutationType,
							//Selection selection,
							FitnessFunction fitnessFct,
							Representation representation) {
		this.popStartSize = popStartSize;
		this.popMaxSize = popMaxSize;
		
		this.maxGenerationsNumber = maxGenerationsNumber;
		this.chromLength = chromLength;
		this.maxIndexValue = this.chromLength - 1;
		this.crossoverType = crossoverType;
		this.mutationType = mutationType;
		//this.selection = selection;
		this.fitnessFct = fitnessFct;
		this.representation = representation;
		
				
	}
	
	
	public void startSimulation() {
		
		/* 1) Make a population according to the specified size */
		Population population = new Population(popStartSize, this.fitnessFct);
		int generationsNumber = 0;
		
		
		/**until the stop criteria are reached:
		 * the maximum population size or
		 * the maximum number of generations
		 * continue to produce offsprings(and mutants)
		 * and put them into the population*/
		while(population.size <= popMaxSize && generationsNumber <= maxGenerationsNumber) {
		
			/* 2) Choose two parent chromosomes: 
			 * the two fittest chromosomes in the population */	
			Chromosome mom = population.getBestFitChromosome();
			Chromosome dad = population.getSecondFitChromosome();
		
			/* 3) Make a crossover between the chosen chromosomes*/
			setCrossover(crossoverType, fitnessFct, mom, dad);
			crossover.start();
		
			/* 4) Make a mutation to the created offspring*/
			int rangeMin = 0;
			int rangeMax = 100;
			double randomValue = rangeMin + (rangeMax - rangeMin) * random.nextDouble();
		
			if (randomValue <= mutationRate * 100) {
				setMutation(mutationType, fitnessFct, crossover.getKid1());
				mutation.start();
			}
			/* 5) Put the kid into the population*/
			if (mutation == null) {
				population.addChromosome(crossover.getKid1());
			} else {
				population.addChromosome(mutation.getMutant());
			}
			generationsNumber++;
		
		}
		
		
	}
	
	private void setMutation(MutationType mutationType, FitnessFunction fitnessFct, Chromosome kid) {
		switch (mutationType) {
        case SWAP:
        	
        	int index1 = random.nextInt(maxIndexValue - minIndexValue + 1);
        	int index2 = new Random(41).nextInt(maxIndexValue - minIndexValue + 1);
        	mutation = new MutationSwap(fitnessFct, kid, index1, index2);
        	break;
        	
        case LOCALHILLCLIMBING:
        	break;
        case SCRAMBLE:
        	int indx1 = random.nextInt(maxIndexValue - minIndexValue + 1);
        	int indx2 = new Random(41).nextInt(maxIndexValue - minIndexValue + 1);
        	mutation = new MutationScramble(fitnessFct, kid, indx1, indx2);
        	
        	break;
        case SHIFT:
        	
        	int cityNumber = random.nextInt(maxIndexValue - minIndexValue + 1);
        	
        	/**define a random number of steps which will be 
        	 * chosen to replace the chosen city,
        	 * which is in the range between 1 and the number of cities in the tour
        	 * (=chromosome length) - 1,
        	 * because if 0 steps are chosen nothing changes. The same
        	 * as well if the number of steps = the length of the chromosome*/
        	int maxStepsNumber = chromLength - 1;
        	int minStepsNumber = 1;
        	int stepsNumber = random.nextInt(maxStepsNumber - minStepsNumber + 1);
        	
        	mutation = new MutationShift(fitnessFct, kid, cityNumber, stepsNumber);
        	
        	break;
        case INVERSION:
        	break;
        case INSERTION:
        	break;
        case DISPLACEMENT:
        	break;
        case ASSIGNED:
        	break;
        default:
            throw new IllegalArgumentException("The given mutation type does not exist: "
            		+ mutationType.name());
		}
	}
	
	private void setCrossover(CrossoverType crossoverType, FitnessFunction fitnessFct, 
								Chromosome firstChromosome, Chromosome secChromosome) {
		switch (crossoverType) {
        case MODIFIED: {
        	
        	//TODO:Make it random
        	/**Random cut point*/
        	
        	int cutPoint = random.nextInt(maxIndexValue - minIndexValue + 1);
        			
            crossover = new CrossoverModifiedX(fitnessFct, firstChromosome, secChromosome, cutPoint);
            break;
        }
        case ORDERBASED: {
            
        	Set<Integer> indices = new HashSet<Integer>();
        	
        	/**define a random number of indices of the cities which will be 
        	 * chosen. It is in the range between 1 and the number of cities in the tour
        	 * (=chromosome length) - 1,
        	 * cause if no cities or all the cities will be chosen, the 
        	 * offspring chromosome will be just like the first parent chromosome*/
        	
        	int citiesNumber = random.nextInt(chromLength - 1 + 1);
        	for (int i = 0; i < citiesNumber; i++) {
        		int nextIndex = random.nextInt(maxIndexValue - minIndexValue + 1);
        		indices.add(nextIndex);
        	}
        	
        	crossover = new CrossoverOBX(fitnessFct, firstChromosome, secChromosome, indices);
        	break;
        }
        case ORDER: {
        	
        	int cutPoint1 = random.nextInt(maxIndexValue - minIndexValue + 1);
        	int cutPoint2 = new Random(41).nextInt(maxIndexValue - minIndexValue + 1);
        	crossover = new CrossoverOX(fitnessFct, firstChromosome, secChromosome, cutPoint1, cutPoint2);
        	break;
        }	
        case POSITIONBASED: { 
        	
        	Set<Integer> indices = new HashSet<Integer>();
        	
        	/**define a random number of cities which will be 
        	 * chosen, between 1 and the number of cities in the tour
        	 * (=chromosome length) - 1,
        	 * cause if no cities or all the cities will be chosen, the 
        	 * offspring chromosome will be just like the first parent chromosome*/
        	
        	int citiesNumber = random.nextInt(chromLength - 1 + 1);
        	for (int i = 0; i < citiesNumber; i++) {
        		int nextIndex = random.nextInt(maxIndexValue - minIndexValue + 1);
        		indices.add(nextIndex);
        	}
        	
        	crossover = new CrossoverPBX(fitnessFct, firstChromosome, secChromosome, indices);
        	break;
        }	
        case PARTIALLYMAPPED: { 
        	
        	int cutPoint1 = random.nextInt(maxIndexValue - minIndexValue + 1);
        	int cutPoint2 = new Random(41).nextInt(maxIndexValue - minIndexValue + 1);
        	crossover = new CrossoverPMX(fitnessFct, firstChromosome, secChromosome, cutPoint1, cutPoint2);
        	break;
        }	
        case CYCLE: { 
        	
        	crossover = new CrossoverCycleX(fitnessFct, firstChromosome, secChromosome);
        	break;
        }
        default:
            throw new IllegalArgumentException("The given crossover type does not exist: "
            		+ crossoverType.name());
		}
	}
	
	

}
