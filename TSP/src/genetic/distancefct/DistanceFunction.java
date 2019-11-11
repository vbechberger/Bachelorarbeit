package genetic.distancefct;

import genetic.DistTableFiller;

public abstract class DistanceFunction implements DistTableFiller{
	
	public abstract double[][] fillDistTable(double[][] coordinates, int dimOfDistTable);
	
}
