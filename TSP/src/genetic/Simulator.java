package genetic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

import util.Printer;

public class Simulator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String fileName = "./data/a280.tsp";
		
		loadFile(fileName);

	}
	
	
	private static void loadFile(String fileName) {
		
		File file = new File(fileName);
		
		if (!file.canRead() || !file.isFile())
            System.exit(0);
		
		try {
			TSPParser tsp = new TSPParser(file);
			TSPInstance tspInst = tsp.getTspInstance();
			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}

}
