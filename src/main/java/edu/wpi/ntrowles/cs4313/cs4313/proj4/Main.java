package edu.wpi.ntrowles.cs4313.cs4313.proj4;

import edu.wpi.ntrowles.cs4313.cs4313.proj4.beans.Agent;
import edu.wpi.ntrowles.cs4313.cs4313.proj4.beans.State;

public class Main {

	public static void main(String[] args) {
		boolean continuePlaying = true;
		
		Agent a = new Agent();
		State curState = new State();
		curState.initializeBoard();
		curState.toString();
		while(continuePlaying){
			a.doAction(curState);
			
			curState.toString();
			if(curState.isTerminal()){
				continuePlaying = false;
			}
		}
	
	}
	
	
}
