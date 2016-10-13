package edu.wpi.ntrowles.cs4313.cs4313.proj4;

import edu.wpi.ntrowles.cs4313.cs4313.proj4.beans.Action;
import edu.wpi.ntrowles.cs4313.cs4313.proj4.beans.Agent;
import edu.wpi.ntrowles.cs4313.cs4313.proj4.beans.MoveType;
import edu.wpi.ntrowles.cs4313.cs4313.proj4.beans.Position;
import edu.wpi.ntrowles.cs4313.cs4313.proj4.beans.State;

public class Main {

	public static void main(String[] args) {
		/**
		 * Initialize State, Agent, and flags
		 */
		boolean continuePlaying = true;
		
		Agent agent = new Agent();
		State curState = new State();
//		curState.initializeBoard();
//		curState.toString();
		
		
		
		/**
		 * Print out state information
		 */
		System.out.println(curState.toString());
		System.out.println(print2DArray(curState.percieve()));
		System.out.println(curState.getScore());
		
		
		/**
		 * Enter loop until told to stop program
		 */
		while(continuePlaying){
			//a.doAction(curState);
			//Action action = new Action(new Position((int)(Math.random()*curState.getMinesweeperBoard().length), (int)(Math.random()*curState.getMinesweeperBoard()[0].length)), MoveType.DIG);
			
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
			//agent.updateHistory(curState, nextState, action);
			curState = nextState;
			
			/**
			 * Print out state information
			 */
			System.out.println(curState.toString());
			System.out.println(print2DArray(curState.percieve()));
			System.out.println(curState.getScore());
			
			/**
			 * Give information back to agent
			 */
			
			//action that agent took, and resulting state
			//TODO implement
			agent.updateHistory(action, curState);
			
			/**
			 * Update SM of main loop
			 */
			if(curState.isTerminal()){
				continuePlaying = false;
				
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
	
}
