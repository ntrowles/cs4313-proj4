package edu.wpi.ntrowles.cs4313.cs4313.proj4.beans;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Jama.Matrix;
import edu.wpi.ntrowles.cs4313.cs4313.proj4.base.Agent;
import edu.wpi.ntrowles.cs4313.cs4313.proj4.network.Network;

/**
 * The core tester class of our project.
 * The agent is the AI that plays the game.
 * @author bgsarkis
 *
 */
public class RandomAgent extends Agent{
	private Network neuralNetwork;
	private ArrayList<Matrix> xVectors;
	private ArrayList<Matrix> yVectors;
	
	
	private File xFile;
	private File yFile; 
	
	private DataPersistor xDP;
	private DataPersistor yDP;
	
	
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
	 * @throws IOException 
	 */
	public RandomAgent() throws IOException{
		this(new ArrayList<Path>());
		xVectors = new ArrayList<Matrix>();
		yVectors = new ArrayList<Matrix>();
		
		xFile = new File("xVectorData.txt");
		yFile = new File("yVectorData.txt");
		
		xDP = new DataPersistor(xFile, 24, 1);
		yDP = new DataPersistor(yFile, 1, 1);
	}
	
	/**
	 * Overloaded agent constructor, takes in a history of past games.
	 * @param history The playing history of the agent.
	 */
	public RandomAgent(List<Path> history){
		this.history = history;
	}
	
	public Action selectAction(State curState){
//		System.out.println("Selecting action");
		
		char[][] percievedState = curState.percieve();
		
		List<Action> validActions = new ArrayList<Action>();
		//Add all valid actions, DIG and FLAG type actions.
		for(int i=0; i<percievedState.length; i++){
			for(int j=0; j<percievedState[0].length; j++){
				if(percievedState[i][j] == 'h'){
					//Action flagAction = new Action(new Position(j, i), MoveType.FLAG);
					Action digAction = new Action(new Position(j, i), MoveType.DIG);
					//validActions.add(flagAction);
					validActions.add(digAction);
				}
				else if(percievedState[i][j] == 'f'){
					Action digAction = new Action(new Position(j, i), MoveType.DIG);
					validActions.add(digAction);
				}
			}
		}
		
		int ActionSelector = (int)(Math.random() * validActions.size());
		Action selectedAction = validActions.get(ActionSelector);
		//select best actions based on function
		return selectedAction;
	}
	
	public void train(int iterations) throws IOException{
		//haha funny
		xDP.writeData(xVectors);
		yDP.writeData(yVectors);
	}
	
	public Matrix assignXVector(Action action, char[][] percievedState){
		Matrix xVector = new Matrix(24,1);
		int xPosn = action.getPosition().getX();
		int yPosn = action.getPosition().getY();
		
		int count = 0;
		for(int i=-2; i<=2; i++){
			for(int j=-2; j<=2; j++){
				int curXPosn = xPosn + j;
				int curYPosn = yPosn + i;
				
				if(i==0 && j==0){
					continue;
				}
				
				if(curXPosn < 0 | curYPosn < 0 | curXPosn >= percievedState[0].length | curYPosn >= percievedState.length){
					xVector.set(count, 0, -2.0);
				} else if(percievedState[curYPosn][curXPosn] == 'h' || percievedState[curYPosn][curXPosn] == 'f'){
					xVector.set(count, 0, -1.0);
				} else if(percievedState[curYPosn][curXPosn] >= '0' && percievedState[curYPosn][curXPosn] <= '9'){
					String s = "" + percievedState[curYPosn][curXPosn];
					xVector.set(count, 0, Double.parseDouble(s));
				} else if (percievedState[curYPosn][curXPosn] == 'c') {
					xVector.set(count, 0, 0.0);
				} else{
					xVector.set(count, 0 , -1);
				}
				
				count++;
			}
		}
		
		return xVector;
	}
	
	public void updateHistory(Action action, State oldState, State newState){
		//TODO update percieved state/path thing (not really that high a priority, maybe if we want it for another agent algo)
		
		//TODO update training set pairs
		xVectors.add(assignXVector(action, oldState.percieve()));
		Matrix yVector = new Matrix(1,1);
		//1 is bomb, 0 is non-bomb
		yVector.set(0, 0, (newState.percieve()[action.getPosition().getY()][action.getPosition().getX()] == 'b') ? 1 : 0);
		yVectors.add(yVector);
	}
	
	public ArrayList<ArrayList<Matrix>> createTrainingData(State state){
		ArrayList<ArrayList<Matrix>> stateTrainingData = new ArrayList<ArrayList<Matrix>>();
		
		ArrayList<Matrix> xVectors = new ArrayList<Matrix>();
		ArrayList<Matrix> yVectors = new ArrayList<Matrix>();
		
		
		char[][] percievedState = state.percieve();
		for(int i=0; i<percievedState.length; i++){
			for(int j=0; j<percievedState[0].length; j++){
				Action curAction = new Action(new Position(j, i), MoveType.DIG);
//				System.out.println("Action to evaluate: x=" + curAction.getPosition().getX() + ", y=" + curAction.getPosition().getY());
				Matrix xVector = assignXVector(curAction, percievedState);
//				double curActionH = neuralNetwork.forwardPropogate(xVector).get(neuralNetwork.getNumLayers()-1).get(0, 0);
//				System.out.println("Resulting hypothesis: " + curActionH);
				//peek row, column
				Matrix yVector = state.peek(i, j);
				
				//add vectors to respective sets
				xVectors.add(xVector);
				yVectors.add(yVector);
				
			}
		}
		
		stateTrainingData.add(xVectors);
		stateTrainingData.add(yVectors);
		return stateTrainingData;
	}
	
	public void stopRecording() throws IOException{
		xDP.closeFile();
		yDP.closeFile();
	}
	
}
