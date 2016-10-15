package edu.wpi.ntrowles.cs4313.cs4313.proj4;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import Jama.Matrix;
import edu.wpi.ntrowles.cs4313.cs4313.proj4.beans.Action;
import edu.wpi.ntrowles.cs4313.cs4313.proj4.beans.AgentNN;
import edu.wpi.ntrowles.cs4313.cs4313.proj4.beans.DataGenerator;
import edu.wpi.ntrowles.cs4313.cs4313.proj4.beans.DataPersistor;
import edu.wpi.ntrowles.cs4313.cs4313.proj4.beans.State;

public class AgentNNTest {

//	@Test
//	public void agentTestRun() throws IOException{
//		boolean continuePlaying = true;
//		boolean newGame = true;
//		int numGames = 0;
//		ArrayList<double[]> gameScoreSet = new ArrayList<double[]>();
//		double[] curGameScores = new double[50];
//		
//		AgentNN agent = new AgentNN();
//		State curState = new State();
//
//		/**
//		 * Enter loop until told to stop program
//		 */
//		while(continuePlaying){
//			/**
//			 * Game initialization
//			 */
//			if(newGame){
//				curState = new State();
//				System.out.println(curState.toString());
//				System.out.println(print2DArray(curState.percieve()));
//				System.out.println(curState.getScore());
//				newGame = false;
//			}
//			
//			/**
//			 * Agent selects action
//			 */
//			Action action = agent.selectAction(curState);
//			
//			/**
//			 * Print out action information
//			 */
//			System.out.println("x: " + action.getPosition().getX() + ", y: " + action.getPosition().getY());
//			
//			/**
//			 * Perform action on state to get next state, assign it to be curState
//			 */
//			State nextState = curState.nextState(action);
//			
//			
//			/**
//			 * Print out state information
//			 */
//			System.out.println(nextState.toString());
//			System.out.println(print2DArray(nextState.percieve()));
//			System.out.println(nextState.getScore());
//			
//			/**
//			 * Give information back to agent
//			 */
//			
//			//action that agent took, and resulting state
//			//TODO implement (sort of done)
//			agent.updateHistory(action, curState, nextState);
//			
//			curState = nextState;
//			/**
//			 * Update SM of main loop
//			 */
//			if(curState.isTerminal()){
//				curGameScores[numGames%50] = curState.getScore();
//				
//				numGames++;
//				newGame = true;
//			}
//			if(numGames % 50 == 0 && newGame){
//				gameScoreSet.add(curGameScores);
//				curGameScores = new double[50];
//				agent.train(20);
//				//continuePlaying = false;
//			} else if(numGames == 500){
//				continuePlaying = false;
//			}
//		}
//		
//		for(int i=0; i<gameScoreSet.size(); i++){
//			System.out.println(printArray(gameScoreSet.get(i)));
//			int sum = 0;
//			for(int j=0; j<gameScoreSet.get(i).length; j++){
//				sum += gameScoreSet.get(i)[j];
//			}
//			System.out.println("Avg: " + sum/gameScoreSet.get(i).length + " | " + printArray(gameScoreSet.get(i)));
//		}
//	}
	
	
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
	
	
	@Test
	public void agentStandardRun() throws IOException{
		boolean continuePlaying = true;
		boolean newGame = true;
		int numGames = 0;
		double[] gameScoreSet = new double[100];
		
		DataGenerator generator = new DataGenerator();
		
		ArrayList<ArrayList<Matrix>> dataSet = generator.generateDataPairs(50000);
		
		ArrayList<Matrix> xVectorsTotal = dataSet.get(0);
		ArrayList<Matrix> yVectorsTotal = dataSet.get(1);
		
		
		
		ArrayList<Matrix> xVectorsTraining = new ArrayList<Matrix>();
		ArrayList<Matrix> xVectorsTest = new ArrayList<Matrix>();
		ArrayList<Matrix> yVectorsTraining = new ArrayList<Matrix>();
		ArrayList<Matrix> yVectorsTest = new ArrayList<Matrix>();
		
		xVectorsTraining.addAll(xVectorsTotal.subList(0, (int)(0.75*xVectorsTotal.size())));
		yVectorsTraining.addAll(yVectorsTotal.subList(0, (int)(0.75*yVectorsTotal.size())));
		
		xVectorsTest.addAll(xVectorsTotal.subList((int)(0.75*xVectorsTotal.size()), xVectorsTotal.size()));
		yVectorsTest.addAll(yVectorsTotal.subList((int)(0.75*yVectorsTotal.size()), yVectorsTotal.size()));
		
		
		File xVecFile = new File("xVectorsRandom.txt");
		File yVecFile = new File("yVectorsRandom.txt");
		DataPersistor xVecDp = new DataPersistor(xVecFile, 24, 1);
		DataPersistor yVecDp = new DataPersistor(yVecFile, 1, 1);
		
		xVecDp.writeData(xVectorsTotal);
		yVecDp.writeData(yVectorsTotal);
		
		xVecDp.closeFile();
		yVecDp.closeFile();
		
		AgentNN agent = new AgentNN();
		agent.setxVectors(xVectorsTotal);
		agent.setyVectors(yVectorsTotal);
		State curState = new State();
		
		
		agent.train(100);

		
		
		
		
		/**
		 * Enter loop until told to stop program
		 */
		while(continuePlaying){
			/**
			 * Game initialization
			 */
			if(newGame){
				curState = new State();
				System.out.println(curState.toString());
				System.out.println(print2DArray(curState.percieve()));
				System.out.println(curState.getScore());
				newGame = false;
			}
			
			/**
			 * Agent selects action
			 */
			Action action = agent.selectAction(curState);
			
			/**
			 * Print out action information
			 */
			System.out.println("x: " + action.getPosition().getX() + ", y: " + action.getPosition().getY());
			
			/**
			 * Perform action on state to get next state, assign it to be curState
			 */
			State nextState = curState.nextState(action);
			
			
			/**
			 * Print out state information
			 */
			System.out.println(nextState.toString());
			System.out.println(print2DArray(nextState.percieve()));
			System.out.println(nextState.getScore());
			
			/**
			 * Give information back to agent
			 */
			
			//action that agent took, and resulting state
			//TODO implement (sort of done)
			agent.updateHistory(action, curState, nextState);
			
			curState = nextState;
			/**
			 * Update SM of main loop
			 */
			if(curState.isTerminal()){
				gameScoreSet[numGames] = curState.getScore();
				
				numGames++;
				newGame = true;
			}
			if(numGames == 100){
				continuePlaying = false;
			}
		}
		
		System.out.println(printArray(gameScoreSet));
		int sum = 0;
		for(int j=0; j<gameScoreSet.length; j++){
			sum += gameScoreSet[j];
		}
		System.out.println("Avg: " + sum/gameScoreSet.length + " | " + printArray(gameScoreSet));
	}
	
}
