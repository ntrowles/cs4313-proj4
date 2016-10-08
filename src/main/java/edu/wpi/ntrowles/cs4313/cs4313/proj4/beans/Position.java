package edu.wpi.ntrowles.cs4313.cs4313.proj4.beans;

public class Position {
	private final int x;
	private final int y;
	
	public Position(){
		this(0,0);
	}
	
	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
}
