package genetic;

public class Population {
	
	//general parameters
	
	//size
	final int MIN_SIZE = 5;
	int size;
	
	
	
	
	public Population(int size) {
		setSize(size);
	}


	
	
	
	
	
	/*Getter- and setter methods*/
	public int getSIZE() {
		return this.size;
	}
	
	void setSize(int size) {
		if(size < MIN_SIZE) {
			throw new IllegalArgumentException("The size of the population "
							+ "has to be greater than " + MIN_SIZE + " !");
		}
		this.size = size;
	}
	
	

}
