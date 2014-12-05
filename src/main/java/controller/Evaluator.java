package main.java.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Evaluator {

	private ArrayList<List<Map.Entry<Integer, Integer>>> relevanceTable = new ArrayList<List<Map.Entry<Integer, Integer>>>();
	
	private String qrelpath;
	
	public Evaluator(ArrayList<List<Map.Entry<Integer, Integer>>> relevanceTable, String qrelPath){
		this.relevanceTable = relevanceTable;
	}
	
	public void evaluate(){
		for (File f : new File(qrelpath).listFiles()) {
			BufferedReader bf=null;
			try {
				bf = new BufferedReader(new FileReader(f));
				while(bf.readLine()!=null){
					
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
