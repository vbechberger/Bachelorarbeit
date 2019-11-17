package genetic;

import java.io.File;
import java.io.IOException;


public class Simulator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//String fileName = "./data/a280.tsp";
		//String fileName = "./data/ali535.tsp";
		String fileName = "./data/bayg29.tsp";
		
		loadFile(fileName);

	}
	
	
	private static void loadFile(String fileName) {
		
		//File[] fileArray = f.listFiles();
		
		File file = new File(fileName);
		
		if (!file.canRead() || !file.isFile()) {
			System.err.println("The file could not be read!");
            System.exit(0);
		}
		try {
			TSPParser tsp = new TSPParser(file);
			TSPInstance tspInst = tsp.getTspInstance();
			//tspInst.getDistanceTable().printFirstCol();

			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}

}
