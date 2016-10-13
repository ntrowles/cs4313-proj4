package edu.wpi.ntrowles.cs4313.cs4313.proj4.beans;

import java.util.ArrayList;
import edu.wpi.ntrowles.cs4313.cs4313.proj4.beans.Position;
import edu.wpi.ntrowles.cs4313.cs4313.proj4.beans.Action;

/**
 * Class encoding the state of the board.
 * @author bgsarkis
 * @author ntrowles
 * @author ttrieu
 */
public class State {
	
	/**
	 * Flag determining game end.
	 */
	private final boolean terminal;
	/**
	 * Board represented by array of PositionInfos.
	 */
	private final PositionInfo[][] minesweeperBoard;
	/**
	 * Score of the game.
	 */
	private final double score;
	/**
	 * Number of bombs in this instance of the game.
	 */
	private final int numBombs;

	/**
	 * Collection of positions with bombs.
	 */
	private ArrayList<Position> bombPosns;
	/**
	 * Collection of positions without bombs.
	 */
	private ArrayList<Position> nonBombPosns;
	
	/**
	 * Accessor for collection of positions that have bombs in them.
	 * @return ArrayList of positions that have bombs in them.
	 */
	public ArrayList<Position> getBombPosns() {
		return bombPosns;
	}

	/**
	 * Mutator for collection of positions that have bombs in them.
	 * @param bombPosns The new list of positions which have bombs in them.
	 */
	public void setBombPosns(ArrayList<Position> bombPosns) {
		this.bombPosns = bombPosns;
	}

	/**
	 * Accessor for collection of positions that DO NOT have bombs in them.
	 * @return ArrayList of positions that DO NOT have bombs in them.
	 */
	public ArrayList<Position> getNonBombPosns() {
		return nonBombPosns;
	}
	
	/**
	 * Mutator for collection of positions that DO NOT have bombs in them.
	 * @param nonBombPosns The new list of positions which do not have bombs in them.
	 */
	public void setNonBombPosns(ArrayList<Position> nonBombPosns) {
		this.nonBombPosns = nonBombPosns;
	}
	
	/**
	 * Default State: 
	 * Has a y size of 10 and an x size of 10.
	 * The default number of bombs in this state is 10.
	 * Default score is 0.
	 * Default terminal flag is false.
	 */
	public State(){
		this(10, 10, 10, 0, false);
	}
	
	/**
	 * Generalized overloaded state constructor
	 * @param boardSizeY Horizontal size of board.
	 * @param boardSizeX Vertical size of board.
	 * @param numBombs Number of bombs in board.
	 * @param score Current score.
	 * @param terminal Current play-end condition.
	 */
	public State(int boardSizeY, int boardSizeX, int numBombs, double score, boolean terminal){
		this.minesweeperBoard = new PositionInfo[boardSizeY][boardSizeX];
		this.score = score;
		this.numBombs = numBombs;
		
		this.bombPosns = new ArrayList<Position>();
		this.nonBombPosns = new ArrayList<Position>();
		initializeBoard();
		
		initializeBoard();
		
		this.terminal = terminal;
	}
	
	/**
	 * State constructor with more specific arguments.
	 * @param board Double array of Position infos, a hardcoded board.
	 * @param score Current score of the game.
	 * @param numBombs Number of bombs on the board.
	 * @param terminal Current play-end condition.
	 * @param bombPosns Spaces that do have bombs in them.
	 * @param nonBombPosns Spaces that don't have bombs in them.
	 */
	public State(PositionInfo[][] board, double score, int numBombs, boolean terminal, ArrayList<Position> bombPosns, ArrayList<Position> nonBombPosns){
		this.minesweeperBoard = board;
		this.score = score;
		this.numBombs = numBombs;
		this.terminal = terminal;
		
		this.bombPosns = bombPosns;
		this.nonBombPosns = nonBombPosns;
	}

	/**
	 * Accessor for the physical board.
	 * @return Double array of position infos representing the board.
	 */
	public PositionInfo[][] getMinesweeperBoard() {
		return minesweeperBoard;
	}
	
	/**
	 * Accessor for current score.
	 * @return Score of the game at this state.
	 */
	public double getScore() {
		return this.score;
	}
	
	/**
	 * Accessor for number of bombs on the board.
	 * @return Number of bombs on the board
	 */
	public int getNumBombs() {
		return this.numBombs;
	}
	
	/**
	 * Create a new board, starting off with every space
	 * having no bomb, then adding them once the board is
	 * set up.
	 */
	public void initializeBoard(){
		for(int i=0; i<minesweeperBoard.length; i++){
			for(int j=0; j<minesweeperBoard[0].length; j++){
				minesweeperBoard[i][j] = new PositionInfo(j, i);
				nonBombPosns.add(new Position(j, i));
			}
		}
		for(int i=0; i<numBombs; i++){
			assignBomb();
		}
	}
	
	/**
	 * Helper function to drop bombs (but he keeps on forgetting)
	 * what he wrote down the whole crowd goes spaghetti....
	 * 
	 * ^^Sarkis I swear...
	 * 
	 * Helper function to place bombs in random positions on the board
	 * This is accomplished by randomly selecting a position
	 * in the list of spaces with no bombs, then placing a bomb
	 * their. This entails removing said position from the collection
	 * of non-bomb spaces and adding to the collection fo bomb
	 * spaces.
	 */
	public void assignBomb(){
		int numNonBombs = nonBombPosns.size();
		int randPosnIndex = (int)(Math.random() * numNonBombs);
		Position randPosn = nonBombPosns.get(randPosnIndex);
		
		minesweeperBoard[randPosn.getY()][randPosn.getX()].setBomb(true);
		nonBombPosns.remove(randPosnIndex);
		bombPosns.add(randPosn);
		int y = randPosn.getY();
		int x = randPosn.getX();
		
		for(int i = -1; i <= 1; i++){
			for(int j = -1; j <= 1; j++){
				if(!(i == 0 && j == 0) && x+j >=0 && y+i >= 0 && x+j < minesweeperBoard[0].length && y+i < minesweeperBoard.length){
					minesweeperBoard[randPosn.getY() + i][randPosn.getX() + j]
						.setNumNeighbors(minesweeperBoard[randPosn.getY()+i][randPosn.getX()+j].getNumNeighbors() + 1);
				}
			}
		}
		
//		System.out.println(toString());
		
	}
	
	/**
	 * Represents the new state of a board based on an action made.
	 * @param a The click made to a certain coordinate.
	 * @return Updated state of the board.
	 */
	//FIXME this shouldn't update the board, it should copy the board and make any alterations then
	//FIXED
	//NOT fixed in flagging action
	public State nextState(Action a){
		//Action contains a position
		//This is the position that will be clicked
		Position toClick = a.getPosition();
		
		//We now change the information of this location
		int x = toClick.getX();
		int y = toClick.getY();
		
		PositionInfo[][] nextBoard = copyBoard();
		
		switch(a.getMoveType()){
		
		
		case DIG:
			
			//dig
			return dig(nextBoard[y][x], nextBoard);
			
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
	
	/**
	 * Helper function invoked when a dig action occurs.
	 * It may cause a chain reaction of revealing squares,
	 * thus the state is not updated until all squares able to
	 * be revealed in a grouping are revealed.
	 * @param pInfo PositionInfo of interest.
	 * @return The updated state after a dig.
	 */
	private State dig(PositionInfo pInfo, PositionInfo[][] minesweeperBoard){
		int x = pInfo.getPosition().getX();
		int y = pInfo.getPosition().getY();
		int lengthY = minesweeperBoard.length;
		int lengthX = minesweeperBoard[0].length;
		
		//perform dig
		//Is this where we modify score? (Because nothing hidden must affect the heuristic somehow?)
		pInfo.setHidden(false);
		
		//If its a bomb YOU GOT ISIS'D BITCH!!!!
		if(this.minesweeperBoard[y][x].isBomb()){
			//Return a new state with terminal set to true
			return new State(minesweeperBoard, this.score, this.getNumBombs() - 1, true, copyPositionArray(getBombPosns()), copyPositionArray(getNonBombPosns()));
		}
		
		//perform dig around surrounding positions if current position has no bombs around it
		//Modify the score to represent new state ?
		if(minesweeperBoard[y][x].getNumNeighbors() == 0){
			for(int i = -1; i <= 1; i++){
				for(int j = -1; j <= 1; j++){
					if(x+j >=0 && y+i >= 0 && x+j < lengthX && y+i < lengthY &&
							minesweeperBoard[y+i][x+j].isHidden() == true){
						
						dig(minesweeperBoard[y+i][x+j], minesweeperBoard);
					}
				}
			}
		}
		
		//This is right AFTER we change the score in the correct manner.
		ArrayList<Position> bombPosnsCopy = copyPositionArray(bombPosns);
		ArrayList<Position> nonBombPosnsCopy = copyPositionArray(nonBombPosns);
		return new State(minesweeperBoard, this.score + 1.0, this.getNumBombs(), false, bombPosnsCopy, nonBombPosnsCopy);
	}

	/**
	 * Accessor for Terminal flag
	 * @return Current state of terminal flag.
	 */
	public boolean isTerminal() {
		return terminal;
	}
	
	/**
	 * The board state itself printed out in Stringbuilder format
	 * Some specifications:
	 * - Each unit of width is marked by a series of four dashes "----"
	 * - Each unit of height is a "|" 
	 * - Together these form boxes that contain a toString of the state of each box.
	 * This is an oversimplified string representation to facilitate reinforced learning
	 */
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
	
	public PositionInfo[][] copyBoard(){
		//copy board
		PositionInfo[][] boardCopy = new PositionInfo[minesweeperBoard.length][minesweeperBoard[0].length];
		for(int i=0; i<minesweeperBoard.length; i++){
			for(int j=0; j<minesweeperBoard.length; j++){
				boardCopy[i][j] = minesweeperBoard[i][j].copy();
			}
		}
		
		return boardCopy;
		
//		//copy arrays
//		ArrayList<Position> bombPosnsCopy = new ArrayList<Position>(this.bombPosns);
//		ArrayList<Position> nonBombPosnsCopy = new ArrayList<Position>(this.nonBombPosns);
//		
//		//return new State with copied information
//		return new State(boardCopy, this.score, this.numBombs, this.terminal, bombPosnsCopy, nonBombPosnsCopy);
	}
	
	public ArrayList<Position> copyPositionArray(ArrayList<Position> posArray){
		ArrayList<Position> copy = new ArrayList<Position>();
		for(Position pos : posArray){
			copy.add(new Position(pos.getX(), pos.getY()));
		}
		
		return copy;
	}
}
