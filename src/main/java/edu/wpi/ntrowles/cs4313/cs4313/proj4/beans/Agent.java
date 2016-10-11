package edu.wpi.ntrowles.cs4313.cs4313.proj4.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * The core tester class of our project.
 * The agent is the AI that plays the game.
 * @author bgsarkis
 *
 */
public class Agent {
	/**
	 * Contains a list of paths of past games, the memory of the agent.
	 */
	private List<Path> history;
	
	/**
	 * Current game the Agent is playing.
	 */
	private Path curGame;
	
	/**
	 * Default agent constructor, creates a new path collection.
	 */
	public Agent(){
		this(new ArrayList<Path>());
	}
	
	/**
	 * Overloaded agent constructor, takes in a history of past games.
	 * @param history The playing history of the agent.
	 */
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
