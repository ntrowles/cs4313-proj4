package edu.wpi.ntrowles.cs4313.cs4313.proj4.beans;

public class PositionInfo {
	private boolean hidden;
	private int numNeighbors;
	private char marker;
	
	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public void setNumNeighbors(int numNeighbors) {
		this.numNeighbors = numNeighbors;
	}

	public void setMarker(char marker) {
		this.marker = marker;
	}

	public int getNumNeighbors() {
		return numNeighbors;
	}

	public char getMarker() {
		return marker;
	}

	public PositionInfo(){
		this(true, 0, '0');
	}
	
	public PositionInfo(boolean hidden, int numNeighbors, char marker){
		this.hidden = hidden;
		this.numNeighbors = numNeighbors;
		this.marker = marker;
	}
	
	
}
