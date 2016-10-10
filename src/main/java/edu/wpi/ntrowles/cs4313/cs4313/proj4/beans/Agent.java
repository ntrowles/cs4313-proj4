package edu.wpi.ntrowles.cs4313.cs4313.proj4.beans;

import java.util.ArrayList;
import java.util.List;

public class Agent {
	private List<Path> history;
	private Path curGame;
	
	public Agent(){
		this(new ArrayList<Path>());
	}
	
	public Agent(List<Path> history){
		this.history = history;
	}
	
	public Action selectAction(State curState){
		List<Action> validActions = new ArrayList<Action>();
		//Add all valid actions, DIG and FLAG type actions.
		//select best actions based on function
		Action bestAction = new Action();
		return bestAction;
	}
	
	public double doAction(State curState){
		Action nextAction = selectAction(curState);
		
	}
}