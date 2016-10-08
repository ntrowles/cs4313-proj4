package edu.wpi.ntrowles.cs4313.cs4313.proj4.beans;

public class Action {
	private final Position posn;
	
	public Action(Position posn){
		this.posn = posn;
	}
	
	public Action(){
		this(new Position());
	}
	
	
}
