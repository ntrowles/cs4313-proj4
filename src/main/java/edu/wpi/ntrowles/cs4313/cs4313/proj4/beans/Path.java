package edu.wpi.ntrowles.cs4313.cs4313.proj4.beans;

import java.util.LinkedList;

/**
 * Represents a tree of various past states and actions.
 * @author ntrowles
 *
 */
public class Path {
	/**
	 * Collection of previous states in the current game.
	 */
	private LinkedList<State> prevStates;
	/**
	 * Collection of actions made in the current game.
	 */
	private LinkedList<Action> actions;
	
	/**
	 * Default Path constructor
	 * Instantiates an empty collection of states and
	 * an empty collection of all actions executed.
	 */
	public Path(){
		this(new LinkedList<State>(), new LinkedList<Action>());
	}
	
	/**
	 * Overloaded Path constructor
	 * @param prevStates Collection of previous states.
	 * @param actions Collection of actions made.
	 */
	public Path(LinkedList<State> prevStates, LinkedList<Action> actions){
		this.prevStates = prevStates;
		this.actions = actions;
	}

	/**
	 * Accessor for collection of previous states.
	 * @return Current collection of all past states.
	 */
	public LinkedList<State> getPrevStates() {
		return prevStates;
	}

	/**
	 * Mutator for collection of previous states.
	 * @param prevStates The new collection of previous states. 
	 */
	public void setPrevStates(LinkedList<State> prevStates) {
		this.prevStates = prevStates;
	}

	/**
	 * Accessor for collection of all past actions made.
	 * @return Current list of all actions made.
	 */
	public LinkedList<Action> getActions() {
		return actions;
	}

	/**
	 * Mutator for past actions made
	 * @param actions New set of all actions made.
	 */
	public void setActions(LinkedList<Action> actions) {
		this.actions = actions;
	}
	
	
}
