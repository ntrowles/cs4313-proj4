package edu.wpi.ntrowles.cs4313.cs4313.proj4.beans;

import java.io.IOException;
import java.util.ArrayList;

import Jama.Matrix;
import edu.wpi.ntrowles.cs4313.cs4313.proj4.network.Network;

public class Tester {
	public Tester(){
		//intentionally left blank
	}
	
	public ArrayList<Double> nnTest(Network network, ArrayList<ArrayList<Matrix>> dataSet) throws IOException{
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
		
		System.out.println("xVectorsTotal size: " + xVectorsTotal.size() + ", yVectorsTotal size: " + yVectorsTotal.size());
		
//		generator.printVectors(xVectorsTotal, "xVector", 24, 1);
//		generator.printVectors(yVectorsTotal, "yVector", 1, 1);
		
		for(int i=0; i<100; i++){
			network.gradientDescent(xVectorsTraining, yVectorsTraining);
		}
		
		ArrayList<Double> fScores = new ArrayList<Double>();
		//training set accuracy
		int numOnes = 0;
		int numZeros = 0;
		int numOnesCorrect = 0;
		int numZerosCorrect = 0;
		int numCorrectTrainingSet = 0;
		int trainingSetSize = xVectorsTraining.size();
		for(int i=0; i<trainingSetSize; i++){
			ArrayList<Matrix> aVectors = network.forwardPropogate(xVectorsTraining.get(i));
			double hypothesis = aVectors.get(aVectors.size()-1).get(0,0); // get hypothesis
			if(hypothesis >= 0.5){ //thinks state is a bomb
				if(yVectorsTraining.get(i).get(0, 0) == 1){
					numCorrectTrainingSet++;
					numOnes++;
					numOnesCorrect++;
				} else {
					numZeros++;
				}
			} else { //thinks state is not a bomb
				if(yVectorsTraining.get(i).get(0, 0) == 0){
					numCorrectTrainingSet++;
					numZeros++;
					numZerosCorrect++;
				} else {
					numOnes++;
				}
			}
		}
		System.out.println("Training Set\nNumber Correct: " + numCorrectTrainingSet + "\nTotal training set size: " + trainingSetSize);
		System.out.println("Num Ones:  " + numOnes + ", Num Ones Correct:  " + numOnesCorrect);
		System.out.println("Num Zeros: " + numZeros + ", Num Zeros Correct: " + numZerosCorrect);
		double precision = (double)numOnesCorrect/(numOnesCorrect + (numZeros - numZerosCorrect));
		double recall = (double)numOnesCorrect/numOnes;
		fScores.add(new Double(2*(precision*recall/(precision+recall))));
		
		
		//test set accuracy
		
		numOnes = 0;
		numZeros = 0;
		numOnesCorrect = 0;
		numZerosCorrect = 0;
		numCorrectTrainingSet = 0;
		int numCorrectTestSet = 0;
		int testSetSize = xVectorsTest.size();
		for(int i=0; i<testSetSize; i++){
			ArrayList<Matrix> aVectors = network.forwardPropogate(xVectorsTest.get(i));
			double hypothesis = aVectors.get(aVectors.size()-1).get(0,0); // get hypothesis
			if(hypothesis >= 0.5){ //thinks state is a bomb
				if(yVectorsTest.get(i).get(0, 0) == 1){
					numCorrectTrainingSet++;
					numOnes++;
					numOnesCorrect++;
				} else {
					numZeros++;
				}
			} else { //thinks state is not a bomb
				if(yVectorsTest.get(i).get(0, 0) == 0){
					numCorrectTrainingSet++;
					numZeros++;
					numZerosCorrect++;
				} else {
					numOnes++;
				}
			}
		}
		System.out.println("Test Set\nNumber Correct: " + numCorrectTestSet + "\nTotal test set size: " + testSetSize);
		System.out.println("Num Ones:  " + numOnes + ", Num Ones Correct:  " + numOnesCorrect);
		System.out.println("Num Zeros: " + numZeros + ", Num Zeros Correct: " + numZerosCorrect);
		precision = (double)numOnesCorrect/(numOnesCorrect + (numZeros - numZerosCorrect));
		recall = (double)numOnesCorrect/numOnes;
		fScores.add(new Double(2*(precision*recall/(precision+recall))));
		
		return fScores;
	}
}
