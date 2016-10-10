package edu.wpi.ntrowles.cs4313.cs4313.proj4.beans;

public class PositionInfo {
	private boolean bomb;
	public boolean isBomb() {
		return bomb;
	}

	public void setBomb(boolean bomb) {
		this.bomb = bomb;
	}

	private boolean hidden;
	private int numNeighbors;
	private char marker;
	
	/**
	 * Determines if square is selected. Everything starts as hidden.
	 */
	public boolean isHidden() {
		return hidden;
	}

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

	public void setMarker(char marker) {
		this.marker = marker;
	}

	/**
	 * Number of nearby squares that have a bomb
	 * @return Number of nearby bombs.
	 * 
	 */
	public int getNumNeighbors() {
		return numNeighbors;
	}

	public char getMarker() {
		return marker;
	}

	public PositionInfo(){
		this(false, true, 0, '0');
	}
	
	public PositionInfo(boolean bomb, boolean hidden, int numNeighbors, char marker){
		this.bomb = bomb;
		this.hidden = hidden;
		this.numNeighbors = numNeighbors;
		this.marker = marker;
	}
	
	
}
