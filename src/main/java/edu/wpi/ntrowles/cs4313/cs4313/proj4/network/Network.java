package edu.wpi.ntrowles.cs4313.cs4313.proj4.network;

import java.util.ArrayList;

import Jama.Matrix;

public class Network {
	int numLayers; //number of layers in network (input + hidden + output)
	double randRange;
	ArrayList<Integer> numNeurons; // number of neurons at each layer
	ArrayList<Matrix> Thetas; // weights between each layer
	double alpha; //learning rate
	
	public int getNumLayers() {
		return numLayers;
	}

	public void setNumLayers(int numLayers) {
		this.numLayers = numLayers;
	}

	public double getRandRange() {
		return randRange;
	}

	public void setRandRange(double randRange) {
		this.randRange = randRange;
	}

	public ArrayList<Integer> getNumNeurons() {
		return numNeurons;
	}

	public void setNumNeurons(ArrayList<Integer> numNeurons) {
		this.numNeurons = numNeurons;
	}

	public ArrayList<Matrix> getThetas() {
		return Thetas;
	}

	public void setThetas(ArrayList<Matrix> thetas) {
		Thetas = thetas;
	}

	public Network(){
		this.numLayers = 4;
		ArrayList<Integer> numNeurons = new ArrayList<Integer>();
		numNeurons.add(24);
		numNeurons.add(9);
		numNeurons.add(9);
		numNeurons.add(1);
		this.numNeurons = numNeurons;
		this.randRange = 50;
		this.Thetas = initializeThetas();

	}
	
	public Network(int numLayers, ArrayList<Integer> numNeurons){
		this.numLayers = numLayers;
		this.numNeurons = numNeurons;
		this.Thetas = initializeThetas();
		this.randRange = 50;
	}
	
	public Network(int numLayers, ArrayList<Integer> numNeurons, ArrayList<Matrix> initialThetas){
		this.numLayers = numLayers;
		this.numNeurons = numNeurons;
		this.Thetas = initialThetas;
		this.randRange = 50;
	}
	
	public ArrayList<Matrix> initializeThetas(){
		ArrayList<Matrix> thetas = new ArrayList<Matrix>();
		for(int i=0; i<numLayers-1; i++){
//			Matrix curTheta = Matrix.random(numNeurons.get(i+1), numNeurons.get(i));
			Matrix curTheta = new Matrix(numNeurons.get(i+1), numNeurons.get(i));
			for(int j=0; j<curTheta.getRowDimension(); j++){
				for(int k=0; k<curTheta.getColumnDimension(); k++){
					double random = Math.random()*2.0*randRange - randRange;
					curTheta.set(j, k, random);
				}
			}
			thetas.add(curTheta);
		}
		
		return thetas;
	}
	
	public double activationFn(double input){
		return 1.0/(1.0+Math.exp(input));
	}
	
	public Matrix activationFn(Matrix input){
		Matrix onesMatrix = new Matrix(input.getRowDimension(), input.getColumnDimension(), 1.0);
		Matrix expInput = new Matrix(input.getRowDimension(), input.getColumnDimension());
		for(int i=0; i<input.getRowDimension(); i++){
			for(int j=0; j<input.getColumnDimension(); j++){
				expInput.set(i, j, Math.exp(input.get(i, j)));
			}
		}
		return onesMatrix.arrayLeftDivide(onesMatrix.plus(expInput));
		
	}
	
	public ArrayList<Matrix> backPropagate(ArrayList<Matrix> xVectors, ArrayList<Matrix> yVectors){
		ArrayList<Matrix> DeltaList = new ArrayList<Matrix>();
		
		for(int i=0; i<numNeurons.size()-1; i++){
			Matrix curDelta = new Matrix(numNeurons.get(i+1), numNeurons.get(i));
			DeltaList.add(curDelta);
		}
		
		for(int i=0; i<xVectors.size(); i++){
			ArrayList<Matrix> aList = forwardPropogate(xVectors.get(i));
			ArrayList<Matrix> dList = new ArrayList<Matrix>(numLayers);
			Matrix dOutput = aList.get(aList.size()-1).minus(yVectors.get(i));
			dList.set(numLayers-1, dOutput);
			
			for (int j=aList.size()-2; j>0; j--){
				Matrix onesMatrix = new Matrix(aList.get(i).getRowDimension(), aList.get(i).getColumnDimension(), 1.0);
				Matrix dCur = Thetas.get(j).transpose().times(dList.get(j+1)).arrayTimes(aList.get(i)).arrayTimes(onesMatrix.minus(aList.get(i)));
				dList.set(j, dCur);
			}
			
			for (int j=0; j<DeltaList.size(); j++){
				Matrix curDelta = DeltaList.get(j);
				DeltaList.set(j, curDelta.plus(dList.get(j+1).times(aList.get(j).transpose())));
			}
			
		}
		
		ArrayList<Matrix> partialDerivatives = new ArrayList<Matrix>();
		for(int i=0; i<DeltaList.size(); i++){
			Matrix partialDerivative = DeltaList.get(i).times(1.0/xVectors.size());
			partialDerivatives.add(partialDerivative);
		}
		
		return partialDerivatives;
		
	}
	
	public ArrayList<Matrix> forwardPropogate(Matrix xVector){
		ArrayList<Matrix> aList = new ArrayList<Matrix>();
		ArrayList<Matrix> zList = new ArrayList<Matrix>();
		
		Matrix a0Vector = xVector.copy();
		aList.add(a0Vector);
		
		for(int i=1; i<numLayers; i++){
			Matrix acurVector = activationFn(Thetas.get(i-1).times(aList.get(i-1)));
			aList.add(acurVector);
		}
		
//		for(int i=1; i<numLayers; i++){
//			
//		}
		
		return aList;
	}
	
	public void gradientDescent(ArrayList<Matrix> xVectors, ArrayList<Matrix> yVectors){
		ArrayList<Matrix> DeltaList = backPropagate(xVectors, yVectors);
		for(int i=0; i<Thetas.size(); i++){
			Matrix curTheta = Thetas.get(i);
			Thetas.set(i, curTheta.minus(DeltaList.get(i).times(alpha)));
		}
	}
}
