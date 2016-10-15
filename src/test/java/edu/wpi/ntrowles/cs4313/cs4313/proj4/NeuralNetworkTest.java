package edu.wpi.ntrowles.cs4313.cs4313.proj4;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import Jama.Matrix;
import edu.wpi.ntrowles.cs4313.cs4313.proj4.beans.DataGenerator;
import edu.wpi.ntrowles.cs4313.cs4313.proj4.network.Network;

public class NeuralNetworkTest {
	
	@Test
	public void nnTest() throws IOException{
		Network network = new Network();
		DataGenerator generator = new DataGenerator();
		
		ArrayList<ArrayList<Matrix>> dataSet = generator.generateDataPairs(1000);
		
		ArrayList<Matrix> xVectorsTotal = dataSet.get(0);
		ArrayList<Matrix> yVectorsTotal = dataSet.get(1);
		
		
		
		ArrayList<Matrix> xVectorsTraining = new ArrayList<Matrix>();
		ArrayList<Matrix> xVectorsTest = new ArrayList<Matrix>();
		ArrayList<Matrix> yVectorsTraining = new ArrayList<Matrix>();
		ArrayList<Matrix> yVectorsTest = new ArrayList<Matrix>();
		
		xVectorsTraining.addAll(xVectorsTotal.subList(0, (int)(0.75*xVectorsTotal.size())));
		yVectorsTraining.addAll(yVectorsTotal.subList(0, (int)(0.75*yVectorsTotal.size())));
		
		xVectorsTest.addAll(xVectorsTotal.subList((int)(0.75*xVectorsTotal.size()), xVectorsTotal.size()));
		yVectorsTest.addAll(yVectorsTotal.subList((int)(0.75*yVectorsTotal.size()), yVectorsTotal.size()));
		
		for(int i=0; i<100; i++){
			network.gradientDescent(xVectorsTraining, yVectorsTraining);
		}
		
		//training set accuracy
		int numCorrectTrainingSet = 0;
		int trainingSetSize = xVectorsTest.size();
		for(int i=0; i<xVectorsTest.size(); i++){
			ArrayList<Matrix> aVectors = network.forwardPropogate(xVectorsTest.get(i));
			double hypothesis = aVectors.get(aVectors.size()-1).get(0,0); // get hypothesis
			if(hypothesis >= 0.5){ //thinks state is a bomb
				if(yVectorsTest.get(i).get(0, 0) == 1){
					numCorrectTrainingSet++;
				}
			} else { //thinks state is not a bomb
				if(yVectorsTest.get(i).get(0, 0) == 0){
					numCorrectTrainingSet++;
				}
			}
		}
		System.out.println("Training Set\nNumber Correct: " + numCorrectTrainingSet + "\nTotal training set size: " + trainingSetSize);
		
		//test set accuracy
		for(int i=0; i<xVectorsTest.size(); i++){
			
		}
		
	}
}
