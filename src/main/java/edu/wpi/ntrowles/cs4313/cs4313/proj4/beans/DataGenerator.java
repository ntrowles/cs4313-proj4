package edu.wpi.ntrowles.cs4313.cs4313.proj4.beans;

import java.util.ArrayList;

import Jama.Matrix;

public class DataGenerator {
	public DataGenerator(){
		//intentionally left blank
	}

	public ArrayList<ArrayList<Matrix>> generateDataPairs(int numPairs){
		//dataset; contains 2 lists of matrices (xVectors, yVectors)
		ArrayList<ArrayList<Matrix>> dataset = new ArrayList<ArrayList<Matrix>>();
		
		ArrayList<Matrix> xVectors = new ArrayList<Matrix>();
		ArrayList<Matrix> yVectors = new ArrayList<Matrix>();
		
		State curState = new State();
		AgentNN agent = new AgentNN();
		
		
		
		boolean newGame = true;
		//loop through and generate numPairs
		int count = 0;
		while(count<numPairs){
			ArrayList<ArrayList<Matrix>> curStateTraining = agent.createTrainingData(curState);
		}
		
		return dataset;
	}
}
