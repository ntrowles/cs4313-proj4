package edu.wpi.ntrowles.cs4313.cs4313.proj4.beans;

/**
 * 
 * PositionInfo class containing information crucial to
 * implementation of program that would not be adequately
 * handled by the position class alone.
 * @author bgsarkis
 * @author ntrowles
 */
public class PositionInfo {
	private Position pos;
	private boolean bomb;
	public boolean isBomb() {
		return bomb;
	}

	/**
	 * Mutates the flag of whether a space has a bomb.
	 * @param bomb the desired flag setting
	 */
	public void setBomb(boolean bomb) {
		this.bomb = bomb;
	}

	/**
	 * Flag if a space had been dug yet.
	 */
	private boolean hidden;
	
	/**
	 * Demarcates number of nearby spaces that have a bomb flag set to true.
	 */
	private int numNeighbors;
	
	/**
	 * Flag is a space if marked to not be dug.
	 */
	private char marker;
	
	/**
	 * Accessor for hidden flag.
	 */
	public boolean isHidden() {
		return hidden;
	}

	/**
	 * Mutator for determining if position has been dug.
	 * @param hidden the desired hidden flag setting.
	 */
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	/**
	 * Changes amount of immediately nearby squares that have a bomb
	 * @param numNeighbors Number of nearby squares that have a bomb.
	 */
	public void setNumNeighbors(int numNeighbors) {
		this.numNeighbors = numNeighbors;
	}

	/**
	 * Mutator for marker flag.
	 * @param marker the desired marker flag setting
	 */
	public void setMarker(char marker) {
		this.marker = marker;
	}
	
	/**
	 * Creates a new position in a 2d coordinate plane
	 * @param x Horizontal axis coordinate.
	 * @param y Vertical axis coordinate.
	 */
	public void setPosition(int x, int y){
		pos = new Position(x, y);
	}

	/**
	 * Number of nearby squares that have a bomb
	 * @return Number of nearby bombs.
	 */
	public int getNumNeighbors() {
		return numNeighbors;
	}

	/**
	 * Accessor for marker flag
	 * @return State of marker flag.
	 */
	public char getMarker() {
		return marker;
	}
	
	/**
	 * Accessor for position
	 * @return This position's coordinates.
	 */
	public Position getPosition(){
		return pos;
	}

	/**
	 * PositionInfo default constructor.  
	 * Default state is no bomb, hidden,
	 * no neighbors, and a '0' signifying no
	 * marker on the space.
	 */
	public PositionInfo(){
		this(false, true, 0, '0');
	}
	
	/**
	 * Overloaded position constructor
	 * @param bomb Does this space have a bomb?
	 * @param hidden Has this space been dug?
	 * @param numNeighbors How many spaces around this space have bombs?
	 * @param marker Is this space labaled dangerous?
	 */
	public PositionInfo(boolean bomb, boolean hidden, int numNeighbors, char marker){
		this.bomb = bomb;
		this.hidden = hidden;
		this.numNeighbors = numNeighbors;
		this.marker = marker;
	}
	
	/**
	 * Display status of space in StringBuilder format. (StringBuilder is faster under most implementations)
	 * The thread will contain
	 * 1. An "h" if the space is hidden or a " " if it is dug.
	 * 2. The char that the marker flag is set to.
	 * 3. A "b" if the space has a bomb or the number of neighbors. 
	 */
	public String toString(){
		StringBuilder b = new StringBuilder();
		if(hidden){
			b.append("h");
		} else {
			b.append(" ");
		}
		
		b.append(marker);
		
		if(bomb){
			b.append("b");
		} else {
			b.append(numNeighbors);
		}
		
		return b.toString();
	}
	
}
