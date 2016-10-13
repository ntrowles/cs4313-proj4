package edu.wpi.ntrowles.cs4313.cs4313.proj4.beans;

import java.util.ArrayList;
import java.util.List;

import Jama.Matrix;
import edu.wpi.ntrowles.cs4313.cs4313.proj4.network.Network;

/**
 * The core tester class of our project.
 * The agent is the AI that plays the game.
 * @author bgsarkis
 *
 */
public class Agent {
	private Network neuralNetwork = new Network();
	private ArrayList<Matrix> xVectors;
	private ArrayList<Matrix> yVectors;
	
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
		char[][] percievedState = curState.percieve();
		
		List<Action> validActions = new ArrayList<Action>();
		Action bestAction = new Action();
		double bestActionH = 1;
		boolean baInitialized = false;
		//Add all valid actions, DIG and FLAG type actions.
		//TODO for now, I am only worried about digging actions, will add flagging actions later
		for(int i=0; i<percievedState.length; i++){
			for(int j=0; j<percievedState[0].length; j++){
				if(percievedState[i][j] == 'h'){
					Action curAction = new Action(new Position(j, i), MoveType.DIG);
					Matrix xVector = new Matrix(24, 1);
					double curActionH = neuralNetwork.forwardPropogate(xVector).get(neuralNetwork.getNumLayers()-1).get(0, 0);
					if(!baInitialized){
						bestAction = curAction;
						//Matrix yVector = new Matrix(1, 1);
						bestActionH = curActionH;
					} else if(curActionH < bestActionH){ //trying to minimize hypothesis, as (h(x) = 0) => no-bomb
						bestAction = curAction;
						bestActionH = curActionH;
					}
				}
			}
		}
		
		//select best actions based on function
		return bestAction;
	}
	
	
}
