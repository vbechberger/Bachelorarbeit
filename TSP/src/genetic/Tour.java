package genetic;

//import util.SaveCopy;

public class Tour {
	
	private int[] tour;

	public Tour(int[] tour) {
		setTour(tour);
	}

	public int[] getTour() {
		return tour;
	}

	public void setTour(int[] tour) {
		//this.tour = new int[tour.length];
		//SaveCopy.copy(this.tour, tour);
		this.tour = tour;
	}
	
	public String toString() {
		String result = "";
		for (int i = 0; i < tour.length; i++) {
			result = result + tour[i] + " ";
		}
		return result;		
	}

}
