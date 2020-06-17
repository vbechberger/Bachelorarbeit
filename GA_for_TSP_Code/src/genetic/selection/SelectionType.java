package genetic.selection;

import java.util.Random;
import genetic.Population;

/**
 * Represents type of selection operator.
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public enum SelectionType {
	TOURNAMENT;
	
	/**
	 * Given the required selection.
	 * @param rand is the Random object.
	 * @param pop is the population.
	 * @return
	 */
	public Selection getSelection(Random rand, Population pop) {
		
		switch (this) {
		case TOURNAMENT: {
			
			return new TournamentSelection(rand, pop);						
		} 
		default:
			throw new IllegalArgumentException("The given selection type does not exist: "
            		+ this.name());
		}
		
		
	}
}
