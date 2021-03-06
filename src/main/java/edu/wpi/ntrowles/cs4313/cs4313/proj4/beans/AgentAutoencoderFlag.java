package edu.wpi.ntrowles.cs4313.cs4313.proj4.beans;

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
public class AgentAutoencoderFlag extends Agent{
	private Network neuralNetwork;
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
	public AgentAutoencoderFlag(){
		this(new ArrayList<Path>());
		neuralNetwork = new Network();
		xVectors = new ArrayList<Matrix>();
		yVectors = new ArrayList<Matrix>();
	}
	
	/**
	 * Overloaded agent constructor, takes in a history of past games.
	 * @param history The playing history of the agent.
	 */
	public AgentAutoencoderFlag(List<Path> history){
		this.history = history;
		this.neuralNetwork = new Network();
	}
	
	public void train(int iterations){
		// ArrayList of integers
		ArrayList<Integer> layerNum = new ArrayList<Integer>();
		layerNum.add(xVectors.get(0).getRowDimension());
		layerNum.add(xVectors.get(0).getRowDimension()*3/8);
		layerNum.add(xVectors.get(0).getRowDimension());
		Network autoEncoderNet = new Network(3, layerNum);
		
		
		for(int i = 0; i < iterations; i++){
			autoEncoderNet.gradientDescent(xVectors, xVectors);
		}
		
		neuralNetwork = new Network();
		neuralNetwork.setThetas(autoEncoderNet.getThetas(), 0);
		
		for(int i = 0; i < iterations; i++){
			autoEncoderNet.gradientDescent(xVectors, xVectors);
		}
	}
	
	
	public Action selectAction(State curState){
		System.out.println("Selecting action");
		
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
					//Now we determine the difference between 0 or 1.  Pick the action that minimizes the difference
					
					Action digAtThisSpot = new Action(new Position(j, i), MoveType.DIG);
					Action flagThisSpot = new Action(new Position(j, i), MoveType.FLAG);
					Matrix selectVector = assignXVector(digAtThisSpot, percievedState);
					//if a dig 	
					if(neuralNetwork.forwardPropogate(selectVector).get(neuralNetwork.getNumLayers()-1).get(0, 0) < bestActionH){
//						bestActionH = neuralNetwork.forwardPropogate(selectVector).get(neuralNetwork.getNumLayers()-1).get(0, 0);
						System.out.println("Action to evaluate: x=" + digAtThisSpot.getPosition().getX() + ", y=" + digAtThisSpot.getPosition().getY());
						Matrix xVector = assignXVector(digAtThisSpot, percievedState);
						double curActionH = neuralNetwork.forwardPropogate(xVector).get(neuralNetwork.getNumLayers()-1).get(0, 0);
						System.out.println("Resulting hypothesis: " + curActionH);
						if(!baInitialized){
							bestAction = digAtThisSpot;
							//Matrix yVector = new Matrix(1, 1);
							bestActionH = curActionH;
							baInitialized = true;
						} else if(curActionH < bestActionH){ //trying to minimize hypothesis, as (h(x) = 0) => no-bomb
							bestAction = digAtThisSpot;
							bestActionH = curActionH;
						}
						
					}
					//if a flag
					else if((1 - neuralNetwork.forwardPropogate(selectVector).get(neuralNetwork.getNumLayers()-1).get(0, 0) < bestActionH)){
//						bestActionH = 1 - neuralNetwork.forwardPropogate(selectVector).get(neuralNetwork.getNumLayers()-1).get(0, 0);
						System.out.println("Action to evaluate: x=" + flagThisSpot.getPosition().getX() + ", y=" + flagThisSpot.getPosition().getY());
						Matrix xVector = assignXVector(flagThisSpot, percievedState);
						double curActionH = 1 - neuralNetwork.forwardPropogate(xVector).get(neuralNetwork.getNumLayers()-1).get(0, 0);
						System.out.println("Resulting hypothesis: " + curActionH);
						if(!baInitialized){
							bestAction = flagThisSpot;
							//Matrix yVector = new Matrix(1, 1);
							bestActionH = curActionH;
							baInitialized = true;
						} else if(curActionH < bestActionH){ //trying to minimize hypothesis, as (h(x) = 0) => no-bomb
							bestAction = flagThisSpot;
							bestActionH = curActionH;
						}
					}
					
				}
				else if (percievedState[i][j] == 'f') {
					Action digAtThisSpot = new Action(new Position(j, i), MoveType.DIG);
					Matrix selectVector = assignXVector(digAtThisSpot, percievedState);
					
//					bestActionH = neuralNetwork.forwardPropogate(selectVector).get(neuralNetwork.getNumLayers()-1).get(0, 0);
					System.out.println("Action to evaluate: x=" + digAtThisSpot.getPosition().getX() + ", y=" + digAtThisSpot.getPosition().getY());
					Matrix xVector = assignXVector(digAtThisSpot, percievedState);
					double curActionH = neuralNetwork.forwardPropogate(xVector).get(neuralNetwork.getNumLayers()-1).get(0, 0);
					System.out.println("Resulting hypothesis: " + curActionH);
					if(!baInitialized){
						bestAction = digAtThisSpot;
						//Matrix yVector = new Matrix(1, 1);
						bestActionH = curActionH;
						baInitialized = true;
					} else if(curActionH < bestActionH){ //trying to minimize hypothesis, as (h(x) = 0) => no-bomb
						bestAction = digAtThisSpot;
						bestActionH = curActionH;
					}
				}
			}
		}
		//select best actions based on function
		return bestAction;
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
	
	public void newGameInit(){
		//TODO holy shit this is the last thing on my priority list
	}
	
	
	
	
}

