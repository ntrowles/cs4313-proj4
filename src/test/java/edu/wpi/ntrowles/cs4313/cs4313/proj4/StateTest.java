package edu.wpi.ntrowles.cs4313.cs4313.proj4;

import org.junit.Assert.*;
import org.junit.Test;
import junit.framework.TestCase;
import edu.wpi.ntrowles.cs4313.cs4313.proj4.beans.State;

public class StateTest extends TestCase{
	
	@Test
	public static void toStringTest(){
		State testState = new State();
		
		for(int r = 0; r < 10; r++){
			for(int c = 0; c < 10; c++){
				assertTrue(testState.percieve()[r][c] == 'h');
			}
			
		}
	}
}
