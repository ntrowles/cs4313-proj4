package edu.wpi.ntrowles.cs4313.cs4313.proj4.beans;

import java.util.ArrayList;

public class State {
	private char[][] minesweeperBoard;
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
		this(10, 10, 10, 0);
	}
	
	public State(int boardSizeY, int boardSizeX, int numBombs, double score){
		this.score = score;
		this.numBombs = numBombs;
		initializeBoard(boardSizeY, boardSizeX);
		
		this.bombPosns = new ArrayList<Position>();
		this.nonBombPosns = new ArrayList<Position>();
	}

	public char[][] getMinesweeperBoard() {
		return minesweeperBoard;
	}
	
	public double getScore() {
		return this.score;
	}
	
	public int getNumBombs() {
		return this.numBombs;
	}
	
	public void initializeBoard(int boardSizeY, int boardSizeX){
		minesweeperBoard = new char[boardSizeY][boardSizeX];
		for(int i=0; i<numBombs; i++){
			assignBomb();
		}
	}
	
	public void assignBomb(){
		int numNonBombs = nonBombPosns.size();
		int randPosnIndex = (int)(Math.random() * numNonBombs);
		Position randPosn = nonBombPosns.get(randPosnIndex);
		
		minesweeperBoard[randPosn.getY()][randPosn.getX()] = 'b';
		
	}
	
	
}
