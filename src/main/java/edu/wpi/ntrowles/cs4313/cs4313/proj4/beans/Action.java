package edu.wpi.ntrowles.cs4313.cs4313.proj4.beans;

/**
 * Class that encapsulates agent working in the
 * environment of mine sweeper
 * 
 * @author ntrowles
 * @author bgsarkis
 *
 */
public class Action {
	
	/**
	 * Position that is object of the action.
	 */
	private final Position posn;
	/**
	 * The kind of action made.
	 */
	private final MoveType move;
	
	/**
	 * Overloaded action constructor
	 * @param posn Position that action will act upon
	 * @param move Type of move to be executed
	 */
	public Action(Position posn, MoveType move){
		this.posn = posn;
		this.move = move;
	}
	
	/**
	 * Default action constructor, has no position and performs a DIG.
	 */
	public Action(){
		this(new Position(), MoveType.DIG);
	}
	
	/**
	 * Accessor for the position instance variable.
	 * @return The position of this action instance.
	 */
	public Position getPosition(){
		return posn;
	}
	
	/**
	 * Accessor for the move type of this action.
	 * @return The enumerated move type of this action.
	 */
	public MoveType getMoveType(){
		return move;
	}
	
	
}



