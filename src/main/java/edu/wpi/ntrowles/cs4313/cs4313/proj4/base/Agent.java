package edu.wpi.ntrowles.cs4313.cs4313.proj4.base;

import java.io.IOException;
import java.util.ArrayList;

import Jama.Matrix;
import edu.wpi.ntrowles.cs4313.cs4313.proj4.beans.Action;
import edu.wpi.ntrowles.cs4313.cs4313.proj4.beans.State;

public abstract class Agent {
	protected ArrayList<Matrix> xVectors;
	protected ArrayList<Matrix> yVectors;
	
	public abstract Action selectAction(State state);
	public abstract void updateHistory(Action action, State oldState, State newState);
	public abstract void train (int iterations) throws IOException;
}
