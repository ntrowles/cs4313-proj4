package edu.wpi.ntrowles.cs4313.cs4313.proj4.beans;

import java.io.IOException;
import java.util.ArrayList;

import Jama.Matrix;
import edu.wpi.ntrowles.cs4313.cs4313.proj4.base.Agent;

public class DataGenerator {
	public DataGenerator(){
		//intentionally left blank
	}

	public ArrayList<ArrayList<Matrix>> generateDataPairs(int numPairs) throws IOException{
		//dataset; contains 2 lists of matrices (xVectors, yVectors)
		ArrayList<ArrayList<Matrix>> dataset = new ArrayList<ArrayList<Matrix>>();
		
		ArrayList<Matrix> xVectors = new ArrayList<Matrix>();
		ArrayList<Matrix> yVectors = new ArrayList<Matrix>();
		
		State curState = new State();
		RandomAgent agent = new RandomAgent();

		//loop through and generate numPairs
		int count = 0;
		while(count<numPairs){
			ArrayList<ArrayList<Matrix>> curStateTraining = agent.createTrainingData(curState);
			xVectors.addAll(curStateTraining.get(0));
			yVectors.addAll(curStateTraining.get(1));
			
			count += curStateTraining.size();
			
		}
		
		
		dataset.add(xVectors);
		dataset.add(yVectors);
		return dataset;
	}
	
	public void printVectors(ArrayList<Matrix> inputMatrix, String vectorName, int yLength, int xLength){
		for(int i = 0; i < inputMatrix.size(); i++){
			System.out.println(vectorName + ": ");
			for(int y = 0; y < yLength; y++){
				for(int x = 0; x < xLength; x++){
					System.out.print(inputMatrix.get(i).get(y, x) + "\t");
				}
				System.out.println("");
			}
		}
	}
	
}
