package main.java.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StopArray {

	private static final File stopList = new File("/home/pzeroual/5A/REPCONN/stopliste.txt");
	
	private static ArrayList<String> stopArray= new ArrayList<String>();
	
	{
		BufferedReader fr= null;
		String line = null;
		try {
			 fr = new BufferedReader(new FileReader(stopList));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 try {
			while( ( line = fr.readLine() ) != null ) {
				  stopArray.add(line);
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ArrayList<String> getStopArray(){
		return stopArray;
	}
}
