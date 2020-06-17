package genetic.selection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import genetic.Chromosome;
import genetic.Population;

/**
 * Represents the tournament selection operator:
 * Th number of participants is drawn randomly from the interval [2, 10].
 * The fittest chromosome is selected among them.
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public class TournamentSelection extends Selection{
	
	/**
	 * Number of participants in the tournament.
	 */
	private int numberOfParticipants = -1;
	
	/**
	 * The minimal number of participants
	 */
	private int MIN_NUMBER = 2;
	
	/**
	 * The maximal number of participants.
	 */
	private int MAX_NUMBER = 10;
	
	/**
	 * Constructor.
	 * @param rand is the Random object
	 * @param pop is the given population from which a chromowome will be selected.
	 */
	public TournamentSelection(Random rand, Population pop) {
		super(rand, pop);
	}
	
	
	/**
	 * Starts the tournament and gives the winner chromosome.
	 * @return the fittest chromosome among those who 
	 * 			participated in the tournament.
	 */
	public Chromosome getWinner() {
		
		numberOfParticipants = rand.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;	
		
		List<Chromosome> participants = new ArrayList<Chromosome>();
		
		int maxIndexExkl = pop.getCurrentSize();
		
		int count = 0;
		while(count < numberOfParticipants) {
			int nextIndex = rand.nextInt(maxIndexExkl);	
			
			/*Fill in the list of participants
			 * with the Chromosomes which stand at the corresponding indices
			 */
			putNextParticipantAtIndex(participants, nextIndex);
			count++;
		}
				
		Chromosome best = getBestFitnessValueChromosome(participants);
    	
		/*return the fittest one*/
    	return best;
		
	}
	
	/**
	 * Returns the fittest chromosome in the given list.
	 * @param list is the given list of chromosomes.
	 * @return the fittest chromosome.
	 */
	private Chromosome getBestFitnessValueChromosome(List<Chromosome> list) {
		
		Chromosome best = list.get(0);
		double bestFitness = best.getFitness();
		
		for(Chromosome next: list) {
			if(next.getFitness() > bestFitness) {
				bestFitness = next.getFitness();
				best = next;
			}
		}
		return best;
	}

	
	/**
	 * Puts the Chromosome which occupies the specified index in the
	 * population into the list of participants.
	 * @param participants is the list of participants.
	 * @param index is the index of the chromosome in population
	 * 						which has to be added to the list of participants.
	 */
	private void putNextParticipantAtIndex(List<Chromosome> participants, int index) {
		
		Chromosome next = pop.getChromosomeAtIndex(index);
		participants.add(next);			
	}
	
	
	/*Getter and setter methods*/
	
	/**
	 * 
	 * @return number of participants in the tournament.
	 */
	public int getNumberOfParticipants() {
		return numberOfParticipants;
	}

	/**
	 * Allows to change the lower bound of the interval.
	 * @param mIN_NUMBER the given minimal number of participants.
	 */
	public void setMIN_NUMBER(int mIN_NUMBER) {
		if(mIN_NUMBER < MIN_NUMBER) {
			throw new IllegalArgumentException("Not enough participants: " + mIN_NUMBER);
		}
		MIN_NUMBER = mIN_NUMBER;
	}

	/**
	 * Sets the maximal number of participants.
	 * @param mAX_NUMBER is the given number.
	 */
	public void setMAX_NUMBER(int mAX_NUMBER) {

		if(mAX_NUMBER > MAX_NUMBER) {
			throw new IllegalArgumentException("Too many participants: " + mAX_NUMBER);
		}
		
		if(pop.getCurrentSize() < mAX_NUMBER) {
			throw new IllegalArgumentException("Too many participants"
					+ "for the current population size: " + pop.getCurrentSize() 
					+". The chosen"
					+ "number of participants in the tournament is: "
					+ mAX_NUMBER);
		}
		MAX_NUMBER = mAX_NUMBER;
	}
	
	
	/**
	 * Fixes the number of participants at the given number:
	 * Therefore, instead of drawing randomly the number of participants
	 * from the defined interval, this
	 * fixed number will be used.
	 * @param number is the number at which the number of participants 
	 * 					which take part in the tournament is fixed.
	 */
	public void makeParticipantsNumberFixed(int number) {
		setMAX_NUMBER(number);
		setMIN_NUMBER(number);
	}
}
