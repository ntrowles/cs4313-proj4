package edu.wpi.ntrowles.cs4313.cs4313.proj4;

import java.util.ArrayList;

import org.junit.Test;

import static org.junit.Assert.*;
import edu.wpi.ntrowles.cs4313.cs4313.proj4.beans.*;
import junit.framework.*;

public class StateTest extends State {
	
	State testState;
	
	@Test
	public void setup () {
		/*ArrayList<Position> thing = new ArrayList<Position>();
		thing.add(Position(1,1));
		thing.add(Position(2,2));
		thing.add(Position(3,3));
		thing.add(Position(4,4));
		thing.add(Position(5,5));
		thing.add(Position(6,6));
		thing.add(Position(7,7));
		thing.add(Position(8,8));
		thing.add(Position(9,9));
		thing.add(Position());*/
		this.testState = new State(10, 10, 10, 10, false);
		//this.testState.initializeBoard();
	}
	

	
	@Test
	public void getNumBombsTest(){
		setup();
		int bombs = testState.getNumBombs();
		assertTrue(bombs == 10);
	}
}
