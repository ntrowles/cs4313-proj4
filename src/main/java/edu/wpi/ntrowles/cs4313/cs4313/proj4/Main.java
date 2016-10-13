package edu.wpi.ntrowles.cs4313.cs4313.proj4;

import edu.wpi.ntrowles.cs4313.cs4313.proj4.beans.Action;
import edu.wpi.ntrowles.cs4313.cs4313.proj4.beans.Agent;
import edu.wpi.ntrowles.cs4313.cs4313.proj4.beans.MoveType;
import edu.wpi.ntrowles.cs4313.cs4313.proj4.beans.Position;
import edu.wpi.ntrowles.cs4313.cs4313.proj4.beans.State;

public class Main {

	public static void main(String[] args) {
		boolean continuePlaying = true;
		
		Agent agent = new Agent();
		State curState = new State();
//		curState.initializeBoard();
		curState.toString();
		while(continuePlaying){
			//a.doAction(curState);
			System.out.println(curState.toString());
			System.out.println(curState.percieve());
			System.out.println(curState.getScore());
			//Action action = new Action(new Position((int)(Math.random()*curState.getMinesweeperBoard().length), (int)(Math.random()*curState.getMinesweeperBoard()[0].length)), MoveType.DIG);
			Action action = agent.selectAction(curState);
			System.out.println("x: " + action.getPosition().getX() + ", y: " + action.getPosition().getY());
			State nextState = curState.nextState(action);
			//agent.updateHistory(curState, nextState, action);
			curState = nextState;
			
			if(curState.isTerminal()){
				continuePlaying = false;
			}
		}
	
	}
	
	
}
