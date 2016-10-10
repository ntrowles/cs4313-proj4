package edu.wpi.ntrowles.cs4313.cs4313.proj4.beans;

/**
 * Class to encapsulate x and y coordinates of positions.
 * @author bgsarkis
 * @author ntrowles
 *
 */
public class Position {
	/**
	 * Horizontal coordinate
	 */
	private final int x;
	/**
	 * Vertical coordinate
	 */
	private final int y;
	
	/**
	 * Default position constructor starts at upper left corner.
	 */
	public Position(){
		this(0,0);
	}
	
	/**
	 * Overloaded position constructor
	 * @param x Desired horizontal coordinate
	 * @param y Desired vertical coordinate
	 */
	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Accessor for X coordinate.
	 * @return Horizontal coordinate of this position.
	 */
	public int getX(){
		return this.x;
	}
	
	/**
	 * Accessor for Y coordinate.
	 * @return Vertical coordinate of this position.
	 */
	public int getY() {
		return this.y;
	}
}
