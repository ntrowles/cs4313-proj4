package edu.wpi.ntrowles.cs4313.cs4313.proj4.beans;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Jama.Matrix;

public class DataPersistor {
	private File file;
	private FileWriter fileWriter;
	private ArrayList<Matrix> outputList;
	private int xLength, yLength;
	
	public DataPersistor(File f, int yl, int xl) throws IOException{
		this.file = f;
		this.fileWriter = new FileWriter(f);
		this.outputList = new ArrayList<Matrix>();
		this.xLength = xl;
		this.yLength = yl;
	}
	
	public void writeData(ArrayList<Matrix> inputList) throws IOException{
		//TODO writing an ArrayList<Matrix> to a file
		for(int i = 0; i < inputList.size(); i++){
			for(int y = 0; y < yLength; y++){
				for(int x = 0; x < xLength; x++){
					double val = inputList.get(i).get(y, x);
					fileWriter.write(val + " ");
				}
			}
		}
	}
	
	public void writeData(ArrayList<Matrix> inputList, int begin, int end) throws IOException{
		//TODO writing an ArrayList<Matrix> to a file
		for(int i = begin - 1; i < end; i++){
			for(int y = 0; y < xLength; y++){
				for(int x = 0; x < yLength; x++){
					double val = inputList.get(i).get(y, x);
					fileWriter.write(val + " ");
				}
			}
		}
	}
	
	public void closeFile() throws IOException{
		fileWriter.close();
	}
	
	public ArrayList<Matrix> readData() throws FileNotFoundException{
		//TODO data parsing implementation
		Scanner scan = new Scanner(file);
		scan.useDelimiter(" ");	
		
		while(scan.hasNext()){
			Matrix outputMatrix = new Matrix(yLength, xLength);
			for(int y = 0; y < yLength; y++){
				for(int x = 0; x < xLength; x++){
					double val = scan.nextDouble();
					//System.out.println(val);
					outputMatrix.set(y, x, val);
				}
			}
			outputList.add(outputMatrix);
		}
		
		return outputList;
	}
}
