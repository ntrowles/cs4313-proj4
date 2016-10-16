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
		
		boolean newGame = true;
		boolean skipState = false;

		//loop through and generate numPairs
		int count = 0;
		while(count<numPairs){
			if(newGame){
				curState = new State();
				
				System.out.println(curState.toString());
				System.out.println(print2DArray(curState.percieve()));
				System.out.println(curState.getScore());
				
				newGame = false;
				skipState = true;
			}
			if(!skipState){
				ArrayList<ArrayList<Matrix>> curStateTraining = agent.createTrainingData(curState);
				xVectors.addAll(curStateTraining.get(0));
				yVectors.addAll(curStateTraining.get(1));
				
				count += curStateTraining.get(0).size();
				
				System.out.println("xVectors size: " + xVectors.size() + ", yVectors size: " + yVectors.size());
				System.out.println("count: " + count);
				
				
			}
			skipState = false;
			
			curState = curState.nextState(agent.selectAction(curState));
			System.out.println(curState.toString());
			System.out.println(print2DArray(curState.percieve()));
			System.out.println(curState.getScore());
			
			if(curState.isTerminal()){
				newGame = true;
			}
			
		}
		
		
		dataset.add(xVectors);
		dataset.add(yVectors);
		return dataset;
	}
	
	public void printVectors(ArrayList<Matrix> inputMatrix, String vectorName, int yLength, int xLength){
		for(int i = 0; i < inputMatrix.size(); i++){
			System.out.println(vectorName + ": " + i);
			Matrix curMatrix = inputMatrix.get(i).transpose();
			for(int y = 0; y < xLength; y++){
				for(int x = 0; x < yLength; x++){
					System.out.print(curMatrix.get(y, x) + "\t");
				}
				System.out.println("");
			}
		}
	}
	
	
	public static String print2DArray(char[][] arr){
		StringBuilder b = new StringBuilder();
		for(int i=0; i<arr.length; i++){
			for(int j=0; j<arr.length; j++){
				b.append(arr[i][j]);
			}
			b.append("\n");
		}
		
		return b.toString();
	}
	
	public static String printArray(double[] arr){
		StringBuilder b = new StringBuilder();
		for(int i=0; i<arr.length-1; i++){
			b.append(arr[i]);
			b.append(",");
		}
		b.append(arr[arr.length-1]);
		return b.toString();
	}
	
}
