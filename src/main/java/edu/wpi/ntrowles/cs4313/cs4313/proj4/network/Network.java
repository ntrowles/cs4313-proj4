package edu.wpi.ntrowles.cs4313.cs4313.proj4.network;

import java.util.ArrayList;

import Jama.Matrix;

public class Network {
	int numLayers; //number of layers in network (input + hidden + output)
	ArrayList<Integer> numNeurons; // number of neurons at each layer
	ArrayList<Matrix> thetas; // weights between each layer
	
	public double activationFn(double input){
		return 1.0/(1.0+Math.exp(input));
	}
	
	public double[][] backPropagate(ArrayList<double[]> x, ArrayList<Double> y){
		Matrix Delta = new Matrix();
		
	}
	
	public ArrayList<double[]> forwardPropogate(double[] x, double y){
		ArrayList<double[]> a = new ArrayList<double[]>();
		double[] z = new double[numLayers];
		double[] a0 = new double[x.length];
		for(int i=0; i<x.length; i++){
			a0[i] = x[i];
		}
		
		for(int i=1; i<numLayers; i++){
			
		}
		
		return a;
	}
}
