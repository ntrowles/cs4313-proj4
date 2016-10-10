package edu.wpi.ntrowles.cs4313.cs4313.proj4.beans;

public class Action {
	private final Position posn;
	private final MoveType move;
	
	public Action(Position posn, MoveType move){
		this.posn = posn;
		this.move = move;
	}
	
	public Action(){
		this(new Position(), MoveType.DIG);
	}
	
	
}
