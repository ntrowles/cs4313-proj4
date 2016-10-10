package edu.wpi.ntrowles.cs4313.cs4313.proj4.beans;

import java.util.ArrayList;
import edu.wpi.ntrowles.cs4313.cs4313.proj4.beans.Position;
import edu.wpi.ntrowles.cs4313.cs4313.proj4.beans.Action;

public class State {
	private final boolean terminal;
	
	private final PositionInfo[][] minesweeperBoard;
	private final double score;
	private final int numBombs;
	
	public ArrayList<Position> getBombPosns() {
		return bombPosns;
	}

	public void setBombPosns(ArrayList<Position> bombPosns) {
		this.bombPosns = bombPosns;
	}

	public ArrayList<Position> getNonBombPosns() {
		return nonBombPosns;
	}

	public void setNonBombPosns(ArrayList<Position> nonBombPosns) {
		this.nonBombPosns = nonBombPosns;
	}

	private ArrayList<Position> bombPosns;
	private ArrayList<Position> nonBombPosns;
	
	public State(){
		this(10, 10, 10, 0, false);
	}
	
	public State(int boardSizeY, int boardSizeX, int numBombs, double score, boolean terminal){
		this.minesweeperBoard = new PositionInfo[boardSizeY][boardSizeX];
		this.score = score;
		this.numBombs = numBombs;
		initializeBoard();
		
		this.bombPosns = new ArrayList<Position>();
		this.nonBombPosns = new ArrayList<Position>();
		
		this.terminal = terminal;
	}
	
	public State(PositionInfo[][] board, double score, int numBombs, boolean terminal, ArrayList<Position> bombPosns, ArrayList<Position> nonBombPosns){
		this.minesweeperBoard = board;
		this.score = score;
		this.numBombs = numBombs;
		this.terminal = terminal;
	}

	public PositionInfo[][] getMinesweeperBoard() {
		return minesweeperBoard;
	}
	
	public double getScore() {
		return this.score;
	}
	
	public int getNumBombs() {
		return this.numBombs;
	}
	
	public void initializeBoard(){
		for(int i=0; i<minesweeperBoard.length; i++){
			for(int j=0; j<minesweeperBoard[0].length; j++){
				minesweeperBoard[i][j] = new PositionInfo();
				nonBombPosns.add(new Position(j, i));
			}
		}
		for(int i=0; i<numBombs; i++){
			assignBomb();
		}
	}
	
	public void assignBomb(){
		int numNonBombs = nonBombPosns.size();
		int randPosnIndex = (int)(Math.random() * numNonBombs);
		Position randPosn = nonBombPosns.get(randPosnIndex);
		
		minesweeperBoard[randPosn.getY()][randPosn.getX()].setBomb(true);
		nonBombPosns.remove(randPosnIndex);
		bombPosns.add(randPosn);
		
	}
	
	/**
	 * Represents the new state of a board based on an action made.
	 * @param a The click made to a certain coordinate.
	 * @return Updated state of the board.
	 */
	public State nextState(Action a){
		//Action contains a position
		//This is the position that will be clicked
		Position toClick = a.getPosition();
		
		//We now change the information of this location
		int x = toClick.getX();
		int y = toClick.getY();
		
		switch(a.getMoveType()){
			
		case DIG:
			
			//dig
			return dig(minesweeperBoard[x][y]);
			
			/* Why do we have this again?
			 * 
			 * 
			 *else{minesweeperBoard[x][y].
			 *else Square at position becomes revealed, and
			 *possibly many others.
			 */
			
		case FLAG:
			minesweeperBoard[x][y].setMarker('f');
			//Return a new state that reflects the updated marker.
			//Because the position info was already changed here, 
			// we just return what is already there.
			return new State(this.minesweeperBoard, this.score, this.getNumBombs(), false, this.getBombPosns(), this.getNonBombPosns());
	
		}
		
		//Make the next state based on updated information and return
		State nextState = new State();
		return nextState;
	}
	
	private State dig(PositionInfo pInfo){
		int x = pInfo.getPosition().getX();
		int y = pInfo.getPosition().getY();
		int length = minesweeperBoard[0].length;
		
		//perform dig
		//Is this where we modify score? (Because nothing hidden must affect the heuristic somehow?)
		pInfo.setHidden(false);
		
		//If its a bomb YOU GOT ISIS'D BITCH!!!!
		if(this.minesweeperBoard[x][y].isBomb()){
			//Return a new state with terminal set to true
			return new State(this.minesweeperBoard, this.score, this.getNumBombs() - 1, true, this.getBombPosns(), this.getNonBombPosns());
		}
		
		//perform dig around surrounding positions if current position has no bombs around it
		//Modify the score to represent new state ?
		if(minesweeperBoard[x][y].getNumNeighbors() == 0){
			for(int i = -1; i <= 1; i++){
				for(int j = -1; j <= 1; j++){
					if(x+i >=0 && y+i >= 0 && x+i < length && y+i < length &&
							minesweeperBoard[x+i][y+i].isHidden() == true){
						
						dig(minesweeperBoard[x+i][y+i]);
					}
				}
			}
		}
		
		//This is right AFTER we change the score in the correct manner.
		return new State(this.minesweeperBoard, this.score, this.getNumBombs(), false, this.getBombPosns(), this.getNonBombPosns());
		
		//Return a new state that reflects the dug squares all dug up.
	}

	public boolean isTerminal() {
		return terminal;
	}
	
	public String toString(){
		StringBuilder b = new StringBuilder();
		
		for(int j=0;j<minesweeperBoard.length;j++){
			b.append("----");
		}
		b.append("\n");
		for(int i=0;i<minesweeperBoard.length;i++){
			b.append("|");
			for(int j=0;j<minesweeperBoard.length;j++){
				b.append(minesweeperBoard[i][j].toString() + "|");
			}
			b.append("\n-");
			for(int j=0;j<minesweeperBoard.length;j++){
				b.append("----");
			}
			b.append("\n");
		}
		
		return b.toString();
	}
}
