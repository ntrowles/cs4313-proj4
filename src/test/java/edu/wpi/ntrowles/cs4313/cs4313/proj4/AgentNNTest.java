package edu.wpi.ntrowles.cs4313.cs4313.proj4;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import edu.wpi.ntrowles.cs4313.cs4313.proj4.beans.Action;
import edu.wpi.ntrowles.cs4313.cs4313.proj4.beans.AgentNN;
import edu.wpi.ntrowles.cs4313.cs4313.proj4.beans.State;

public class AgentNNTest {
	@Test
	public void agentTestRun() throws IOException{
		boolean continuePlaying = true;
		boolean newGame = true;
		int numGames = 0;
		ArrayList<double[]> gameScoreSet = new ArrayList<double[]>();
		double[] curGameScores = new double[50];
		
		AgentNN agent = new AgentNN();
		State curState = new State();

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
				curGameScores[numGames%50] = curState.getScore();
				
				numGames++;
				newGame = true;
			}
			if(numGames % 50 == 0 && newGame){
				gameScoreSet.add(curGameScores);
				curGameScores = new double[50];
				agent.train(20);
				//continuePlaying = false;
			} else if(numGames == 500){
				continuePlaying = false;
			}
		}
		
		for(int i=0; i<gameScoreSet.size(); i++){
			System.out.println(printArray(gameScoreSet.get(i)));
			int sum = 0;
			for(int j=0; j<gameScoreSet.get(i).length; j++){
				sum += gameScoreSet.get(i)[j];
			}
			System.out.println("Avg: " + sum/gameScoreSet.get(i).length + " | " + printArray(gameScoreSet.get(i)));
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
