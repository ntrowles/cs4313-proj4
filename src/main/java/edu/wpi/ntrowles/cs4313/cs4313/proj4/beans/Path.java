package edu.wpi.ntrowles.cs4313.cs4313.proj4.beans;

import java.util.LinkedList;

public class Path {
	private LinkedList<State> prevStates;
	private LinkedList<Action> actions;
	
	public Path(){
		this(new LinkedList<State>(), new LinkedList<Action>());
	}
	
	public Path(LinkedList<State> prevStates, LinkedList<Action> actions){
		this.prevStates = prevStates;
		this.actions = actions;
	}

	public LinkedList<State> getPrevStates() {
		return prevStates;
	}

	public void setPrevStates(LinkedList<State> prevStates) {
		this.prevStates = prevStates;
	}

	public LinkedList<Action> getActions() {
		return actions;
	}

	public void setActions(LinkedList<Action> actions) {
		this.actions = actions;
	}
	
	
}
